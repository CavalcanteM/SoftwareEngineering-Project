package Throwers;

import java.util.Random;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

/*
    This class is an implementation of the interface Thrower, to see what each
    method does you need to go to see the comments of the implemented class
 */
public class FlameThrower implements Thrower {

    private ConcreteThrower ct;
    private Polygon damageBox;
    private boolean active;
    private final int type;
    private float x;
    private float y;
    private int i;
    private ParticleSystem ft;
    private ConfigurableEmitter fire;
    private Shape rect;
    private long lastTime;
    private long onTime;
    private long offTime;
    private long actualTime;

    public FlameThrower(float x, float y, int size, int type) {
        this.lastTime = System.currentTimeMillis();
        this.onTime = 2000 + new Random().nextInt(1000);
        this.offTime = 1500+ new Random().nextInt(1000);
        this.actualTime = offTime;
        this.fire = new ConfigurableEmitter("fire");
        this.fire.spread.setValue(0f);
        this.fire.initialSize.setMin(50);
        this.fire.initialSize.setMax(60);
        this.i = 0;
        this.active = true;
        this.type = type;
        this.damageBox = new Polygon();
        this.ct = new ConcreteThrower(x, y, type);
        /*  
            This switch represent the management of the ParticleSystem and the
            ConfigurableEmitter according to the type of Thrower, defined by the
            int type
         */
        switch (this.type) {
            case 1:
                this.fire.yOffset.setMax(30f * (size - 1));
                this.fire.xOffset.setMax(10f);
                this.fire.angularOffset.setValue(0f);
                this.rect = new Rectangle(x + 7.5f, y - 30 * size, +15, +30 * size);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/fire.png");
                this.x = x + 10;
                this.y = y - size * 30 + 33;
                break;
            case 2:
                this.fire.yOffset.setMax(10f);
                this.fire.xOffset.setMax(30f * (size - 1));
                this.fire.angularOffset.setValue(90f);
                this.rect = new Rectangle(x + 30, y + 7.5f, 30 * size, 15);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/fire_90.png");
                this.x = x + 30;
                this.y = y + 10;
                break;
            case 3:
                this.fire.yOffset.setMax(30f * (size - 1));
                this.fire.xOffset.setMax(10f);
                this.fire.angularOffset.setValue(180f);
                this.rect = new Rectangle(x + 7.5f, y + 25 + 3, 15, 30 * size);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/fire_180.png");
                this.y = y + 27;
                this.x = x + 10;
                break;
            default:
                this.fire.yOffset.setMax(10f);
                this.fire.xOffset.setMax(30f * (size - 1));
                this.fire.angularOffset.setValue(270f);
                this.rect = new Rectangle(x - 30 * size, y + 7.5f, 30 * size, +15);
                this.ft = new ParticleSystem("./src/graphics/png/thrower/fire_270.png");
                this.y = y + 10;
                this.x = x - size * 30 + 30;
                break;
        }
        this.ft.addEmitter(fire);
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
        return rect;
    }

    @Override
    public void render() {
        if (this.active) {
            this.ft.render(this.x, this.y);
        }
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
    public void update(int delta) {
        this.ft.update(delta);
        updateActive();
    }

    @Override
    public int doDamage() {
        if (this.active) {
            return 1;
        } else {
            return 0;
        }
    }
}
