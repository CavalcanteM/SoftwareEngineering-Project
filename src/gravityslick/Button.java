/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick;

import net.java.games.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author fabrizio
 */
public class Button {
    private int h;
    private int l;
    private int menuH;
    private int menuL;
    private Shape button;
    private String text;
    
    public Button (int h, int l, Menu m, String text){
        this.h=h;
        this.l=l;
        this.menuL=Math.round((m.getMenu().getCenterX()-this.l/2));
        this.menuH=Math.round((m.getMenu().getCenterY()-this.h/2));
        this.text = text;
    }
    
    public void init(GameContainer gc) throws SlickException {
        button = new Rectangle(this.menuL,this.menuH,this.l,this.h);
        
    }
    
    public void renderWin(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.fill(button);
        g.setColor(Color.black);
        g.drawString(this.text, Math.round(this.menuL+this.l/2-g.getFont().getWidth(text)/2) ,Math.round(this.menuH+this.h/2-g.getFont().getHeight(text)/2));
        
    }

    void update(GameContainer gc, int delta) {
        Input input = gc.getInput();
        int xpos = input.getMouseX();
        int ypos = input.getMouseY();
        if(xpos>this.menuL && ypos>this.menuH && xpos<this.menuL+this.l && ypos< this.menuH+this.h){
            if(input.isMousePressed(0))
                gc.resume();
        }
        
    }
    
}
