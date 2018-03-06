// CS 0401 Fall 2017
// This program tests the LoginPanel class, which is required for Assignment 4
// Note how it is used and also note the snapshots shown in TestLogin.htm
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TestLoginPanel implements LoginInterface{
	private JFrame theWindow;
	private String vFileName;
	private Voter P;
	private LoginPanel logPan;
	private JButton login;
	
	public TestLoginPanel(){
		theWindow = new JFrame("Testing Login Panel");
		theWindow.setLayout(new GridLayout(1,2));
		vFileName = "voters.txt";
		
		login = new JButton("Click to Login");
		login.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 30));
		login.addActionListener(new LoginListener());
		
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.add(login);
		theWindow.pack();
		theWindow.setVisible(true);
	}
	// This method is from LoginInterface and will be called from the
	// LoginPanel.  The idea is that information obtained from LoginPanel
	// will be encapsulated within that Panel, and this method enables the
	// Voter to be passed back to this program.  Once this method is called
	// the LoginPanel is removed from the display.  Then we can use the
	// returned Voter object however we see fit.  In this case we simply
	// call the vote() method and save it back to the file.  However, in
	// your main program you will first have the Voter make the ballot
	// choices and actually vote.
	public void setVoter(Voter newVoter){
		P = newVoter;
		theWindow.remove(logPan);
		theWindow.add(login);
		theWindow.pack();
		JOptionPane.showMessageDialog(theWindow, "Voter: " + P.toString());
		JOptionPane.showMessageDialog(theWindow, "Now voting...");
		P.vote();
		JOptionPane.showMessageDialog(theWindow, "Voter: " + P.toString());
		JOptionPane.showMessageDialog(theWindow, "Saving back to file...");
		Voter.saveVoter(vFileName, P);
	}	
	// When the login button is clicked we add the LoginPanel to the window
	// and allow it to "do its job".  When it is finished, it will pass the
	// new Player back with the setPlayer method.  For details on the expected
	// functionality of LoginPanel, see the snapshot file TestLogin.htm
	private class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// Note that the TestLoginPanel object is passed as an argument to
			// the LoginPanel.  Since TestLoginPanel implements LoginInterface
			// it can be accessed in that way from LoginPanel.  In other words,
			// the header for this constructor should be:
			// public LoginPanel (String voterFile, LoginInterface L)
			// In your LoginPanel class you should store this reference as an
			// instance variable since you will need it to call the setVoter()
			// method once the login is complete.
			logPan = new LoginPanel(vFileName, TestLoginPanel.this);
			theWindow.remove(login);
			theWindow.add(logPan);
			theWindow.pack();
		}
	}
	public static void main(String [] args){
		new TestLoginPanel();	
	}
}