package menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public interface Command {
	
	void execute(GameContainer gc, int delta) throws SlickException;
}