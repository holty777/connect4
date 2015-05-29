import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Connect4Panel extends JPanel implements ActionListener, MouseListener{
	
	private MainWindow mw;
	private Board board;
	private AI aiPlayer;
	private ArrayList<Integer> AIMove;
	private ArrayList<Integer> xP;
	private ArrayList<Integer> yP;
	private BufferedImage tok1;
	private BufferedImage tok2;
	private boolean paintBoard;
	private boolean player;
	private boolean ai;
	private boolean finished;
	private int xPos;
	private int yPos;
	private int tempCol;
	private int tempRow;
	private int turn;
	public int scoreA;
	public int scoreB;
	
	/**
	 * Constructor for a Connect4 panel
	 * @throws IOException 
	 */
	public Connect4Panel(MainWindow mw) throws IOException {
		this.mw = mw;
        this.paintBoard = true;
        this.player = true;
        this.ai = false;
        this.finished = false;
		this.board = new Board();
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.xPos = 0;
        this.yPos = 0;
        this.tempCol = 0;
        this.tempRow = 0;
        this.setTurn(0);
        this.AIMove = new ArrayList<Integer>();
        this.xP = new ArrayList<Integer>();
        this.yP = new ArrayList<Integer>();
        this.tok1 = ImageIO.read(new File("src/TokenRed.png"));
        this.tok2 = ImageIO.read(new File("src/TokenYellow.png"));
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
        	mw.drawGame("mvp", turn, scoreA, scoreB, isAi(), aiPlayer.getDifficulty());
        } else {
        	mw.drawGame("pvp", turn, scoreA, scoreB, isAi(), "");
        }
        
        //this makes sure the board keeps the old drawn graphics too
        for (int i = 0; i < xP.size(); i++){
        	xPos = xP.get(i);
        	yPos = yP.get(i);
        	if (i % 2 == 0){
        		paintAToken(g);
        	} else {
        		paintBToken(g);
        	}
        }
    }
    
    //drawing player 1 token
    public void paintAToken(Graphics g){
    	
    	Graphics2D g2 = (Graphics2D) g;
  
    	g2.drawImage(tok2, 17 + getxPos()*109, 671 - getyPos()*109, null);
    }
    
    //drawing player 2 token
    public void paintBToken(Graphics g){
    	
    	Graphics2D g2 = (Graphics2D) g;
    	
    	g2.drawImage(tok1, 17 + getxPos()*109, 671 - getyPos()*109, null);
    }

    public void hoverToken(Graphics g, String inst, int num){
      	
    	Graphics2D g2 = (Graphics2D) g;
    	
    	g2.drawImage(tok1, num*109, num*109, null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == mw.getRestart()){
			clearEverything();
			setVisible();
			setFinished(false);
			setPlayer(true);
			clearScore();
		} else if (e.getSource() == mw.getPvp()){
			clearEverything();
			setAi(false);
			setVisible();
			setFinished(false);
			setPlayer(true);
		} else if (e.getSource() == mw.getMvp()){
			clearEverything();
			setAi(true);
			if (mw.getEasy().isSelected()){
				aiPlayer = new AI("easy", board, xP, yP);
			} else if (mw.getHard().isSelected()){
				aiPlayer = new AI("hard", board, xP, yP);
			}
			setVisible();
			setFinished(false);
			setPlayer(true);
			clearScore();
		}

		repaint();

	}
    
    public void mousePressed(MouseEvent e) {
    	if (e.getSource() == mw.getToken(0)){
 			if (board.isLegal(0)){
 				tempCol = 0;
 				tempRow = board.getNextToken(tempCol, player);
 				xP.add(tempCol);
 				yP.add(tempRow);
 				
 				board.addToken(tempCol, tempRow, player);
 				incTurn();
 				changePlayer();
 			}
 		} else if (e.getSource() == mw.getToken(1)){
 			if (board.isLegal(1)){
 				tempCol = 1;
 				tempRow = board.getNextToken(tempCol, player);
 				xP.add(tempCol);
 				yP.add(tempRow);
 				
 				board.addToken(tempCol, tempRow, player);
 				incTurn();
 				changePlayer();
 			}
 		} else if (e.getSource() == mw.getToken(2)){
 			if (board.isLegal(2)){
 				tempCol = 2;
 				tempRow = board.getNextToken(tempCol, player);
 				xP.add(tempCol);
 				yP.add(tempRow);
 				
 				board.addToken(tempCol, tempRow, player);
 				incTurn();
 				changePlayer();
 			}
 		} else if (e.getSource() == mw.getToken(3)){
 			if (board.isLegal(3)){
 				tempCol = 3;
 				tempRow = board.getNextToken(tempCol, player);
 				xP.add(tempCol);
 				yP.add(tempRow);

 				board.addToken(tempCol, tempRow, player);
 				incTurn();
 				changePlayer();
 			}
 		} else if (e.getSource() == mw.getToken(4)){
 			if (board.isLegal(4)){
 				tempCol = 4;
 				tempRow = board.getNextToken(tempCol, player);
 				xP.add(tempCol);
 				yP.add(tempRow);
 				
 				board.addToken(tempCol, tempRow, player);
 				incTurn();
 				changePlayer();
 			}
 		} else if (e.getSource() == mw.getToken(5)){
 			if (board.isLegal(5)){
 				tempCol = 5;
 				tempRow = board.getNextToken(tempCol, player);
 				xP.add(tempCol);
 				yP.add(tempRow);
 				
 				board.addToken(tempCol, tempRow, player);
 				incTurn();
 				changePlayer();
 			}
 		} else if (e.getSource() == mw.getToken(6)){
 			if (board.isLegal(6)){
 				tempCol = 6;
 				tempRow = board.getNextToken(tempCol, player);
 				xP.add(tempCol);
 				yP.add(tempRow);
 				
 				board.addToken(tempCol, tempRow, player);
 				incTurn();
 				changePlayer();
 			}
 		}
    	repaint();

    	 

 		if (getTurn() > 0){
 			if (board.weHaveAWinner(tempRow, tempCol)){
 				showWin();
 				setFinished(true);
 			} else if (board.isBoardFull(getTurn())){
 				showDraw();
 				setFinished(true);
 			}
 		}
 		
 		if (isAi() && (getTurn() % 2 == 1) && !isFinished()){
 			AIMove = aiPlayer.placeToken();
 			
 			tempCol = AIMove.get(0);
 			tempRow = AIMove.get(1);
 			
 			xP.add(tempCol);
 			yP.add(tempRow);
 			
 			board.addToken(tempCol, tempRow, player);
 			
 			AIMove.clear();
 			aiPlayer.clearMoves();
 			incTurn();
 			changePlayer();

 			repaint();
 			
 			if (board.weHaveAWinner(tempRow, tempCol)){
 				showWin();
 			} else if (board.isBoardFull(getTurn())){
 				showDraw();
 			} 
 		}
 		
     }

     public void mouseReleased(MouseEvent e) {
    	 if (e.getSource() == mw.getToken(0)){
      		mw.released(0, player);
   		} else if (e.getSource() == mw.getToken(1)){
   			mw.released(1, player);
   		} else if (e.getSource() == mw.getToken(2)){
   			mw.released(2, player);
   		} else if (e.getSource() == mw.getToken(3)){
   			mw.released(3, player);
   		} else if (e.getSource() == mw.getToken(4)){
   			mw.released(4, player);
   		} else if (e.getSource() == mw.getToken(5)){
   			mw.released(5, player);
   		} else if (e.getSource() == mw.getToken(6)){
   			mw.released(6, player);
   		}
     }

     public void mouseEntered(MouseEvent e) {
    	 if (e.getSource() == mw.getToken(0)){
    		mw.hover(0, player);
 		} else if (e.getSource() == mw.getToken(1)){
 			mw.hover(1, player);
 		} else if (e.getSource() == mw.getToken(2)){
 			mw.hover(2, player);
 		} else if (e.getSource() == mw.getToken(3)){
 			mw.hover(3, player);
 		} else if (e.getSource() == mw.getToken(4)){
 			mw.hover(4, player);
 		} else if (e.getSource() == mw.getToken(5)){
 			mw.hover(5, player);
 		} else if (e.getSource() == mw.getToken(6)){
 			mw.hover(6, player);
 		}
     }

     public void mouseExited(MouseEvent e) {
    	 if (e.getSource() == mw.getToken(0)){
     		mw.unhover(0, player);
  		} else if (e.getSource() == mw.getToken(1)){
  			mw.unhover(1, player);
  		} else if (e.getSource() == mw.getToken(2)){
  			mw.unhover(2, player);
  		} else if (e.getSource() == mw.getToken(3)){
  			mw.unhover(3, player);
  		} else if (e.getSource() == mw.getToken(4)){
  			mw.unhover(4, player);
  		} else if (e.getSource() == mw.getToken(5)){
  			mw.unhover(5, player);
  		} else if (e.getSource() == mw.getToken(6)){
  			mw.unhover(6, player);
  		}
     }

     public void mouseClicked(MouseEvent e) {
    	 
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

	/**
	 * increments the turn number
	 */
	public void incTurn(){
		this.setTurn(this.getTurn() + 1);
	}
	
	public void changePlayer(){
		if (player){
			setPlayer(false);
		} else {
			setPlayer(true);
		}
	}
	
	/**
	 * displays the draw window
	 */
	public void showDraw(){
		final JFrame parent = new JFrame();
        
		JOptionPane.showMessageDialog(parent, "It is a draw!");
		setInvisible();
	}

	/**
	 * displays the winner window
	 */
	public void showWin(){
		final JFrame parent = new JFrame();
        
		if (!isAi()){
			if (isPlayer()){
				JOptionPane.showMessageDialog(parent, "Player 2 Wins!!!");
				incrementScore(player);
			} else {
				JOptionPane.showMessageDialog(parent, "Player 1 Wins!!!");
				incrementScore(player);
			}
		} else {
			if (isPlayer()){
				JOptionPane.showMessageDialog(parent, "Computer Wins!!!");
			} else {
				JOptionPane.showMessageDialog(parent, "Player 1 Wins!!!");
			}
		}
		
		setInvisible();
	}
	
	public void setInvisible(){
		for (JPanel b : mw.getTokens()){
			b.setVisible(false);
		}
	}
	
	public void setVisible(){
		for (JPanel b : mw.getTokens()){
			b.setVisible(true);
		}
	}
	
	/**
	 * increments the score
	 * @param player the current player
	 */
	public void incrementScore(boolean player) {
		if (player == false) {
			scoreA++;
		} else {
			scoreB++;
		}
	}
	
	/**
	 * clears the score
	 */
	public void clearScore(){
		setScoreA(0);
		setScoreB(0);
	}
	
	/**
	 * resets the game back to initial state
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
