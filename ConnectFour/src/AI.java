import java.util.ArrayList;
import java.util.Random;


public class AI {
	
	private Board board;
	private int playerNum;
	private String diff;
	private ArrayList<Integer> moves;
	private Random random;
	private int rNum;
	private boolean finished;
	
	public AI (String diff, Board board) {
		this.board = board;
		this.setDiff(diff);
		this.moves = new ArrayList<Integer>();
		random = new Random();
		
	}
	
	public ArrayList<Integer> placeToken () {
		if (diff.equals("easy")){
			easyMode();
			
		}
		
		return moves;
	}
	
	public void easyMode () {
		while (!isFinished()){
			
			rNum = random.nextInt(6);

			if (board.isLegal(rNum)){
				moves.add(rNum);
				moves.add(board.getNextToken(rNum));
				setFinished(true);
			}
		}
		setFinished(false);
	}
	
	public void intermediateMode() {
		
	}
	
	public void hardMode () {
		
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
	 * @return the diff
	 */
	public String getDiff() {
		return diff;
	}

	/**
	 * @param diff the diff to set
	 */
	public void setDiff(String diff) {
		this.diff = diff;
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
