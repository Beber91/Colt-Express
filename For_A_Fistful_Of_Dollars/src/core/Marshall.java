package core;

import java.util.Random;

public class Marshall extends Character{
	
	private String name;
	private final double NERVOSITE_MARSHALL;
	private Random rand;
	private int ID;
	
	/**
	 * Constructor for a new Marshall, one will be called at the beginning of the game
	 * @param x the Marshall's x position
	 * @param y his y position
	 * @param name his name
	 * @param nervosity the probability that he will move, he can move up to once at each round
	 * @param ID his identifier, in case there are more than one Marshalls on the train
	 */
	public Marshall(int x, int y, String name, double nervosity, int ID) {
		super(x,y);
		this.name = name;
		this.NERVOSITE_MARSHALL = nervosity;
		this.ID = ID;
		this.rand = new Random();
	}
	
	/*
	 * GETTERS
	 */
	
	/**
	 * Method to return the name of the Marshall
	 * @return the name of the Marshall
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Method to get the ID of the Marshall
	 * @return the ID of the Marshall
	 */
	public int getID() {
		return this.ID;
	}
	
	
	
	/*
	 * METHODS
	 */
	
	
	/**
	 * Method to make the Marshall move one Case to the left
	 * @return true if the Marshall can move to the left, false if he can't (ie if he's already in the last wagon)
	 */
	private boolean moveLeft() {
		if (this.getCoord()[0]-1 >= 0) {
			this.setX(this.getCoord()[0]-1);
			System.out.println(this.name + " se déplace vers l'arrière du train.");
			return true;
		} else {
			System.out.println(this.name + " est déjà dans le dernier wagon.");
			return false;
		}
	}
	
	/**
	 * Method to make the Marshall move one Case to the right
	 * @param t the Train where the Marshall is
	 * @return true if the Marshall can move to the right, false otherwise
	 */
	private boolean moveRight(Train t) {
		if (this.getCoord()[0]+1 <= t.getNB_WAGONS()) {
			this.setX(this.getCoord()[0]+1);
			System.out.println(this.name + " se déplace vers l'avant du train.");
			return true;
		} else {
			System.out.println(this.name + " est déjà dans la locomotive.");
			return false;
		}
	}
	
	/**
	 * Method which will determine whether the Marshall moves left or right, depending on the result of a random experience
	 * @param t the Train where the Marshall is
	 * @return the result of moveLeft or moveRight if the experience has a lower result than the Marshall's nervosity 
	 * or false otherwise 
	 */
	public boolean move(Train t) {
		double probaMove = this.rand.nextDouble();
		if (probaMove < this.NERVOSITE_MARSHALL) {
			if (this.rand.nextDouble()<0.5) {
				return this.moveLeft();
			} else {
				return this.moveRight(t);
			}
		}
		return false;
		//t.getTrain()[this.x][this.y].escapeRoof(t);
	}
	
}
