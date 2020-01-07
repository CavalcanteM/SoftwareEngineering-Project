package menu;

import IsaacMain.GameIsaac;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class SelectLevel implements Command{
	private int Level;
	private int World;

	public SelectLevel(int World, int Level) {
		this.World=World;
		this.Level=Level;
	}

	@Override
	public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
		GameIsaac.loadedLevel=Level;
		GameIsaac.loadedWorld=World;
		sbg.getState(1).init(gc, sbg); 
		sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());   //enter in state 1 -> game isaac
		gc.setMusicOn(false);
	}

}