/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import IsaacMain.OptionMenu;
import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author danya
 */
public class Back implements Command{
    
    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException{
        sbg.enterState(OptionMenu.previousState);//, new FadeOutTransition(), new FadeInTransition());
    }
}