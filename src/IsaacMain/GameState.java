/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsaacMain;

import menu.BackToMainMenu;
import menu.Button;
import menu.Exit;
import menu.Menu;
import menu.NextLevel;
import menu.RestartLevel;
import menu.Resume;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author danya
 */
public class GameState extends BasicGameState {
    
    private Level level;
    private Player player;
    private Menu pause;
    private Menu end;
    private Menu deathMenu;
    private CollisionManager collisionManager;
    
    /**
     * 
     */
    void setLevel() {
        level = new Level();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int getID() {
        return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param gc
     * @param sbg
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * 
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    /**
     * 
     * @param gc
     * @param sbg
     * @param delta
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
