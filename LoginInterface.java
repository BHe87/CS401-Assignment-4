// CS 0401 Fall 2017
// This interface is used by the LoginPanel to make a call back to the object
// that initiated it.  See how the initiating object is passed into the LoginPanel
// and see the code for it in TestLoginPanel.java.

public interface LoginInterface
{
	public void setVoter(Voter newV);
}