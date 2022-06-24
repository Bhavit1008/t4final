import java.util.List;

@Entity
pubic class Product{
	@Id
	@GeneratedValue(stratergy=GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private Double price;

	@ManyToOne
	private User seller;
	
	@ManyToOne
	private Category category;

	public Product(){
		super();
	}

}

