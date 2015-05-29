import java.util.ArrayList;
import java.util.Random;


public class AI {
	
	private Board board;
	private Board tempBoard;
	private ArrayList<Integer> moves;
	private ArrayList<Integer> xPos;
	private ArrayList<Integer> yPos;
	private String difficulty;
	private Random random;
	private boolean finished;
	private int rNum;
	private int hDepth;
	
	public AI (String difficulty, Board board, ArrayList<Integer> xP, ArrayList<Integer> yP) {
		this.board = board;
		this.difficulty = difficulty;
		this.xPos = xP;
		this.yPos = yP;
		this.moves = new ArrayList<Integer>();
		this.hDepth = 2;
		random = new Random();
		
	}
	
	/**
	 * @return the coord to place next token depending on difficulty
	 */
	public ArrayList<Integer> placeToken () {
		if (difficulty.equals("easy")){
			easyMode();
			
		} else if (difficulty.equals("hard")){
			hardMode();
		}
		
		return moves;
	}
	
	/**
	 * easy mode ai
	 */
	public void easyMode () {
		while (!isFinished()){

			rNum = block();
			
			if (!(rNum < 7)) {
				rNum = random.nextInt(7);
			}

			if (board.isLegal(rNum)){
				moves.add(rNum);
				moves.add(board.getNextToken(rNum, false));
				setFinished(true);
			}
		}
		setFinished(false);
	}
	
	/**
	 * hard mode ai
	 */
	public void hardMode () {
		while (!isFinished()){
			
			rNum = block();
			
			if (!(rNum < 7)) {
				tempBoard = copyBoard(board);
				rNum = negaMax(tempBoard, -10000000, 0, true, (xPos.get(xPos.size()-1)), (yPos.get(yPos.size()-1)));
			}
			if (board.isLegal(rNum)){
				moves.add(rNum);
				moves.add(board.getNextToken(rNum, false));
				setFinished(true);
			}
			
		}
		setFinished(false);
	}


	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the tempBoard
	 */
	public Board getTempBoard() {
		return tempBoard;
	}

	/**
	 * @param tempBoard the tempBoard to set
	 */
	public void setTempBoard(Board tempBoard) {
		this.tempBoard = tempBoard;
	}

	/**
	 * @return the moves
	 */
	public ArrayList<Integer> getMoves() {
		return moves;
	}

	/**
	 * @param moves the moves to set
	 */
	public void setMoves(ArrayList<Integer> moves) {
		this.moves = moves;
	}

