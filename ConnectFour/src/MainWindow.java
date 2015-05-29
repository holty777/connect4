import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;


public class MainWindow {
	
	private JFrame mainFrame;
	private Connect4Panel connect4Panel;
	private ArrayList<JPanel> tokens;
	private JPanel outsidePanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel experiment;
    private JPanel scores;
	private JButton restart;
	private JButton pvp;
	private JButton mvp;
	private JRadioButton easy;
	private JRadioButton hard;
	private ButtonGroup bg;
    GridLayout experimentLayout;
    GridBagLayout leftSideLayout;
    GridBagConstraints con;


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
		experimentLayout = new GridLayout(7,7);
		leftSideLayout = new GridBagLayout();
		leftPanel.setLayout(leftSideLayout);
		rightPanel.setLayout(leftSideLayout);
		experiment.setLayout(experimentLayout);
		mainFrame = new JFrame("Connect 4 GUI Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the connect4 panel
		connect4Panel = new Connect4Panel(this);
        connect4Panel.setLayout(experimentLayout);
        con = new GridBagConstraints();  
		
		//create the rightPanel
		JLabel pic = new JLabel(new ImageIcon("src/logo.png"));
		rightPanel.add(pic);
		
        //Creates outside Panel
        outsidePanel = new JPanel();
        
        // create the leftPanel
  		restart = new JButton("Restart");		
  		pvp =  new JButton ("Player VS Player");
  		mvp = new JButton ("Player VS Computer");
  		easy = new JRadioButton ("Easy Mode");
  		easy.setSelected(true);
  		hard = new JRadioButton ("Hard Mode");
       
        //create token buttons 
        tokens = new ArrayList<JPanel>(7);
        createTopButtons();
        createButtonGroup();
        createLeftButtons();
        
        outsidePanel.add(leftPanel);
        outsidePanel.add(connect4Panel);
        outsidePanel.add(rightPanel);
	}


	/**
	 * @return the mainFrame
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * @param mainFrame the mainFrame to set
	 */
	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * @return the connect4Panel
	 */
	public Connect4Panel getConnect4Panel() {
		return connect4Panel;
	}

	/**
	 * @param connect4Panel the connect4Panel to set
	 */
	public void setConnect4Panel(Connect4Panel connect4Panel) {
		this.connect4Panel = connect4Panel;
	}

	/**
	 * @return the tokens
	 */
	public ArrayList<JPanel> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens the tokens to set
	 */
	public void setTokens(ArrayList<JPanel> tokens) {
		this.tokens = tokens;
	}

	/**
	 * @return the outsidePanel
	 */
	public JPanel getOutsidePanel() {
		return outsidePanel;
	}

	/**
	 * @param outsidePanel the outsidePanel to set
	 */
	public void setOutsidePanel(JPanel outsidePanel) {
		this.outsidePanel = outsidePanel;
	}

	/**
	 * @return the leftPanel
	 */
	public JPanel getLeftPanel() {
		return leftPanel;
	}

	/**
	 * @param leftPanel the leftPanel to set
	 */
	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	/**
	 * @return the rightPanel
	 */
	public JPanel getRightPanel() {
		return rightPanel;
	}

	/**
	 * @param rightPanel the rightPanel to set
	 */
	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	/**
	 * @return the experiment
	 */
	public JPanel getExperiment() {
		return experiment;
	}

	/**
	 * @param experiment the experiment to set
	 */
	public void setExperiment(JPanel experiment) {
		this.experiment = experiment;
	}

	/**
	 * @return the scores
	 */
	public JPanel getScores() {
		return scores;
	}

	/**
	 * @param scores the scores to set
	 */
	public void setScores(JPanel scores) {
		this.scores = scores;
	}

	/**
	 * @return the restart
	 */
	public JButton getRestart() {
		return restart;
	}

	/**
	 * @param restart the restart to set
	 */
	public void setRestart(JButton restart) {
		this.restart = restart;
	}

	/**
	 * @return the pvp
	 */
	public JButton getPvp() {
		return pvp;
	}

	/**
	 * @param pvp the pvp to set
	 */
	public void setPvp(JButton pvp) {
		this.pvp = pvp;
	}

	/**
	 * @return the mvp
	 */
	public JButton getMvp() {
		return mvp;
	}

	/**
	 * @param mvp the mvp to set
	 */
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

	/**
	 * @return the bg
	 */
	public ButtonGroup getBg() {
		return bg;
	}

	/**
	 * @param bg the bg to set
	 */
	public void setBg(ButtonGroup bg) {
		this.bg = bg;
	}

	/**
	 * @return the experimentLayout
	 */
	public GridLayout getExperimentLayout() {
		return experimentLayout;
	}

