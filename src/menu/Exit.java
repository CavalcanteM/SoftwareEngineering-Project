package menu;

import org.newdawn.slick.GameContainer;

public class Exit implements Command{

	@Override
	public void execute(GameContainer gc, int delta) {
		gc.exit();
	}

}