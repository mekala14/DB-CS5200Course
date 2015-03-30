package cs5200;

public class Cast {
	private int castid;
	private String movieid;
	private String actorid;
	private String characterName;
	
	
	public Cast() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cast(int castid, String movieid, String actorid,
			String characterName) {
		super();
		this.castid = castid;
		this.movieid = movieid;
		this.actorid = actorid;
		this.characterName = characterName;
	}

	

	public int getThecastid() {
		return castid;
	}

	public void setThecastid(int castid) {
		this.castid = castid;
	}

	public String getThemovieid() {
		return movieid;
	}

	public void setThemovieid(String movieid) {
		this.movieid = movieid;
	}

	public String getTheactorid() {
		return actorid;
	}

	public void setTheactorid(String finame) {
		this.actorid = finame;
	}

	public String getThecharacterName() {
		return characterName;
	}

	public void setThelastName(String characterName) {
		this.characterName = characterName;
	}

	
}
