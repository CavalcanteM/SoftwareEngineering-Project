/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Reward implements Entity {
    /*
    The "Reward" class represent the objects that the player has to catch to 
    win the level. 
    It has a Shape variable to mantain its hitbox.
    */
    
    private Shape Hitbox;
    int x, y;

    public Reward(int x, int y) {
        this.x = x;
        this.y = y;
        Hitbox = new Rectangle(x, y, 30, 30);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        Image image = new Image("./graphics/png/burger_s.png");
        image.draw(x, y);
    }

    @Override
    public Shape getHitBox() {
        return Hitbox;
    }

}
