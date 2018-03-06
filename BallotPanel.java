import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

import java.awt.event.*;  
public class BallotPanel extends JPanel{
	private JFrame theWindow;
	private JPanel panel1, panel2, panel3, panel4;
	private JLabel punk, movie, phone;
	private JButton voteButton,ramones,clash,blackFlag,x,badBrains,princessBride,deadPoetsSociety,casablanca,iPhone,galaxy,pixel,onePlus;
	private MyListener theListener;
	private Voter V;
	private Scanner fileReader;
	private File ballotFile, resultsFile;
	private FileWriter FW;
	private BufferedWriter BW;
	private PrintWriter PW;
	private int ballotCounter = 0;
	VoteInterface vInterface;
	
	public BallotPanel(String ballotFileName, VoteInterface VI){
		super();
		try{
			vInterface = VI;
			
			panel1 = new JPanel();
			panel1.setLayout(new GridLayout(1,6));
			panel2 = new JPanel();
			panel2.setLayout(new GridLayout(1,4));
			panel3 = new JPanel();
			panel3.setLayout(new GridLayout(1,5));
			panel4 = new JPanel();
			panel4.setLayout(new GridLayout(1,1));
			
			voteButton = new JButton("Cast Your Vote");
			voteButton.addActionListener(new MyListener());
			
			ramones = new JButton("Ramones");
			ramones.addActionListener(new MyListener());
			clash = new JButton("Clash");
			clash.addActionListener(new MyListener());
			blackFlag = new JButton("Black Flag");
			blackFlag.addActionListener(new MyListener());
			x = new JButton("X");
			x.addActionListener(new MyListener());
			badBrains = new JButton("Bad Brains");
			badBrains.addActionListener(new MyListener());
			
			princessBride = new JButton("Princess Bride");
			princessBride.addActionListener(new MyListener());
			deadPoetsSociety = new JButton("Dead Poets Society");
			deadPoetsSociety.addActionListener(new MyListener());
			casablanca = new JButton("Casablanca");
			casablanca.addActionListener(new MyListener());
			
			iPhone = new JButton("Iphone");
			iPhone.addActionListener(new MyListener());
			galaxy = new JButton("Galaxy");
			galaxy.addActionListener(new MyListener());
			pixel = new JButton("Pixel");
			pixel.addActionListener(new MyListener());
			onePlus = new JButton("OnePlus");
			onePlus.addActionListener(new MyListener());
			
			punk = new JLabel("Best Punk");
			movie = new JLabel("Best Movie");
			phone = new JLabel("Best Phone");
			
			panel1.add(punk);
			panel1.add(ramones);
			panel1.add(clash);
			panel1.add(blackFlag);
			panel1.add(x);
			panel1.add(badBrains);
			panel2.add(movie);
			panel2.add(princessBride);
			panel2.add(deadPoetsSociety);
			panel2.add(casablanca);		
			panel3.add(phone);
			panel3.add(iPhone);
			panel3.add(galaxy);
			panel3.add(pixel);
			panel3.add(onePlus);
			panel4.add(voteButton);
			
			theWindow = new JFrame("BallotPanel");
			theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			theWindow.setLayout(new GridLayout(4,1));
			theWindow.add(panel1);
			theWindow.add(panel2);
			theWindow.add(panel3);
			theWindow.add(panel4);
			theWindow.pack();
			
			Scanner fileReader = new Scanner(new File("ballots.txt"));
			ArrayList<String> ballotsData = new ArrayList<String>();
			ArrayList<String> candidates = new ArrayList<String>();
			ArrayList<String> files = new ArrayList<String>();
			String[] z = new String[4];
			
			while(fileReader.hasNext()) {
				String line = fileReader.nextLine();
				ballotsData.add(line);
			}
			for(int i = 1; i < ballotsData.size(); i++) {//Making the file
				String[] x = ballotsData.get(i).split(":");
				resultsFile = new File(x[0] + ".txt");//Making the the files
				FW = new FileWriter(resultsFile);
				for(int j = 0; j < x.length; j++) {//separating the contents 
					String y = x[2];
					z = y.split(",");
				}
				for(int u = 0; u < z.length; u++) {//filling the files with data
					candidates.add(z[u]);
					FW.write(z[u] + ": " + ballotCounter + "\n");
					FW.flush();
					System.out.println(z[u]);
				}
			}
			System.out.println(candidates);
			FW.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
		//	fileReader.close();
			//VI.voted();
		}
		//VI.voted();
		//theWindow.setVisible(true);
	}
	public void resetBallots(){
		theWindow.setVisible(true);
		System.out.println("FUCK");
	}
	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int count = 0;
			Component theEventer = (Component) e.getSource();
			if(theEventer == ramones) {
				ramones.setBackground(Color.RED);
			}
			else if(theEventer == clash) {
				clash.setBackground(Color.RED);
			}
			else if(theEventer == blackFlag) {
				blackFlag.setBackground(Color.RED);
			}
			else if(theEventer == x) {
				x.setBackground(Color.RED); 
			}
			else if(theEventer == princessBride) {
				princessBride.setBackground(Color.RED);
			}
			else if(theEventer == deadPoetsSociety) {
				deadPoetsSociety.setBackground(Color.RED); 
			}
			else if(theEventer == casablanca) {
				casablanca.setBackground(Color.RED);
			}
			else if(theEventer == iPhone) {
				iPhone.setBackground(Color.RED); 
			}
			else if(theEventer == galaxy) {
				galaxy.setBackground(Color.RED);
			}
			else if(theEventer == pixel) {
				pixel.setBackground(Color.RED);
			}
			else if(theEventer == onePlus) {
				onePlus.setBackground(Color.RED);
			}
			else if(theEventer == voteButton) {
				//JOptionPane.showInternalConfirmDialog(theWindow, "Confirm Vote?", "information", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				int option = JOptionPane.showConfirmDialog(voteButton.getTopLevelAncestor(), "Confirm Vote?");//need valid parent component not theWindow
					if(option == 0) {
						System.out.println("yes");
						//save shit
						vInterface.voted();
					}
					else if(option == 1) {
						System.out.println("no");
					}
					else if(option == 2) {
						System.out.println("cancel");
					}
			}
		}
	}
}
//public class Office {//inner class//	
//}