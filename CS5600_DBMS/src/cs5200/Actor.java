package cs5200;

public class Actor {
	private String actorid;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	
	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Actor(String id, String firstName,
			String lastName, String dateOfBirth) {
		super();
		this.actorid = id;
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public String getTheactorid(){
		return actorid;
	}
	
	public void setTheactorid(String id)
	{
		this.actorid = id;
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

	
}
