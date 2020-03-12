package display;

import javax.swing.*;
import core.*;
import display.Window;

import java.awt.*;
import java.util.ArrayList;

interface Observer {
	public void update();
}


@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private Controle buttons;
	private ViewTrain vt;
	
	/**
	 * Constructor of a window which initializes the display following the information of the train
	 * @param train : a Train used to initialize the display
	 * @param c : the controller receiving all actions from Window
	 */
	public Window(Train train, ArrayList<Bandit> bandits)  {
		Controller c = new Controller(train, bandits);

		
		// Gives the window a title
		this.setTitle("ColtExpress");
		
		// Gives the window a size
		this.setSize(500, train.getNB_WAGONS()*100+100);
		this.setLayout(new FlowLayout());
		
		//Initializes and add the container which will contain the view of the Train
		this.vt = new ViewTrain(train);
		this.add(vt);
		
		//Initializes and add the container which will contain all the buttons for the interaction with the game
		this.buttons = new Controle(c);
		this.add(buttons);
		
		// Centers the window
		this.setLocationRelativeTo(null);		
		
		//Disable the window resize option
		this.setResizable(true);
		
		// Allows the close button of the window to effectively work
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	
}


@SuppressWarnings("serial")
class Controle extends JPanel{
	
	//All the buttons
	private JButton up;
	private JButton down;
	private JButton left;
	private JButton right;
	private JButton shootUp;
	private JButton shootDown;
	private JButton shootLeft;
	private JButton shootRight;
	private JButton rob;
	private JButton action;
	private JButton delete;
	
	/**
	 * Constructor of the container of the buttons for the interaction with the game
	 * @param c : the controller receiving all input from the buttons of the class
	 */
	public Controle(Controller c) {
		//Set an absolute Layout to manually place all buttons
		this.setLayout(null);
		//Initializes the size of the container
		this.setPreferredSize(new Dimension(500,500));
		
		this.up = new JButton("Move Up");
		this.add(this.up);	
		this.up.setBounds(70, 0, 110, 20);
		//Add interactions with the JButtons
		this.up.setActionCommand("UP");
		this.up.addActionListener(c);
		
		
		this.down = new JButton("Move Down");
		this.add(this.down);
		this.down.setBounds(70, 50, 110, 20);
		
		this.down.setActionCommand("DOWN");
		this.down.addActionListener(c);
		
		
		this.right = new JButton("Move Right");
		this.add(this.right);
		this.right.setBounds(140, 25, 110, 20);
		
		this.right.setActionCommand("RIGHT");
		this.right.addActionListener(c);
		
		
		this.left = new JButton("Move Left");
		this.add(this.left);
		this.left.setBounds(0, 25, 110, 20);
		
		this.left.setActionCommand("LEFT");
		this.left.addActionListener(c);
		
		
		this.action = new JButton("Action !");
		this.add(this.action);
		this.action.setBounds(175,200,150,50);
		
		this.action.setActionCommand("ACTION");
		this.action.addActionListener(c);
		
		
		this.shootUp = new JButton("Shoot Up !");
		this.add(this.shootUp);
		this.shootUp.setBounds(320,50,110,20);
		
		this.shootUp.setActionCommand("SHOOT UP");
		this.shootUp.addActionListener(c);
		
		this.shootDown = new JButton("Shoot Down !");
		this.add(this.shootDown);
		this.shootDown.setBounds(320,100,110,20);
		
		this.shootDown.setActionCommand("SHOOT DOWN");
		this.shootDown.addActionListener(c);
		
		this.shootLeft = new JButton("Shoot Left !");
		this.add(this.shootLeft);
		this.shootLeft.setBounds(250,75,110,20);
		
		this.shootLeft.setActionCommand("SHOOT LEFT");
		this.shootLeft.addActionListener(c);
		
		this.shootRight = new JButton("Shoot Right !");
		this.add(this.shootRight);
		this.shootRight.setBounds(390,75,110,20);
		
		this.shootRight.setActionCommand("SHOOT RIGHT");
		this.shootRight.addActionListener(c);
		
		this.delete = new JButton("Delete");
		this.add(this.delete);
		this.delete.setBounds(320, 0, 110, 20);
		
		this.delete.setActionCommand("DELETE");
		this.delete.addActionListener(c);
		
		this.rob = new JButton("Rob");
		this.add(this.rob);
		this.rob.setBounds(70, 125, 110, 20);
		
		this.rob.setActionCommand("ROB");
		this.rob.addActionListener(c);
		}
	
	
	
	
	
}

