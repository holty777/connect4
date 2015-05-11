import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainWindow {
	
	private JFrame mainFrame;
	private Connect4Panel connect4Panel;
	private JButton newGameButton;
	private JButton newTestButton;

	/**
	 * Method to bootstrap the main frame
	 * @param args
	 */
	public static void main(String[] args) {
		final MainWindow mw = new MainWindow();
		
		// display the main window in a different thread.
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mw.display();
            }
        });
	}
	
	/**
	 * Constructor for the main window.
	 */
	public MainWindow() {
		mainFrame = new JFrame("Soduku GUI Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the connect4 panel
		connect4Panel = new Connect4Panel();
        connect4Panel.setLayout(null);
		
		// Create a new button and add the action listener.
//		newGameButton = new JButton("New Game");
//      newGameButton.setBounds(150, 375, 150, 50);
//		newGameButton.addActionListener(connect4Panel);
//		connect4Panel.add(newGameButton);
		
		// Or do it through an anonymous inner class
		newGameButton = new JButton(new AbstractAction("New Game") {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        connect4Panel.generateBoard();
		        connect4Panel.repaint();
		    }
			
			
		});
		newGameButton.setBounds(250,375,150,50);
		connect4Panel.add(newGameButton);

		newTestButton = new JButton(new AbstractAction("Add Token") {
			@Override
		    public void actionPerformed(ActionEvent e) {
				connect4Panel.setPaintToken(true);
		        connect4Panel.setxPos(1);
		        connect4Panel.setyPos(1);
		        connect4Panel.repaint();
				connect4Panel.setPaintToken(false);
		    }
		});
		
		newTestButton.setBounds(50,375,150,50);
        connect4Panel.add(newTestButton);
	}

	/**
	 * Method to display the main window
	 */
	private void display() {

		mainFrame.getContentPane().add(connect4Panel);
		mainFrame.pack();
        mainFrame.setVisible(true);
	}

}
