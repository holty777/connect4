import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Highscores {
	public ArrayList<HighScore> highscoreList;
	
	public Highscores () {
		//display max 10 high scores?
		highscoreList = new ArrayList<HighScore>();
	}

	public ArrayList getHighscoreList() throws FileNotFoundException {
		Scanner sc = null;
	    sc = new Scanner(new FileReader("HighScores.txt"));
	    while(sc.hasNext()){
	    	String splitt = sc.nextLine();
			String[] action = splitt.split("\\s+");
			int score = Integer.parseInt(action[1]);
			createHighScore(action[0], score);
	    }

		return highscoreList;
	}
	
	public void createHighScore(String name, int score){
		for (HighScore score1 : highscoreList){
			highscoreList.add(new HighScore(name, score));
		}
	}
}
