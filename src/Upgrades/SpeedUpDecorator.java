package Upgrades;

import IsaacMain.Player;

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
    public SpeedUpDecorator(Player player) {
        super();
        this.durate = 5000;
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
     * Check if the durate of the powerUp is ended. If ended, the powerUp is disactived
     */
    @Override
    public void updateActive() {
        if (this.upgradeActive && ((System.currentTimeMillis() - this.activationTime) > this.durate)) {
            super.setSpeedUp(1);
            this.upgradeActive = false;
        }
    }

    /**
     * The activation of this powerUp the speed doubles the
     */
    @Override
    public void activation(){
        this.upgradeActive = true;
        this.activationTime = System.currentTimeMillis();
        super.setSpeedUp(2);
        super.setSpeedUpDecorator(this);
        System.out.println("attivo");
    }
    
}
