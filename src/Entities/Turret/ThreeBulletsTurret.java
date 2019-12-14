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
    public Bullet Shoot(float x2, float y2) {
        Bullet asd = new Bullet(x, y, x2, y2, hitboxArea);
        bulletList.add(asd);
        return asd;
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.setColor(Color.red);
        g.draw(hitboxArea);     
        
        if (bulletList != null) {
            for (int i = 0; i < bulletList.size(); i++) {
                bulletList.get(i).render(g);
            }
        }
    }

    public void removeBullet(int i) {
        bulletList.remove(i);
    }

}
