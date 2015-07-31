package pl.spring.demo.entity;

public class AuthorTo {
	private Long id;
	private String firstName;
	private String lastName;

	public AuthorTo(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getID() {
		return id;
	}

	public void putID(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void putFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void putLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}
}
