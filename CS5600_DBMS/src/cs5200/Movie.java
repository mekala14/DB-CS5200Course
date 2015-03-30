package cs5200;

public class Movie {
	private String movieid;
	private String title;
	private String posterImage;
	private String releaseDate;

	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(String movieid, String title, String posterImage,
			String releaseDate) {
		super();
		this.movieid = movieid;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}

	

	public String getThemovieid() {
		return movieid;
	}

	public void setThemovieid(String movieid) {
		this.movieid = movieid;
	}

	public String getThetitle() {
		return title;
	}

	public void setThetitle(String title) {
		this.title = title;
	}

	public String getTheposterImage() {
		return posterImage;
	}

	public void setTheposterImage(String posterImage) {
		this.posterImage = posterImage;
	}

	public String getThereleaseDate() {
		return releaseDate;
	}

	public void setThereleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

}
