/**
 * This class "State" works at a state
 * in a deterministic automata that depicts a
 * Turing machine. It holds fields that determine whether of
 * not it is an accept state and its name , as well as gettors and settors
 * for these fields 
 * @author laols574
 *
 */
public class State {
	private String stateName;
	private boolean accept;
	//constructor 
	//takes "acceptance" and "name"
	public State(String stateName, boolean accept) {
		this.stateName = stateName;
		this.accept = accept;
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public void setName(String name) {
		this.stateName = name;
	}
	
	public boolean isAcceptState() {
		return accept;
	}
	
}
