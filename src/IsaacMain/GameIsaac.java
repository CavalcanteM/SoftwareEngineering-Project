package IsaacMain;

import Entities.Turret.Bullets.Bullet;
import menu.*;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameIsaac extends StateBasedGame {
    
    /**
     * @throws org.newdawn.slick.SlickException
     */
    public GameIsaac() throws SlickException {
        super("ISAAC");
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.addState(new GameState());
    }
    
}
