package IsaacMain;

import Throwers.Thrower;
import Entity.Entity;
import ShootingEnemies.bullet.Bullet;
import ShootingEnemies.ShootingEnemy;
import java.util.ArrayList;
import org.newdawn.slick.geom.Shape;
import StaticEnemy.StaticEnemy;

//Questa classe viene inizializzata nel costruttore di playerHitbox (riga 55) e 
//ne tiene un riferimento come parametro di classe (riga 50).
//è stato commentato il metodo collidesWith in playerHitbox in riga 527
//e sono stati modificati gli if in riga 463 e 479
/**
 * Manages collisions within the map. In this class the character's life and
 * points are updated, with relative generation of subsequent rewards.
 */
public class CollisionManager implements Mediator {

    //It keeps a reference for all objects that can cause collisions
    private Player playerInstance;
    private Level level;
    private Points pts;
    private Shape reward, playerHitbox;
    private ArrayList<Entity> blocks;
    private ArrayList<ShootingEnemy> turrets;
    private ArrayList<StaticEnemy> spikes;
    private ArrayList<Thrower> throwers, lasers;
    private int difficulty;
    private long lastHitTime = System.currentTimeMillis() - 3000;

    /*This parameters are used only in the test of the class*/
    protected boolean test1 = false;
    protected boolean test2 = false;
    protected boolean test3 = false;
    protected boolean test4 = false;

    /**
     * Inizialize all the instance of the class
     *
     * @param level
     */
    public CollisionManager(Level level) {
        this.level = level;
        this.difficulty = level.getDifficulty();
        this.setParameters(level);
        this.playerInstance = Player.getPlayerInstance();

    }

    public CollisionManager() {
    }

    /**
     * This method has to detect the collisions of the player with the map's
     * objects. This method call the increasing points function if the player
     * collides with a reward and also call the decreasing life function when
     * the player collides with a spike
     *
     * @return false if collides with a no stopping object or not collides, true
     * if collides with a stopping object
     */
    @Override
    public boolean collidesWith() {
        int i;
        playerHitbox = playerInstance.getPlayer();

        if (pts != null) {
            getReward();
        }

        //Check if the playerHitbox collides with the fire or the Thrower
        for (Thrower t : throwers) {
            if (playerHitbox.intersects(t.getDamageBox()) && t.isActive()) {
                //this.test3 = true;
                playerInstance.getDamaged(t.doDamage() * difficulty);
            }
            if (playerHitbox.intersects(t.getHitBox())) {
                return true;
            }
        }

        for (Thrower t : lasers) {
            if (playerHitbox.intersects(t.getDamageBox()) && t.isActive()) {
                //this.test4 = true;
                playerInstance.getDamaged(t.doDamage() * difficulty);
            }
            if (playerHitbox.intersects(t.getHitBox())) {
                return true;
            }
        }

        //Check if the playerHitbox collides with a spike
        if (spikes != null) {
            for (i = 0; i < spikes.size(); i++) {
                StaticEnemy spike = spikes.get(i);
                if (playerHitbox.intersects(spike.getHitbox())) {
                    /*this assignment is used in the test of this class and the next linee must be commented*/
                    //test2=true;
                    playerInstance.getDamaged(spike.doDamage() * difficulty);
                }
            }
        }
        //check if the playerHitbox collides with a obstacle
        if (blocks != null) {
            for (i = 0; i < blocks.size(); i++) {
                if (playerHitbox.intersects(blocks.get(i).getHitBox())) {
                    return true;
                }
            }
        }

        //check if the playerHitbox enters the HitboxArea of the turret
        if (turrets.size() != 0 && turrets != null) {
            for (i = 0; i < turrets.size(); i++) {
                if (playerHitbox.intersects(turrets.get(i).getHitboxArea()) || turrets.get(i).getHitboxArea().contains(playerHitbox)) {
                    ShootingEnemy turret = turrets.get(i);
                    turret.Shoot(playerHitbox.getCenterX(), playerHitbox.getCenterY());
                }
            }
        }

        for (int j = 0; j < turrets.size(); j++) {
            ArrayList<Bullet> bul = turrets.get(j).getBullet();
            for (i = 0; i < bul.size(); i++) {
                try {
                    Bullet bullet = bul.get(i);
                    Shape bulletshape = bullet.getShape();
                    if (bulletshape != null) {
                        if (playerHitbox.intersects(bulletshape)) {
                            playerInstance.getDamaged(bullet.getDamage() * difficulty);
                            bullet.remove();
                        }
                    }
                } catch (Exception e) {
                }

            }
        }

        return false;
    }

    /**
     * This method has the scope of the detection of the collision between Isaac
     * and the Shape of the current reward that Isacc has to collect
     */
    private void getReward() {
        if (playerHitbox.intersects(this.reward)) {
            /*This assignment is used for the test of this class*/
            //test1=true;
            if (pts.iterator().hasNext()) {
                this.reward = pts.iterator().next().getHitBox();
            }
        }
    }

    /**
     * Takes the blocks and the enemies/weapons from the level Invoked when a
     * level is finished and another one has to start
     *
     * @param level the current level of the game
     */
    public void setParameters(Level level) {
        this.blocks = level.getBlock();
        this.pts = level.getPts();
        this.spikes = level.getSpikes();
        if (pts.iterator().hasNext()) {
            reward = pts.iterator().next().getHitBox();
        }
        this.throwers = level.getThrowers();
        this.lasers = level.getLaserThrowers();
        this.turrets = level.getShootingEnemy();
    }

    /**
     * This method is used only in the test of this class
     *
     * @param blocks
     * @param pts
     * @param spikes
     * @param player
     * @param reward
     * @param t
     */
    public void setParameters(ArrayList<Entity> blocks, Points pts, ArrayList<StaticEnemy> spikes, Shape player, Shape reward, ArrayList<Thrower> t) {
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
