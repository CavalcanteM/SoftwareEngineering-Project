/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsaacMain;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danya
 */
public interface GalaxyComponent {
    
    public void init(GameContainer gc) throws SlickException;
    
    public void update(GameContainer gc, int delta) throws SlickException;
    
    public void render(GameContainer gc, Graphics g) throws SlickException;
    
    public void add(GalaxyComponent galaxyComponent);
    
    public ArrayList<GalaxyComponent> getChildren();
    
    public GalaxyComponent getChild(int index);
}
