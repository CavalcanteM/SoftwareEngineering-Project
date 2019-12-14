package menu;

import IsaacMain.GameState;
import IsaacMain.Level;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class NextLevel implements Command{
        
        private GameState gs;
        
        public NextLevel(GameState gs){
            this.gs = gs;
        }
        
	@Override
	public void execute(GameContainer gc, int delta) throws SlickException{
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Level level = new Level("Level 2", 1, 1);
                gs.setLevel(level);
                gs.getLevel().init(gc);
                gs.getCollisionManager().setParameters(gs.getLevel());
        }

}