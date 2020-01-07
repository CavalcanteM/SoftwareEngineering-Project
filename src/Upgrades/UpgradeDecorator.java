package Upgrades;

import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 * This class is extended by all the type of decorator
 * 
 */
public class UpgradeDecorator implements UpgradeComponent{

    protected UpgradeComponent player;

    public UpgradeDecorator() {
        player = Player.getPlayerInstance();
    }
    
    public Shape getHitbox(){
        return null;
    }
    
    public void updateActive(){
    }
    
    public boolean isUpgradeActive(){
        return false;
    }
    
    public void activation(){
        
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public int getNumHearts() {
        return player.getNumHearts();
    }

    @Override
    public boolean hasShield(){
        return player.hasShield();
    }
    
    @Override
    public void setNumHearts(int numHearts) {
        player.setNumHearts(numHearts);
    }

    @Override
    public void setNumVoidHearts(int NumVoidHearts) {
        player.setNumVoidHearts(NumVoidHearts);
    }

    @Override
    public void setSpeedUp(float speedUp) {
        player.setSpeedUp(speedUp);
    }

    @Override
    public void setShield(boolean shield) {
        player.setShield(shield);
    }

    @Override
    public int getNumVoidHearts() {
        return player.getNumVoidHearts();
    }

    @Override
    public void setSpeedUpDecorator(UpgradeDecorator speedUpDecorator) {
        player.setSpeedUpDecorator(speedUpDecorator);
    }
    
    @Override
    public void setShieldDecorator(UpgradeDecorator shieldDecorator) {
        player.setShieldDecorator(shieldDecorator);
    }

    @Override
    public void getDamaged(int damage) {
        player.getDamaged(damage);
    }

    @Override
    public Shape getPlayer() {
        return player.getPlayer();
    }
    
}
