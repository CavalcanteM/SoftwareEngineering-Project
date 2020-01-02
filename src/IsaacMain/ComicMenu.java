/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsaacMain;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author pando
 */
public class ComicMenu extends BasicGameState {
    private Shape skipB;
    private int xSkip = 800;
    private int ySkip = 650;
    private int hSkip = 50;
    private int lSkip = 150;
    
    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        skipB = new Rectangle(xSkip,ySkip,lSkip,hSkip);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.fill(skipB);
        g.draw(skipB);
        g.setColor(Color.black);
        g.drawString("Skip", xSkip+(lSkip/2)-(g.getFont().getWidth("Skip")/2) , ySkip+(hSkip/2)-(g.getFont().getHeight("skip")/2));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int posX = gc.getInput().getMouseX();
        int posY = gc.getInput().getMouseY();
        
        
        if (gc.getInput().isMouseButtonDown(0) && posX>this.xSkip && posX<(this.xSkip+lSkip) &&
		posY>ySkip && posY<(ySkip+hSkip)){
            
            sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
        }
    }
    
}
