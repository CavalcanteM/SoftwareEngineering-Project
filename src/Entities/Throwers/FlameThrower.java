package Entities.Throwers;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class FlameThrower implements Thrower{
    
    private final Polygon hitBox;
    private Polygon damageBox;
    private boolean active;
    private final int type;
    private float x;
    private float y;
    private int i;
    private ParticleSystem ft;
    private ConfigurableEmitter fire;
    private Shape rect;

    public FlameThrower(float x, float y, int size, int type){
        this.fire = new ConfigurableEmitter("fire"); 
        this.fire.spread.setValue(0f);
        this.fire.initialSize.setMax(25);
        this.fire.initialSize.setMin(5);
        this.fire.initialSize.setMax(50);
        this.i = 0;
        this.hitBox = new Polygon();
        this.active = true;
        this.type = type;
        this.damageBox = new Polygon();
        switch (this.type) {
            case 1:
                this.fire.yOffset.setMax(30f*(size-1));
                this.fire.xOffset.setMax(10f);
                this.fire.angularOffset.setValue(0f);
                this.rect= new Rectangle(x + 7.5f,y,+15,-30*size);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/fire.png");
                this.hitBox.addPoint(x, y+30);
                this.hitBox.addPoint(x+15, y+3);
                this.x = x + 15;
                this.y = y +3;
                this.hitBox.addPoint(x+30, y+30);
                this.x -= 5;
                this.y -= size*30-30;
                break;
            case 2:
                this.fire.yOffset.setMax(10f);
                this.fire.xOffset.setMax(30f*(size-1));
                this.fire.angularOffset.setValue(90f);
                this.rect = new Rectangle(x+30, y+7.5f ,30*size,15);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/fire_90.png");   
                this.hitBox.addPoint(x, y);
                this.hitBox.addPoint(x+27, y+15);
                this.x = x + 27;
                this.y = y + 15;
                this.hitBox.addPoint(x, y+30);
                this.y -= 5;
                break;
            case 3:
                this.fire.yOffset.setMax(30f*(size-1));
                this.fire.xOffset.setMax(10f);
                this.fire.angularOffset.setValue(180f);
                this.rect = new Rectangle(x+7.5f,y+25+3,15,30*size);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/fire_180.png");   
                this.hitBox.addPoint(x, y);
                this.hitBox.addPoint(x+15, y+27);
                this.x = x + 15;
                this.y = y + 27;
                this.hitBox.addPoint(x+30, y);
                this.x -= 5;
                break;
            default:
                this.hitBox.addPoint(x+30, y);
                this.hitBox.addPoint(x, y+15);
                this.x = x;
                this.y = y + 15;
                this.hitBox.addPoint(x+30, y+30);
                this.fire.yOffset.setMax(10f);
                this.fire.xOffset.setMax(30f*(size-1));
                this.fire.angularOffset.setValue(270f);
                this.rect = new Rectangle(x,y+7.5f,-30*size,+15);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/fire_270.png");   
                this.y -= 5;
                this.x -= size*30-30;
                break;
        }
        this.ft.addEmitter(fire);
    }
    
    @Override
    public Shape getHitBox() {
        return this.hitBox;
    }

    @Override
    public boolean isActive() {
        this.setReset();
        return active;
    }

    @Override
    public void setReset() {
        i++;
        if(i == 180){
            this.active = !this.active;
            this.i = 0;
        }
    }
    
    @Override
    public Shape getDamageBox() {
        return rect;
    }
    
    public void render(){
        switch (this.type) {
            case 1:
                
        }
        this.ft.render(this.x, this.y);
    }
    
    public void update(int delta){
        this.ft.update(delta);
    }
    
    public float getYOffset(){
        return this.fire.yOffset.getMax();
    }
}