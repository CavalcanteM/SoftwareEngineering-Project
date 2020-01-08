package menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Exit implements Command {

    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
        gc.exit();
    }

}
