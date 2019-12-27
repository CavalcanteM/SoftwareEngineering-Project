package menu;

import IsaacMain.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ContinueGame implements Command{
    
    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) {
                                   //game is not paused anymore
//        try {   
//            //gc.reinit();                        //restart the gameisaac,for now that we havent saved game
//            Player.getPlayerInstance().resetStats();
//            Player.getPlayerInstance().init(gc);
//        } catch (SlickException ex) {
//            Logger.getLogger(ContinueGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        gc.resume(); 
        sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());   //enter in state 1 -> game isaac
        gc.setMusicOn(false);
        System.out.println("state 1");
    }
    
}