	/**
	 * @return the xPos
	 */
	public ArrayList<Integer> getxPos() {
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(ArrayList<Integer> xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public ArrayList<Integer> getyPos() {
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(ArrayList<Integer> yPos) {
		this.yPos = yPos;
	}

	/**
	 * @return the difficulty
	 */
	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @return the random
	 */
	public Random getRandom() {
		return random;
	}

	/**
	 * @param random the random to set
	 */
	public void setRandom(Random random) {
		this.random = random;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * @return the rNum
	 */
	public int getrNum() {
		return rNum;
	}

	/**
	 * @param rNum the rNum to set
	 */
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	/**
	 * @return the hDepth
	 */
	public int gethDepth() {
		return hDepth;
	}

	/**
	 * @param hDepth the hDepth to set
	 */
	public void sethDepth(int hDepth) {
		this.hDepth = hDepth;
	}

	public void clearMoves(){
		this.moves.clear();
	}
	
	/**
	 * 
	 * @return the column to block
	 */
	public int block () {
		int cNum = 9;
		int tCount = 0;
		
		// check vertical
		for (int i = 0; i < 7; i++) {
			tCount = 0;
			if (board.getBoard().get(i).size() < 3) {
				continue;
			} else {
				for (int j = 0; j < board.getBoard().get(i).size(); j++) {
					if (board.getBoard().get(i).get(j).isPlayer()) {
						tCount++;
					} else {
						tCount = 0;
					}
					
					if (tCount == 3 && board.isLegal(i)) {
						if (isBlocked(i, j+1)) {
							break;
						} else {
							cNum = i;
							break;
						}
					}
				}
			}
			
			if (cNum != 9) {
				break;
			}
		}
		
		// check horizontal
		if (cNum == 9) {
			int xPrev = xPos.get(xPos.size()-1);
			int yPrev = yPos.get(yPos.size()-1);
			int count = 0;
			int noMatch = 0;
			
			for (int i = 0; i < 4; i++) {
				count = 0;
				
				ArrayList<Tokens> columnj = board.getBoard().get(i);
				ArrayList<Tokens> columnj1 = board.getBoard().get(i + 1);
				ArrayList<Tokens> columnj2 = board.getBoard().get(i + 2);
				ArrayList<Tokens> columnj3 = board.getBoard().get(i + 3);
		
				int a = 99;
				int b = 98;
				int c = 97;
				int d = 96;
				
				if (yPrev < columnj.size()) 
					a = columnj.get(yPrev).getPlayerNum();
				if (yPrev < columnj1.size())
					b = columnj1.get(yPrev).getPlayerNum();
				if (yPrev < columnj2.size())
					c = columnj2.get(yPrev).getPlayerNum();
				if (yPrev < columnj3.size())
					d = columnj3.get(yPrev).getPlayerNum();
				
				if (a == 1)	{
					count++;
				} else {
					noMatch = i;
				}
				if (b == 1) {
					count++;
				} else {
					noMatch = i+1;
				}
				if (c == 1) {
					count++;
				} else {
					noMatch = i+2;
				}
				if (d == 1) {
					count++;
				} else {
					noMatch = i+3;
				}
				
				if (count == 3) {
					if (board.getBoard().get(noMatch).size() == board.getBoard().get(xPrev).size()-1) {
						cNum = noMatch;
					}
				}
				
				if (cNum != 9) {
					break;
				}
			}
		}
		
		return cNum;
	}
	
	/**
	 * 
	 * @param xC the x cord
	 * @param yC the y cord
	 * @return whether that x,y cord it is blocked
	 */
	public boolean isBlocked (int xC, int yC) {
		boolean status = false;
		for (int s = 0; s < xPos.size(); s++) {
			if (xPos.get(s) == xC && yPos.get(s) == yC) {
				status = true;
			}
		}
		return status;
	}
	
	/**
	 * 
	 * @param toCopy the board to copy
	 * @return the copied board
	 */
	public Board copyBoard (Board toCopy) {
		Board newBoard = new Board();
		ArrayList<ArrayList<Tokens>> tempGboard = new ArrayList<ArrayList<Tokens>>(7);
		for (int i = 0; i < 7; i++) {
			tempGboard.add(new ArrayList<Tokens>(toCopy.getBoard().get(i)));
		}
		newBoard.setBoard(tempGboard);	
		return newBoard;
	}
	

	/**
	 * @param boardCopy the copy of the board
	 * @param alpha the alpha value
	 * @param depth the depth to explore
	 * @param player the player number
	 * @param xCoord the x value
	 * @param yCoord the y value
	 * @return the negamax value
	 */
	public int negaMax (Board boardCopy, int alpha, int depth, boolean player, int xCoord, int yCoord) {		
		int bestPath = 0;
		int nodeVal = alpha;
		int pNum;
		boolean nextPlayer;
		
		if (player) {
			pNum = 1;
			nextPlayer = false;
		} else {
			pNum = 2;
			nextPlayer = true;
		}
		
		if (boardCopy.weHaveAWinner(yCoord, xCoord)) {
			// IF WE HAVE A WINNERRRRR
			nodeVal = 10000000;
			if (pNum == 2) {
				nodeVal = nodeVal * -1; 
			}
			
		} else if ((boardCopy.getBoard().get(0).size() == 6) &&
					(boardCopy.getBoard().get(1).size() == 6) &&
					(boardCopy.getBoard().get(2).size() == 6) &&
					(boardCopy.getBoard().get(3).size() == 6) &&
					(boardCopy.getBoard().get(4).size() == 6) &&
					(boardCopy.getBoard().get(5).size() == 6) &&
					(boardCopy.getBoard().get(6).size() == 6)) {
						// if its a draw
						nodeVal = 0;
						
		} else if (depth == hDepth) {
			nodeVal = evalBoard(boardCopy, pNum);
			
			if (pNum == 1) {
				nodeVal = nodeVal*-1; 
			}
			
		} else {
			for (int col = 0; col < 7; col++) {
				Board nextState = copyBoard(boardCopy);
				
				if (nextState.isLegal(col)){
					nextState.addToken(col, (nextState.getBoard().get(col).size()), nextPlayer);
					int nValue = -negaMax (nextState, -10000000, (depth+1), nextPlayer, col, (nextState.getBoard().get(col).size()-1));
					
					if (nValue >= nodeVal) {
						bestPath = col;
						nodeVal = nValue;
					}
				} else {
					continue;
				}
			}
		}
		
		if (depth == 0) {
			// finished recursing back up
			return bestPath;
		} else {
			// still recursing
			return nodeVal;
		}
	}
	
	/**
	 * 
	 * @param nextState
	 * @param player
	 * @return
	 */
	public int evalBoard (Board nextState, int playerNum) {
		// prioritise horizontal > diagonal > vertical
		int vertical = 1;
		int diagonal = 2;
		int horizontal = 3;
		
		int cTwo = 5;
		int cThree = 1000;
		
		int bestVal = 0;
		
		// horizontal connect 2
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 4; c++) {
				// (xx00)
				if ((r < nextState.getBoard().get(c).size()) && 
					(r < nextState.getBoard().get(c+1).size()) &&
					(r <= nextState.getBoard().get(c+2).size()) &&
					(r <= nextState.getBoard().get(c+3).size())) {
					
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= r) &&
							(nextState.getBoard().get(c+3).size() <= r)) {
								bestVal += cTwo*horizontal;
						}
				}
				
				// (x0x0)
				if ((r < nextState.getBoard().get(c).size()) && 
					(r <= nextState.getBoard().get(c+1).size()) &&
					(r < nextState.getBoard().get(c+2).size()) &&
					(r <= nextState.getBoard().get(c+3).size())) {
						
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= r) && 
							(nextState.getBoard().get(c+2).get(r).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= r)) {
								bestVal += cTwo*horizontal;
						}
				}
				
				// (x00x)
				if ((r < nextState.getBoard().get(c).size()) && 
					(r <= nextState.getBoard().get(c+1).size()) &&
					(r <= nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
							
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= r) && 
							(nextState.getBoard().get(c+2).size() <= r) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cTwo*horizontal;
						}
				}
				
