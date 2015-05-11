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
	private Integer[][] board;
	private boolean paintBoard;
	private boolean paintToken;
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
        setBorder(BorderFactory.createLineBorder(Color.black));
        board = new Integer[7][6];
        this.paintBoard = true;
        this.paintToken = false;
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
        
        // finish later
        //get XCoords/YCoords from another function
        System.out.println(paintToken);
        if (paintToken){
        	for (Integer i : xP){
        		xPos = xP.get(i);
        		yPos = yP.get(i);
        		paintToken(g);
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
    
    public void paintToken(Graphics g){
    	
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.BLACK);
    	g2.fillOval(120 + getxPos()*80, 520 - getyPos()*80, 60, 60);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mw.getToken(0)){
			setPaintToken(true);
			//change the way to get x and make it dynamically change each
			//time we call it + do a checkislegal move in another function
			tempx = 0;
			tempy = 0;
			xP.add(tempx);
			yP.add(tempy);
		} else if (e.getSource() == mw.getToken(1)){
			setPaintToken(true);
			tempx = 1;
			tempy = 0;
			xP.add(tempx);
			yP.add(tempy);
		} else if (e.getSource() == mw.getToken(2)){
			setPaintToken(true);
			tempx = 2;
			tempy = 0;
			xP.add(tempx);
			yP.add(tempy);
		} else if (e.getSource() == mw.getToken(3)){
			setPaintToken(true);
			tempx = 3;
			tempy = 0;
			xP.add(tempx);
			yP.add(tempy);
		} else if (e.getSource() == mw.getToken(4)){
			setPaintToken(true);
			tempx = 4;
			tempy = 0;
			xP.add(tempx);
			yP.add(tempy);
		} else if (e.getSource() == mw.getToken(5)){
			setPaintToken(true);
			tempx = 5;
			tempy = 0;
			xP.add(tempx);
			yP.add(tempy);
		} else if (e.getSource() == mw.getToken(6)){
			setPaintToken(true);
			tempx = 6;
			tempy = 0;
			xP.add(tempx);
			yP.add(tempy);
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
	 * @return the paintToken
	 */
	public boolean isPaintToken() {
		return paintToken;
	}

	/**
	 * @param paintBoard the paintBoard to set
	 */
	public void setPaintToken(boolean paintToken) {
		this.paintToken = paintToken;
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
