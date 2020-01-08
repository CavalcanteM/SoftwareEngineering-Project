package ShootingEnemy.bullet;

import ShootingEnemy.ShootingEnemy;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.tiled.TiledMap;

public class Bullet {

    int x1, y1;
    float x2, y2;
    double vX, vY;
    ShootingEnemy turret;
    Shape bullet, hitboxArea;
    ArrayList<Double> increments;
    TiledMap map;
    int velocity;
    Image sprite;
    boolean flag;
    Sound sound;

    /**
     *
     * @param velocity set the speed velocity
     */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    /**
     *
     * @return bullet velocity
     */
    public int getVelocity() {
        return this.velocity;
    }

    /**
     *
     * @param x1 x starting point of the bullet
     * @param y1 y starting point of the bullet
     * @param x2 x position of the player at the moment of the intersection with
     * the turret activation area
     * @param y2 x position of the player at the moment of the intersection with
     * the turret activation area
     * @param hitboxArea it is the activation area of the turret, the bullet
     * destroys himself once he goes outside of this box
     * @param turret the turret that shot the bullet
     * @throws SlickException
     */
    public Bullet(int x1, int y1, float x2, float y2, Shape hitboxArea, ShootingEnemy turret) throws SlickException {
        this.sprite = new Image("./graphics/png/Bullet.png");
        this.sound = new Sound("./src/sound/pew.wav");
        this.x1 = x1 * 30;
        this.x2 = x2;
        this.y1 = y1 * 30;
        this.y2 = y2;
        this.hitboxArea = hitboxArea;
        velocity = 5;
        this.turret = turret;
        this.flag = false;
        this.bullet = new Circle(this.x1 + 15, this.y1 + 15, 5);
        ComputeRoute(this.x1, this.y1, x2, y2);
    }

    /**
     * This function computes the distance of the player from the bullet
     * and the direction the bullet has to follow to hit the player.
     * @param x1 x starting point of the bullet
     * @param y1 y starting point of the bullet
     * @param x2 x position of the player at the moment of the intersection with
     * the turret activation area
     * @param y2 x position of the player at the moment of the intersection with
     * the turret activation area
     */
    void ComputeRoute(int x1, int y1, float x2, float y2) {

        double x = x2 - x1;
        double y = y2 - y1;

        //Pithagorean theorem to compute the vectorial components and get the velocities.
        double distance = Math.sqrt(x * x + y * y);
        vX = x / Math.abs(distance) * velocity;
        vY = y / Math.abs(distance) * velocity;
    }
/**
 * THis method returns Null if the bullet is no more in the hitboxArea.
 * @return the shape of the bullet
 */
    public Shape getShape() {

        if (hitboxArea.contains(bullet)) {
            return bullet;
        } else {
            remove();
            return null;
        }
    }
    /**
     * 
     * @return damage of the bullet, expressed in number of player's hearts.
     */
    public int getDamage() {
        return 1;
    }
/**
 * private method that serves to move the bullet
 */
    private void UpdateImage() {
        bullet.setX((float) (bullet.getX() + vX));
        bullet.setY((float) (bullet.getY() + vY));
        ShapeRenderer.textureFit(this.bullet, sprite);

        if (!this.sound.playing() && this.flag == false) {
            this.sound.play(1, 0.05f);
            this.flag = true;
        }
    }
/**
 * 
 * @param g Graphic needed to render the bullet
 * @throws SlickException 
 */
    public void render(Graphics g) throws SlickException {

        if (hitboxArea.contains(bullet)) {
            UpdateImage();
            ShapeRenderer.textureFit(this.bullet, sprite);
        }
    }
/**
 *  This function simply invokes turret.removeBullet(this)
 */
    public void remove() {
        turret.removeBullet(this);
    }
}
