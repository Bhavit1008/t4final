import java.util.List;

@Entity
pubic class User{
	@Id
	@GeneratedValue(stratergy=GenerationType.IDENTITY)
	private Integer userid;
	private String username;
	private String password;
	
	
	@ManyToMany
	private Set<Object> roles;

	public User(){
		super();
	}
	
	public User(String username, String password, Set<Object> roles){
	super();
	this.username = username;
	this.password=password;
	this.roles = roles;

	}
}

