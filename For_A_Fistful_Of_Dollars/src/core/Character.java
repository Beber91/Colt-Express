package core;

//All Bandits pop 0,0 (up, left)

public class Character {	
	private int x, y;//la position du personnage
	
	public Character(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int[] getCoord() {
		int[] temp = new int[2];
		temp[0] = x;
		temp[1] = y;
		return temp;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}


