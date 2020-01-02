package IsaacMain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danya
 */
public class GameStatesManager extends StateBasedGame {
    
    public GameStatesManager(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MainMenu());
        this.addState(new GameIsaac());
        this.addState(new OptionMenu());
        this.addState(new Tutorial() );
        this.addState(new ComicMenu());
        
    }
}
