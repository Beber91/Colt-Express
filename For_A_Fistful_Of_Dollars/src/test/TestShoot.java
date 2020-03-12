package test;


import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.Bandit;
import core.Direction;
import core.Marshall;
import core.Train;

class TestShoot {
	private Bandit test1;
	private Bandit test2;
	private Bandit test3;
	private Train game;
	private int nb_actions = 4;
	private int nb_wagons = 4;
	

	public TestShoot() {	
		test1 = new Bandit("Buster Scruggs", 0, nb_actions, Color.RED);
		test2 = new Bandit("Horace", 1, nb_actions, Color.GREEN);
		test3 = new Bandit("Wyatt", 2, nb_actions, Color.BLUE);
		System.out.println("Les bandits sont initialises \n");
		
		ArrayList<Bandit> bandits = new ArrayList<Bandit>();
		bandits.add(test1);
		bandits.add(test2);
		bandits.add(test3);
		ArrayList<Marshall> marshalls = new ArrayList<Marshall>();
		game = new Train(nb_wagons, bandits, marshalls);
	}

	@Test
	
	//tests the most common situations (not finished because of bugs)
	void testShootout() {
		test2.move(Direction.RIGHT, game);
		test2.move(Direction.RIGHT, game);
		
		test3.move(Direction.RIGHT, game);
		test3.move(Direction.DOWN, game);
		
		test1.move(Direction.RIGHT, game);
		test1.shoot(Direction.RIGHT, game);
		test1.shoot(Direction.DOWN, game);
	}

}
