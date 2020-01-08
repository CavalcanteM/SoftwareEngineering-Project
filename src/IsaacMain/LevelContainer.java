package IsaacMain;

import Entity.Entity;
import ShootingEnemy.ShootingEnemy;
import StaticEnemy.StaticDamage;
import Throwers.Thrower;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * This class represents the "Composite" according to the Composite design pattern
 * @author danya
 */
public class LevelContainer implements GalaxyComponent {

    private String name;
    private ArrayList<GalaxyComponent> children;
    private static final long serialversionUId = 1;
    
    /**
     * Constructor of the class LevelContainer
     * @param name the name of the current level container (it can be the level of the galaxy or the name of a world)
     */
    public LevelContainer(String name) {
        this.name = name;
        this.children = new ArrayList<GalaxyComponent>();
    }
    
    /**
     * Constructor of the class LevelContainer
     * @param name the name of the current level container (it can be the level of the galaxy or the name of a world)
     * @param children a list of GalaxyComponent representing the galaxy or the worlds
     */
    public LevelContainer(String name, ArrayList<GalaxyComponent> children) {
        this.name = name;
        this.children = children;
    }
    
    /**
     * Setter method of the parameter name
     * @param name new name of the current level container 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Getter method of the parameter name
     * @return a String representing the name of the current level container
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * method add inherited from the interface GalaxyComponent
     * @param galaxyComponent 
     */
    @Override
    public void add(GalaxyComponent galaxyComponent) {
        this.children.add(galaxyComponent);
    }
    
    /**
     * method getChildren inherited from the interface GalaxyComponent
     * @return 
     */
    @Override
    public ArrayList<GalaxyComponent> getChildren() {
        return this.children;
    }
    
    /**
     * method getChild inherited from the interface GalaxyComponent
     * @param index
     * @return 
     */
    @Override
    public GalaxyComponent getChild(int index) {
        return this.children.get(index);
    }
    
    /**
     * method init inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {

    }
    
    /**
     * method update inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @param gc
     * @param delta
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

    }
    
    /**
     * method render inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @param gc
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

    }
    
    /**
     * method getShootingEnemy inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @return 
     */
    @Override
    public ArrayList<ShootingEnemy> getShootingEnemy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * method getRewards inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @return 
     */
    @Override
    public ArrayList<Entity> getRewards() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * method getBlock inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @return 
     */
    @Override
    public ArrayList<Entity> getBlock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * method getSpikes inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @return 
     */
    @Override
    public ArrayList<StaticDamage> getSpikes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * method getPowerup inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @return 
     */
    @Override
    public Powerup getPowerup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * method getPts inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @return 
     */
    @Override
    public Points getPts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * method getThrowers inherited from the interface GalaxyComponent
     * Not inplemented in this class because it represent a "Composite" according to the Composite pattern
     * @return 
     */
    @Override
    public ArrayList<Thrower> getThrowers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}