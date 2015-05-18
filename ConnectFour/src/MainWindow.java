import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MainWindow {
	
	private JFrame mainFrame;
	private Connect4Panel connect4Panel;
	private JPanel outsidePanel;
	private ArrayList<JButton> tokens;
	private JButton restart;
	private JButton pvp;
	private JButton mvp;
    GridLayout experimentLayout = new GridLayout(7,7);
    GridLayout leftSideLayout = new GridLayout(5,2);
    GridLayout outsideLayout = new GridLayout(1,1);
    List<JLabel> holes = new ArrayList<JLabel>();
    private JPanel leftPanel;
    private JPanel experiment;

	/**
	 * Method to bootstrap the main frame
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
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
	 * @throws IOException 
	 */
	public MainWindow() throws IOException {
		experiment = new JPanel();
		leftPanel = new JPanel();
		leftPanel.setLayout(leftSideLayout);
		experiment.setLayout(experimentLayout);
		mainFrame = new JFrame("Connect 4 GUI Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the connect4 panel
		connect4Panel = new Connect4Panel(this);
        connect4Panel.setLayout(experimentLayout);
		restart = new JButton("Restart");
		pvp =  new JButton ("New Game PVP");
		mvp = new JButton ("New Game Computer");
        //Creates outside Panel
        //Adds buttons on left side
        outsidePanel = new JPanel();
        outsidePanel.add(leftPanel);
        outsidePanel.add(connect4Panel);
        
        //Add buttons to Grid
      
        //create token buttons 
        tokens = new ArrayList<JButton>(7);
        createButtons();
        createLeftButtons();
		
	}
	
	public ArrayList<JButton> getTokens() {
		return tokens;
	}

	public void setTokens(ArrayList<JButton> tokens) {
		this.tokens = tokens;
	}

	public JButton getRestart() {
		return restart;
	}
	
	public void setRestart(JButton restart) {
		this.restart = restart;
	}

	public JButton getPvp() {
		return pvp;
	}

	public void setPvp(JButton pvp) {
		this.pvp = pvp;
	}

	public JButton getMvp() {
		return mvp;
	}

	public void setMvp(JButton mvp) {
		this.mvp = mvp;
	}

	public void createButtons(){
		int i = 0;
		
		for (int j = 0; j < 7; j++){
			JButton a = new JButton("");
        	tokens.add(a);
        }
	 
		for (JButton j : tokens){
		    j.addActionListener(connect4Panel);
			j.setBounds(130 + i*80, 50,40,40);
			connect4Panel.add(j);
			i++;
		}
		
		for(i=0;i<42;i++){
        	connect4Panel.add(new JLabel(new ImageIcon("src/pic1.png")));
        }
	
		
	}
	public void createLeftButtons(){
        leftPanel.add(pvp);
        leftPanel.add(mvp);
        leftPanel.add(restart);
        
		restart.addActionListener(connect4Panel);
		pvp.addActionListener(connect4Panel);
		mvp.addActionListener(connect4Panel);
	}
	
	public void drawGame(String name){
		if (name.equals("pvp")){
			leftPanel.removeAll();
			leftPanel.add(new JLabel(new ImageIcon("src/pvp.png")));
			createLeftButtons();
			
			leftPanel.revalidate();
		} else {
			leftPanel.removeAll();
			leftPanel.add(new JLabel(new ImageIcon("src/mvp.png")));
			createLeftButtons();
			
			leftPanel.revalidate();
		}
		
	}
	public JButton getToken(int i) {
		return tokens.get(i);
	}

	/**
	 * Method to display the main window
	 */
	private void display() {

		mainFrame.getContentPane().add(outsidePanel);
		mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
	}


}
