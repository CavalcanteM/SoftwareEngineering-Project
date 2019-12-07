/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollisionDetection;

import Entities.*;
import Main.Player;
import java.util.List;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Heisenberg
 */
public abstract class CollisionDetectionStrategy implements Directions {

    protected TiledMap map;
    protected List<Wall> walls;
    protected List<Spike> spikes;

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Spike> getSpikes() {
        return spikes;
    }

    public abstract boolean detectCollision(Player player);

}
