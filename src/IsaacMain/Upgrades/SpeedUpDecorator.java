package IsaacMain.Upgrades;

import IsaacMain.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class SpeedUpDecorator extends UpgradeDecorator{
    
    private long activationTime;
    private long durate;
    private boolean upgradeActive;
    
    public SpeedUpDecorator(Player player) {
        super();
        this.durate = 5000;
    }

    @Override
    public boolean isUpgradeActive(){
        return upgradeActive;
    }        
    
    @Override
    public void updateActive() {
        if (this.upgradeActive && ((System.currentTimeMillis() - this.activationTime) > this.durate)) {
            super.setSpeedUp(1);
            this.upgradeActive = false;
        }
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException{
        
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
    
    }

    public void activation(){
        this.upgradeActive = true;
        this.activationTime = System.currentTimeMillis();
        super.setSpeedUp(2);
        super.setSpeedUpDecorator(this);
        System.out.println("attivo");
    }
    
}