	/**
	 * @param experimentLayout the experimentLayout to set
	 */
	public void setExperimentLayout(GridLayout experimentLayout) {
		this.experimentLayout = experimentLayout;
	}

	/**
	 * @return the leftSideLayout
	 */
	public GridBagLayout getLeftSideLayout() {
		return leftSideLayout;
	}

	/**
	 * @param leftSideLayout the leftSideLayout to set
	 */
	public void setLeftSideLayout(GridBagLayout leftSideLayout) {
		this.leftSideLayout = leftSideLayout;
	}

	/**
	 * @return the con
	 */
	public GridBagConstraints getCon() {
		return con;
	}

	/**
	 * @param con the con to set
	 */
	public void setCon(GridBagConstraints con) {
		this.con = con;
	}

	/**
	 * create the top JPanels
	 */
	public void createTopButtons(){
		int i = 0;
		
		for (int j = 0; j < 7; j++){
			JPanel a = new JPanel();
			a.setSize(110,110);
        	tokens.add(a);
        }

		for (JPanel j : tokens){
		    j.addMouseListener(connect4Panel);
			connect4Panel.add(j);
		}
		
		for(i=0;i<42;i++){
        	connect4Panel.add(new JLabel(new ImageIcon("src/board.png")));
        }

	}
	
	/**
	 * create button group of the difficulty levels
	 */
	public void createButtonGroup(){
		bg = new ButtonGroup();
		
		bg.add(easy);
		bg.add(hard);
	}
	
	/**
	 * creates the left panel buttons
	 */
	public void createLeftButtons(){
		
		restart.addActionListener(connect4Panel);
		pvp.addActionListener(connect4Panel);
		mvp.addActionListener(connect4Panel);
		
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 3;
		con.gridwidth = 2;

        leftPanel.add(pvp, con);
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 4;
		con.gridwidth = 1;

        leftPanel.add(easy, con);
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 1;
		con.gridy = 4;
		con.gridwidth = 1;

        leftPanel.add(hard, con);
        con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 5;
		con.gridwidth = 2;

        leftPanel.add(mvp, con);
        con.fill = GridBagConstraints.PAGE_END;
		con.gridx = 0;
		con.gridy = 6;
		con.gridwidth = 2;

        leftPanel.add(restart, con);
     
	}
	
	/**
	 * @param name the gameMode
	 * @param turn the turn number
	 * @param scoreA the score of player 1
	 * @param scoreB the score of player 2
	 * @param isAi if there is an ai
	 * @param diff the difficulty level
	 */
	public void drawGame(String name, int turn, int scoreA, int scoreB, boolean isAi, String diff){
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
		
		
		scores = new JPanel();
		scores.setPreferredSize(new Dimension(150,80));
		scores.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		if (!isAi){
		scores.add(new JLabel ("<html><center>~ Scores ~</center><br>Player 1 = " + scoreA + "<br>Player 2 = " + scoreB + "</html>"));
		} else {
			scores.add(new JLabel ("<html>Computer Difficulty</font> <br><br></html>", JLabel.CENTER));
			if (diff.equals("easy")){
				scores.add(new JLabel ("Easy Mode"));
			} else {
				scores.add(new JLabel ("Hard Mode"));
			}
			
		}
		
		con.fill = GridBagConstraints.CENTER;
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 2;

		leftPanel.add(scores, con);
		createLeftButtons();

		leftPanel.revalidate();

	}

	/**
	 * @param num the column the mouse was hovered over
	 * @param player the current player
	 */
	public void hover(int num, boolean player){
		tokens.get(num).removeAll();	
		tokens.get(num).revalidate();
		tokens.get(num).repaint();
		if (player){
			tokens.get(num).add(new JLabel(new ImageIcon("src/TokenYellow.png")));
		} else {
			tokens.get(num).add(new JLabel(new ImageIcon("src/TokenRed.png")));
		}
	}
	
	/**
	 * @param num the column the mouse was unhovered from
	 * @param player the current player
	 */
	public void unhover(int num, boolean player){
		tokens.get(num).removeAll();	
		tokens.get(num).revalidate();
		tokens.get(num).repaint();
	}

	/**
	 * @param num the column the mouse was released from
	 * @param player the current player
	 */
	public void released (int num, boolean player){
		tokens.get(num).removeAll();	
		tokens.get(num).revalidate();
		tokens.get(num).repaint();
		if (player){
			tokens.get(num).add(new JLabel(new ImageIcon("src/TokenYellow.png")));
		} else {
			tokens.get(num).add(new JLabel(new ImageIcon("src/TokenRed.png")));
		}
	}
	
	/**
	 * @param i the token number
	 * @return the JPanel in the array
	 */
	public JPanel getToken(int i) {
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
