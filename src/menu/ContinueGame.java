package menu;

import IsaacMain.GameIsaac;
import IsaacMain.Saves;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ContinueGame implements Command{

	private Saves saves;
	public ContinueGame() {
		saves=new Saves().loadProgress();
	}
    
	
    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
		
		GameIsaac.loadedLevel=saves.getLastLevel();
		GameIsaac.loadedWorld=saves.getLastWorld();
		System.out.println("LivelloC: "+GameIsaac.loadedLevel);
		System.out.println("MondoC: "+GameIsaac.loadedWorld);
                                   //game is not paused anymore
//        try {   
//            //gc.reinit();                        //restart the gameisaac,for now that we havent saved game
//            Player.getPlayerInstance().resetStats();
//            Player.getPlayerInstance().init(gc);
//        } catch (SlickException ex) {
//            Logger.getLogger(NewGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        gc.setMusicOn(false);
        sbg.getState(1).init(gc, sbg); 
        gc.getInput().clearKeyPressedRecord();
        sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());   //enter in state 1 -> game isaac
        
    }
    
}