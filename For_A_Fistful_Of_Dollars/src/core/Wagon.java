package core;

import java.util.ArrayList;
import java.util.Random;

public class Wagon extends Case{
	
	
	/**
	 * Constructor which constructs a Wagon tile with a random number (between 0-4) of passengers and no bandits or marshalls
	 */
	public Wagon() {
		super(new ArrayList<Marshall>(), new ArrayList<Bandit>(), new ArrayList<Integer>());

		Random rand = new Random();
		ArrayList<Integer> nb = new ArrayList<Integer>();
		for(int i = 0; i < rand.nextInt(4) + 1; i++) {
			if(rand.nextFloat() > 0.5) {
				nb.add(500);
			}else {
				nb.add(rand.nextInt(499) + 1);
			}
		}
		this.setNB_PASSENGER(nb);
	}
}