				// (0xx0)
				if ((r <= nextState.getBoard().get(c).size()) && 
					(r < nextState.getBoard().get(c+1).size()) &&
					(r < nextState.getBoard().get(c+2).size()) &&
					(r <= nextState.getBoard().get(c+3).size())) {
							
						if ((nextState.getBoard().get(c).size() <= r) && 
							(nextState.getBoard().get(c+1).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= r)) {
								bestVal += cTwo*horizontal;
						}
				}
				
				// (0x0x)
				if ((r <= nextState.getBoard().get(c).size()) && 
					(r < nextState.getBoard().get(c+1).size()) &&
					(r <= nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
					
						if ((nextState.getBoard().get(c).size() <= r) && 
							(nextState.getBoard().get(c+1).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= r) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cTwo*horizontal;
						}
				}
				
				// (00xx)
				if ((r <= nextState.getBoard().get(c).size()) && 
					(r <= nextState.getBoard().get(c+1).size()) &&
					(r < nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
							
						if ((nextState.getBoard().get(c).size() <= r) && 
							(nextState.getBoard().get(c+1).size() <= r) && 
							(nextState.getBoard().get(c+2).get(r).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cTwo*horizontal;
						}
				}				
			}
		}
		
		// diagonal connect 2 (/, sw to ne)
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 4; c++) {
				/*    0
				 *   0
				 *  x
				 * x
				 */
				if ((r < nextState.getBoard().get(c).size()) && 
					((r+1) < nextState.getBoard().get(c+1).size()) &&
					((r+2) <= nextState.getBoard().get(c+2).size()) &&
					((r+3) <= nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r+1).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= (r+2)) &&
							(nextState.getBoard().get(c+3).size() <= (r+3))) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/*    0
				 *   x
				 *  0
				 * x
				 */
				if ((r < nextState.getBoard().get(c).size()) && 
					((r+1) <= nextState.getBoard().get(c+1).size()) &&
					((r+2) < nextState.getBoard().get(c+2).size()) &&
					((r+3) <= nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= (r+1)) && 
							(nextState.getBoard().get(c+2).get(r+2).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= (r+3))) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/*    x
				 *   0
				 *  0
				 * x
				 */
				if ((r < nextState.getBoard().get(c).size()) && 
					((r+1) <= nextState.getBoard().get(c+1).size()) &&
					((r+2) <= nextState.getBoard().get(c+2).size()) &&
					((r+3) < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= (r+1)) && 
							(nextState.getBoard().get(c+2).size() <= (r+2)) &&
							(nextState.getBoard().get(c+3).get(r+3).getPlayerNum() == playerNum)) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/*    0
				 *   x
				 *  x
				 * 0
				 */
				if ((r <= nextState.getBoard().get(c).size()) && 
					((r+1) < nextState.getBoard().get(c+1).size()) &&
					((r+2) < nextState.getBoard().get(c+2).size()) &&
					((r+3) <= nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).size() <= r) && 
							(nextState.getBoard().get(c+1).get(r+1).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r+2).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= (r+3))) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/*    x
				 *   0
				 *  x
				 * 0
				 */
				if ((r <= nextState.getBoard().get(c).size()) && 
					((r+1) < nextState.getBoard().get(c+1).size()) &&
					((r+2) <= nextState.getBoard().get(c+2).size()) &&
					((r+3) < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).size() <= r) && 
							(nextState.getBoard().get(c+1).get(r+1).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= (r+2)) &&
							(nextState.getBoard().get(c+3).get(r+3).getPlayerNum() == playerNum)) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/*    x
				 *   x
				 *  0
				 * 0
				 */
				if ((r <= nextState.getBoard().get(c).size()) && 
					((r+1) <= nextState.getBoard().get(c+1).size()) &&
					((r+2) < nextState.getBoard().get(c+2).size()) &&
					((r+3) < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).size() <= r) && 
							(nextState.getBoard().get(c+1).size() <= (r+1)) && 
							(nextState.getBoard().get(c+2).get(r+2).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r+3).getPlayerNum() == playerNum)) {
								bestVal += cTwo*diagonal;
						}
				}
			}
		}
		
		// opposite diagonal connect 2 (\, nw to se)
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 4; c++) {
				/* x
				 *  x
				 *   0
				 *    0
				 */
				if (((r+3) < nextState.getBoard().get(c).size()) && 
					((r+2) < nextState.getBoard().get(c+1).size()) &&
					((r+1) <= nextState.getBoard().get(c+2).size()) &&
					(r <= nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r+3).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r+2).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= (r+1)) &&
							(nextState.getBoard().get(c+3).size() <= r)) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/* x
				 *  0
				 *   x
				 *    0
				 */
				if (((r+3) < nextState.getBoard().get(c).size()) && 
					((r+2) <= nextState.getBoard().get(c+1).size()) &&
					((r+1) < nextState.getBoard().get(c+2).size()) &&
					(r <= nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r+3).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= (r+2)) && 
							(nextState.getBoard().get(c+2).get(r+1).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= r)) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/* x
				 *  0
				 *   0
				 *    x
				 */
				if (((r+3) < nextState.getBoard().get(c).size()) && 
					((r+2) <= nextState.getBoard().get(c+1).size()) &&
					((r+1) <= nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r+3).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= (r+2)) && 
							(nextState.getBoard().get(c+2).size() <= (r+1)) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/* 0
				 *  x
				 *   x
				 *    0
				 */
				if (((r+3) <= nextState.getBoard().get(c).size()) && 
					((r+2) < nextState.getBoard().get(c+1).size()) &&
					((r+1) < nextState.getBoard().get(c+2).size()) &&
					(r <= nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).size() <= (r+3)) && 
							(nextState.getBoard().get(c+1).get(r+2).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r+1).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= r)) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/* 0
				 *  x
				 *   0
				 *    x
				 */
				if (((r+3) <= nextState.getBoard().get(c).size()) && 
					((r+2) < nextState.getBoard().get(c+1).size()) &&
					((r+1) <= nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).size() <= (r+3)) && 
							(nextState.getBoard().get(c+1).get(r+2).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= (r+1)) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cTwo*diagonal;
						}
				}
				
				/* 0
				 *  0
				 *   x
				 *    x
				 */
				if (((r+3) <= nextState.getBoard().get(c).size()) && 
					((r+2) <= nextState.getBoard().get(c+1).size()) &&
					((r+1) < nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).size() <= (r+3)) && 
							(nextState.getBoard().get(c+1).size() <= (r+2)) && 
							(nextState.getBoard().get(c+2).get(r+1).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cTwo*diagonal;
						}
				}
			}
		}
		
		// vertical connect 2
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 7; c++) {
				/* 0
				 * 0
				 * x
				 * x
				 */
				if ((r+3) <= nextState.getBoard().get(c).size()) {
									
					if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
						(nextState.getBoard().get(c).get(r+1).getPlayerNum() == playerNum) && 
						(nextState.getBoard().get(c).size() <= (r+2))) {
							bestVal += cTwo*vertical;
					}
				}
			}
		}
		
		// horizontal connect 3
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 4; c++) {
				// (xxx0)
				if ((r < nextState.getBoard().get(c).size()) && 
					(r < nextState.getBoard().get(c+1).size()) &&
					(r < nextState.getBoard().get(c+2).size()) &&
					(r <= nextState.getBoard().get(c+3).size())) {
					
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= r)) {
								bestVal += cThree*horizontal;
						}
				}
				
				// (xx0x)
				if ((r < nextState.getBoard().get(c).size()) && 
					(r < nextState.getBoard().get(c+1).size()) &&
					(r <= nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
						
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= r) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cThree*horizontal;
						}
				}
				
				// (x0xx)
				if ((r < nextState.getBoard().get(c).size()) && 
					(r <= nextState.getBoard().get(c+1).size()) &&
					(r < nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
							
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= r) && 
							(nextState.getBoard().get(c+2).get(r).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cThree*horizontal;
						}
				}
				
				// (0xxx)
				if ((r <= nextState.getBoard().get(c).size()) && 
					(r < nextState.getBoard().get(c+1).size()) &&
					(r < nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
							
						if ((nextState.getBoard().get(c).size() <= r) && 
							(nextState.getBoard().get(c+1).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cThree*horizontal;
						}
				}	
			}
		}
				
		// diagonal connect 3 (/, sw to ne)
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 4; c++) {
				/*    0
				 *   x
				 *  x
				 * x
				 */
				if ((r < nextState.getBoard().get(c).size()) && 
					((r+1) < nextState.getBoard().get(c+1).size()) &&
					((r+2) < nextState.getBoard().get(c+2).size()) &&
					((r+3) <= nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r+1).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r+2).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= (r+3))) {
								bestVal += cThree*diagonal;
						}
				}
				
				/*    x
				 *   0
				 *  x
				 * x
				 */
				if ((r < nextState.getBoard().get(c).size()) && 
					((r+1) < nextState.getBoard().get(c+1).size()) &&
					((r+2) <= nextState.getBoard().get(c+2).size()) &&
					((r+3) < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r+1).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= (r+2)) &&
							(nextState.getBoard().get(c+3).get(r+3).getPlayerNum() == playerNum)) {
								bestVal += cThree*diagonal;
						}
				}
				
				/*    x
				 *   x
				 *  0
				 * x
				 */
				if ((r < nextState.getBoard().get(c).size()) && 
					((r+1) < nextState.getBoard().get(c+1).size()) &&
					((r+2) <= nextState.getBoard().get(c+2).size()) &&
					((r+3) < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= (r+1)) && 
							(nextState.getBoard().get(c+2).get(r+2).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r+3).getPlayerNum() == playerNum)) {
								bestVal += cThree*diagonal;
						}
				}
				
				/*    x
				 *   x
				 *  x
				 * 0
				 */
				if ((r <= nextState.getBoard().get(c).size()) && 
					((r+1) < nextState.getBoard().get(c+1).size()) &&
					((r+2) < nextState.getBoard().get(c+2).size()) &&
					((r+3) < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).size() <= r) && 
							(nextState.getBoard().get(c+1).get(r+1).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r+2).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r+3).getPlayerNum() == playerNum)) {
								bestVal += cThree*diagonal;
						}
				}
			}
		}
		
		// opposite diagonal connect 3 (\, nw to se)
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 4; c++) {
				/* x
				 *  x
				 *   x
				 *    0
				 */
				if (((r+3) < nextState.getBoard().get(c).size()) && 
					((r+2) < nextState.getBoard().get(c+1).size()) &&
					((r+1) < nextState.getBoard().get(c+2).size()) &&
					(r <= nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r+3).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r+2).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r+1).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).size() <= r)) {
								bestVal += cThree*diagonal;
						}
				}
				
				/* x
				 *  x
				 *   0
				 *    x
				 */
				if (((r+3) < nextState.getBoard().get(c).size()) && 
					((r+2) < nextState.getBoard().get(c+1).size()) &&
					((r+1) <= nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r+3).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).get(r+2).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).size() <= (r+1)) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cThree*diagonal;
						}
				}
				
				/* x
				 *  0
				 *   x
				 *    x
				 */
				if (((r+3) < nextState.getBoard().get(c).size()) && 
					((r+2) <= nextState.getBoard().get(c+1).size()) &&
					((r+1) < nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).get(r+3).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+1).size() <= (r+2)) && 
							(nextState.getBoard().get(c+2).get(r+1).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cThree*diagonal;
						}
				}
				
				/* 0
				 *  x
				 *   x
				 *    x
				 */
				if (((r+3) <= nextState.getBoard().get(c).size()) && 
					((r+2) < nextState.getBoard().get(c+1).size()) &&
					((r+1) < nextState.getBoard().get(c+2).size()) &&
					(r < nextState.getBoard().get(c+3).size())) {
								
						if ((nextState.getBoard().get(c).size() <= (r+3)) && 
							(nextState.getBoard().get(c+1).get(r+2).getPlayerNum() == playerNum) && 
							(nextState.getBoard().get(c+2).get(r+1).getPlayerNum() == playerNum) &&
							(nextState.getBoard().get(c+3).get(r).getPlayerNum() == playerNum)) {
								bestVal += cThree*diagonal;
						}
				}
			}
		}
		
		// vertical connect 3
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 7; c++) {
				/* 0
				 * x
				 * x
				 * x
				 */
				if ((r+3) <= nextState.getBoard().get(c).size()) {
									
					if ((nextState.getBoard().get(c).get(r).getPlayerNum() == playerNum) && 
						(nextState.getBoard().get(c).get(r+1).getPlayerNum() == playerNum) && 
						(nextState.getBoard().get(c).get(r+2).getPlayerNum() == playerNum) &&
						(nextState.getBoard().get(c).size() <= (r+3))) {
							bestVal += cThree*vertical;
					}
				}
			}
		}
		
		return bestVal;
	}
	
}
