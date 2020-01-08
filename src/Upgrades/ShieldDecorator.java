package Upgrades;

import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

/**
 * This upgrade gives to the player a shield that it protects him until the next
 * damage for a certain period of time
 */
public class ShieldDecorator extends UpgradeDecorator {

    private Image imm;
    private boolean upgradeActive;
    private long activationTime;
    private long durate;
    private boolean damaged;
    private Shape shape;

    /**
     * The object's constructor
     *
     * @param player
     */
    public ShieldDecorator(UpgradeComponent player) {
        super(player);
        durate = 20000;
        damaged = false;
        execute();
    }

    /**
     * this method renders the shield around the player
     *
     * @param gc
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (imm == null) {
            imm = new Image("./graphics/png/ShieldImage.png");
        }
        if (upgradeActive) {
            shape = new Rectangle(super.getPlayer().getCenterX() - 35, super.getPlayer().getCenterY() - 35, 70, 70);
            ShapeRenderer.textureFit(shape, imm);

        }
        super.render(gc, g);
    }

//    /**
//     * 
//     * @return the parameter upgradeActive
//     */
//    
//    public boolean isUpgradeActive(){
//        return upgradeActive;
//    }
//    
    /**
     * Activation of the powerUp
     */
    @Override
    public void execute() {
        upgradeActive = true;
        this.activationTime = System.currentTimeMillis();
    }

    /**
     * Check if the durate of the powerUp is ended. If ended, the powerUp is
     * disactived
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if ((this.upgradeActive && ((System.currentTimeMillis() - this.activationTime) > this.durate))) {
            this.upgradeActive = false;
        }
        super.update(gc, delta);
    }

    /**
     * After a damage the durate of the shield is setted to one or an hald of
     * second
     *
     * @param damage
     */
    @Override
    public void getDamaged(int damage) {
        if (!damaged) {
            this.durate = System.currentTimeMillis() - this.activationTime + (1000 / damage);
            damaged = true;
        }
        else if(!upgradeActive){
            super.getDamaged(damage);
        }
    }
}
