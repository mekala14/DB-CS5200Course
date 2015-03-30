package cs5200;

public class Comment {
	private int id;
	private String username;
	private String movieid;
	private String comment;
	private String date;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int id,String username, String movieid, String comment,
			String date) {
		super();
		this.id = id;
		this.username = username;
		this.movieid = movieid;
		this.comment = comment;
		this.date = date;
		}

	public int getTheid(){
		return id;
	}
	
	public void setTheid(int id)
	{
		this.id = id;
	}

	public String getTheusername() {
		return username;
	}

	public void setTheusername(String username) {
		this.username = username;
	}

	public String getThemovieid() {
		return movieid;
	}

	public void setThemovieid(String movieid) {
		this.movieid = movieid;
	}

	public String getThecomment() {
		return comment;
	}

	public void setThecomment(String comment) {
		this.comment = comment;
	}

	public String getThedate() {
		return date;
	}

	public void setThedate(String date) {
		this.date = date;
	}

	
}
