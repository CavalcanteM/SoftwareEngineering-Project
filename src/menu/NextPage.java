package menu;

import IsaacMain.LevelSelector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NextPage implements Command {

    private LevelSelector ls;

    public NextPage(LevelSelector ls) {
        this.ls = ls;
    }

    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
        ls.increasePage();
        try {
            Thread.sleep(200);  // Change if the cahnge is too slow
        } catch (InterruptedException ex) {}
    }
}