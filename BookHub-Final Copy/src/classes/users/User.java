package classes.users;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
public class User {
		
	@Id
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private String email;
	private String dob;
	private String contact;
	private int validate;
	private String role;
	
	@OneToMany(mappedBy="users")
	private List<comment> comnt;
	
	@OneToMany(mappedBy="usr")
	private List<comment_author> auth_comnt;
	
	@ManyToMany
	@JoinTable(name = "userlink", joinColumns = { @JoinColumn(name = "following_uname", referencedColumnName = "username") }, inverseJoinColumns = { @JoinColumn(name = "follower_uname", referencedColumnName = "username") })
	private List<User> followers;
	
	@ManyToMany(mappedBy = "followers")
	private List<User> following;
	
	@OneToMany(mappedBy="user")
	private List<rating> ratings;
	

	@ManyToMany
	@JoinTable(name = "authorlink", joinColumns = { @JoinColumn(name = "username", referencedColumnName = "username") }, inverseJoinColumns = { @JoinColumn(name = "author_id", referencedColumnName = "id") })
	private List<author> authors;
	
	
	public List<rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<rating> ratings) {
		this.ratings = ratings;
	}

	@OneToMany(mappedBy="usrs")
	private List<shelf> shlf;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String first_name,
			String last_name, String email, String dob, String contact) {
		super();
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.dob = dob;
		this.contact = contact;
	}

	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public List<comment> getComnt() {
		return comnt;
	}

	public void setComnt(List<comment> comnt) {
		this.comnt = comnt;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<author> authors) {
		this.authors = authors;
	}
	public List<comment_author> getAuth_comnt() {
		return auth_comnt;
	}

	public void setAuth_comnt(List<comment_author> auth_comnt) {
		this.auth_comnt = auth_comnt;
	}

	public List<shelf> getShlf() {
		return shlf;
	}

	public void setShlf(List<shelf> shlf) {
		this.shlf = shlf;
	}
	public int getValidate() {
		return validate;
	}
	public void setValidate(int validate) {
		this.validate = validate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}