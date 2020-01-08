package menu;

import IsaacMain.GameIsaac;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class NewGame implements Command {

    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
        GameIsaac.loadedLevel = 0;
        GameIsaac.loadedWorld = 0;

        gc.setMusicOn(false);

        sbg.getState(3).init(gc, sbg);
        sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
        System.out.println("state 3");
    }

}
