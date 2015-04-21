package classes.users;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class shelf {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username")
	private User usrs;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="book_id")
	private book boks;

	public shelf() {
		super();
	}

	public shelf(int id) {
		super();
		this.id = id;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public User getUsrs() {
		return usrs;
	}

	public void setUsrs(User usrs) {
		this.usrs = usrs;
	}

	public book getBoks() {
		return boks;
	}

	public void setBoks(book boks) {
		this.boks = boks;
	}

}