import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Help extends JFrame {
	
	GridLayout layout1 = new GridLayout(10,2);
	JTabbedPane tabs = new JTabbedPane();
	final static JPanel goal = new JPanel();
	final static JPanel instructions = new JPanel();
	
	
	public Help(){
		this.setTitle("Help");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	void display(){
		this.pack();
        this.setVisible(true);
		this.setSize(400, 400);
		this.setLayout(layout1);
		
		goal.add(new JLabel("The aim of the game is too connect 4 tokens in a row!"));
		goal.add(new JLabel("Good Luck!!"));

		instructions.add(new JLabel("1. Select a game type (Player vs Player/Computer)"));
		instructions.add(new JLabel("2. Press the buttons on top to drop a token down that column"));
		instructions.add(new JLabel("3. To restart the game press the restart button"));
		instructions.add(new JLabel("If you think you are good try playing hard mode!!"));
		
		tabs.addTab("Goal", goal);
		tabs.addTab("Instructions", instructions);




	}

}
