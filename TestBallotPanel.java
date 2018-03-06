// CS 401 Fall 2017 Assignment 4
// Driver program to test BallotPanel class.  Your BallotPanel class must work with this
// program with no changes.  To see how the execution should work, see the file
// TestBallot.htm
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TestBallotPanel implements VoteInterface{
	// You must implement the BallotPanel class.  For some details on the required
	// functionality see Assignment 4 and TestBallot.htm
	private BallotPanel ballots;
	private JButton startButton;
	private JFrame theWindow;
	private String ballotFile;
	private ActionListener startListener;

	public TestBallotPanel(String bFile){
		ballotFile = bFile;
		// Constructor takes a String ballot file name and a reference back to the
		// TestBallotPanel object.  This back reference allows the BallotPanel object
		// to signal back to this panel when it is "done" with the voting.  This 
		// back reference is implemented via the VoteInterface interface.  See also
		// some comments in file VoteInterface.java
		//
		// The constructor should parse the ballot file make a new graphical ballot
		// for each office and also a new results file for each office.  See details
		// in TestBallotPanel.htm.  Because this will create a new file for each office,
		// you only want to create this BallotPanel one time when the program is first
		// started.  However, (as demonstrated here) the panel can be shown and hidden
		// and can be reset for each voter.
		ballots = new BallotPanel(ballotFile, this);
		startButton = new JButton("See your ballots");
		startButton.setFont(new Font("Serif", Font.BOLD, 30));
		startButton.setEnabled(true);
		startListener = new StartListenType();
		startButton.addActionListener(startListener);
		theWindow = new JFrame("BallotPanel Test Program");
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setLayout(new FlowLayout());
		theWindow.add(startButton);
		theWindow.pack();
		theWindow.setVisible(true);
		// Note that the BallotPanel has not been added to the window yet.  This will
		// only happen in response to a click of the startButton.
	}
	// This method will be called FROM the BallotPanel to indicate that the voter has
	// finished voting.  This should be called after the votes have been finalized and
	// the results files have been updated.  Note that we are simply removing the
	// BallotPanel from the window and adding back the startButton.  To see how this
	// works see TestBallotPanel.htm
	public void voted(){
		theWindow.remove(ballots);
		theWindow.add(startButton);
		theWindow.pack();
	}
	private class StartListenType implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// User has clicked on the startButton, so show the BallotPanel by adding
			// it to theWindow and call the resetBallots() method to get it ready for
			// the next voter.
			if (e.getSource() == startButton){
				theWindow.remove(startButton);
				theWindow.add(ballots);
				ballots.resetBallots();
				theWindow.pack();
			}
		}
	}
	// A "one line" main here just creates a Lab8 object
	public static void main(String [] args) throws IOException{
		new TestBallotPanel(args[0]);
	}
}