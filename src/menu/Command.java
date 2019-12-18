package menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public interface Command {
	
	void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException;
}