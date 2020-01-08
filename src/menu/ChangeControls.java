package menu;

import IsaacMain.OptionMenu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
//import org.newdawn.slick.state.transition.FadeInTransition;
//import org.newdawn.slick.state.transition.FadeOutTransition;

public class ChangeControls implements Command {

    public ChangeControls(int stateID) {
        //this.currentState = stateID;
    }

    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) {
        OptionMenu.previousState = sbg.getCurrentStateID();
        sbg.enterState(2);//, new FadeOutTransition(), new FadeInTransition());
    }
}
