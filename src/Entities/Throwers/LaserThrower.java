package Entities.Throwers;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class LaserThrower implements Thrower{
    private ConcreteThrower ct;
    private Shape damageBox;
    private boolean active;
    private final int type;
    private float x;
    private float y;
    private int i;
    private ParticleSystem ft;
    private ConfigurableEmitter laser;
    
    public LaserThrower(float x, float y, int size, int type){
        this.laser = new ConfigurableEmitter("laser"); 
        this.laser.spread.setValue(0f);
        this.laser.yOffset.setMax(30f*(size-1));
        this.laser.initialSize.setEnabled(true);
        this.laser.initialSize.setMin(40f);
        this.laser.initialSize.setMax(45f);
        this.laser.initialDistance.setEnabled(true);
        this.i = 0;
        this.active = true;
        this.type = type;
        this.ct = new ConcreteThrower(x,y,type);
        /*  
            This switch represent the management of the ParticleSystem and the
            ConfigurableEmitter according to the type of Thrower, defined by the
            int type
        */
        switch (this.type) {
            case 1:
                this.laser.yOffset.setMax(30f*(size-1));
                this.laser.angularOffset.setValue(0f);
                this.damageBox = new Rectangle(x + 10f,y -30*size,10,+30*size);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser.png");
                this.x = x + 12.5f;
                this.y = y - size*30 + 33;
                break;
            case 2:
                this.laser.yOffset.setMax(10f);
                this.laser.xOffset.setMax(30f*(size-1));
                this.laser.angularOffset.setValue(90f);
                this.damageBox = new Rectangle(x+27, y+10f ,30*size,10);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser_90.png");
                this.x = x + 30;
                this.y = y + 10;
                break;
            case 3:
                this.laser.yOffset.setMax(30f*(size-1));
                this.laser.xOffset.setMax(10f);
                this.laser.angularOffset.setValue(180f);
                this.damageBox = new Rectangle(x+10,y+27,10,30*size);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser_180.png");   
                this.y = y + 27;
                this.x = x + 10;
                break;
            default:
                this.laser.yOffset.setMax(10f);
                this.laser.xOffset.setMax(30f*(size-1));
                this.laser.angularOffset.setValue(270f);
                this.damageBox = new Rectangle(x-30*size,y+10f,30*size,+10);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser_270.png");   
                this.y = y + 10;
                this.x = x - size*30 + 30;
                break;
        }
        this.ft.addEmitter(laser);
    }
    
    @Override
    public Shape getHitBox() {
        return this.ct.getHitBox();
    }
    
    @Override
    public boolean isActive() {
        this.setReset();
        return active;
    }
    
    @Override
    public void setReset() {
        i++;
        if(i == 120){
            this.active = !this.active;
            this.i = 0;
        }
    }
    
    @Override
    public Shape getDamageBox() {
        return damageBox;
    }
    
    
    @Override
    public void render(){
        this.ft.render(this.x, this.y);
    }
    
    @Override
    public void update(int delta){
        this.ft.update(delta);
    }
}
