package menu;

import org.newdawn.slick.GameContainer;

public interface Command {
	
	void execute(GameContainer gc, int delta);
}