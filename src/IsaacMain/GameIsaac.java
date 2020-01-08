package IsaacMain;

import java.io.*;
import menu.BackToMainMenu;
import menu.Button;
import menu.ChangeControls;
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
import Upgrades.UpgradeComponent;

/**
 * Main state of the game
 * @author danya
 */
public class GameIsaac extends BasicGameState {

    private Level level;
    private UpgradeComponent player;
    private Menu pause;
    private Menu end;
    private Menu deathMenu;
    private CollisionManager collisionManager;
    private GalaxyComponent galaxy;
    private Saves progress;
    public static int loadedLevel;
    public static int loadedWorld;

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    /**
     * Getter method for the parameter collisionManager
     * @return the current istance of the collision manager
     */
    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    /**
     * Getter method for the parameter galaxy
     * @return the tree containing all worlds and levels
     */
    public GalaxyComponent getGalaxy() {
        return galaxy;
    }

    /**
     * Method getID inherited from BasicGameState
     * @return the ID of this state
     */
    @Override
    public int getID() {
        return 1;
    }

    /**
     * Method init inherited from BasicGameState, in which are initialized: -
     * The player - The starting level - The pause, end and death menus - The
     * collision manager
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = Player.getPlayerInstance();   // Using Singleton class Player

        //Inizialize the Level list and set the first level as current level
        this.initLevelList();
        level = (Level) galaxy.getChild(loadedWorld).getChild(loadedLevel);

        //Initialize the menu
        pause = new Menu();
        end = new Menu();
        deathMenu = new Menu();

        /*
         * Creation of the buttons present in the menus
         * Application of the Command design pattern,
         * the constructor will decide the function executed by the button instantiating a Command
         * as third parameter
         * Check the package menu to see all the available commands
         */
        Button resume = new Button(50, 150, new Resume(), "Resume");                    //Creating the single button
        Button restart = new Button(50, 150, new RestartLevel(this), "Restart");        //The constructor will decide, the function executed by the button
        Button exit = new Button(50, 150, new Exit(), "Quit");                          //Check the pakage menu to see all the commands
        Button next = new Button(50, 150, new NextLevel(this, loadedWorld, loadedLevel), "Next Level");
        Button options = new Button(50, 150, new ChangeControls(this.getID()), "Settings");
        Button main = new Button(50, 150, new BackToMainMenu(), "Main Menu");

