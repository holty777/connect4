import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class Connect4Panel extends JPanel implements ActionListener{
	
	private MainWindow mw;
	private Board board;
	private boolean paintBoard;
	private int xPos;
	private int yPos;
	private int tempx;
	private int tempy;
	private ArrayList<Integer> xP;
	private ArrayList<Integer> yP;
	
	/**
	 * Constructor for a Connect4 panel
	 */
	public Connect4Panel(MainWindow mw) {
		this.mw = mw;
		this.board = new Board();
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.paintBoard = true;
        this.xPos = 0;
        this.yPos = 0;
        this.tempx = 0;
        this.tempy = 0;
        this.xP = new ArrayList<Integer>();
        this.yP = new ArrayList<Integer>();

    }

    /**
     * Set the size of the panel
     */
	public Dimension getPreferredSize() {
        return new Dimension(800,800);
    }

	/**
	 * Method to paint the board on the screen
	 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // convert the graphics component into graphics 2D
        Graphics2D g2 = (Graphics2D) g;
        paintBoard(g2);
 
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

    public void paintBoard(Graphics g){
    	 // draw grid lines
        for (int i = 0; i < 8; i++) {
        	((Graphics2D) g).setStroke(new BasicStroke(1));
        	g.setColor(Color.RED);
            g.drawLine(110 + i*80, 110, 110 + i*80, 590);
            for (int j = 0; j < 7; j++) {
            	g.setColor(Color.PINK);
            	g.drawLine(110, 110 + j*80, 670, 110 + j*80);
            }
        }
        
        
    }
    
    public void paintAToken(Graphics g){
    	
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.BLACK);
    	g2.fillOval(120 + getxPos()*80, 520 - getyPos()*80, 60, 60);
    }
    
    public void paintBToken(Graphics g){
    	
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.RED);
    	g2.fillOval(120 + getxPos()*80, 520 - getyPos()*80, 60, 60);
    }
    /*
    public void paintPlayer(Graphics g) {
    	Graphics2D g
    }
    */
    @Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mw.getToken(0)){
			if (board.isLegal(0)){
				tempx = 0;
				tempy = board.getNextToken(tempx);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy);
			} else {
				//do something (print error message? not sure)
			}
		} else if (e.getSource() == mw.getToken(1)){
			if (board.isLegal(1)){
				tempx = 1;
				tempy = board.getNextToken(tempx);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy);
			} else {
				//do something (print error message? not sure)
			}
			
		} else if (e.getSource() == mw.getToken(2)){
			if (board.isLegal(2)){
				tempx = 2;
				tempy = board.getNextToken(tempx);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy);
			} else {
				//do something (print error message? not sure)
			}
			
		} else if (e.getSource() == mw.getToken(3)){
			if (board.isLegal(3)){
				tempx = 3;
				tempy = board.getNextToken(tempx);
				xP.add(tempx);
				yP.add(tempy);

				board.addToken(tempx, tempy);
			} else {
				//do something (print error message? not sure)
			}
			
		} else if (e.getSource() == mw.getToken(4)){
			if (board.isLegal(4)){
				tempx = 4;
				tempy = board.getNextToken(tempx);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy);
			} else {
				//do something (print error message? not sure)
			}
			
		} else if (e.getSource() == mw.getToken(5)){
			if (board.isLegal(5)){
				tempx = 5;
				tempy = board.getNextToken(tempx);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy);
			} else {
				//do something (print error message? not sure)
			}
			
		} else if (e.getSource() == mw.getToken(6)){
			if (board.isLegal(6)){
				tempx = 6;
				tempy = board.getNextToken(tempx);
				xP.add(tempx);
				yP.add(tempy);
				
				board.addToken(tempx, tempy);
			} else {
				//do something (print error message? not sure)
			}
			
		} else if (e.getSource() == mw.getRestart()){
			board = new Board();
			xP.clear();
			yP.clear();
		}
		
		if (!(e.getSource() == mw.getRestart())){
			//change the ad
		}
		
		repaint();
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

}
