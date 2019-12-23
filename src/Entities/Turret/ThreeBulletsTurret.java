package Entities.Turret;

import Entities.Turret.Bullets.Bullet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class ThreeBulletsTurret implements ShootingEnemy {

    private Shape hitboxArea;
    int x, y, i;
    ArrayList<Bullet> bulletList = new ArrayList<>();
    private long lastHitTime = System.currentTimeMillis() - 3000;
    private long waitingTime = 3000;
    private long shootTime = 200;
    private int k = 0;
    private long currentTime = waitingTime;

    public ThreeBulletsTurret(int x, int y, Shape hitboxArea) {
        this.x = x;
        this.y = y;
        this.hitboxArea = hitboxArea;
    }

    public ArrayList<Bullet> getBullet() {
        return this.bulletList;
    }

    @Override
    public Shape getHitboxArea() {
        return this.hitboxArea;
    }

    @Override
    public ArrayList<Bullet> Shoot(float x2, float y2) {

        if ((System.currentTimeMillis() - this.lastHitTime) > currentTime) {

            try {
                bulletList.add(new Bullet(x, y, x2, y2, hitboxArea, this));
            } catch (SlickException ex) {
                Logger.getLogger(ThreeBulletsTurret.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (++k % 3 == 0) {
                currentTime = waitingTime;
            } else {
                currentTime = shootTime;
            }

            this.lastHitTime = System.currentTimeMillis();

            return bulletList;
        } else {
            return null;
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

}
