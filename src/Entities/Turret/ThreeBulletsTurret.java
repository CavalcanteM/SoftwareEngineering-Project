package Entities.Turret;

import Entities.Turret.Bullets.Bullet;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class ThreeBulletsTurret implements ShootingEnemy {

    private Shape hitboxArea;
    int x, y, i;
    ArrayList<Bullet> bulletList = new ArrayList<>();
    private long lastHitTime = System.currentTimeMillis() - 3000;

    public ThreeBulletsTurret(int x, int y, Shape hitboxArea) {
        this.x = x;
        this.y = y;
        this.hitboxArea = hitboxArea;
    }

    @Override
    public Shape getHitboxArea() {
        return this.hitboxArea;
    }

    @Override
    public ArrayList<Bullet> Shoot(float x2, float y2) {
        if ((System.currentTimeMillis() - this.lastHitTime) > 3000) {
            this.lastHitTime = System.currentTimeMillis();
            ArrayList<Bullet> temp = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                temp.add(new Bullet(x, y, x2, y2, hitboxArea, this));

            }

            bulletList.addAll(temp);
            return temp;
        } else {
            return null;
        }
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.setColor(Color.red);
        //  g.draw(hitboxArea);     

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
