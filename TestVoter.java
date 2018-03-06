// CS 401 Fall 2017 Assignment 4
// Driver program to test Voter class
// This program should run without any changes utilizing your Voter class.
// Note both the instance methods and the static methods that are used here.
// To see the results of this program execution, see file TestVoter.txt
import java.util.*;
import java.io.*;
public class TestVoter{
	public static void main(String [] args){
		 Voter V;
		 String [] tests = {"1234", "5678", "7777"};
		 for (String S: tests){
		 	// getVoter() is a static method and thus called from the class directly.
			// getVoter() takes two arguments -- the String name of a voters file and 
		 	// a String voter id value.  If the voter id is found in the file it will
		 	// return a new Voter object created from the data in the file.  If the 
			// voter id is not found it will return null.
		 	V = Voter.getVoter("voters.txt", S);
		 	if (V == null){
		 		System.out.println("Sorry, but voter " + S + " is not registered\n");
		 	}
		 	else{
				// Note some Voter instance methods that are called below
		 		String name = V.getName();
				String id = V.getId();
		 		System.out.println("Welcome " + name + " with Id " + id + "!");
		 		System.out.println("Here is your info:");
		 		System.out.println(V.toString());
		 		if (V.hasVoted()){
		 			System.out.println("Sorry, " + name + " but you have already voted\n");
		 		}
		 		else{
		 			System.out.println(name + " you are eligible to vote in this election!");
		 			System.out.println("We will now mark you as having voted");
		 			V.vote();
		 			System.out.println("...and save this information back to the file\n");
		 			// saveVoter() takes two arguments -- the String name of a voters file
		 			// and a Voter reference.  It will rewrite the voters file in a "safe"
		 			// way, updating the record corresponding to V and leaving the other
		 			// records unchanged.  For more information on "safe" saving of the file
		 			// see the Assignment 4 document.
		 			Voter.saveVoter("voters.txt", V);
		 		}
		 	}
		 }			
	}
}