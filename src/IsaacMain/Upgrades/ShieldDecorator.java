package IsaacMain.Upgrades;

import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

/**
 * This upgrade gives to the player a shield that it protects him until the next damage for a certain period of time
 */
public class ShieldDecorator extends UpgradeDecorator{

    private Image imm;
    private boolean upgradeActive;
    private long activationTime;
    private long durate;
    private boolean damaged = false;
    private Shape shape;
    
    /**
     * The object's constructor
     * @param player 
     */
    public ShieldDecorator(Player player) {
        super();
        durate = 20000;
    }

    /**
     * This method initializes the image of the shield
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        imm = new Image("./graphics/png/ShieldImage.png");
    }

    /**
     * this method renders the shield around the player
     * @param gc
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if(imm == null){
            this.init(gc);
        }
        shape = new Rectangle(super.getPlayer().getCenterX()-35, super.getPlayer().getCenterY()-35, 70, 70);
        ShapeRenderer.textureFit(shape, imm);
    }
    
    /**
     * 
     * @return the parameter upgradeActive
     */
    @Override
    public boolean isUpgradeActive(){
        return upgradeActive;
    }
    
    /**
     * Activation of the powerUp
     */
    @Override
    public void activation(){
        upgradeActive = true;
        this.activationTime = System.currentTimeMillis();
        super.setShieldDecorator(this);
        super.setShield(upgradeActive);
    }
    
    /**
     * Check if the durate of the powerUp is ended. If ended, the powerUp is disactived
     */
    @Override
    public void updateActive() {
        if (this.upgradeActive && ((System.currentTimeMillis() - this.activationTime) > this.durate)) {
            System.out.println("Disattivazione");
            this.upgradeActive = false;
            super.setShield(upgradeActive);
        }
    }
    
    /**
     * After a damage the durate of the shield is setted to one or an hald of second
     * @param damage 
     */
    @Override
    public void getDamaged(int damage){
        if(!damaged){
            this.durate = System.currentTimeMillis() - this.activationTime + (1000/damage);
            damaged = true;
        }
    }
}