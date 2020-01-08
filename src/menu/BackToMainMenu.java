package menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class BackToMainMenu implements Command{

	@Override
	public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException{
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                gc.resume();                            //game not paused
                sbg.enterState(0,new FadeOutTransition(), new FadeInTransition()); //enter in mainmenu state
                gc.setMusicOn(true);
                System.out.println("state 0");
        }

}