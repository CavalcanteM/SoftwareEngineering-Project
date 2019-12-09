/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick.Factory;

import Entities.Wall;
import Entities.Entity;
import Entities.Reward;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Adria
 */
public class RewardList {

    private int x;
    private int y;
    private final int rewardlayer;
    private TiledMap map;
    
    /**
     * The constructor needs the TileD map in which
     * the character has to move and extract the ID
     * if the layer called "Obj"
     * @param map
     */
    public RewardList(TiledMap map) {
        this.map = map;
        this.rewardlayer = this.map.getLayerIndex("Reward");
    }
    
    
    public ArrayList<Entity> getEntities() {
        RewardFactory factory = new RewardFactory(this.map);
        ArrayList<Entity> Reward = new ArrayList<>();
        for(y = 0; y < map.getHeight(); y++){
            for(x = 0; x < map.getWidth(); x++){
                /*  In this "if" we check if in the tiles with coordinates(x,y) there is an object. 
                    If it is present, we create a shape 30x30 pixels in the position of the object. */
                if(map.getTileId(x, y, rewardlayer) != 0){
                    Reward.add(factory.getEntity(x*30,y*30));
                 //   Spikes.add(new Reward(x*30, y*30));
                }
            }
        }
        return Reward;
    }
    
}
