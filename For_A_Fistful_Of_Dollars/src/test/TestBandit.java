package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import core.Bandit;
import core.Direction;
import core.Marshall;
import core.Train;

class TestBandit {
	private Bandit test;
	private Train game;
	private int nb_wagons = 4;
	private int nb_actions = 20;
	
	public TestBandit() {
		
		test = new Bandit("Django", 0, nb_actions, Color.RED);
		ArrayList<Bandit> bandits = new ArrayList<Bandit>();
		bandits.add(test);
		ArrayList<Marshall> marshalls = new ArrayList<Marshall>();
		game = new Train(nb_wagons, bandits, marshalls);
	}
	
	@Test
	
	//Unit test to make sure you cannot exit the borders of the train
	void testBanditMovement() {
		
		
		boolean Moved = test.move(Direction.UP, game);//zere trying to go outside the trains coordinates
		assert !Moved;
		int[] pos = test.getCoord();
		assertEquals(0, pos[0]);//checking if we stayed in the same spot if we tried to go somewhere impossible
		assertEquals(0, pos[1]);
		
		//repeating the same thing for every single tile, and every single possible outcome
		Moved = test.move(Direction.LEFT, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(0, pos[0]);
		assertEquals(0, pos[1]);
		
		Moved = test.move(Direction.RIGHT, game);
		Moved = test.move(Direction.UP, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(1, pos[0]);
		assertEquals(0, pos[1]);
		
		Moved = test.move(Direction.RIGHT, game);
		Moved = test.move(Direction.UP, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(2, pos[0]);
		assertEquals(0, pos[1]);
		
		Moved = test.move(Direction.RIGHT, game);
		Moved = test.move(Direction.UP, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(3, pos[0]);
		assertEquals(0, pos[1]);
		
		Moved = test.move(Direction.RIGHT, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(3, pos[0]);
		assertEquals(0, pos[1]);
		
		Moved = test.move(Direction.DOWN, game);
		Moved = test.move(Direction.RIGHT, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(3, pos[0]);
		assertEquals(1, pos[1]);
		
		Moved = test.move(Direction.DOWN, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(3, pos[0]);
		assertEquals(1, pos[1]);
		
		Moved = test.move(Direction.LEFT, game);
		Moved = test.move(Direction.DOWN, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(2, pos[0]);
		assertEquals(1, pos[1]);
		
		Moved = test.move(Direction.LEFT, game);
		Moved = test.move(Direction.DOWN, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(1, pos[0]);
		assertEquals(1, pos[1]);
		
		Moved = test.move(Direction.LEFT, game);
		Moved = test.move(Direction.DOWN, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(0, pos[0]);
		assertEquals(1, pos[1]);
		
		Moved = test.move(Direction.LEFT, game);
		assert !Moved;
		pos = test.getCoord();
		assertEquals(0, pos[0]);
		assertEquals(1, pos[1]);
		
		Moved = test.move(Direction.UP, game);
		assert Moved;
		pos = test.getCoord();
		assertEquals(0, pos[0]);
		assertEquals(0, pos[1]);
	}
	
	

}
