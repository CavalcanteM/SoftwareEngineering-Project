package IsaacMain;

import StaticEnemy.StaticDamage;
import Throwers.Factory.ThrowersClient;
import Throwers.Thrower;
import Entity.Entity;
import StaticEnemy.Factory.StaticEnemyClient;
import Entity.EntityClient;
import ShootingEnemy.ShootingEnemy;
import ShootingEnemy.Factory.ShootingEnemyClient;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class has the scope of the definition of a level's enviroment.
 */
public class Level implements GalaxyComponent {

    private TiledMap map;
    private ArrayList<Entity> blocks, rewards, upgrades;
    private ArrayList<StaticDamage> spikes;
    private ArrayList<ShootingEnemy> turrets;

    //private ArrayList<Thrower> flameThrowers, laserThrowers;
    private ArrayList<Thrower> throwers;
    private int score, index, difficulty;
    private String name;
    private Points pts;
    private Powerup up;
    private Graphics g;
    private static final long serialversionUId = 1;
    
    /**
     * Constructor of the class Level
     * @param name the name of the level, it will be printed at the top right of the screen
     * @param score the number of rewards the player has to collect in the level
     * @param index the index of the level (used for loading the correct level)
     * @param difficulty a number between 1 and 10 representing the difficulty of the level, that will influence the parameters of the enemies
     */
    public Level(String name, int score, int index, int difficulty) {
        this.name = name;
        this.score = score;
        this.index = index;
        this.difficulty = difficulty;
    }
    
    /**
     * Getter method for the parameter score
     * @return an integer representing the score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Getter method for the parameter throwers
     * @return an ArrayList of Thrower objects containing the throwers
     */
    public ArrayList<Thrower> getThrowers() {
        return throwers;
    }
    
    /**
     * Getter method for the parameter map of type TiledMap
     * @return 
     */
    public TiledMap getMap() {
        return map;
    }
    
    /**
     * Method getShootingEnemy inherited from the interface Galaxy Component
     * @return 
     */
    @Override
    public ArrayList<ShootingEnemy> getShootingEnemy() {
        return turrets;
    }
    
    /**
     * Method getRewards inherited from the interface Galaxy Component
     * @return 
     */
    @Override
    public ArrayList<Entity> getRewards() {
        return rewards;
    }
    
    /**
     * Method getBlock inherited from the interface Galaxy Component
     * @return 
     */
    @Override
    public ArrayList<Entity> getBlock() {
        return blocks;
    }
    
    /**
     * Method getSpikes inherited from the interface Galaxy Component
     * @return 
     */
    @Override
    public ArrayList<StaticDamage> getSpikes() {
        return spikes;
    }
    
    /**
     * Method getPowerup inherited from the interface Galaxy Component
     * @return 
     */
    @Override
    public Powerup getPowerup(){
        return up;
    }
    
    /**
     * Method getPts inherited from the interface Galaxy Component
     * @return 
     */
    @Override
    public Points getPts() {
        return pts;
    }

    /**
     * Method init inherited from the interface galaxyComponent
     * It is a sort of constructor of the class and sets the parameters of the class
     * @param gc
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        /**
         * The difficulty parameter passed to every concrete factory to generate
         * objects that vary based on the difficulty level.
         */
        this.map = new TiledMap("\\src\\map_level\\Level_" + this.index + ".tmx");
        this.spikes = new StaticEnemyClient(this.map, this.difficulty).getStaticEnemyList();
        EntityClient entityClient = new EntityClient(this.map);
        this.blocks = entityClient.getEntities("Walls");
        this.rewards = entityClient.getEntities("Rewards");
        ThrowersClient throwers_client = new ThrowersClient(this.map, this.difficulty);
        this.upgrades = entityClient.getEntities("Upgrades");
        
        this.throwers = throwers_client.getThrowers("Fire");
        this.throwers.addAll(throwers_client.getThrowers("Laser"));
        
        //Create an array list of turrets calling the List creator
        this.turrets = new ShootingEnemyClient(this.map, this.difficulty).getList();
        if(this.index == 4){
            this.pts = new Points(rewards, score, new Image("./graphics/pieces/harmAndLeg_resized.png"));
        }else if(this.index == 8){
            this.pts = new Points(rewards, score, new Image("./graphics/pieces/body_resized.png"));
        }else if(this.index == 12){
            this.pts = new Points(rewards, score, new Image("./graphics/pieces/head_resized.png"));
        }else if(this.index == 16){
            this.pts = new Points(rewards, score, new Image("./graphics/pieces/cpu_resized.png"));
        }else{
            this.pts = new Points(rewards, score); 
        }
        this.pts.init();
        this.up = new Powerup(upgrades);
        this.up.init();
    }
    
    /**
     * Method update inherited from the interface Galaxy Component
     * @param gc
     * @param delta
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if (!this.pts.iterator().hasNext()) {
            gc.pause();
        }
        for (Thrower t : throwers) {
            t.update(delta);
        }

    }

    /**
     * Method render inherited from the interface GalaxyComponent
     * This method is invoked only by GravitySlick.render() and has to render
     * the map
     *
     * @param gc
     * @param g
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.render(0, 0, map.getLayerIndex("Background"));
        map.render(0, 0, map.getLayerIndex("StaticEnemies"));
        map.render(0, 0, map.getLayerIndex("Fire"));
        map.render(0, 0, map.getLayerIndex("Laser"));
        map.render(0, 0, map.getLayerIndex("Walls"));


        /**
         * I only have to render the picked x turrets that are present in the
         * "turrets" ArrayList and not all the one present in the actual map. I
         * will only render x of them! NOTE: I have to offset the obtained
         * hitbox location because of its shape:
         * +-------------+
         * |             |
         * |    XXXXX    |
         * |    XXXXX    |
         * |    XXXXX    |
         * |             |
         * +-------------+
         */

        if (pts.iterator().hasNext()) {
            pts.render(gc, g);
        }

        up.render(gc, g);

        for(Thrower t: throwers){
            t.render();
        }

        for (ShootingEnemy turret : turrets) {
            turret.render(g, map);
        }

        g.setColor(Color.white);
        g.drawString(this.name, 850, 5);
    }
    
    /**
     * Method add inherited from the interface Galaxy Component
     * Not inplemented in this class because it represent a "Leaf" according to the Composite pattern
     * @param galaxyComponent 
     */
    @Override
    public void add(GalaxyComponent galaxyComponent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Method add inherited from the interface Galaxy Component
     * Not inplemented in this class because it represent a "Leaf" according to the Composite pattern
     * @return 
     */
    @Override
    public ArrayList<GalaxyComponent> getChildren() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Method add inherited from the interface Galaxy Component
     * Not inplemented in this class because it represent a "Leaf" according to the Composite pattern
     * @param index
     * @return 
     */
    @Override
    public GalaxyComponent getChild(int index) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
