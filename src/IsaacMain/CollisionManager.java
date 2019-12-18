package IsaacMain;

import Entities.Entity.*;
import Entities.StaticDamage.StaticDamage;
import Entities.Throwers.*;
import java.util.ArrayList;
import org.newdawn.slick.geom.Shape;

//Questa classe viene inizializzata nel costruttore di playerHitbox (riga 55) e 
//ne tiene un riferimento come parametro di classe (riga 50).
//è stato commentato il metodo collidesWith in playerHitbox in riga 527
//e sono stati modificati gli if in riga 463 e 479
/**
 * Manages collisions within the map. In this class the character's life and points 
 * are updated, with relative generation of subsequent rewards.
 */
public class CollisionManager implements Mediator{
    //It keeps a reference for all objects that can cause collisions
    private Player playerInstance;
    private Level level;
    private ArrayList<Entity> blocks;
    private Points pts;
    private Shape reward;
    private ArrayList<StaticDamage> spikes;
    private Shape playerHitbox;
    private ArrayList<Thrower> throwers;
    private ArrayList<Thrower> lasers;
    
    /*This two parameter are used only in the test of the class*/
    protected boolean test1=false;
    protected boolean test2=false;
    protected boolean test3=false;
    protected boolean test4=false;
    
    /**
     * Inizialize all the instance of the class
     * @param level     
     */
    public CollisionManager(Level level){
        this.level = level;
        this.setParameters(level);
        this.playerInstance = Player.getPlayerInstance();
        this.throwers = level.getThrowers();
        this.lasers = level.getLaserThrowers();
    }

    public CollisionManager() {
    }
    
    /**
     * This method has to detect the collisions of the player
     * with the map's objects. This method call the increasing points function
     * if the player collides with a reward and also call the decreasing life 
     * function when the player collides with a spike
     * 
     * @return false if collides with a no stopping object or not collides, 
     * true if collides with a stopping object
     */
    @Override
    public boolean collidesWith(){
        int i;
        playerHitbox = playerInstance.getPlayer();
        
        if(pts != null){
            getReward();
        }
        
        //Check if the playerHitbox collides with the fire or the Thrower
        for(Thrower t: throwers){
            if(playerHitbox.intersects(t.getDamageBox())){
                //this.test3 = true;
                playerInstance.getDamaged(1);
            }
            if(playerHitbox.intersects(t.getHitBox())){
                return true;
            }
        }
        
        for(Thrower t: lasers){
            if(playerHitbox.intersects(t.getDamageBox())){
                //this.test4 = true;
                playerInstance.getDamaged(2);
            }
            if(playerHitbox.intersects(t.getHitBox())){
                return true;
            }
        }
        
        //Check if the playerHitbox collides with a spike
        if(spikes != null){
            for(i=0; i < spikes.size(); i++){
                if(playerHitbox.intersects(spikes.get(i).getHitbox())){
                    /*this assignment is used in the test of this class and the next linee must be commented*/
                    //test2=true;
                    playerInstance.getDamaged(spikes.get(i).doDamage());
                }
            }
        }
        //check if the playerHitbox collides with a obstacle
        if(blocks != null){
            for(i = 0; i < blocks.size(); i++){
                if(playerHitbox.intersects(blocks.get(i).getHitBox())){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    /**
     * This method has the scope of the detection of the collision between Isaac 
     * and the Shape of the current reward that Isacc has to collect
     */
    private void getReward(){
        if(playerHitbox.intersects(this.reward)){
            /*This assignment is used for the test of this class*/
            //test1=true;
            if(pts.iterator().hasNext()){
                this.reward = pts.iterator().next().getHitBox();
            }
        }
    }
    
    /**
     * Takes the blocks and the enemies/weapons from the level
     * Invoked when a level is finished and another one has to start
     * @param level the current level of the game
     */
    public void setParameters(Level level){
        this.blocks = level.getBlock();
        this.pts = level.getPts();
        this.spikes = level.getSpikes();
        if(pts.iterator().hasNext()){
           reward = pts.iterator().next().getHitBox();
        }
    }
    
    /**
     * This method is used only in the test of this class
     * @param blocks
     * @param pts
     * @param spikes
     * @param player
     * @param reward
     * @param t
     */
    public void setParameters(ArrayList<Entity> blocks, Points pts, ArrayList<StaticDamage> spikes, Shape player, Shape reward, ArrayList<Thrower> t ){
        this.blocks = blocks;
        this.pts = pts;
        this.spikes = spikes;
        this.playerHitbox = player;        
        this.reward = reward;
        this.throwers = new ArrayList<>();
        this.throwers.add(t.get(0));
        this.lasers = new ArrayList<>();
        this.lasers.add(t.get(1));   
    }
}