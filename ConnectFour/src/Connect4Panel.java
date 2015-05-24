import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Connect4Panel extends JPanel implements ActionListener{
	
	private MainWindow mw;
	private Board board;
	private boolean paintBoard;
	private boolean player;
	private boolean ai;
	private int xPos;
	private int yPos;
	private int tempx;
	private int tempy;
	private int turn;
	private ArrayList<Integer> AIMove;
	private ArrayList<Integer> xP;
	private ArrayList<Integer> yP;
	private AI aiPlayer;
	
	/**
	 * Constructor for a Connect4 panel
	 * @throws IOException 
	 */
	public Connect4Panel(MainWindow mw) throws IOException {
		this.mw = mw;
        this.paintBoard = true;
        this.player = true;
        this.ai = false;
		this.board = new Board();
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.xPos = 0;
        this.yPos = 0;
        this.tempx = 0;
        this.tempy = 0;
        this.setTurn(0);
        this.AIMove = new ArrayList<Integer>();
        this.xP = new ArrayList<Integer>();
        this.yP = new ArrayList<Integer>();
        

    }

    /**
     * Set the size of the panel
     */
	public Dimension getPreferredSize() {
        return new Dimension(770,770);
    }

	/**
	 * Method to paint the board on the screen
	 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        
        if (isAi()){
        	mw.drawGame("mvp", turn);
        } else {
        	mw.drawGame("pvp", turn);
        }
 
        //this makes sure the board keeps the old drawn graphics too
        for (int i = 0; i < xP.size(); i++){
        	xPos = xP.get(i);
        	yPos = yP.get(i);
        	if (i % 2 == 0){ //cheat way to get alternating players (change maybe)
        		paintAToken(g);
        	} else {
        		paintBToken(g);
        	}
        }

    }
    
    public void paintTest(Graphics g){
    	Graphics2D g2 = (Graphics2D) g;
    	g2.fillRect(0, 0, 10, 10);
    }
    
    public void paintAToken(Graphics g){
    	
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.YELLOW);
    	g2.fillRect(15 + getxPos()*109, 670 - getyPos()*110, 90, 90);
    }
    
    public void paintBToken(Graphics g){
    	
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.RED);
    	g2.fillRect(15 + getxPos()*109, 670 - getyPos()*110, 90, 90);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	

		if (e.getSource() == mw.getToken(0)){
			if (board.isLegal(0)){
				tempx = 0;
				tempy = board.getNextToken(tempx, player);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy, player);
				incTurn();
			}
		} else if (e.getSource() == mw.getToken(1)){
			if (board.isLegal(1)){
				tempx = 1;
				tempy = board.getNextToken(tempx, player);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy, player);
				incTurn();
			}
		} else if (e.getSource() == mw.getToken(2)){
			if (board.isLegal(2)){
				tempx = 2;
				tempy = board.getNextToken(tempx, player);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy, player);
				incTurn();
			}
		} else if (e.getSource() == mw.getToken(3)){
			if (board.isLegal(3)){
				tempx = 3;
				tempy = board.getNextToken(tempx, player);
				xP.add(tempx);
				yP.add(tempy);

				board.addToken(tempx, tempy, player);
				incTurn();
			}
		} else if (e.getSource() == mw.getToken(4)){
			if (board.isLegal(4)){
				tempx = 4;
				tempy = board.getNextToken(tempx, player);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy, player);
				incTurn();
			}
		} else if (e.getSource() == mw.getToken(5)){
			if (board.isLegal(5)){
				tempx = 5;
				tempy = board.getNextToken(tempx, player);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy, player);
				incTurn();
			}
		} else if (e.getSource() == mw.getToken(6)){
			if (board.isLegal(6)){
				tempx = 6;
				tempy = board.getNextToken(tempx, player);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy, player);
				incTurn();
			}
		} else if (e.getSource() == mw.getRestart()){
			clearEverything();
			setVisible();
		} else if (e.getSource() == mw.getPvp()){
			clearEverything();
			setAi(false);
			setVisible();
		} else if (e.getSource() == mw.getMvp()){
			clearEverything();
			setAi(true);
			if (mw.getEasy().isSelected()){
				aiPlayer = new AI("easy", board);
			} else if (mw.getHard().isSelected()){
				aiPlayer = new AI("hard", board);
			}
			setVisible();
		}
		
		if (isAi() && (getTurn() % 2 == 1)){
			AIMove = aiPlayer.placeToken();
			
			tempx = AIMove.get(0);
			tempy =	AIMove.get(1);
			
			xP.add(tempx);
			yP.add(tempy);
			
			board.addToken(tempx, tempy, player);
			
			AIMove.clear();
			aiPlayer.clearMoves();
			incTurn();
		}
		
		if (!(e.getSource() == mw.getRestart()) && !(e.getSource() == mw.getPvp())
				&& !(e.getSource() == mw.getMvp())){
				changePlayer();
		}
		
		repaint();
		if (board.isBoardFull(getTurn())){
			showDraw();
		} else if (board.weHaveAWinner(tempy, tempx)){
			
			showWin();
		}
	}
    
	/**
	 * @return the paintBoard
	 */
	public boolean isPaintBoard() {
		return paintBoard;
	}

	/**
	 * @param paintBoard the paintBoard to set
	 */
	public void setPaintBoard(boolean paintBoard) {
		this.paintBoard = paintBoard;
	}
	
	/**
	 * @return the current player
	 */
	public boolean isPlayer() {
		return player;
	}

	/**
	 * @param player
	 */
	public void setPlayer(boolean player) {
		this.player = player;
	}

	/**
	 * @return if ai is present in the game
	 */
	public boolean isAi() {
		return ai;
	}

	public void setAi(boolean ai) {
		this.ai = ai;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}  
	
	
	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * increments the turn number
	 */
	public void incTurn(){
		this.setTurn(this.getTurn() + 1);
	}
	
	public void changePlayer(){
		if (player){
			this.player = false;
		} else if (!player){
			this.player = true;
		}
	}
	
	public void showDraw(){
		final JFrame parent = new JFrame();
        
		JOptionPane.showMessageDialog(parent, "It is a draw!");
		setInvisible();
	}

	public void showWin(){
		final JFrame parent = new JFrame();
        
		if (isPlayer()){
			JOptionPane.showMessageDialog(parent, "Player 2 Wins!!!");
		} else {
			JOptionPane.showMessageDialog(parent, "Player 1 Wins!!!");

		}
		setInvisible();
	}
	
	public void setInvisible(){
		for (JButton b : mw.getTokens()){
			b.setVisible(false);
		}
	}
	
	public void setVisible(){
		for (JButton b : mw.getTokens()){
			b.setVisible(true);
		}
	}
	
	
	/**
	 * 
	 */
	public void clearEverything(){
		board = new Board();
		xP.clear();
		yP.clear();
		setTurn(0);
		AIMove.clear();
		if (aiPlayer != null){
			aiPlayer.setBoard(board);
		}
	}
}
