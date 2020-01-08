package menu;

import IsaacMain.OptionMenu;
import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Back implements Command{
    
    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException{
        sbg.enterState(OptionMenu.previousState);//, new FadeOutTransition(), new FadeInTransition());
    }
}