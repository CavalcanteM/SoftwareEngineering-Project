package menu;

import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Menu{
	
	private int h=0;
	private int l=0;
	private ArrayList<Button> buttons;
	private Shape background;
	private Shape menu;

	/**
	 * Creates a new menu with the buttons taken from an ArrayList
	 * @param buttons ArrayList of buttons
	 */
	public Menu(ArrayList<Button> buttons) {
		this.buttons = new ArrayList<>();
		buttons.forEach((b) -> {
			this.addButton(b);
		});
	}

	/**
	 * Creates an empty menu
	 */
	public Menu() {
		this.buttons = new ArrayList<>();
	}
	
	

	
	/*	Getters	*/
	public int getH() {
		return h;
	}

	public int getL() {
		return l;
	}
	
	/**
	 * Add a single button to the menu and resize it
	 * @param button 
	 */
	public void addButton(Button button){
		this.buttons.add(button);			//Adds the button to the list
		this.h+=button.getH()+20;			//The 20px adds the padding under the button
		int max=0;							//The lenght of the largest button
		for (Button b : buttons) {			//Search for the largest button
			if(max<b.getL()){
				max=b.getL();
			}
		}
		this.l=max+20;
	}
	
	public void init(GameContainer gc) throws SlickException {
		background = new Rectangle(0,0,gc.getScreenWidth(),gc.getScreenHeight());
		int x = Math.round((gc.getWidth()-this.l)/2);
		int y = Math.round((gc.getHeight()-this.h)/2);
		menu = new Rectangle(x,y,this.l,this.h);
		x+=10;
		y+=10;
		for (Button b : buttons){
			b.init(gc, y, x);
			y+=70;
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(new Color(0,0,0, 0.65f));
                g.fill(background);
		g.setColor(new Color(60, 60, 60));
		g.fill(menu);
		for (Button b : buttons){
			b.render(gc, g);
		}
	}
	
	public void update(GameContainer gc, int delta){
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			gc.resume();
			return;
		}
		
		for (Button b : buttons){
			if (gc.getInput().isMouseButtonDown(0) && posX>b.getX() && posX<(b.getX()+b.getL()) &&
					gc.getHeight()-posY>b.getY() && gc.getHeight()-posY<(b.getY()+b.getH())){
				b.update(gc, delta);
				return;
			}		
		}
	}
}