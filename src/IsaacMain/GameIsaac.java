package IsaacMain;

import menu.*;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameIsaac extends BasicGame {

    private Level level;
    private Player player;
    private Menu pause;
    private Menu end;
    private Menu deathMenu;
    private CollisionManager collisionManager;

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
	player = Player.getPlayerInstance();   // Using Singleton class Player
	level = new Level();
	pause = new Menu();	
	end = new Menu();
	deathMenu = new Menu();
	//Initialize the menu
	Button resume = new Button(50,150,new Resume(),"Resume");					//Creating the single button
	Button restart = new Button(50,150,new RestartLevel(),"Restart");			//The constructor will decide, the function executed by the button
	Button exit = new Button(50,150,new Exit(),"Quit");							//Check the pakage menu to see all the commands
	Button next = new Button(50,150,new NextLevel(),"Next Level");
	Button main = new Button(50,150,new BackToMainMenu(),"Main Menu");
	//Adding the buttons to the menus
	pause.addButton(resume);
	pause.addButton(restart);
	pause.addButton(exit);	
	pause.addButton(main);
	end.addButton(next);
	end.addButton(restart);
	end.addButton(main);
	end.addButton(exit);
	deathMenu.addButton(restart);
	deathMenu.addButton(main);
	deathMenu.addButton(exit);
	deathMenu.init(gc);
	pause.init(gc);
	end.init(gc);
	level.init(gc, 5);
	this.collisionManager = new CollisionManager(level);
	this.player.setCollisionManager(this.collisionManager);
	player.init(gc);
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
			
			if (player.getNumHearts()<=0 | !level.getPts().iterator().hasNext() | gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
				gc.pause();
			}
        }
		else{
			if (player.getNumHearts()<=0){
				deathMenu.update(gc,delta);
			}
			else if (!level.getPts().iterator().hasNext()){
				end.update(gc, delta);
			}
			else{
				pause.update(gc, delta);
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

        if (gc.isPaused()){
			if (!level.getPts().iterator().hasNext()){
				end.render(gc, g);
			}
			else if (player.getNumHearts()<=0){
				deathMenu.render(gc, g);
			}
			else {
				pause.render(gc, g);
			}
		}
    }

    void setLevel() {
        level = new Level();
    }
}