
public class HighScore {

	private String name;
	private int score;
	
	public HighScore(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	//return name
	public String getName(){
		return this.name;
	}
	
	//return score
	public int getScore(){
		return this.score;
	}
}
