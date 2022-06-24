import java.util.List;

@Entity
pubic class Role{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer roleId;
	private String role;


	@ManyToMany(targetEntity=User.class)
	@JoinTable(name="userRole,
	joinColumns= {@JoinColumn(name="roleId")},
	inverseJoinColumns= {@JoinColumn(name="userId")}
	)
	private Set<Object> users;

	public Set<Object> getUsers(){
		return users;
	}

	public User(){
		super();
	}

}

