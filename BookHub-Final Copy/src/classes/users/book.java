package classes.users;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.eclipse.persistence.annotations.Cache;

@Entity
@Cache(shared=false)
public class book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int likes;

	private int publish_year;
	
	private String poster;
	private String description;
	private String publisher;
	private int isbn;
	private int rating_count;
	private int sum_rating;
	private int avg_rating;
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private author auth;
	
	@OneToMany(mappedBy="bks")
	private List<comment> comnt;
	
	@OneToMany(mappedBy="bks")
	private List<rating> ratings;
	
	@OneToMany(mappedBy="boks")
	private List<shelf> shlf;
	
	public book() {
		super();
	}
	public book(int id, int likes, int publish_year,
			String poster, String description, String publisher, int isbn, int rating_count, int sum_rating, int avg_rating, String name) {
		super();
		this.id = id;
		this.likes = likes;
	
		this.publish_year = publish_year;
		this.poster = poster;
		this.description = description;
		this.publisher = publisher;
		this.isbn = isbn;
		this.rating_count = rating_count;
		this.sum_rating = sum_rating;
		this.avg_rating = avg_rating;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public int getPublish_year() {
		return publish_year;
	}
	public void setPublish_year(int publish_year) {
		this.publish_year = publish_year;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public author getAuth() {
		return auth;
	}
	public void setAuth(author auth) {
		this.auth = auth;
	}
	public List<comment> getComnt() {
		return comnt;
	}
	public void setComnt(List<comment> comnt) {
		this.comnt = comnt;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public int getRating_count() {
		return rating_count;
	}
	public void setRating_count(int rating_count) {
		this.rating_count = rating_count;
	}
	public int getSum_rating() {
		return sum_rating;
	}
	public void setSum_rating(int sum_rating) {
		this.sum_rating = sum_rating;
	}
	public int getAvg_rating() {
		return avg_rating;
	}
	public void setAvg_rating(int avg_rating) {
		this.avg_rating = avg_rating;
	}
	public List<shelf> getShlf() {
		return shlf;
	}
	public void setShlf(List<shelf> shlf) {
		this.shlf = shlf;
	}
	public List<rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<rating> ratings) {
		this.ratings = ratings;
	}
	
	
}