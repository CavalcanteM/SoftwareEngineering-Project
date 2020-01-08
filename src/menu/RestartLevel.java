package menu;

import IsaacMain.GalaxyComponent;
import IsaacMain.GameIsaac;
import IsaacMain.Level;
import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class RestartLevel implements Command{
    
    private GameIsaac gs;
    
    public RestartLevel(GameIsaac gs){
        this.gs = gs;
    }
    
    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException{
        gc.resume();
        try {
            GalaxyComponent world = gs.getGalaxy().getChild(GameIsaac.loadedWorld);
            Level level = (Level)world.getChild(GameIsaac.loadedLevel);
            gs.setLevel(level);
            gs.getLevel().init(gc);
            Player.getPlayerInstance().resetStats();
            Player.getPlayerInstance().init(gc);
            
            gs.getCollisionManager().setParameters(gs.getLevel());
            gc.resume();
            gc.getInput().clearKeyPressedRecord();
            sbg.enterState(1);
        } catch(SlickException ex){
            ex.printStackTrace();
        }
    }

}