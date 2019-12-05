/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 *
 * @author fabrizio
 */
public class Point {
    
    private Shape point;
    private int l;
    private int h;
    private int x;
    private int y;
    private Image image;
    
    public Point(int l, int h, int x, int y) throws SlickException{
        this.h=h;
        this.l=l;
        this.x=x;
        this.y=y; 
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Shape getPoint() {
        return point;
    }
  
    public void init(){
        point = new Rectangle(this.l, this.h, this.x, this.y); 
        
    }
    
    public void update(GameContainer gc, int delta) throws SlickException{
        
    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException{
       Image image = new Image("./graphics/png/burger_s.png");
       ShapeRenderer.textureFit(point, image);
    }
    
}
