package menu;

import IsaacMain.OptionMenu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Brings the game into the OptionMenu State and saves the previous state
 */
public class ChangeControls implements Command {

    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) {
        OptionMenu.previousState = sbg.getCurrentStateID();
        sbg.enterState(2);//, new FadeOutTransition(), new FadeInTransition());
    }
}
