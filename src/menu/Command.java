package menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Interface that declares the method used for all the commands
 */
public interface Command {

    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException;
}
