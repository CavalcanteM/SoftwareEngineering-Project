package IsaacMain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class manages all the states present in the game
 * @author danya
 */
public class GameStatesManager extends StateBasedGame {
    
    /**
     * Constructor of the class GameStatesManager
     * @param name the name of the game (ISAAC)
     */
    public GameStatesManager(String name) {
        super(name);
    }
    
    /**
     * Initialize the list of states of the game
     * 
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MainMenu());
        this.addState(new GameIsaac());
        this.addState(new OptionMenu());
        this.addState(new Tutorial() );
        this.addState(new ComicMenu());
        this.addState(new LevelSelector());
    }
}