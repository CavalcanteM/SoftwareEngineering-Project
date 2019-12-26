package ShootingEnemy;

import ShootingEnemy.Bullet.Bullet;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public interface ShootingEnemy {

    public Shape getActivationArea();
    
    public Shape getHitbox();

    public Bullet Shoot(float x2, float y2);

    public void render(Graphics g,TiledMap map) throws SlickException;

    public void removeBullet(Bullet bul);

    public ArrayList<Bullet> getBullet();
    
    public boolean isVisible();
}
