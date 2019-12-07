/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollisionDetection;

import Entities.Wall;
import Main.Player;
import Main.StaticLevel;

/**
 *
 * @author Heisenberg
 */
public class CollisionDetectionWall extends CollisionDetectionStrategy {
    
    
    public CollisionDetectionWall(StaticLevel level){
        this.walls=level.getWalls();
        this.map=level.getMap();
    }

    @Override
    public boolean detectCollision(Player player) {
        boolean collision = false;
        for (Wall wall : walls) {
            if (wall.intersects( player.getShape() )) {
                collision = true;
                break;
            }
        }
        return collision;
    }

}
