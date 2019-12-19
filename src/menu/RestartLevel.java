package menu;

import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class RestartLevel implements Command{

	@Override
	public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException{
		gc.resume();
                try {
                    //gc.reinit();
                    Player.getPlayerInstance().resetStats();
                    Player.getPlayerInstance().init(gc);
                } catch(SlickException ex){
                    ex.printStackTrace();
                }
	}

}