import java.util.*;


public class Board {
	ArrayList<ArrayList<Tokens>> board;
	boolean playerTurn;
	boolean gameFinished;
	String gameMode;
	String gameDifficulty;
	int scoreA;
	int scoreB;
	boolean isLegal;
	
	public Board() {
		board = new ArrayList<ArrayList<Tokens>>(7);
		fillBoard();
	}
	
	public boolean checkGameFinished() {
		//check if winner/board full
		return false;
	}
	
	public void incrementScore(boolean player) {
		if (player == false) {
			scoreA++;
		} else {
			scoreB++;
		}
	}
	
	public void addHighscore(String name, int score) {
		//set the highscore
	}
	
	public ArrayList<ArrayList<Tokens>> getBoard() {
		return board;
	}
	public void setBoard(ArrayList<ArrayList<Tokens>> board) {
		this.board = board;
	}
	
	public boolean isPlayerTurn() {
		return playerTurn;
	}
	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}
	public boolean isGameFinished() {
		return gameFinished;
	}
	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}
	public String getGameMode() {
		return gameMode;
	}
	
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	
	public void setGameDifficulty(String gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}
	public int getScoreA() {
		return scoreA;
	}
	public void setScoreA(int scoreA) {
		this.scoreA = scoreA;
	}
	public int getScoreB() {
		return scoreB;
	}
	public void setScoreB(int scoreB) {
		this.scoreB = scoreB;
	}
	
	
	
	
	// self created functions
	public void fillBoard(){
		for (int i = 0; i < 7; i++){
			ArrayList<Tokens> arr = new ArrayList<Tokens>(6);
			board.add(arr); 
		}
	}
	
	public void addToken(int col, int row, boolean player){
		Tokens t = new Tokens (player);
		board.get(col).add(t);
	}
	
	public int getNextToken(int i, boolean player) {
		if (board.get(i) == null){
			Tokens token = new Tokens (player);
			board.get(i).add(token);

		}
		
		return board.get(i).size();
		
	}
	
	public boolean isLegal(int i){
		boolean l = true;
		
		if (board.get(i).size() > 5){
			l = false;
		}
		
		return l;
	}
}
