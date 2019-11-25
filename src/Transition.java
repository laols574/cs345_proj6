/**
 * This class "Transition" implements a
 * transition between states in a Turing Machine. 
 * These transitions hold a condition character, a 
 * character to be written, a direction to move in and 
 * two Strings that hold the values for the states on either side, 
 * as well as gettors for these values 
 * @author laols574
 *
 */
public class Transition {
	private String from;
	private char read;
	private char write;
	private boolean moveDir;
	String to;
	
	//constructor
	public Transition(String from, char read, char write, boolean moveDir, String to) {
		this.from = from;
		this.read = read;
		this.write = write;
		this.moveDir = moveDir;
		this.to= to;
		
	}
	
	
	//gettors 
	public String getFrom() {
		return from;
	}
	
	public String getTo() {
		return to;
	}
	
	public boolean getMoveDir() {
		return moveDir;
	}
	
	public char getWrite() {
		return write;
	}
	
	public char getRead() {
		return read;
	}
	
}