@SuppressWarnings("serial")
class ViewTrain extends JPanel implements Observer{

	//A reference to the current train
	private Case[][] train;
	
	//The size of the a Case	
	private final int sizeTile = 200;
	private static final Color BROWN = new Color(153,102,0);
	
	/**
	 * Constructor which initializes the container which will contain the view of the Train
	 * @param train : the current train
	 */
	public ViewTrain(Train train) {
		this.train = train.getTrain();
		Dimension dim = new Dimension(train.getNB_WAGONS()*sizeTile, 2*sizeTile);
		this.setPreferredSize(dim);
		this.setVisible(true);
	}
	
	@Override
	public void update() {
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.repaint();
		for (int i = 0; i<train.length; i++) {
			for (int j = 0; j < train[i].length; j++) {
				this.paint(g, train[i][j],i*this.sizeTile, j*sizeTile);
			}
		}
	}
	
	private void paint(Graphics g, Case c, int x, int y) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, sizeTile, sizeTile);
		g.setColor(Color.GRAY);
		g.drawRect(x, y, sizeTile, sizeTile);
		
		//Draw Bandits with loots inside them
		for (int i = 0; i<c.getBandits().size(); i++) {
			int xBandit = x+i*50+10;
			int yBandit = y+4;
			g.setColor(c.getBandits().get(i).getColor());
			g.fillRect(xBandit, yBandit, 30, 92);
			for (int j = 0; j<c.getBandits().get(i).getPocket().size(); j++) {
				int loot = c.getBandits().get(i).getLoot().get(j);
				if (loot == 500) {
					g.setColor(Color.yellow);
				} else if (loot == 1000) {
					g.setColor(BROWN);
				} else {
					g.setColor(Color.ORANGE);
				}
				int xTemp = xBandit +5;
				int yTemp = yBandit + j*20 +6;
				g.fillRect(xTemp, yTemp, 20, 20);
			}

		}
		
		//Draw Loots in the Case
		ArrayList<Integer> loot = c.getNB_PASSENGERS();
		for (int i = 0; i<loot.size(); i++) {
			int xLoot = 20*(i%4)+110+x;
			int yLoot = 20*(i/4)+110+y;
			if (loot.get(i) == 500) {
				g.setColor(Color.yellow);
			} else if (loot.get(i) == 1000) {
				g.setColor(BROWN);
			} else {
				g.setColor(Color.ORANGE);
			}
			g.fillRect(xLoot, yLoot	, 20, 20);
		}
		
		//Draw Marshall
		if (c.containsMarshall()) {
			g.setColor(Color.YELLOW);
			int[] xTr1 = new int[] {x+50, x+90, x+10};
			int[] yTr1 = new int[] {y+110, y+170, y+170};
			
			int[] xTr2 = new int[] {x+10, x+90, x+50};
			int[] yTr2 = new int[] {y+130,y+130,y+190};
			g.fillPolygon(xTr1, yTr1, xTr1.length);
			g.fillPolygon(xTr2, yTr2, xTr2.length);
		}
		
	}
	
	
}

abstract class Observable{
	private ArrayList<Observer> observers;
	
	public Observable() {
		this.observers = new ArrayList<Observer>();
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	public void notifyObserver() {
		for (Observer o: observers) {
			o.update();
		}
	}
}
