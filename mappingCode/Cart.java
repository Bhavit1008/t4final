import java.util.List;

@Entity
pubic class Cart{
	@Id
	@GeneratedValue(stratergy=GenerationType.IDENTITY)
	private Integer cartid;
	private Double totalAmount;
	
	@OneToOne(targetEntity=User.class)
	private User user;
	@OneToMany
	private List<CartProduct> cartProducts;

	public Cart(){
		super();
	}

