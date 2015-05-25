import java.util.ArrayList;
import java.util.Random;


public class AI {
	
	private Board board;
	private Board tempBoard;
	private String difficulty;
	private ArrayList<Integer> moves;
	private Random random;
	private int rNum;
	private boolean finished;
	
	private ArrayList<Integer> xPos;
	private ArrayList<Integer> yPos;
	
	public AI (String difficulty, Board board, ArrayList<Integer> xP, ArrayList<Integer> yP) {
		this.board = board;
		this.difficulty = difficulty;
		this.xPos = xP;
		this.yPos = yP;
		this.moves = new ArrayList<Integer>();
		random = new Random();
		
	}
	
	public ArrayList<Integer> placeToken () {
		if (difficulty.equals("easy")){
			easyMode();
			
		} else if (difficulty.equals("hard")){
			hardMode();
		}
		
		return moves;
	}
	
	public void easyMode () {
		while (!isFinished()){

			//int xPrev = xPos.get(xPos.size()-1);
			//int yPrev = yPos.get(yPos.size()-1);
			
			if (block() < 7) {
				rNum = block();
			} else {
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
	
	public void hardMode () {
		while (!isFinished()){
			
			if (block() < 7) {
				rNum = block();
			} else {
				rNum = random.nextInt(7);
			}
			
			if (board.isLegal(rNum)){
				moves.add(rNum);
				moves.add(board.getNextToken(rNum, false));
				setFinished(true);
			}
			
			/*tempBoard = board;
			
			// possible states
			for (int i = 0; i < 7; i++) {
				if (tempBoard.isLegal(i)) {
					//minmax (Y)
				}
			}*/

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
	 * @return the difficulty
	 */
	public String getDiff() {
		return difficulty;
	}

	/**
	 * @param diff the difficulty to set
	 */
	public void setDiff(String difficulty) {
		this.difficulty = difficulty;
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

	public void clearMoves(){
		this.moves.clear();
	}
	
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
			for (int i = 0; i < board.getBoard().get(i).size(); i++) {
				// check col
			}
			
		}
		// check diagonal
		if (cNum == 9) {
			for (int i = 0; i < 7; i++) {
				
			}
		}
		
		return cNum;
	}
	
	public boolean isBlocked (int xC, int yC) {
		boolean status = false;
		for (int s = 0; s < xPos.size(); s++) {
			if (xPos.get(s) == xC && yPos.get(s) == yC) {
				status = true;
			}
		}
		return status;
	}

	public int minMax (Board board, int tempRow, int tempCol) {
		
		// to do
		return 0;
	}
	
}
