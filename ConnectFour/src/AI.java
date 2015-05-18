import java.util.ArrayList;
import java.util.Random;


public class AI {
	
	private Board board;
	private int playerNum;
	private String difficulty;
	private ArrayList<Integer> moves;
	private Random random;
	private int rNum;
	private boolean finished;
	
	public AI (String difficulty, Board board) {
		this.board = board;
		this.difficulty = difficulty;
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
			
			rNum = random.nextInt(7);

			if (board.isLegal(rNum)){
				moves.add(rNum);
				moves.add(board.getNextToken(rNum, false));
				setFinished(true);
			}
		}
		setFinished(false);
	}
	
	public void intermediateMode() {
		
	}
	
	public void hardMode () {
		while (!isFinished()){
			
			rNum = random.nextInt(7);

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

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
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
	

	
}
