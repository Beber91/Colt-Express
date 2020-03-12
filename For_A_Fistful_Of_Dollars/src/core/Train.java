package core;

import java.util.ArrayList;

public class Train {
	private int NB_WAGONS; 
	private ArrayList<Bandit> BANDITS;
	private ArrayList<Marshall> MARSHALLS;
	private Case[][] train;
	private ArrayList<Tuple<Action,Integer>> todo;
	
	/**
	 * Constructor which generates a train of length nb_wagons and height 2 and has nb_bandits in it 
	 * @param nb_wagons : the number of wagons in the train (engine included)
	 * @param nb_bandits : the number of bandits in the train
	 */
	public Train(int nb_wagons, ArrayList<Bandit> bandits, ArrayList<Marshall> marshalls) {
		this.NB_WAGONS = nb_wagons;
		this.BANDITS = bandits;
		this.MARSHALLS = marshalls;
		this.todo = new ArrayList <Tuple<Action,Integer>>();
		this.train = new Case[this.NB_WAGONS][2];
		for (int i = 0; i<this.train.length; i++) {
			for (int j = 0; j<this.train[i].length; j++) {
				if (j == 0) {
					if (i == 0) {
						this.train[i][j] = new Roof(new ArrayList<Bandit>(bandits));
					} else {
						this.train[i][j] = new Roof(new ArrayList<Bandit>(0));
					}
				} else {
					if (i!= this.train.length -1) {
						this.train[i][j] = new Wagon();
					} else {
						this.train[i][j] = new Engine(marshalls);
					}
				}
			}
		}
	}
	
	/*
	 * GETTERS
	 */
	
	
	/**
	 * Method to get the value of the NB_WAGONS 
	 * @return the value of NB_WAGONS
	 */
	public int getNB_WAGONS() {
		return this.NB_WAGONS;
	}
	
	
	/**
	 * Method to get the value of the NB_BANDITS
	 * @return the value of NB_BANDITS
	 */
	public int getNB_BANDITS() {
		return this.BANDITS.size();
	}
	
	/**
	 * Method to get the value of the NB_MARSHALLS
	 * @return the value of NB_MARSHALLS
	 */
	public int getNB_MARSHALLS() {
		return this.MARSHALLS.size();
	}
	
	/**
	 * Method to get the set of Case sets that represent the Train
	 * @return
	 */
	public Case[][] getTrain() {
		return this.train;
	}
	
	/*
	 * METHODS
	 */
	
	/**
	 * Method which moves a given Bandit in one Direction
	 * @param ID the ID of the Bandit that has to move
	 * @param d the Direction in which the Bandit has to move
	 */
	public void moveBandit(int ID, Direction d) {
		int oldx = this.BANDITS.get(ID).getCoord()[0];
		int oldy = this.BANDITS.get(ID).getCoord()[1];
		if (this.BANDITS.get(ID).move(d, this)) {
			this.train[oldx][oldy].removeBandit(ID);
			this.train[this.BANDITS.get(ID).getCoord()[0]][this.BANDITS.get(ID).getCoord()[1]].addBandit(this.BANDITS.get(ID));
			if (this.train[this.BANDITS.get(ID).getCoord()[0]][this.BANDITS.get(ID).getCoord()[1]].containsMarshall()){
				this.escapeRoof(this.BANDITS.get(ID).getCoord()[0],this.BANDITS.get(ID).getCoord()[1]);
			}
		}
	}
	
	/**
	 * Method which moves the Marshall given
	 * @param ID the ID of the Marshall that has to move
	 */
	public void moveMarshall(int ID) {
		int oldx = this.MARSHALLS.get(ID).getCoord()[0];
		int oldy = this.MARSHALLS.get(ID).getCoord()[1];
		if (this.MARSHALLS.get(ID).move(this)){
			this.train[oldx][oldy].removeMarshall(ID);
			this.train[this.MARSHALLS.get(ID).getCoord()[0]][this.MARSHALLS.get(ID).getCoord()[1]].addMarshall(this.MARSHALLS.get(ID));
		}
	}
	
	/**
	 * Method which sets a given Bandit to take a loot from the Case where he is
	 * @param ID the ID of the Bandit
	 */
	public void holdUp(int ID) {
		this.BANDITS.get(ID).holdUp(this);
	}
	
	/**
	 * Method which makes a Case's Bandit escape from the Wagon to the Roof
	 * @param x the position of the Case in the Train
	 * @param y
	 */
	public void escapeRoof(int x, int y) {
		for(int i = 0; i<this.train[x][y].getBandits().size(); i++) {
			int ID = this.train[x][y].getBandits().get(i).getID();
			if (this.BANDITS.get(ID).escapeRoof(this)) {
				this.train[x][y].removeBandit(ID);
				this.train[x][0].addBandit(this.BANDITS.get(ID));
			}
			
		}
	}
	
	/**
	 * Method which initializes for each Case a number of loot between O and 4
	 */
	
	//  A RETIRER CAR DEJA DANS LE CONSTRUCTEUR DE WAGON
	public void createLoot() {
		for(int j = 0; j < NB_WAGONS; j++) {
			this.train[1][j].newLoot();
		}
	}

	public void addToDo(ArrayList<Tuple<Action,Integer>> a) {
		this.todo = a;
	}
	
	public void nextAction() {
		if (this.todo.size()!=0) {
			int ID = this.todo.get(0).getY();
			Action act = this.todo.remove(0).getX();
			System.out.println(ID);
			switch(act) {
			case MOVEDOWN:
				this.moveBandit(ID, Direction.DOWN);
				break;
			case MOVELEFT:
				this.moveBandit(ID, Direction.LEFT);
				break;
			case MOVERIGHT:
				this.moveBandit(ID, Direction.RIGHT);
				break;
			case MOVEUP:
				this.moveBandit(ID, Direction.UP);
				break;
			case ROB:
				this.holdUp(ID);
				break;
			case SHOOTDOWN:
				this.BANDITS.get(ID).shoot(Direction.DOWN, this);
				break;
			case SHOOTLEFT:
				this.BANDITS.get(ID).shoot(Direction.LEFT, this);
				break;
			case SHOOTRIGHT:
				this.BANDITS.get(ID).shoot(Direction.RIGHT, this);
				break;
			case SHOOTUP:
				this.BANDITS.get(ID).shoot(Direction.UP, this);
				break;
			default:
				break;
			
			}
			this.moveMarshall(0);
			
		} else {
			this.todo = new ArrayList<Tuple<Action,Integer>>();
			for(int j = 0; j < BANDITS.get(0).getActionList().size(); j++) {//on parcourt d'abord les actions
				for (int i = 0; i<BANDITS.size(); i++) {//avant de parcourir les bandits pour chaque action
					todo.add(new Tuple<Action,Integer>(BANDITS.get(i).getActionList().get(j), BANDITS.get(i).getID()));
				}
			}
		}
	
	}

}








