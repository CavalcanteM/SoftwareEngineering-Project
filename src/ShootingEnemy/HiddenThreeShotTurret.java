package ShootingEnemy;

import ShootingEnemy.bullet.Bullet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class HiddenThreeShotTurret implements ShootingEnemy {

    private Shape hitboxArea, hitboxturret;
    int x, y, i;
    public boolean visible;

    ArrayList<Bullet> bulletList = new ArrayList<>();
    private long lastHitTime = System.currentTimeMillis() - 3000;
    private long waitingTime = 3000, shootTime = 200;
    private int k = 0, j = 0, difficulty;
    private long currentTime = waitingTime;

    /**
     *
     * @param x x position of the turret in the map
     * @param y y position of the turret in the map
     * @param hitboxArea activation area of the turret
     * @param difficulty represents the level of difficulty
     */
    public HiddenThreeShotTurret(int x, int y, Shape hitboxArea, int difficulty) {
        this.x = x;
        this.y = y;
        this.hitboxturret = new Rectangle((x * 30) + 10, (y * 30) + 10, 10, 10);
        this.hitboxArea = hitboxArea;
        this.difficulty = difficulty;
    }

    /**
     *
     * @return All the bullets that are being shoot by the turret
     */

    @Override
    public ArrayList<Bullet> getBullet() {
        return this.bulletList;
    }

    /**
     *
     * @return gets the activation area of the turret
     */
    @Override
    public Shape getActivationArea() {
        return this.hitboxArea;
    }

    /**
     *
     * @param x_player x position of the player in terms of pixels
     * @param y_player y position of the player in terms of pixels
     * @return the bullet that has been shot
     */
    @Override
    public Bullet Shoot(float x_player, float y_player) {

        Bullet bullet = null;
        if ((System.currentTimeMillis() - this.lastHitTime) > currentTime) {
            visible = true;
            try {
                bullet = new Bullet(x, y, x_player, y_player, hitboxArea, this);
                bulletList.add(bullet);
            } catch (SlickException ex) {
                Logger.getLogger(ThreeBulletsTurret.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (++k % 3 == 0) {
                currentTime = waitingTime;

            } else {
                currentTime = shootTime;
            }

            this.lastHitTime = System.currentTimeMillis();

            return bullet;
        } else {

            return bullet;
        }
    }

    /**
     *
     * @param g Graphics object needed for rendering.
     * @param map TiledMap object in which the turret is present.
     * @throws SlickException
     */
    @Override
    public void render(Graphics g, TiledMap map) throws SlickException {
        g.setColor(Color.yellow);
        g.draw(this.hitboxArea);
        if (visible == true) {
            map.render(x * 30, y * 30, x, y, 1, 1, map.getLayerIndex("HiddenTurrets"), true);
        }

        for (Bullet b : bulletList) {
            b.render(g);
        }

    }

    /**
     *
     * @return returns if the turret is currently visible.
     */
    @Override
    public boolean isVisible() {
        return visible;
    }

    /**
     *
     * @param bul destroys the bullet
     */
    @Override
    public void removeBullet(Bullet bul) {
        bulletList.remove(bul);
        if (++j % 3 == 0) {
            visible = false;
        }
    }

    /**
     *
     * @return hitbox of the turret
     */
    @Override
    public Shape getHitbox() {
        return hitboxturret;
    }

}
