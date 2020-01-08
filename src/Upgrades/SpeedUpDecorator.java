package Upgrades;

import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 * This powerup doubles the speed
 */
public class SpeedUpDecorator extends UpgradeDecorator{
    
    private long activationTime;
    private long durate;
    private boolean upgradeActive;
    
    /**
     * 
     * @param player 
     */
    public SpeedUpDecorator(UpgradeComponent player) {
        super(player);
        this.durate = 5000;
    }

    /**
     * 
     * @return the parameter upgradeActive
     */
    public boolean isUpgradeActive(){
        return upgradeActive;
    }        
    
    /**
     * Check if the durate of the powerUp is ended. If ended, the powerUp is disactived
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        System.out.println("Sono nell'update di SpeedUp");
        System.out.println("Sono passati" +(System.currentTimeMillis() - this.activationTime));
        if (((System.currentTimeMillis() - this.activationTime) > this.durate)) {
            super.setSpeedUp(1);
            this.upgradeActive = false;
        }else{
            super.setSpeedUp(2);
        }
        super.update(gc, delta);
    }

    /**
     * The activation of this powerUp the speed doubles the
     */
    @Override
    public void execute(){
        this.upgradeActive = true;
        this.activationTime = System.currentTimeMillis();
        System.out.println("Sono entrato a "+activationTime);
        super.setSpeedUp(2);
        super.setSpeedUpDecorator(this);
        System.out.println("attivo");
    }
    
}
