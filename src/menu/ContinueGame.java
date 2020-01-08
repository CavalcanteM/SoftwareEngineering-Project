package menu;

import IsaacMain.GameIsaac;
import IsaacMain.Saves;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Starts the game from the first level not completed
 */
public class ContinueGame implements Command {

    private Saves saves;

    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
        saves = new Saves().loadProgress();
        GameIsaac.loadedLevel = saves.getLastLevel();
        GameIsaac.loadedWorld = saves.getLastWorld();
        gc.setMusicOn(false);
        sbg.getState(1).init(gc, sbg);
        gc.getInput().clearKeyPressedRecord();
        sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());   //enter in state 1 -> game isaac
    }
}