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
	
	public boolean weHaveAWinner (int row, int column) 	//Board position given by (row,column)
	{
		boolean result = false;
		
		//Checking columns
		//iterate through column given 
		ArrayList<Tokens> columns = board.get(column);
		for (int i = 0; i < columns.size(); i++)
		{
			String tokenColour = columns.get(0).getColour();
			int counter = 0;
			if (columns.get(i).getColour().equals(tokenColour))
			{
				counter += counter;
				if (counter == 4)
				{
					result = true;
					break;
				}
			}
			else
			{
				tokenColour = columns.get(i).getColour();
				counter = 0;
			}
		}
		
		//get row number and check rows
		for (int j = 0; j <= 3; j++)
		{
			ArrayList<Tokens> columnj = board.get(j);
			ArrayList<Tokens> columnj1 = board.get(j + 1);
			ArrayList<Tokens> columnj2 = board.get(j + 2);
			ArrayList<Tokens> columnj3 = board.get(j + 3);
			String a = columnj.get(row).getColour();
			String b = columnj1.get(row).getColour();
			String c = columnj2.get(row).getColour();
			String d = columnj3.get(row).getColour();
			if(a.equals(b))										// A = B
			{
				if(c.equals(d))									// C = D
				{
					if(a.equals(c))								// A = C, therefore they are all equal 
					{
						result = true;
						break;
					}
				}
			}
		}
		
		//check diagonals
		
		String token = board.get(column).get(row).getColour();
		//North East from token
		String ne1 = board.get(column + 1).get(row + 1).getColour();
		String ne2 = board.get(column + 2).get(row + 2).getColour();
		String ne3 = board.get(column + 3).get(row + 3).getColour();
		//South West from token
		String sw1 = board.get(column - 1).get(row - 1).getColour();
		String sw2 = board.get(column - 2).get(row - 2).getColour();
		String sw3 = board.get(column - 3).get(row - 3).getColour();
		//North West from token
		String nw1 = board.get(column - 1).get(row + 1).getColour();
		String nw2 = board.get(column - 2).get(row + 2).getColour();
		String nw3 = board.get(column - 3).get(row + 3).getColour();
		//South East from token
		String se1 = board.get(column + 1).get(row - 1).getColour();
		String se2 = board.get(column + 2).get(row - 2).getColour();
		String se3 = board.get(column + 3).get(row - 3).getColour();
		
		// win with NE/SW diagonal
		if(token.equals(ne1))						// token = ne1					
		{
			if(ne1.equals(ne2))						//	token = ne1 = ne2		
			{
				if(ne2.equals(ne3))					//  token = ne1 = ne2 = ne3		GG	 
				{
					result = true;
				}
				else if(ne2.equals(sw1))			//	sw1 = token = ne1 = ne2		GG
				{
					result = true;
				}
			}
			else if (ne1.equals(sw1))				//	sw1 = token = ne1
			{
				if(sw1.equals(sw2))					//	sw2 = sw1 = token = ne1		GG
				{
					result = true;					
				}
			}
		}
		else if (token.equals(sw1))					// sw1 = token 
		{
			if(sw1.equals(sw2))						// sw2 = sw1 = token 
			{
				if(sw2.equals(sw3))					// sw3 = sw2 = sw1 = token 		GG
				{
					result = true;
				}
			}
		}
				
		//win with NW/SE diagonal
		if(token.equals(nw1))						// token = nw1					
		{
			if(nw1.equals(nw2))						//	token = nw1 = nw2		
			{
				if(nw2.equals(nw3))					//  token = nw1 = nw2 = nw3		GG	 
				{
					result = true;
				}
				else if(nw2.equals(se1))			//	se1 = token = nw1 = nw2		GG
				{
					result = true;
				}
			}
			else if (nw1.equals(se1))				//	se1 = token = nw1
			{
				if(se1.equals(se2))					//	se2 = se1 = token = nw1		GG
				{
					result = true;					
				}
			}
		}
		else if (token.equals(se1))					// se1 = token 
		{
			if(se1.equals(se2))						// se2 = se1 = token 
			{
				if(se2.equals(se3))					// se3 = se2 = se1 = token 		GG
				{
					result = true;
				}
			}
		}
		
		
		
		return result;
	}
}
