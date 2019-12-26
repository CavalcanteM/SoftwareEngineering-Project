package IsaacMain.Upgrades;

import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

public class ShieldDecorator extends UpgradeDecorator{

    private Image imm;
    private boolean upgradeActive;
    private long activationTime;
    private long durate;
    private boolean damaged = false;
    private Shape shape;
    
    public ShieldDecorator(Player player) {
        super();
        durate = 20000;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        imm = new Image("./graphics/png/ShieldImage.png");
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if(imm == null){
            this.init(gc);
        }
        shape = new Rectangle(super.getPlayer().getCenterX()-30, super.getPlayer().getCenterY()-30, 60, 60);
        ShapeRenderer.textureFit(shape, imm);
    }
    
    @Override
    public boolean isUpgradeActive(){
        return upgradeActive;
    }
    
    @Override
    public void activation(){
        upgradeActive = true;
        this.activationTime = System.currentTimeMillis();
        super.setShieldDecorator(this);
        super.setShield(upgradeActive);
    }
    
    @Override
    public void updateActive() {
        if (this.upgradeActive && ((System.currentTimeMillis() - this.activationTime) > this.durate)) {
            System.out.println("Disattivazione");
            this.upgradeActive = false;
            super.setShield(upgradeActive);
        }
    }
    
    @Override
    public void getDamaged(int damage){
        if(!damaged){
            this.durate = System.currentTimeMillis() - this.activationTime + (1000/damage);
            damaged = true;
        }
    }
}