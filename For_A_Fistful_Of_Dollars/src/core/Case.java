package core;

import java.util.ArrayList;
import java.util.Random;

public class Case{
	
	private ArrayList<Marshall> marshalls;
	private ArrayList<Bandit> bandits;
	private ArrayList<Integer> NB_PASSENGERS;
	
	public Case(ArrayList<Marshall> m, ArrayList<Bandit> b, ArrayList<Integer> nb) {
		this.marshalls = m;
		this.bandits = b;
		this.NB_PASSENGERS = nb;
	}
	
	public void setNB_PASSENGER(ArrayList<Integer> nb) {
		this.NB_PASSENGERS = nb;
	}
	
	/**
	 * Method to get the value of the NB_PASSENGERS
	 * @return the value of NB_PASSENGERS
	 */
	public ArrayList<Integer> getNB_PASSENGERS() {
		return this.NB_PASSENGERS;
	}
	
	/**
	 * Method to get the Marshalls 
	 * @return Marshalls of the Case
	 */
	public ArrayList<Marshall> getMarshalls() {
		return this.marshalls;
	}
	
	/**
	 * Method to get the Bandits on the Case
	 * @return the Bandits of the Case
	 */
	public ArrayList<Bandit> getBandits() {
		return this.bandits;
	}
	
	/**
	 * Method to know whether there is Marshall on the Case
	 * @return true if there is at least one Marshall on the Case, false otherwise
	 */
	public boolean containsMarshall(){
		return this.marshalls.size()!=0;
	}
	
	/**
	 * Method which will remove an index from the NB_PASSENGERS array
	 * @param passenger the index we want to remove
	 */
	public void removePassenger(int passenger) {
		this.NB_PASSENGERS.remove(passenger);
	}
	
	/**
	 * Method which gives the number of loot/passengers there will be for a given Case
	 */
	public void newLoot() {
		Random R = new Random();
		int nbLoot = R.nextInt(4);
		this.NB_PASSENGERS = new ArrayList<Integer>(nbLoot) ;
	}
	
	/**
	 * Method to add a new loot to the current case, for instance a loot that a bandit will drop when shot at
	 * @param loot the amount that will be added to the loots on the case
	 */
	public void addLoot(int loot) {
		this.NB_PASSENGERS.add(loot);
	}
	
	public void escapeRoof(Train t) {
		
	}
	
	/**
	 * Method which adds a Bandit to the Bandit list of the Case
	 * @param bandit
	 */
	public void addBandit(Bandit bandit) {
		this.bandits.add(bandit);
	}
	
	/**
	 * Method which removes a given bandit from the list of bandits on the Case
	 * @param ID the ID of the Bandit
	 */
	public void removeBandit(int ID) {
		for (int i = 0; i< this.bandits.size(); i++) {
			if (this.bandits.get(i).getID() == ID) {
				this.bandits.remove(i);
			}
		}
	}
	
	/**
	 * Method which adds a Marshall to the Marshall list of the Case
	 * @param marshall
	 */
	public void addMarshall(Marshall marshall) {
		this.marshalls.add(marshall);
	}
	
	/**
	 * Method which removes a Marshall from the Marshall list of the Case
	 * @param ID the ID of the Marshall that is to be removed
	 */
	public void removeMarshall(int ID) {
		for (int i = 0; i<this.marshalls.size(); i++) {
			if (this.marshalls.get(i).getID() == ID) {
				this.marshalls.remove(i);
			}
		}
	}
	
	/**
	 * Method which randomly chooses one of the bandits on the case
	 * @return the chosen Bandit
	 */
	public Bandit chooseRandomBandit() {
		Random R = new Random();
		int idBandit = R.nextInt(this.bandits.size());//on parcourt la liste des bandits presents dans la case
		return this.bandits.get(idBandit);
	}
	

}
