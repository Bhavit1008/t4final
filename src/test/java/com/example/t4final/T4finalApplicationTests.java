package com.example.t4final;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.t4final.repository.CartProductRepo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class T4finalApplicationTests {
	
	@Autowired
	MockMvc mvc;
	
	@Autowired
	CartProductRepo cartProductRepo;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void productSearchStatus() throws Exception {
		mvc.perform(get("/api/public/product/search").param("keyword", "tablet"))
		.andExpect(status().is(200)).andExpect(jsonPath("$", notNullValue()));
	}
	
	@Test
	public void productSearchWQithoutKeyword() throws Exception {
		mvc.perform(get("/api/public/product/search"))
		.andExpect(status().is(400));
	}
	
	
	@Test
	public void productSearchWithProductName() throws Exception {
		MvcResult res = mvc.perform(get("/api/public/product/search").param("keyword", "tablet"))
		.andExpect(status().is(200)).andExpect(jsonPath("$", notNullValue())).andReturn();
		
		JSONArray arr = (JSONArray) new JSONParser().parse(res.getResponse().getContentAsString());
		assert(arr.size()>0);
		for(Object obj: arr) {
			assertThat( ((JSONObject) obj).get("productName").toString().toLowerCase().contains("tablet"));
		}
	}
	
	@Test
	public void productSearchWithCategoryName() throws Exception {
		MvcResult res = mvc.perform(get("/api/public/product/search").param("keyword", "tablet"))
		.andExpect(status().is(200)).andExpect(jsonPath("$", notNullValue())).andReturn();
		
		JSONArray arr = (JSONArray) new JSONParser().parse(res.getResponse().getContentAsString());
		assert(arr.size()>0);
		for(Object obj: arr) {
			assertThat( ((JSONObject) obj).get("productName").toString().toLowerCase().contains("medicine"));
		}
	}
	
	@Test
	public void consumerAuthEndPoint() throws Exception {
		mvc.perform(get("/api/public/consumer/cart")).andExpect(status().is(401));
	}
	
	@Test
	public void sellerAuthEndPoint() throws Exception {
		mvc.perform(get("/api/public/seller/product")).andExpect(status().is(401));
	}
	
	
	/*
	 * creating json credential
	 * 
	 * */
	public String getJSONcreds(String u,String p) {
		Map<String,String> m = new HashMap<>();
		m.put("username", u);
		m.put("password", p);
		return new JSONObject(m).toJSONString();
	}
	
	public MockHttpServletResponse loginHelper(String u,String p) throws Exception {
		return mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(getJSONcreds(u,p))).andExpect(status().is(200))
				.andReturn().getResponse();
	}
	
	@Test
	@Order(10)
	public void consumerLoginWithBadCreds() throws Exception{
		mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(getJSONcreds("user","password"))).andExpect(status().is(401));
	}
	
	@Test
	@Order(11)
	public void consumerLoginWithValidCreds() throws Exception{
		assertEquals(200,loginHelper("Rose","password").getStatus());
		System.out.println(loginHelper("Rose","password").getContentAsString());
		assertNotEquals("",loginHelper("Rose","password").getContentAsString());
	}
	
	@Test
	@Order(12)
	public void consumerGetCartWithValidJWT() throws Exception{
		mvc.perform(get("/api/test/consumer").header("Authorization","Bearer "+loginHelper("Rose","password").getContentAsString()))
		.andExpect(status().is(200))
		.andExpect(jsonPath("$.cartId",is(not(equalTo("")))))
		.andExpect(jsonPath("$.cartProducts[0].qauntity",is(2)))
		.andExpect(jsonPath("$.cartProducts[0].product.productName",containsStringIgnoringCase("Crocin pain relief tablet")))
		.andExpect(jsonPath("$.cartProducts[0].product.category.categoryName",is("Medicines")));
		System.out.println("==================="+content());
	}
	
	@Test
	@Order(13)
	public void consumerGetCartWithValiJWT() throws Exception{
		
	}
	
	
}
