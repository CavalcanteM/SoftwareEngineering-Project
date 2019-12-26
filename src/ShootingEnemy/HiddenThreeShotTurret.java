/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShootingEnemy;

import ShootingEnemy.ShootingEnemy;
import ShootingEnemy.ThreeBulletsTurret;
import ShootingEnemy.Bullet.Bullet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Adria
 */
public class HiddenThreeShotTurret implements ShootingEnemy {

    private Shape hitboxArea, hitboxturret;
    int x, y, i;
    public boolean visible;

    ArrayList<Bullet> bulletList = new ArrayList<>();
    private long lastHitTime = System.currentTimeMillis() - 3000;
    private long waitingTime = 3000, shootTime = 200;
    private int k = 0,j=0, difficulty;
    private long currentTime = waitingTime;
    

    public HiddenThreeShotTurret(int x, int y, Shape hitboxArea, int difficulty) {
        this.x = x;
        this.y = y;
        this.hitboxturret = new Rectangle((x * 30) + 10, (y * 30) + 10, 10, 10);
        this.hitboxArea = hitboxArea;
        this.difficulty = difficulty;
    }
    
    

    @Override
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
            visible = true;
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
    public void render(Graphics g,TiledMap map) throws SlickException {
                
        if (visible == true) {
            map.render(x*30,y*30,x,y,1,1,map.getLayerIndex("HiddenTurrets"),true);
        }

        for (Bullet b : bulletList) {
            b.render(g);
        }

    }

    public boolean isVisible() {
        return visible;
    }

    public void removeBullet(Bullet bul) {
        bulletList.remove(bul);
        if (++j % 3 == 0) {
            visible = false;
            System.out.println("Visible= "+visible);
        }
    }
    

    @Override
    public Shape getHitbox() {
       return hitboxturret;
    }

}
