import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  
public class LoginPanel extends JPanel {
	private JFrame theWindow;
	private JPanel topPanel, midPanel, botPanel;
	private JLabel label1, label2;
	private JTextField theText;
	private JButton theButton;
	private MyListener theListener;
	private Voter V;
	private String ID;
	LoginInterface L;
	
	public LoginPanel(String voterFile, LoginInterface L) {
		super();
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,1));
		midPanel = new JPanel();
		midPanel.setLayout(new GridLayout(1,2));
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(1,1));
		
		theButton = new JButton("Submit");	
		theButton.setFont(new Font("Comic Sans", Font.BOLD, 60));
		theListener = new MyListener();
		theButton.addActionListener(new MyListener());
		
		theText = new JTextField();
		theText.setFont(new Font("Comic Sans", Font.BOLD, 60 ));
		theText.addActionListener(theListener);
		
		label1 = new JLabel("Please Log Into the Site");
		label1.setFont(new Font("Comic Sans", Font.BOLD, 60 ));
		label2 = new JLabel("Voter ID:");
		label2.setFont(new Font("Comic Sans", Font.BOLD, 60 ));
		
		topPanel.add(label1);
		midPanel.add(label2);
		midPanel.add(theText);
		botPanel.add(theButton);
		
		theWindow = new JFrame("LoginPanel");
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setLayout(new GridLayout(3,1));
		theWindow.add(topPanel);
		theWindow.add(midPanel);
		theWindow.add(botPanel);
		theWindow.pack();
		theWindow.setVisible(true);
	}
	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Component theEventer = (Component) e.getSource();
			if(theEventer == theButton) {
				String textEntered = theText.getText();
				V = Voter.getVoter("voters.txt", textEntered);
				ID = V.getId();
				System.out.println(ID);
				if(textEntered.equals(ID)) {
					if(V.hasVoted()) {
					JOptionPane.showMessageDialog(theWindow, V.getName() + ", you have already voted!");
					}
					else {//want this
						JOptionPane.showMessageDialog(theWindow, V);
						JOptionPane.showMessageDialog(theWindow, "now voting...");
						V.vote();//difference between this?
						JOptionPane.showMessageDialog(theWindow, V);
						JOptionPane.showMessageDialog(theWindow, "Saving back to the file...");
						Voter.saveVoter("voters.txt", V);//and this?
						L.setVoter(V);//problem
						System.out.println("HERE");
					}
				}
				else {//y doesn't it ever go here? dangling else?
					JOptionPane.showMessageDialog(theWindow, "You are not registered, sorry!");
				}
			}
			else if(theEventer == theText) {
				String textEntered = theText.getText();
				V = Voter.getVoter("voters.txt", textEntered);
				System.out.println(V + "***");
				theText.setText("");
				theWindow.pack();
				JOptionPane.showMessageDialog(theWindow, "TextField");
			}
		}
	}
}
