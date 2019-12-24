package IsaacMain.Upgrades;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class SpeedUpDecorator extends UpgradeDecorator{

    private Image speedUpImage;
    private Shape hitbox;
    private long activationTime;
    private long durate;
    private boolean upgradeActive;
    
    public SpeedUpDecorator(int x, int y) {
        super();
        this.hitbox = new Rectangle(x, y, 30, 30);
        this.durate = 5000;
    }

    @Override
    public boolean isUpgradeActive(){
        return upgradeActive;
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
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
        this.speedUpImage = new Image("./graphics/png/speedUp.png");
        this.upgradeActive = false;
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if(speedUpImage != null && hitbox != null){
            ShapeRenderer.textureFit(this.hitbox, this.speedUpImage);
        }
    }

    public void activation(){
        this.hitbox = null;
        this.speedUpImage = null;
        this.upgradeActive = true;
        this.activationTime = System.currentTimeMillis();
        super.setSpeedUp(2);
    }
    
}
