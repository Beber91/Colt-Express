package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.ArrayList;

import core.Bandit;
import core.Case;
import core.Marshall;
import core.Train;

class TestTrain {
	private Bandit test;
	private Train game;
	private Case[][] tab;
	private int nb_wagons = 4;
	private int nb_actions = 20;
	
	public TestTrain() {
		
		test = new Bandit("Django", 0, nb_actions, Color.RED);
		ArrayList<Bandit> bandits = new ArrayList<Bandit>();
		bandits.add(test);
		ArrayList<Marshall> marshalls = new ArrayList<Marshall>();
		game = new Train(nb_wagons, bandits, marshalls);
		tab = game.getTrain();
	}

	@Test
	//tests to make sure that all the loot methods work properly
	void testInitialisationLoot(){
		int nb_passengers_wagon = 0;
		for(int i = 0; i < nb_wagons-1; i++) {;//nb_wagons -1 because we do not want to test the engine where the loot amount is = to 1000 dollars
			nb_passengers_wagon = 0;//to determine to total amount of passengers in one wagon to make sure it is between the two limits we gave it
			System.out.println("Wagon " + Integer.toString(i) + ":\n");
				
			for(int passengers = 0; passengers < tab[i][1].getNB_PASSENGERS().size(); passengers++) {

				assert(tab[i][1].getNB_PASSENGERS().get(passengers) <= 500 && tab[i][1].getNB_PASSENGERS().get(passengers)> 0);//making sure the money is in the correct amounts
					System.out.println("Le passager " + Integer.toString(passengers) + " a " + Integer.toString(tab[i][1].getNB_PASSENGERS().get(passengers)) + " dollars");
					nb_passengers_wagon++;
			}
					System.out.println();
					assert(nb_passengers_wagon >= 1 && nb_passengers_wagon <= 4);//checking if we do not have too few/too many passengers
		}
		
		assertEquals(tab[3][1].getNB_PASSENGERS().size(), 1);//checking if the engine case is correct
		
		assert(tab[3][1].getNB_PASSENGERS().get(0) == 1000);
		}
		
	}

