package state;

import java.util.ArrayList;


import gender.Gender;

public class StateService {

	public void displayList(ArrayList<State> stateList)
	{
		stateList.forEach((state) -> print(state));
	}
	
	public void print(State state)
	{
		state.displayValues();
	}
	
}
