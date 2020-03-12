package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.Bandit;
import core.Case;
import core.Direction;
import core.Marshall;
import core.Train;

class TestHoldup {
	private Bandit test;
	private Train game;
	private int nb_wagons = 4;
	private int nb_actions = 20;
	private Case[][] tab;
	
	public TestHoldup() {
		
		test = new Bandit("Django", 0, nb_actions, Color.RED);
		ArrayList<Bandit> bandits = new ArrayList<Bandit>();
		bandits.add(test);
		ArrayList<Marshall> marshalls = new ArrayList<Marshall>();
		game = new Train(nb_wagons, bandits, marshalls);
		tab = game.getTrain();
	}
	

	@Test
	//tests for most of the situations you can encounter when using the holdUp method
	void testHoldup() {
		int pocketsize = 0;
		test.move(Direction.DOWN, game);
		while(tab[0][1].getNB_PASSENGERS().size() > 0) {//we empty the wagon of all loot
			pocketsize++;
			test.holdUp(game);
			assertEquals(test.getPocket().size(), pocketsize);
		}
		
		test.holdUp(game);//we try to rob an empty wagon
		assertEquals(test.getPocket().size(), pocketsize);
		
		test.dropLoot(game);//we drop one loot to test if it works and because we want to be able to pick the 1000 dollars in the engine
		pocketsize--;
		assertEquals(test.getPocket().size(), pocketsize);
		
		test.move(Direction.RIGHT, game);
		test.move(Direction.RIGHT, game);
		test.move(Direction.RIGHT, game);
		test.move(Direction.RIGHT, game);//we're in the engine
		
		int[] place = test.getCoord();//we make sure that we are in a spot where there is only one Loot;
		assertEquals(tab[place[0]][place[1]].getNB_PASSENGERS().size(), 1);
		
		test.holdUp(game);//we try to pick it up, so holdup is working in the engine
		pocketsize++;
		assertEquals(test.getPocket().size(), pocketsize);
		
		while(test.getPocket().size() > 0) {
			test.dropLoot(game);//we empty our pocket
			pocketsize--;
			assertEquals(test.getPocket().size(), pocketsize);
		}
		test.dropLoot(game);//we try to drop with an empty pocket
		assertEquals(test.getPocket().size(), pocketsize);		
	}
	
	void testAddLoot() {
		for(int i = 0; i < nb_wagons; i++) {
			tab[i][0].addLoot(500);//addint loot to all the roof tiles (where no passenger array is initialised in the beginning)
		}
		for(int i = 0; i < nb_wagons; i++) {
			assert(tab[i][0].getNB_PASSENGERS().size() == 1);//making sure only one loot was dropped
			assert(tab[i][0].getNB_PASSENGERS().get(i) == 500);//making sure it is of the right value
		}
	}

}
