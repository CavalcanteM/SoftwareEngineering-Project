package IsaacMain;

import java.util.ArrayList;
import menu.BackToMainMenu;
import menu.Button;
import menu.Menu;
import menu.NextPage;
import menu.SelectLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class manages the selection of one of the level that the player has already player
 * After the player completes a level it is added to the list of levels showed in this window
 */
public class LevelSelector extends BasicGameState {

    private ArrayList<Menu> menu;
    private Saves progress;
    private int page;
    
    /**
     * Increases an index representing the menu page with levels shown on the screen
     * Every page represents a world
     */
    public void increasePage() {
        if (page < progress.getLastWorld()) {
            page++;
        } else {
            this.page = 0;
        }
    }
    
    /**
     * method getID inherited from BasicGameState
     * @return 
     */
    @Override
    public int getID() {
        return 5;
    }
    
    /**
     * Method init inherited from BasicGameState
     * @param gc
     * @param sbg
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menu = new ArrayList<>();
        this.page = 0;
        int i = 0;
        progress = new Saves().loadProgress();
        Button next = new Button(50, 150, new NextPage(this), "Next Page");
        Button main = new Button(50, 150, new BackToMainMenu(), "Main Menu");

        for (i = 0; i < progress.getLastWorld(); i++) {															//This loop will create menus and buttons for the completed worlds
            menu.add(new Menu());
            for (int j = 1; j <= 4; j++) {
                menu.get(i).addButton(new Button(50, 150, new SelectLevel(i, j - 1), "Level " + (i+1) + " - " + j));
            }
            menu.get(i).addButton(next);
            menu.get(i).addButton(main);
        }

        if (i == progress.getLastWorld()) {
            menu.add(new Menu());
            for (int j = 0; j <= progress.getLastLevel(); j++) {
                menu.get(i).addButton(new Button(50, 150, new SelectLevel(i, j), "Level " + (i + 1) + " - " + (j + 1)));
            }
            if (progress.getLastWorld() > 0) {																	//Checks if there is only one world
                menu.get(i).addButton(next);
            }
            menu.get(i).addButton(main);
        }

        for (Menu m : menu) {
            m.init(gc);
        }
    }
    
    /**
     * Method render inherited from BasicGameState
     * @param gc
     * @param sbg
     * @param grphcs
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Image background = new Image("./graphics/background.png");
        background = background.getScaledCopy(960, 720);
        background.draw(0, 0);
        menu.get(page).render(gc, grphcs);
    }
    
    /**
     * Method update inherited from BasicGameState
     * @param gc
     * @param sbg
     * @param i
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        menu.get(page).update(gc, i, sbg);
    }
}