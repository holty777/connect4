import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainWindow {
	
	private JFrame mainFrame;
	private Connect4Panel connect4Panel;
	private ArrayList<JButton> tokens;
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
		mainFrame = new JFrame("Connect 4 GUI Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the connect4 panel
		connect4Panel = new Connect4Panel(this);
        connect4Panel.setLayout(null);
		
        tokens = new ArrayList<JButton>(7);
        
        for (int j = 0; j < 7; j++){
        	JButton a = new JButton("O");
        	tokens.add(a);
        }
        
        int i = 0;
        
        for (JButton j : tokens){
		    j.addActionListener(connect4Panel);
			j.setBounds(130 + i*80, 50,40,40);
			connect4Panel.add(j);
			i++;
		}
		

		newTestButton = new JButton("Test");
		newTestButton.setBounds(350,650,150,50);
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

	public JButton getToken(int i) {
		return tokens.get(i);
	}
}
