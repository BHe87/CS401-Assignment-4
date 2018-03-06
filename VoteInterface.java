// CS 0401 Fall 2017
// This interface is used by the BallotPanel to make a call back to the object
// that initiated it.  This method simply signals to the linked
// object that the voting has been completed.

public interface VoteInterface
{
	public void voted();
}