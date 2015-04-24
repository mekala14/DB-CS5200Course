package classes.users;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class author {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String hometown;
	private String gender;
	private int work_count;
	private int followers;
	
	
	private String poster;
	private int goodreads_id;
	
	@OneToMany(mappedBy="auth"/*, cascade = CascadeType.PERSIST*/)
	private List<book> books;
	
	@OneToMany(mappedBy="author")
	private List<comment_author> comnt;
	
	@ManyToMany(mappedBy = "authors")
	private List<User> fans;
	
	public author(int id, String name, String hometown, String gender,
			int work_count, int followers,  String poster,int goodreads_id) {
		super();
		this.id = id;
		this.name = name;
		this.hometown = hometown;
		this.gender = gender;
		this.work_count = work_count;
		this.followers = followers;
		this.goodreads_id = goodreads_id;
		this.poster = poster;
	}
	
	public List<User> getFans() {
		return fans;
	}


	public void setFans(List<User> fans) {
		this.fans = fans;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public int getGoodreads_id() {
		return goodreads_id;
	}
	public void setGoodreads_id(int goodreads_id) {
		this.goodreads_id = goodreads_id;
	}
	
	
	public author() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getWork_count() {
		return work_count;
	}
	public void setWork_count(int work_count) {
		this.work_count = work_count;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public List<book> getBooks() {
		return books;
	}
	public void setBooks(List<book> books) {
		this.books = books;
	}
	public List<comment_author> getComnt() {
		return comnt;
	}
	public void setComnt(List<comment_author> comnt) {
		this.comnt = comnt;
	}
	
}