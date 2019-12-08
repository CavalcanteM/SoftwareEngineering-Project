package gravityslick;

import Entities.Player;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class GameIsaac extends BasicGame{

    private Level level;
    private Player player;
    private CollisionManager cm;
    private Menu menu;
    private Menu dark;
    private Button button;
    private Point point;
    
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
        level = new Level();
        player = Player.getPlayerInstance(level);   // Using Singleton class Player
        menu = new Menu(150,300);
        dark = new Menu(gc.getScreenHeight(), gc.getScreenWidth());
        level.init(gc, 5);
        player.init(gc);
        menu.init(gc);
        button = new Button(50,150, menu);
        button.init(gc);
        dark.init(gc);
      
        level.getBlock().add(point.getPoint());
        
    }
    
    /**
     * @param gc
     * @param delta
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            gc.pause();
        }
        
        if(!gc.isPaused()){
            player.update(gc, delta);
        }
        
        
        else if(gc.isPaused() && (level.getScore() >= player.getScore())){
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
        if(level.getScore() >= player.getScore()){
            point.render(gc, g);
        }
        if(gc.isPaused() && level.getScore() <= player.getScore()){
            dark.renderOpacity(gc, g);
            menu.renderMenu(gc, g);
            button.render(gc, g, "Bravissimo!");
        }
        else if(gc.isPaused() && level.getScore()> player.getScore()){
            System.out.println(player.getScore() + " " + level.getScore());
            dark.renderOpacity(gc, g);
            menu.renderMenu(gc, g);
            button.render(gc, g, "Resume");
            point.render(gc, g);
        }
    }
    
    void setLevel(){level = new Level();}
    
}
