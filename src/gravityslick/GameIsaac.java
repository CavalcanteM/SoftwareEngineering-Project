package gravityslick;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
        level.init(gc, player.getPlayer(), 5);
        player.init(gc);
        menu.init(gc);
        button = new Button(50,150, menu);
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
            level.update(gc, delta);
            player.update(gc, delta);
        }
        /*
            The second condition means that Isaac has not already collected all
            pieces of its girlfriend
        */
        else if(gc.isPaused() && level.getPts().iterator().hasNext()){
            button.update(gc,delta);
        }
    }
    
    /**
     * @param gc
     * @param g
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        level.render(gc,g);
        player.render(gc, g);
        /*
            The second condition means that Isaac has already collected all
            pieces of its girlfriend
        */
        if(gc.isPaused() && !level.getPts().iterator().hasNext()){
            dark.renderOpacity(gc, g);
            menu.renderMenu(gc, g);
            button.render(gc, g, "Bravissimo!");
        }
        else if(gc.isPaused() && level.getPts().iterator().hasNext()){
            dark.renderOpacity(gc, g);
            menu.renderMenu(gc, g);
            button.render(gc, g, "Resume");
        }
    }
}
