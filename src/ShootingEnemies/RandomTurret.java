/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShootingEnemies;

import ShootingEnemy.bullet.Bullet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 *
 * @author Adria
 */
public class RandomTurret implements ShootingEnemy {

    private Shape hitboxArea, hitboxturret;
    int x, y, i;
    public boolean visible;
    ArrayList<Bullet> bulletList = new ArrayList<>();
    private long lastHitTime = System.currentTimeMillis() - 3000;
    private long waitingTime = 3000, shootTime = 200;
    private int k = 0, difficulty;
    private long currentTime = waitingTime;
    private Image image;

    public RandomTurret(int x, int y, Shape hitboxArea, int difficulty)  {
        this.x = x;
        this.y = y;
        this.hitboxturret = new Rectangle(x, y, 30, 30);
        this.hitboxArea = hitboxArea;
        this.difficulty = difficulty;
        
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
        this.image = new Image("./graphics/png/Nut.png");
        if (visible == true){
            ShapeRenderer.textureFit(this.hitboxturret, image);
        }

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
    public void setVisible(boolean t) {
        visible = t;
    }
}
