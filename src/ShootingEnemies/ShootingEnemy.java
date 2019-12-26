package ShootingEnemies;

import ShootingEnemy.bullet.Bullet;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public interface ShootingEnemy {

    public Shape getActivationArea();

    public Bullet Shoot(float x2, float y2);

    public void render(Graphics g) throws SlickException;

    public void removeBullet(Bullet bul);

    public void setVisible(boolean t);
    
    public ArrayList<Bullet> getBullet();
}
