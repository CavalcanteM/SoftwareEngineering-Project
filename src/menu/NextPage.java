package menu;

import IsaacMain.LevelSelector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Command that increases the page index in levelSelector State
 */
public class NextPage implements Command {

    private LevelSelector ls;

    public NextPage(LevelSelector ls) {
        this.ls = ls;
    }

    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
        ls.increasePage();
        try {
            Thread.sleep(200);  //Prevents accidental clicks
        } catch (InterruptedException ex) {}
    }
}