package ShootingEnemies;

import ShootingEnemy.bullet.Bullet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ThreeBulletsTurret implements ShootingEnemy {

    private Shape hitboxArea,hitboxturret;
    int x, y, i;
    ArrayList<Bullet> bulletList = new ArrayList<>();
    private long lastHitTime = System.currentTimeMillis() - 3000;
    private long waitingTime = 3000;
    private long shootTime = 200;
    private int k = 0, difficulty;
    private long currentTime = waitingTime;

    public ThreeBulletsTurret(int x, int y, Shape hitboxArea, int difficulty) {
        this.x = x;
        this.y = y;
        this.hitboxArea = hitboxArea;
        this.difficulty = difficulty;
        this.hitboxturret = new Rectangle(x * 30, y * 30, 30, 30);
    }

    public ArrayList<Bullet> getBullet() {
        return this.bulletList;
    }

    @Override
    public Shape getActivationArea() {
        return this.hitboxArea;
    }

    @Override
    public Bullet Shoot(float x2, float y2) {
        Bullet bullet = null;
        if ((System.currentTimeMillis() - this.lastHitTime) > currentTime) {

            try {
                bullet = new Bullet(x, y, x2, y2, hitboxArea, this);
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

    @Override
    public void render(Graphics g) throws SlickException {
        //g.setColor(Color.red);
        //g.draw(hitboxArea);

        if (bulletList != null) {
            for (int i = 0; i < bulletList.size(); i++) {
                bulletList.get(i).render(g);
            }
        }
    }

    public void removeBullet(Bullet bul) {
        bulletList.remove(bul);
    }
    
     @Override
    public Shape getHitbox() {
       return hitboxturret;
    }

    @Override
    public boolean isVisible() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
