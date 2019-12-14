package IsaacMain;

import Entities.Entity.*;
import Entities.StaticDamage.StaticDamage;
import Entities.Turret.Bullets.Bullet;
import Entities.Turret.ShootingEnemy;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

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
    private ArrayList<Entity> blocks;
    private Points pts;
    private Shape reward;
    private ArrayList<ShootingEnemy> turrets;
    private ArrayList<StaticDamage> spikes;
    private Shape playerHitbox;
    private ArrayList<Bullet> bulletsList;

    private long lastHitTime = System.currentTimeMillis() - 3000;

    /*This two parameter are used only in the test of the class*/
    protected boolean test1 = false;
    protected boolean test2 = false;

    /**
     * Inizialize all the instance of the class
     *
     * @param level
     */
    public CollisionManager(Level level) {
        this.level = level;
        this.setParameters(level);
        this.playerInstance = Player.getPlayerInstance();
        if (pts.iterator().hasNext()) {
            reward = pts.iterator().next().getHitBox();
        }
        bulletsList = new ArrayList<>();

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

        //Check if the playerHitbox collides with a spike
        if (spikes != null) {
            for (i = 0; i < spikes.size(); i++) {
                if (playerHitbox.intersects(spikes.get(i).getHitbox())) {
                    /*this assignment is used in the test of this class and the next linee must be commented
                    test2=true;*/
                    playerInstance.getDamaged(spikes.get(i).doDamage());
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
 
                    if ((System.currentTimeMillis() - this.lastHitTime) > 1000) {
                        this.lastHitTime = System.currentTimeMillis();

                        ShootingEnemy single = turrets.get(i);

                        Bullet bull = (single.Shoot(playerHitbox.getCenterX(), playerHitbox.getCenterY()));
                        bulletsList.add(bull);

                    }
                }
            }
        }

        if (bulletsList != null) {
            Shape bulletshape;
            for (i = 0; i < bulletsList.size(); i++) {
                Bullet bullet = bulletsList.get(i);
                bulletshape = bullet.getShape();
                
               
                if (bulletshape != null) {
                    if (playerHitbox.intersects(bulletshape)) {
                        playerInstance.getDamaged(bulletsList.get(i).getDamage());
                        bulletsList.remove(bullet);
                        bullet.remove();
                    }
                } else {
                    bulletsList.remove(i);
                    turrets.get(i).removeBullet(bullet);
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
            /*This assignment is used for the test of this class
            test1=true;*/
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
        this.turrets = level.getShootingEnemy();
    }

}
