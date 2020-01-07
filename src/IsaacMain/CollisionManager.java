package IsaacMain;

import Upgrades.ShieldDecorator;
import Upgrades.UpgradeDecorator;
import Throwers.Thrower;
import Entity.Entity;
import StaticEnemy.StaticDamage;
import ShootingEnemy.bullet.Bullet;
import ShootingEnemy.ShootingEnemy;
import ShootingEnemy.ThreeBulletsTurret;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.geom.Shape;

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
    private Powerup power;
    private Shape upgrade;
    private Shape reward, playerHitbox;
    private ArrayList<ShootingEnemy> turrets;
    private ArrayList<StaticDamage> spikes;
    private ArrayList<Thrower> throwers, lasers;
    private long lastHitTime = System.currentTimeMillis() - 3000;
    private UpgradeDecorator shieldDecorator;
    private long lastUpgrade = 0;
    private long timeBetweenUpgrade;
    private int i= 0;
    /*This parameters are used only in the test of the class*/
    protected boolean intesting = false;
    protected boolean test1 = false;
    protected boolean test2 = false;
    protected boolean test3 = false;
    protected boolean test4 = false;
    protected boolean test5 = false;
    protected boolean test6 = false;

    /**
     * Inizialize all the instance of the class
     *
     * @param level
     */
    public CollisionManager(Level level) {
        this.level = level;
        this.setParameters(level);
        this.playerInstance = Player.getPlayerInstance();

    }

    public CollisionManager() {
        this.playerInstance = Player.getPlayerInstance();
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
        if (!this.intesting){
            playerHitbox = playerInstance.getPlayer();
        }
        if (this.shieldDecorator != null && this.shieldDecorator.isUpgradeActive()) {
            this.shieldDecorator.updateActive();
        }
        if (pts != null) {
            getReward();
        }

        if (power != null) {
            getUpgrade();
        }

        //Check if the playerHitbox collides with the fire or the Thrower
        for (Thrower t : throwers) {
            if (playerHitbox.intersects(t.getDamageBox()) && t.isActive()) {
                this.test3 = true;
                if (shieldDecorator != null && shieldDecorator.isUpgradeActive()) {
                    shieldDecorator.getDamaged(t.doDamage());
                } else {
                    playerInstance.getDamaged(t.doDamage());
                }
            }
            if (playerHitbox.intersects(t.getHitBox())) {
                return true;
            }
        }

        for (Thrower t : lasers) {
            if (playerHitbox.intersects(t.getDamageBox()) && t.isActive()) {
                this.test4 = true;
                if (shieldDecorator != null && shieldDecorator.isUpgradeActive()) {
                    shieldDecorator.getDamaged(t.doDamage());
                } else {
                    playerInstance.getDamaged(t.doDamage());
                }
            }
            if (playerHitbox.intersects(t.getHitBox())) {
                return true;
            }
        }

        //Check if the playerHitbox collides with a spike
        if (spikes != null) {
            for (i = 0; i < spikes.size(); i++) {
                if (playerHitbox.intersects(spikes.get(i).getHitbox())) {
                    /*this assignment is used in the test of this class and the next linee must be commented*/
                    test2=true;
                    if (shieldDecorator != null && shieldDecorator.isUpgradeActive()) {
                        shieldDecorator.getDamaged(spikes.get(i).doDamage());
                    } else {
                        playerInstance.getDamaged(spikes.get(i).doDamage());
                    }
                }
            }
        }
        //check if the playerHitbox collides with a obstacle
        for (Entity block : blocks) {
            if (playerHitbox.intersects(block.getHitBox())) {
                return true;
            }
        }

        for (ShootingEnemy turret : turrets) {
            if ((playerHitbox.intersects(turret.getActivationArea()) || turret.getActivationArea().contains(playerHitbox)) && !playerHitbox.intersects(turret.getHitbox())) {
                turret.Shoot(playerHitbox.getCenterX(), playerHitbox.getCenterY());
            }
            if (playerHitbox.intersects(turret.getHitbox()) && turret.isVisible()) {
                this.test5 = true;
                return true;
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
                            if (shieldDecorator != null && shieldDecorator.isUpgradeActive()) {
                                shieldDecorator.getDamaged(bullet.getDamage());
                            } else {
                                playerInstance.getDamaged(bullet.getDamage());
                            }
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
            test1=true;
            //pts.getSound().play(1f, 0.1f);
            if (pts.iterator().hasNext()) {
                this.reward = pts.iterator().next().getHitBox();
            }
        }
    }

    /**
     * This method manage the collision with the upgrades and manage also the
     * spawn time of this upgrades and his actiovation
     */
    private void getUpgrade() {
        if (this.upgrade != null) {
            if (playerHitbox.intersects(this.upgrade)) {
                test6 = true;
                //Activation of powerups and decision on the time that must pass before generating the next powerup
                lastUpgrade = System.currentTimeMillis();
                if (power.Powerup() instanceof ShieldDecorator) {
                    this.shieldDecorator = power.Powerup();
                    this.shieldDecorator.activation();
                } else {
                    power.Powerup().activation();
                }
                this.upgrade = null;
                Random ran = new Random();
                this.timeBetweenUpgrade = ran.nextInt(10000);
                power.remove();
            }
        } else {
            if (this.lastUpgrade == 0) {
                //Generation of the first powerup
                if (power.iterator().hasNext()) {
                    this.upgrade = power.iterator().next().getHitBox();
                }
            } else {
                //Generation of the next powerup
                if (power.iterator().hasNext() && System.currentTimeMillis() - this.lastUpgrade > this.timeBetweenUpgrade) {
                    this.upgrade = power.iterator().next().getHitBox();
                }
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
        this.lastUpgrade = 0;
        this.upgrade = null;
        this.power = level.getPowerup();
        this.getUpgrade();
    }

    public void setShieldDecorator(UpgradeDecorator shieldDecorator) {
        this.shieldDecorator = shieldDecorator;
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
    public void setParameters(ArrayList<Entity> blocks, Points pts, ArrayList<StaticDamage> spikes, Shape player, Shape reward, ArrayList<Thrower> t, ThreeBulletsTurret tbt, Entity upgrade) {
        this.intesting = true;
        this.blocks = blocks;
        this.pts = pts;
        this.spikes = spikes;
        this.playerHitbox = player;
        this.reward = reward;
        this.throwers = new ArrayList<>();
        this.throwers.add(t.get(0));
        this.lasers = new ArrayList<>();
        this.lasers.add(t.get(1));
        this.turrets = new ArrayList<ShootingEnemy>();
        this.turrets.add(tbt);
        ArrayList<Entity> upgrades = new ArrayList<Entity>();
        upgrades.add(upgrade);
        this.power = new Powerup(upgrades);
        this.upgrade = power.iterator().next().getHitBox();
    }
}