        //Adding the buttons to the menus
        pause.addButton(resume);
        pause.addButton(restart);
        pause.addButton(options);
        pause.addButton(main);
        pause.addButton(exit);
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
        level.init(gc);
        this.collisionManager = new CollisionManager(level);
        Player.getPlayerInstance().setCollisionManager(this.collisionManager);
        player.init(gc);
    }

    /**
     * Method render inherited from BasicGameState Managed the render of the
     * elements of this state
     *
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        level.render(gc, g);
        if (Player.getPlayerInstance().isAppear()) {
            Player.getPlayerInstance().render(gc, g);
        }
        if (gc.isPaused()) {
            if (!level.getPts().iterator().hasNext()) {
                end.render(gc, g);
            } else if (player.getNumHearts() <= 0) {
                deathMenu.render(gc, g);
            } else {
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

        if (!gc.isPaused()) {
            level.update(gc, delta);
            Player.getPlayerInstance().update(gc, delta);

            if (Player.getPlayerInstance().getNumHearts() <= 0 | !level.getPts().iterator().hasNext() | gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
                gc.pause();
            }
        } else {
            if (Player.getPlayerInstance().getNumHearts() <= 0) {
                deathMenu.update(gc, delta, sbg);
            } else if (!level.getPts().iterator().hasNext()) {
                saveProgress();
                end.update(gc, delta, sbg);
            } else {
                pause.update(gc, delta, sbg);
            }
        }
    }

    /**
     * This method create the treeLevel. It load the tree from the file
     * "treeLevel". If there isn't the tree in the file, the load operation
     * returns null and the treeLevel is created and saved.
     */
    public void initLevelList() {
        //this.galaxy = this.loadTreeLevel();
        if (this.galaxy == null) {
            this.galaxy = new LevelContainer("Centaurus");
            System.out.println("Il load non ha funzionato");

            // Setting first world
            GalaxyComponent world1 = new LevelContainer("World 1");
            GalaxyComponent level1 = new Level("Level 1-1", 4, 1, 1);
            GalaxyComponent level2 = new Level("Level 1-2", 5, 2, 2);
            GalaxyComponent level3 = new Level("Level 1-3", 5, 3, 3);
            GalaxyComponent level4 = new Level("Level 1-4", 5, 4, 4);
            world1.add(level1);
            world1.add(level2);
            world1.add(level3);
            world1.add(level4);
            galaxy.add(world1);

            // Setting second world
            GalaxyComponent world2 = new LevelContainer("World 2");
            GalaxyComponent level5 = new Level("Level 2-1", 4, 5, 1);
            GalaxyComponent level6 = new Level("Level 2-2", 5, 6, 1);
            GalaxyComponent level7 = new Level("Level 2-3", 5, 7, 1);
            GalaxyComponent level8 = new Level("Level 2-4", 7, 8, 1);
            world2.add(level5);
            world2.add(level6);
            world2.add(level7);
            world2.add(level8);
            galaxy.add(world2);

            // Setting third world
            GalaxyComponent world3 = new LevelContainer("World 3");
            GalaxyComponent level9 = new Level("Level 3-1", 5, 9, 1);
            GalaxyComponent level10 = new Level("Level 3-2", 5, 10, 1);
            GalaxyComponent level11 = new Level("Level 3-3", 5, 11, 1);
            GalaxyComponent level12 = new Level("Level 3-4", 5, 12, 1);
            world3.add(level9);
            world3.add(level10);
            world3.add(level11);
            world3.add(level12);
            galaxy.add(world3);

            // Setting fourth world
            GalaxyComponent world4 = new LevelContainer("World 4");
            GalaxyComponent level13 = new Level("Level 4-1", 5, 13, 1);
            GalaxyComponent level14 = new Level("Level 4-2", 5, 14, 1);
            GalaxyComponent level15 = new Level("Level 4-3", 5, 15, 1);
            GalaxyComponent level16 = new Level("Level 4-4", 5, 16, 1);
            world4.add(level13);
            world4.add(level14);
            world4.add(level15);
            world4.add(level16);
            galaxy.add(world4);

            this.saveTreeLevel();
        }
    }

    /**
     * Save the tree level in the file
     */
    public void saveTreeLevel() {
        try {
            FileOutputStream fos = new FileOutputStream("treeLevel.txt");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(galaxy);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the tree level from the file
     *
     * @return galaxy if the load operation works successfully, else it returns
     * null
     */
    public GalaxyComponent loadTreeLevel() {
        try {
            FileInputStream fis = new FileInputStream("treeLevel.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            GalaxyComponent galaxy = (GalaxyComponent) in.readObject();
            in.close();
            return galaxy;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveProgress() {
        progress = new Saves().loadProgress();
        int nextWorld = loadedWorld, nextLevel = loadedLevel;

        if (galaxy.getChild(loadedWorld).getChildren().size() == loadedLevel + 1) {
            //Check if the actualWorld is the last world of the galaxy
            if (galaxy.getChildren().size() == loadedWorld + 1) {
                //Game complete da modificare
            } else {
                nextWorld++;
                nextLevel = 0;
            }
        } else {
            nextLevel++;
        }

        if ((progress.getLastWorld() < nextWorld) | ((progress.getLastWorld() == nextWorld) && (progress.getLastLevel() < nextLevel))) {
            progress.setLastWorld(nextWorld);
            progress.setLastLevel(nextLevel);
            progress.saveProgress();
        }
        System.out.println("GamiIsaac-> MondoSalvato: " + progress.getLastWorld() + " - LivelloSalvato: " + progress.getLastLevel());
    }
}
