package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Wall implements Entity {

    /*
    The "Wall" class represents the single block used to build the static 
    scenario. The player can collide with it without taking damage.
     */
    private Shape Hitbox;

    public Wall(int x, int y) {
        Hitbox = new Rectangle(x, y, 30, 30);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

    }

    @Override
    public Shape getHitBox() {
        return Hitbox;
    }

}
