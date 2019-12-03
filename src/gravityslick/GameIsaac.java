package gravityslick;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class GameIsaac extends BasicGame{

    private StaticLevel level;
    private Player player;
    private Menu menu;
    private Menu dark;
    private Button button;
    
    /**
     * @throws org.newdawn.slick.SlickException
     */
    public GameIsaac() throws SlickException {
        super("ISAAC");
    }
    
    /**
     * @param gc
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        level = new StaticLevel();
        player = Player.getPlayerInstance(level);   // Using Singleton class Player
        menu = new Menu(150,300);
        dark = new Menu(gc.getScreenHeight(), gc.getScreenWidth());
        
        level.init(gc, player.getPlayer());
        player.init(gc);
        menu.init(gc);
        button = new Button(50, 150, menu, "Resume");
        button.init(gc);
        dark.init(gc);
        
    }
    
    /**
     * @param gc
     * @param delta
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if(!gc.isPaused()){
            if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
                player.setIsPaused(true);
                gc.pause();
            } else {
                player.setIsPaused(false);
                player.update(gc, delta);
            }   
        } else {
            button.update(gc, delta);
            if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
                gc.resume();
            }
        }
    }
    
    /**
     * @param gc
     * @param g
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        level.render(gc, g);
        player.render(gc, g);
        if(gc.isPaused()){
            dark.renderOpacity(gc, g);
            menu.renderMenu(gc, g);
            button.renderWin(gc, g);
        }
    }
}
