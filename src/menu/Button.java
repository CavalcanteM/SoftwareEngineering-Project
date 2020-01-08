package menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class will create the template for all the buttons in the game. It uses
 * the COMMAND Behavioral Design Pattern for the implamentation of the
 * functionalities triggered by the buttons
 */
public class Button {

    private int h;
    private int l;
    private Command command;
    private String text;

    /**
     * Will initiate a button giving the basic parameters, in this state it is
     * not ready yet
     *
     * @param h	Height of the button
     * @param l	Width of the button
     * @param command	Command triggered by the button
     * @param text	String displayed on the button
     */
    public Button(int h, int l, Command command, String text) {
        this.h = h;
        this.l = l;
        this.command = command;
        this.text = text;
    }

    /* Getters	*/
    public int getH() {
        return h;
    }

    public int getL() {
        return l;
    }

    public String getText() {
        return text;
    }

    /**
     * Initialises the button setting the Shape and the position
     *
     * @param gc GameContainer
     * @param g	Graphics
     * @param y	Initial position of the Shape in the y-axis
     * @param x	Initial position of the Shape in the x-axis
     * @param shape	Shape of the button inside the menu
     * @throws SlickException
     */
    public void render(GameContainer gc, Graphics g, int x, int y, Shape shape) throws SlickException {
        g.setColor(Color.white);
        g.fill(shape);
        g.setColor(Color.black);
        g.drawString(this.text, x + (this.l / 2) - (g.getFont().getWidth(text) / 2), y + (this.h / 2) - (g.getFont().getHeight(text) / 2));
    }

    public void update(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
        command.execute(gc, delta, sbg);
    }

}
