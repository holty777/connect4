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
	
	
	public void addToken(String token) {
		// add token to arraylist
	}
	
	public boolean checkGameFinished() {
		//check if winner/board full
		return false;
	}
	
	
	public void drawBoard() {
		//GUI
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
	
	public void restart () {
		//new game
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
			Tokens token = new Tokens (true); //testing,  make true for now, will be getPlayer though
			board.add(arr); 
			board.get(i).add(token);
		}
	}
	
	public void addToken(int col, int row){
		Tokens t = new Tokens (true); //testing, make true for now, will be getPlayer though
		board.get(col).add(t);
		System.out.println(board.get(col).size());
	}
	
	public int getNextToken(int i) {
		return board.get(i).size()-1;
		
	}
	
	public int isLegal(int i){
		//TO DO
		return 0;
	}
}
