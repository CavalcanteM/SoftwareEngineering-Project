package IsaacMain;

import java.io.Serializable;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface GalaxyComponent extends Serializable {
    
    public void init(GameContainer gc) throws SlickException;
    
    public void update(GameContainer gc, int delta) throws SlickException;
    
    public void render(GameContainer gc, Graphics g) throws SlickException;
    
    public void add(GalaxyComponent galaxyComponent);
    
    public ArrayList<GalaxyComponent> getChildren();
    
    public GalaxyComponent getChild(int index);
}
