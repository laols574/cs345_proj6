import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class, Proj06_TuringMachine_student, acts a Turing machine.
 * It has three methods:addState and addTransition which build the "rules"
 * of the turing machine. Their construction created the pathways which the Turing Machine
 * takes, incuding the starting state and the accept state. The run method implements
 * the Turing Machine by iterating over a string given to the Turing Machine and 
 * determining whether or not that string leads the automata to the accept state
 * @author laols574
 *
 */
public class Proj06_TuringMachine_student implements Proj06_TuringMachine{
	
	private HashMap<String ,State> states = new HashMap<String, State>();
	private ArrayList<Transition> transitions = new ArrayList<Transition>(); 
	private State startState;
	private String currentString;
	//the constructor 
	public Proj06_TuringMachine_student() {
		
	}
	
	/**
	 * addState
	 * @param stateName - the name of the state added
	 * @param accept - whether or not the state is the accept state
	 * This methods adds the states from the dot file to 
	 * the program. If true is passed in for the "accept"
	 * parameter, then the state added is an "accept" state;
	 * otherwise, it is not. The String parameter is simply the 
	 * name of the state. If the vector is declared with [peripheries == 2]
	 * next to it, it is an accept state.  
	 * It also declares the startState as the first state fed in 
	 */
	@Override
	public void addState(String stateName, boolean accept) {
		//System.out.println(stateName);
		State newEntry = new State(stateName, accept);
		if(startState == null)
			startState = newEntry;
		states.put(stateName, newEntry);
	}
	/**
	 * addTransition
	 * 	@param from = the starting state, "condition"
	 *  @param char written = the character that is written to the String if read 
	 *  @param moveDir = true, if its right, 
	 *  @param to = the end state
	 *  This function adds a transition to an ArrayList that holds all of the Transitions
	 */
	@Override
	public void addTransition(String from, char read, char write, boolean moveDir, String to) {
		Transition newTransition = new Transition(from, read, write, moveDir, to);
		transitions.add(newTransition);
		
	}
	/**run
	 * @param startString - This is the string which will be checked  
	 * by the Turing machine as to whether or not it will be accepted with
	 * the current Turing machine
	 * @param debug -this flag determines whether or not the function is outputting
	 * "debug" output - output that details each state-step in the process, or simply 
	 * the initial state and the final state
	 * This function works by first initialising the index to 0, setting the current String 
	 * to the value of the startString and setting the current state to the start state, so that
	 * the Turing machine starts its works at the beginning of the automata and at the beginning
	 * of the string. It then runs a while loop while the current state is not the accept state, the only
	 * other way to break out of the loop is to not find any paths from the current node given the current
	 * character "read" in the current string. This function writes the transition's character if it's read 
	 * character matches the current character hovered over by the turing machine. It then adjusts the current state
	 * and will add "." if the indices stretch past the current string, and adjusts the index accordingly.
	 */
	@Override
	public void run(String startString, boolean debug){
		//initialize values 
		int currentIndex = 0;
		currentString = startString;
		State currentState = startState;
		
		printState(currentString, currentIndex, currentState);
		
		while(!currentState.isAcceptState()) {

			char currentChar = currentString.charAt(currentIndex);

			Transition path = null;
			//possible paths from currentState
			for(Transition t : transitions) {
				
				//if the beginning of the transition matches the starting state
				if(t.getFrom().equals(currentState.getName())){
					//if the character matches the current char 
					if(t.getRead() == currentChar) {
						path = t;
					}
				}
			}
			if(path == null)
				break;

			currentString = currentString.substring(0,currentIndex) + path.getWrite() + currentString.substring(currentIndex + 1);
			
			//move direction
			if(path.getMoveDir()) {
				currentIndex++;
			}
			else {
				currentIndex--;
			}
			//check if the string goes off the edge, if it does, add a .
			if(currentIndex == currentString.length()) {
				currentString += ".";
			}
				
			if(currentIndex == -1) {
				currentString = "."  + currentString;
				currentIndex++; //make sure the index is not negative
			}
			
			//update state 
			currentState = states.get(path.getTo());					

			if(debug)
				printState(currentString, currentIndex, currentState);
		}
		if(!debug)
			printState(currentString, currentIndex, currentState);
		if(currentState.isAcceptState()) {
			System.out.println("\nMACHINE ACCEPTS!");
		}
		else {
			System.out.println("\nMACHINE REJECTS!");
		}
	
	}
	/**
	 * printState
	 * @param currentString - the string to be printed
	 * @param currentIndex - the current index (determines where the ^ is printed)
	 * @param currentState - the state that is printed to the screen 
	 * prints the output for one state change of the Turing machine
	 */
	private void printState(String currentString, int currentIndex, State currentState) {
		//OUPUT:
		System.out.println(currentString);
		for(int i = 0; i < currentIndex; i++) {
			System.out.print(" ");
		}
		System.out.println("^   state: " + currentState.getName());
	}
	
}
