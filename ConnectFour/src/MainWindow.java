import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;


public class MainWindow {
	
	private JFrame mainFrame;
	private Connect4Panel connect4Panel;
	private JPanel outsidePanel;
	private ArrayList<JButton> tokens;
	private JButton restart;
	private JButton pvp;
	private JButton mvp;
	private JRadioButton easy;
	private JRadioButton hard;
	private ButtonGroup bg;
    GridLayout experimentLayout = new GridLayout(7,7);
    GridBagLayout leftSideLayout = new GridBagLayout();
    GridBagConstraints con;
    List<JLabel> holes = new ArrayList<JLabel>();
    private JPanel leftPanel;
    private JPanel rightPanel;
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
		rightPanel = new JPanel();
		leftPanel.setLayout(leftSideLayout);
		rightPanel.setLayout(leftSideLayout);
		experiment.setLayout(experimentLayout);
		mainFrame = new JFrame("Connect 4 GUI Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the connect4 panel
		connect4Panel = new Connect4Panel(this);
        connect4Panel.setLayout(experimentLayout);
        con = new GridBagConstraints();
        
        // create the leftPanel
		restart = new JButton("Restart");		
		pvp =  new JButton ("Player VS Player");
		mvp = new JButton ("Player VS Computer");
		easy = new JRadioButton ("Easy Mode");
		easy.setSelected(true);
		hard = new JRadioButton ("Hard Mode");
		
		//create the rightPanel
		JLabel pic = new JLabel(new ImageIcon("src/Logo.png"));
		rightPanel.add(pic);
        //Creates outside Panel
        outsidePanel = new JPanel();
        outsidePanel.add(leftPanel);
        outsidePanel.add(connect4Panel );
        outsidePanel.add(rightPanel);
        
       
        //create token buttons 
        tokens = new ArrayList<JButton>(7);
        createTopButtons();
        createButtonGroup();
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

	/**
	 * @return the easy
	 */
	public JRadioButton getEasy() {
		return easy;
	}

	/**
	 * @param easy the easy to set
	 */
	public void setEasy(JRadioButton easy) {
		this.easy = easy;
	}

	/**
	 * @return the hard
	 */
	public JRadioButton getHard() {
		return hard;
	}

	/**
	 * @param hard the hard to set
	 */
	public void setHard(JRadioButton hard) {
		this.hard = hard;
	}

	public void createTopButtons(){
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
        	connect4Panel.add(new JLabel(new ImageIcon("src/board.png")));
        }

	}
	
	public void createButtonGroup(){
		bg = new ButtonGroup();
		
		bg.add(easy);
		bg.add(hard);
	}
	
	public void createLeftButtons(){
		
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 2;

        leftPanel.add(pvp, con);
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 2;
		con.gridwidth = 1;

        leftPanel.add(easy, con);
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 1;
		con.gridy = 2;
		con.gridwidth = 1;

        leftPanel.add(hard, con);
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 3;
		con.gridwidth = 2;

        leftPanel.add(mvp, con);
        con.fill = GridBagConstraints.PAGE_END;
		con.gridx = 0;
		con.gridy = 4;
		con.gridwidth = 2;

        leftPanel.add(restart, con);
		restart.addActionListener(connect4Panel);
		pvp.addActionListener(connect4Panel);
		mvp.addActionListener(connect4Panel);
	}
	
	public void drawGame(String name, int turn){
		leftPanel.removeAll();
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.anchor = GridBagConstraints.PAGE_START;
		con.insets = new Insets(10,0,10,0);
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		
		if (name.equals("pvp") && turn % 2 == 0){
			leftPanel.add(new JLabel(new ImageIcon("src/pvp 1.png")), con);	
		} else if (name.equals("pvp") && turn % 2 == 1){
			leftPanel.add(new JLabel(new ImageIcon("src/pvp 2.png")), con);
		} else{
			leftPanel.add(new JLabel(new ImageIcon("src/mvp.png")), con);
		}
	
		createLeftButtons();

		leftPanel.revalidate();

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
