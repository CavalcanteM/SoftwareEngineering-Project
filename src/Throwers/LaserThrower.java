package Throwers;

import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class LaserThrower implements Thrower {

    private ConcreteThrower ct;
    private Shape damageBox;
    private boolean active;
    private final int type;
    private float x;
    private float y;
    private int i;
    private ParticleSystem ft;
    private ConfigurableEmitter laser;
    private Image imm;
    private long lastTime;
    private long onTime;
    private long offTime;
    private long actualTime;
    private int difficulty;

    public LaserThrower(float x, float y, int size, int type, int difficulty) throws SlickException {
        this.lastTime = System.currentTimeMillis();
        this.onTime = 2000 + new Random().nextInt(1000);
        this.offTime = 1500 + new Random().nextInt(1000);
        this.actualTime = offTime;
        this.laser = new ConfigurableEmitter("laser");
        this.laser.spread.setValue(0.5f);
        this.laser.yOffset.setMax(30f * (size - 1));
        this.laser.initialSize.setEnabled(true);
        this.laser.initialSize.setMin(5f);
        this.laser.initialSize.setMax(10f);
        this.laser.initialDistance.setEnabled(true);
        this.laser.colors.remove(1);
        this.i = 0;
        this.active = true;
        this.type = type;
        this.ct = new ConcreteThrower(x, y, type);
        this.difficulty = difficulty;
        
        /*  
            This switch represent the management of the ParticleSystem and the
            ConfigurableEmitter according to the type of Thrower, defined by the
            int type
         */
        switch (this.type) {
            case 1:
                this.laser.initialSize.setMin(10f);
                this.laser.initialSize.setMax(15f);
                this.setParameters(
                    new Image("./src/graphics/png/thrower/renderLaser.png"), 
                    0f, 
                    30f*(size - 1.5f), 
                    0f, 
                    new Rectangle(x + 10f, y - 30 * size, 10, +30 * size),
                    new ParticleSystem("./src/graphics/png/thrower/laser.png"), 
                    x + 13.5f, 
                    y - size * 30 + 38
                );
//                this.imm = new Image("./src/graphics/png/thrower/renderLaser.png");
//                this.laser.yOffset.setMax(30f * (size - 1.5f));
//                this.laser.angularOffset.setValue(0f);
//                this.damageBox = new Rectangle(x + 10f, y - 30 * size, 10, +30 * size);
//                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser.png");
//                this.x = x + 13.5f;
//                this.y = y - size * 30 + 38;
                break;
            case 2:
                this.setParameters(
                    new Image("./src/graphics/png/thrower/renderLaser90.png"), 
                    30f*(size - 1.5f),
                    10f,  
                    90f, 
                    new Rectangle(x + 27, y + 10f, 30 * size, 10),
                    new ParticleSystem("./src/graphics/png/thrower/laser_90.png"), 
                    x + 30, 
                    y + 10
                );
//                this.imm = new Image("./src/graphics/png/thrower/renderLaser90.png");
//                this.laser.xOffset.setMax(30f * (size - 1.5f));
//                this.laser.yOffset.setMax(10f);
//                this.laser.angularOffset.setValue(90f);
//                this.damageBox = new Rectangle(x + 27, y + 10f, 30 * size, 10);
//                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser_90.png");
//                this.x = x + 30;
//                this.y = y + 10;
                break;
            case 3:
                this.setParameters(
                    new Image("./src/graphics/png/thrower/renderLaser180.png"), 
                    10f,  
                    30f*(size - 1.5f),
                    180f, 
                    new Rectangle(x + 10, y + 27, 10, 30 * size),
                    new ParticleSystem("./src/graphics/png/thrower/laser_180.png"), 
                    x + 10, 
                    y + 27
                );
//                this.imm = new Image("./src/graphics/png/thrower/renderLaser180.png");
//                this.laser.xOffset.setMax(10f);
//                this.laser.yOffset.setMax(30f * (size - 1.5f));
//                this.laser.angularOffset.setValue(180f);
//                this.damageBox = new Rectangle(x + 10, y + 27, 10, 30 * size);
//                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser_180.png");
//                this.x = x + 10;
//                this.y = y + 27;
                break;
            default:
                this.setParameters(
                    new Image("./src/graphics/png/thrower/renderLaser270.png"), 
                    30f*(size - 1.5f),
                    10f,  
                    270f, 
                    new Rectangle(x - 30 * size, y + 10f, 30 * size, +10),
                    new ParticleSystem("./src/graphics/png/thrower/laser_270.png"), 
                    x - size * 30 + 45, 
                    y + 10
                );
//                this.imm = new Image("./src/graphics/png/thrower/renderLaser270.png");
//                this.laser.xOffset.setMax(30f * (size - 1.5f));
//                this.laser.yOffset.setMax(10f);
//                this.laser.angularOffset.setValue(270f);
//                this.damageBox = new Rectangle(x - 30 * size, y + 10f, 30 * size, +10);
//                this.ft = new ParticleSystem("./src/graphics/png/thrower/laser_270.png");
//                this.y = y + 10;
//                this.x = x - size * 30 + 45;
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
        return active;
    }

    @Override
    public Shape getDamageBox() {
        return damageBox;
    }

    private void updateActive() {
        if ((System.currentTimeMillis() - this.lastTime) > this.actualTime) {
            this.active = !this.active;
            if (active) {
                this.actualTime = this.onTime;
            } else {
                this.actualTime = this.offTime;
            }
            this.lastTime = System.currentTimeMillis();
        }
    }

    @Override
    public void render() {
        if (this.active) {
            this.ft.render(this.x, this.y);
            ShapeRenderer.textureFit(this.damageBox, imm);
        }
    }

    @Override
    public void update(int delta) {
        this.ft.update(delta);
        updateActive();
    }

    @Override
    public int doDamage() {
        if (this.active) {
            return 2;
        } else {
            return 0;
        }
    }
    
    /**
     * 
     * @param xOffset
     * @param yOffset
     * @param angularOffset
     * @param damageBox
     * @param ft
     * @param x
     * @param y 
     */
    private void setParameters(Image imm, float xOffset, float yOffset, float angularOffset, Shape damageBox, ParticleSystem ft, float x, float y) {
        this.imm = imm;
        this.laser.xOffset.setMax(xOffset);
        this.laser.yOffset.setMax(yOffset);
        this.laser.angularOffset.setValue(angularOffset);
        this.damageBox = damageBox;
        this.ft = ft;
        this.y = y;
        this.x = x;
    }
}
