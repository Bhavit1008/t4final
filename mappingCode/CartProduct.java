import java.util.List;

@Entity
pubic class CartProduct{

	@Id
	@GeneratedValue(stratergy=GenerationType.IDENTITY)
	private Integer cpId;
	
	@ManyToOne
	private Cart cart;
	private Product product;
	private Integer quantity=1;

	

	public CartProuct(){
		//not sure here if super is used or not
		//super();
	}

}

