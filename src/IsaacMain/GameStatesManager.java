package IsaacMain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameStatesManager extends StateBasedGame {

    public GameStatesManager(String name) {
        super(name);
    }
    
    /**
     * Initialize the states of the game
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