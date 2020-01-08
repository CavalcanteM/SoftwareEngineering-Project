package menu;

import IsaacMain.OptionMenu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Command that changes the State of the game from OptionMenu back to the prevoius one
 */
public class Back implements Command{
    
    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException{
        sbg.enterState(OptionMenu.previousState);//, new FadeOutTransition(), new FadeInTransition());
    }
}