package Entities.Turret.Bullets;

import java.util.ArrayList;
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
    Shape bullet, hitboxArea;
    ArrayList<Double> increments;
    TiledMap map;
    public static Graphics g;

    public static void setG(Graphics g) {
        Bullet.g = g;
    }

    public Bullet(int x1, int y1, float x2, float y2, Shape hitboxArea) {
        this.x1 = x1 * 30;
        this.x2 = x2;
        this.y1 = y1 * 30;
        this.y2 = y2;
        this.hitboxArea = hitboxArea;

        this.bullet = new Rectangle(this.x1, this.y1, 30, 30);
        ComputeVelocity(this.x1, this.y1, x2, y2);
    }

    private void ComputeVelocity(int x1, int y1, float x2, float y2) {

        double x = x2 - x1;
        double y = y2 - y1;

        //Pithagorean theorem to compute the vectorial components and get the velocities.
        double distance = Math.sqrt(x * x + y * y);
        vX = x / Math.abs(distance) / 60;
        vY = y / Math.abs(distance) / 60;

        //System.out.println("Start: " + x1 + "," + y1 + " to: " + x2 + "," + y2 + ". Directions: " + vX + "," + vY + ". Components: " + x + " " + y + " Distance: " + distance);
    }


    public Shape getShape() {

        if (hitboxArea.contains(bullet)) {
            move();
            return bullet;
        } else {
            return null;
        }
    }

    public int getDamage() {
        return 1;
    }

    private void move() {
        bullet.setX((int) (bullet.getX() + (float)vX));
        bullet.setY((int) (bullet.getY() + (float)vY));
    }

    public void render(Graphics g) throws SlickException {
        ShapeRenderer.draw(new Rectangle(1 * 30, 15 * 30, 15 * 30, 90));
        System.out.println("disegna");

    }
}
