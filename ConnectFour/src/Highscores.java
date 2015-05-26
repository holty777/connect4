import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Highscores {
	public ArrayList<HighScore> highscoreList;
	
	public Highscores () {
		//display max 10 high scores?
		highscoreList = new ArrayList<HighScore>();
	}

	public ArrayList<HighScore> getHighscoreList() throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("src/HighScores.txt"));
	    while(sc.hasNext()){
	    	String splitt = sc.nextLine();
			String[] action = splitt.split("\\s+");
			int score = Integer.parseInt(action[1]);
			createHighScore(action[0], score);
	    }

		return highscoreList;
	}
	
	public void createHighScore(String name, int score){
			highscoreList.add(new HighScore(name, score));
	}
	
	public void setHighscoreList() throws IOException{
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter("HighScores.txt", true)))) {
			for (HighScore score : highscoreList){
				out.println(score.getName() + " " + score.getScore());
			}
		}
	}
}
