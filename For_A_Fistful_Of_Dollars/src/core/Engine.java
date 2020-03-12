package core;

import java.util.ArrayList;

public class Engine extends Case{

	
	/**
	 * Constructor which constructs an Engine case with nb_marshalls on it, 0 bandits and a 1000$ loot
	 * @param nb_marshall the number of Marshalls on the tile
	 */
	public Engine(ArrayList<Marshall> marshalls) {
		super(marshalls, new ArrayList<Bandit>(), new ArrayList<Integer>());
		ArrayList<Integer> nb = new ArrayList<Integer>();
		nb.add(1000);
		this.setNB_PASSENGER(nb);
	}
	
	/*
	 * GETTERS
	 */

	/*
	 * METHODS
	 */
	
}
