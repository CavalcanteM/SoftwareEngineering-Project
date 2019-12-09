/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick.Factory;

import Entities.Wall;
import Entities.Entity;
import Entities.Reward;
import gravityslick.EntityFactory.InterfaceEntityFactory;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Adria
 */
public class RewardFactory implements InterfaceEntityFactory {

    private int x;
    private int y;
    private final int objlayer;
    private TiledMap map;

    /**
     * The constructor needs the TileD map in which the character has to move
     * and extract the ID if the layer called "Obj"
     *
     * @param map
     */
    public RewardFactory(TiledMap map) {
        this.map = map;
        this.objlayer = this.map.getLayerIndex("Bolts");
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public Entity getEntity(int x, int y) {
        return new Reward(x, y);
    }

}
