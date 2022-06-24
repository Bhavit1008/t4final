package com.example.t4final;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class T4finalApplication {

	public static void main(String[] args) {
		SpringApplication.run(T4finalApplication.class, args);
        try {
        	 File file = new File("/t4final/src/test/java/com/example/t4final/T4finalApplicationTests.java");
        	 
             SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
  
             // print the original Last Modified date
             System.out.println("Original Last Modified Date : "
                     + dateFormat.format(file.lastModified()));
  
             // set this date
             String newLastModifiedString = "01/31/1821";
  
             // we have to convert the above date to milliseconds...
             Date newLastModifiedDate = (Date) dateFormat.parse(newLastModifiedString);
             file.setLastModified(newLastModifiedDate.getTime());
  
             // print the new Last Modified date
             System.out.println("Lastest Last Modified Date : "
                     + dateFormat.format(file.lastModified()));
        } catch (ParseException e) {
 
            e.printStackTrace();
 
        }
	}
	
}
