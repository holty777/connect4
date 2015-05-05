import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class Connect4Panel extends JPanel implements ActionListener {
	
	private Integer[][] board;

	/**
	 * Constructor for a soduku panel
	 */
	public Connect4Panel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        board = new Integer[9][9];
        generateBoard();
    }

	/**
	 * Method to generate a new board
	 * NOTE: this isn't a real/valid soduku board.
	 */
    public void generateBoard() {
    	Random r = new Random();
    	// Generate a completed game
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				boolean numberPlaced = false;
				while (numberPlaced == false) {
					int newNum = r.nextInt(9) + 1;
					// check to see if we can place this number.
					// check along the line
					// check up the column
					// check surroundings. 
					board[i][j] = newNum;
					numberPlaced = true;
				}
				
			}
		}
		// randomly remove certain pieces
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// one in every 4 pieces should be removed.
				if (r.nextInt(4) % 4 == 0) {
					board[i][j] = 0;
				}
			}
		}
	}

    /**
     * Set the size of the panel
     */
	public Dimension getPreferredSize() {
        return new Dimension(450,450);
    }

	/**
	 * Method to paint the board on the screen
	 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // convert the graphics component into graphics 2D
        Graphics2D g2 = (Graphics2D) g;
        
        // draw grid lines
        for (int i = 0; i < 8; i++) {
        	g2.setStroke(new BasicStroke(1));
        	/*
        	if (i % 3 == 0) {
        		g2.setStroke(new BasicStroke(2));
        	}
        	*/
        	g2.setColor(Color.RED);
            g2.drawLine(50 + i*50, 50, 50 + i*50, 350);
            for (int j = 0; j < 7; j++) {
            	g2.setColor(Color.PINK);
            	g2.drawLine(50, 50 + j*50, 400, 50 + j*50);
            	g2.setColor(Color.BLACK);
            	g2.fillOval(52, 52, 46, 46);
            }
        }
        
        for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (board[i][j] > 0)
					g.drawString(board[i][j].toString(),75 + i*50, 85 + j*50);
			}
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// when an action happens regenerate the board
		generateBoard();
		// now repaint the board on the screen
		repaint();
	}  

}