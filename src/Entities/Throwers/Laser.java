/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Throwers;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

/**
 *
 * @author fabrizio
 */
public class Laser implements Thrower{
    private ConcreteThrower ct;
    private Polygon damageBox;
    private boolean active;
    private final int type;
    private float x;
    private float y;
    private int i;
    private ParticleSystem ft;
    private ConfigurableEmitter laser;
    private Shape rect;
    
    public Laser(float x, float y, int size, int type){
        this.laser = new ConfigurableEmitter("laser"); 
        this.laser.spread.setValue(0f);
        this.laser.yOffset.setMax(30f*(size-1));
        this.laser.initialSize.setEnabled(true);
        this.laser.initialSize.setMin(15f);
        this.laser.initialSize.setMax(15f);
        this.laser.initialDistance.setEnabled(true);
        this.i = 0;
        this.active = true;
        this.type = type;
        this.damageBox = new Polygon();
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
                this.rect= new Rectangle(x-5,y-30*size,10,30*(size));
                this.rect= new Rectangle(x + 7.5f,y -30*size,10,+30*size);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser.png");
                this.x = x + 10;
                this.y = y - size*30 + 33;
                break;
            case 2:
                this.laser.yOffset.setMax(10f);
                this.laser.xOffset.setMax(30f*(size-1));
                this.laser.angularOffset.setValue(90f);
                this.rect = new Rectangle(x+3, y-3 ,30*size,10);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser_90.png");
                this.x = x + 30;
                this.y = y + 10;
                break;
            case 3:
                this.laser.yOffset.setMax(30f*(size-1));
                this.laser.xOffset.setMax(10f);
                this.laser.angularOffset.setValue(180f);
                this.rect = new Rectangle(x-3,y+this.laser.yOffset.getMax(),10,30*size);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser_180.png");   
                this.y = y + 27;
                this.x = x + 10;
                break;
            default:
                this.laser.yOffset.setMax(10f);
                this.laser.xOffset.setMax(30f*(size-1));
                this.laser.angularOffset.setValue(270f);
                this.rect = new Rectangle(x-30*size,y+7.5f,30*size,+15);
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
        return rect;
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
