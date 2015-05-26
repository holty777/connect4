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
	/**
	 * initialise the board with empty array
	 */
	public void fillBoard(){
		for (int i = 0; i < 7; i++){
			ArrayList<Tokens> arr = new ArrayList<Tokens>(6);
			board.add(arr); 
		}
	}

	/**
	 * @param col the column for the token to be placed
	 * @param row the row for the token to be placed
	 * @param player the current player
	 */
	public void addToken(int col, int row, boolean player){
		Tokens t = new Tokens (player);
		board.get(col).add(t);
	}

	/**
	 * @param i the column for the token to be placed in
	 * @param player the players turn
	 * @return the next free place for the token to be placed in
	 */
	public int getNextToken(int i, boolean player) {
		if (board.get(i) == null){
			Tokens token = new Tokens (player);
			board.get(i).add(token);

		}

		return board.get(i).size();

	}

	/**
	 * @param i the column for the token to be placed in
	 * @return if the column is full or not
	 */
	public boolean isLegal(int i){
		boolean l = true;

		if (board.get(i).size() > 5){
			l = false;
		}

		return l;
	}

	/**
	 * @param turn the current turn
	 * @return if the board is full
	 */
	public boolean isBoardFull(int turn)
	{
		boolean boardFull = false;

		if (turn > 41){
			boardFull = true;
		}

		return boardFull;
	}

	/**
	 * @param row the row the token is in
	 * @param column the column the token is in
	 * @return if the board is at a winning state
	 */
	public boolean weHaveAWinner (int row, int column){
		boolean result = false;
		int counter = 0;

		//Checking columns
		//iterate through column given 
		ArrayList<Tokens> columns = board.get(column);
		int tokenColour = columns.get(row).getPlayerNum();

		for (int i = 0; i < columns.size(); i++) {
			if (columns.get(i).getPlayerNum() == tokenColour){
				counter += 1; 
				if (counter == 4) {
					result = true;
					break;
				}
			} else {
				counter = 0;
			}
		}

		
		if (result != true){
			for (int j = 0; j <= 3; j++)
			{
				ArrayList<Tokens> columnj = board.get(j);
				ArrayList<Tokens> columnj1 = board.get(j + 1);
				ArrayList<Tokens> columnj2 = board.get(j + 2);
				ArrayList<Tokens> columnj3 = board.get(j + 3);
		
				int a = 97;
				int b = 65;
				int c = 54;
				int d = 99;
				
				if (row < columnj.size())
					a = columnj.get(row).getPlayerNum();
				if (row < columnj1.size())
					b = columnj1.get(row).getPlayerNum();
				if (row < columnj2.size())
					c = columnj2.get(row).getPlayerNum();
				if (row < columnj3.size())
					d = columnj3.get(row).getPlayerNum();

				if (a == b){										// A = B
					if(c == d){									// C = D
						if(a == c){								// A = C, therefore they are all equal 
							result = true;
							break;
						}
					}
				}
			}
		}
		
		//ignore initialization values
		if (result != true){
			int token = 99;

			int ne1 = 91;
			int ne2 = 92;
			int ne3 = 93;

			int sw1	= 94;
			int sw2	= 95;
			int sw3 = 96;

			int nw1 = 82;
			int nw2 = 83;
			int nw3 = 84;

			int se1 = 85;
			int se2 = 86;
			int se3 = 87;
						
			//check diagonals
			token = board.get(column).get(row).getPlayerNum();			// token just played
			
			//North East from token
			if (column < 6){
				if((row + 1) < board.get(column + 1).size() && row < 6){
					ne1 = board.get(column + 1).get(row + 1).getPlayerNum();
				}
			}
			if (column < 5){
				if((row + 2) < board.get(column + 2).size() && row < 5){
					ne2 = board.get(column + 2).get(row + 2).getPlayerNum();
				}
			}
			if (column < 4){
				if((row + 3) < board.get(column + 3).size() && row < 4){
					ne3 = board.get(column + 3).get(row + 3).getPlayerNum();
				}
			}
			//South West from token
			if (column > 0){
				if((row - 1) < board.get(column - 1).size() && row > 0){
					sw1 = board.get(column - 1).get(row - 1).getPlayerNum();
				}
			}
			if (column > 1){
				if((row - 2) < board.get(column - 2).size() && row > 1){
					sw2 = board.get(column - 2).get(row - 2).getPlayerNum();
				}
			}
			if (column > 2){
				if((row - 3) < board.get(column - 3).size() && row > 2){
					sw3 = board.get(column - 3).get(row - 3).getPlayerNum();
				}
			}
			//North West from token
			if (column > 0){
				if((row + 1) < board.get(column - 1).size() && row < 6){
					nw1 = board.get(column - 1).get(row + 1).getPlayerNum();
				}
			}
			if (column > 1){
				if((row + 2) < board.get(column - 2).size() && row < 5){
					nw2 = board.get(column - 2).get(row + 2).getPlayerNum();
				}
			}
			if (column > 2){
				if((row + 3) < board.get(column - 3).size() && row < 4){
					nw3 = board.get(column - 3).get(row + 3).getPlayerNum();
				}
			}

			//South East from token
			if (column < 6){
				if((row - 1) < board.get(column + 1).size() && row > 0){
					se1 = board.get(column + 1).get(row - 1).getPlayerNum();
				}
			}
			if (column < 5){
				if((row - 2) < board.get(column + 2).size() && row > 1){
					se2 = board.get(column + 2).get(row - 2).getPlayerNum();
				}
			}
			if (column < 4){
				if((row - 3) < board.get(column + 3).size() && row > 2){
					se3 = board.get(column + 3).get(row - 3).getPlayerNum();
				}
			}
			
			// win with NE/SW diagonal
			if(token == ne1){						// token = ne1					
				if(ne1 == ne2){						//	token = ne1 = ne2		
					if(ne2 == ne3){				//  token = ne1 = ne2 = ne3		GG	 
						result = true;
					} else if(ne2 == sw1){				//	sw1 = token = ne1 = ne2		GG
						result = true;
					} else if (ne1 == sw1){		//	sw1 = token = ne1
						if(sw1 == sw2){				//	sw2 = sw1 = token = ne1		GG
							result = true;					
						}
					}
				}
			} else if (token == sw1) {					// sw1 = token 
				if(sw1 == sw2){					// sw2 = sw1 = token 
					if(sw2 == sw3){			// sw3 = sw2 = sw1 = token 		GG
						result = true;
					}
				}
			}

			//win with NW/SE diagonal
			if(token == nw1){						// token = nw1					
				if(nw1 ==nw2){					//	token = nw1 = nw2		
					if(nw2 == nw3){				//  token = nw1 = nw2 = nw3		GG	 
						result = true;
					} else if(nw2 == se1){			//	se1 = token = nw1 = nw2		GG
						result = true;
					}
				} else if (nw1 == se1){			//	se1 = token = nw1
					if(se1 == se2){				//	se2 = se1 = token = nw1		GG
						result = true;					
					}
				}
			}else if (token == se1){				// se1 = token 
				if(se1 == se2){					// se2 = se1 = token 
					if(se2 == se3){				// se3 = se2 = se1 = token 		GG
						result = true;
					}
				}
			}
		}
		return result;
	}
}
