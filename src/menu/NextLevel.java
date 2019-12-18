package menu;

import IsaacMain.GalaxyComponent;
import IsaacMain.GameState;
import IsaacMain.Level;
import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class NextLevel implements Command{
        
        private GameState gs;
        private int actualWorld=1;
        private int actualLevel=1;
        
        public NextLevel(GameState gs){
            this.gs = gs;
        }
        
	@Override
	public void execute(GameContainer gc, int delta) throws SlickException{
		//Check if the actualLevel is the last level of the world
                if(gs.getGalaxy().getChild(actualWorld-1).getChildren().size()==actualLevel){
                    //Check if the actualWorld is the last world of the galaxy
                    if(gs.getGalaxy().getChildren().size()==actualWorld){
                        //Game complete da modificare
                        gc.exit();
                    }else{
                        actualWorld++;
                        actualLevel=1;
                    }
                }else{
                    actualLevel++;
                }
                GalaxyComponent world = gs.getGalaxy().getChild(actualWorld-1);
                Level level =(Level) world.getChild(actualLevel-1);
                gs.setLevel(level);
                gs.getLevel().init(gc);
                Player.getPlayerInstance().resetStats();
                Player.getPlayerInstance().init(gc);
                
                gs.getCollisionManager().setParameters(gs.getLevel());
                gc.resume();
        }

}