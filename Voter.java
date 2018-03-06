import java.util.*;
import java.io.*;
public class Voter {
	private String voterID, voterName;
	private boolean voted;
	private static Scanner fileReader;
	private static File file, tempFile;
	private static FileWriter FW;
	private static BufferedWriter BW;
	private static PrintWriter PW;
	
	public Voter(String voterID, String voterName, boolean voted) {
		this.voterID = voterID;
		this.voterName = voterName;
		this.voted = voted;
	}
	public String getId() {
		return voterID;
	}
	public String getName() {
		return voterName;
	}
	public boolean getVoted() {
		return voted;
	}
	public void vote() {
		voted = true;
	}
	public boolean hasVoted() {
		if(voted)
			return true;
		else
			return false;
	}
	public void setVoterID(String ID) {
		voterID = ID;
	}
	public void setVoterName(String name) {
		voterName = name;
	}
	public static Voter getVoter(String fileName, String ID) {
		try {	
			file = new File(fileName);
			fileReader = new Scanner(new FileInputStream(fileName));
			fileReader.useDelimiter(":");
			String[] searching;
			while(fileReader.hasNextLine()) {
				searching = fileReader.nextLine().split(":");
				if(searching[0].equals(ID)) {
					boolean vote;
					if(searching[2].equalsIgnoreCase("true")){
						vote = true;
					}
					else {
						vote = false;
					}
					return new Voter(searching[0], searching[1], vote);
				}
				else {
				}
			}
			//fileReader.close();
			return null;
		}
		catch (IOException e) {
			System.out.println("IOException");
			return null;
		}
	}
	public static void saveVoter(String fileName, Voter V) {
		ArrayList<String> fileData = new ArrayList();
		try {
			fileReader = new Scanner(new File(fileName));
			fileReader.useDelimiter(":");
			tempFile = new File(fileName + ".tmp");
			FW = new FileWriter(tempFile);
			while(fileReader.hasNext()) {
				String fileLine = fileReader.nextLine();
				//System.out.println(fileLine);
				String ID = fileLine.split(":")[0];
				if(ID.equals(V.getId())) {//writing data to the new file
					String output = "";
					output += V.getId();
					output += ":" + V.getName();
					output += ":" + V.getVoted();
					FW.write(output + "\n");
				}
				else {
					FW.write(fileLine + "\n");
				}
			}
			fileReader.close();
			FW.close();
			fileReader = new Scanner(new File(fileName + ".tmp"));//tmp file
			FW = new FileWriter(new File(fileName));//original file
			while(fileReader.hasNext()){
				FW.write(fileReader.nextLine() + "\n");//copying data from tmp file to original file
			}
			//delete the original file
			file.delete();
			//rename tmp file to original file
			tempFile.renameTo(file);
			FW.close();
		}
		catch(IOException e) {
			System.out.println("Error");
		}
		finally {
			fileReader.close();
		}
//		try {
//			for(int i = 0; i < fileData.size(); i++){
//				FW.write(fileData.get(i) + "\n");
//			}
//			FW.close();
//		}
//		catch(IOException e) {
//			System.out.println("Error");
//		}
//		finally {
//			fileReader.close();
//		}
	}
	public String toString() {
		String str;
		str = "ID: " + voterID + " Name: " + voterName + " Voted? " + voted;
		return str;
	}
}
