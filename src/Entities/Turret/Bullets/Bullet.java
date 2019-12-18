package Entities.Turret.Bullets;

import Entities.Turret.ShootingEnemy;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
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

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public Bullet(int x1, int y1, float x2, float y2, Shape hitboxArea, ShootingEnemy turret) {
        this.x1 = x1 * 30;
        this.x2 = x2;
        this.y1 = y1 * 30;
        this.y2 = y2;
        this.hitboxArea = hitboxArea;
        velocity = 5;
        this.turret=turret;

        this.bullet = new Circle(this.x1 + 15, this.y1 + 15, 5);
        ComputeVelocity(this.x1, this.y1, x2, y2);
    }

    private void ComputeVelocity(int x1, int y1, float x2, float y2) {

        double x = x2 - x1;
        double y = y2 - y1;

        //Pithagorean theorem to compute the vectorial components and get the velocities.
        double distance = Math.sqrt(x * x + y * y);
        vX = x / Math.abs(distance) * velocity;
        vY = y / Math.abs(distance) * velocity;

        System.out.println("Start: " + x1 + "," + y1 + " to: " + x2 + "," + y2 + ". Directions: " + vX + "," + vY + ". Components: " + x + " " + y + " Distance: " + distance);
    }

    public Shape getShape() {

        if (hitboxArea.contains(bullet)) {
            return bullet;
        } else {
            return null;
        }
    }

    public int getDamage() {
        return 1;
    }

    private void UpdateImage() {
        bullet.setX((float) (bullet.getX() + vX));
        bullet.setY((float) (bullet.getY() + vY));
    }

    public void render(Graphics g) throws SlickException {

        if (hitboxArea.contains(bullet)) {
            UpdateImage();
            g.setColor(Color.red);
            g.fill(bullet);
        }
    }
    
    public void remove(){
        turret.removeBullet(this);
    }
}
