package IsaacMain;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Menu {
    private final int h;
    private final int l;
    private Shape menu;
    public Menu (int h, int l){
        this.h=h;
        this.l=l;
    }

    public Shape getMenu() {
        return menu;
    }

    public int getH() {
        return h;
    }

    public int getL() {
        return l;
    }
    
    public void init(GameContainer gc) throws SlickException {
        menu = new Rectangle(Math.round((gc.getWidth()-this.l)/2),Math.round((gc.getHeight()-this.h)/2),this.l,this.h);
    }
    
    public void renderMenu(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(new Color(60,60,60));
        g.fill(menu);
    }
    
    public void renderOpacity(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(new Color(0,0,0, 0.65f));
        g.fill(menu);
    }
    
    public void resume(GameContainer gc) throws SlickException{
        gc.resume();
    }    
}