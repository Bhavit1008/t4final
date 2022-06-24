import java.util.List;

@Entity
pubic class Category{
	@Id
	@GeneratedValue(stratergy=GenerationType.IDENTITY)
	private Integer cateoryId;
	private String categoryName;


	public Category(){
		super();
	}

}

