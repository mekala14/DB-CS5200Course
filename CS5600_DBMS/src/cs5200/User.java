package cs5200;

public class User {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String email;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String firstName,
			String lastName, String dateOfBirth, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	

	public String getTheusername() {
		return username;
	}

	public void setTheusername(String username) {
		this.username = username;
	}

	public String getThepassword() {
		return password;
	}

	public void setThepassword(String password) {
		this.password = password;
	}

	public String getThefirstName() {
		return firstName;
	}

	public void setThefirstName(String finame) {
		this.firstName = finame;
	}

	public String getThelastName() {
		return lastName;
	}

	public void setThelastName(String laname) {
		this.lastName = laname;
	}

	public String getThedateOfBirth() {
		return dateOfBirth;
	}

	public void setThedateOfBirth(String dob) {
		this.dateOfBirth = dob;
	}

	public String getTheemail() {
		return email;
	}

	public void setTheemail(String email) {
		this.email = email;
	}

}
