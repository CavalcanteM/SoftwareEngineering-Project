package menu;

import org.newdawn.slick.GameContainer;

public class Resume implements Command{

	@Override
	public void execute(GameContainer gc, int delta) {
		System.out.println("Resume");
		gc.resume();
	}
	
}