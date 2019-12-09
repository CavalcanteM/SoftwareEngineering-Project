/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick.EntityFactory;

import Entities.Entity;
import Entities.Wall;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Adria
 */
public class WallFactory implements InterfaceEntityFactory {

    private int x;
    private int y;
    private final int wallLayer;
    private TiledMap map;

    public WallFactory(TiledMap map) {
        this.map = map;
        this.wallLayer = this.map.getLayerIndex("Walls");
    }

    @Override
    public Entity getEntity(int x, int y) {
        return new Wall(x, y);
    }

}
