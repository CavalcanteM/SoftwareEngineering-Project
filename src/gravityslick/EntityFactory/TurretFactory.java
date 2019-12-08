/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick.Factory;

import Entities.Entity;
import Entities.Reward;
import Entities.Turret.*;
import Entities.Turret.Turret;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Adria
 */
public class TurretFactory {
    
    private int x;
    private int y;
    private int objlayer;
    private int TurretType;
    private TiledMap map;
    
    /**
     * The constructor needs the TileD map in which
     * the character has to move and extract the ID
     * if the layer called "Obj"
     * @param map
     */
    public TurretFactory(TiledMap map) {
        this.map = map;
        this.objlayer = this.map.getLayerIndex("Turret");
    }
    
    public ArrayList<Turret> getTurrets() {
        ArrayList<Turret> Turret = new ArrayList<>();
        for(y = 0; y < map.getHeight(); y++){
            for(x = 0; x < map.getWidth(); x++){
                TurretType= map.getTileId(x, y, objlayer);
                /*  In this "if" we check if in the tiles with coordinates(x,y) there is an object. 
                    If it is present, we create a shape 30x30 pixels in the position of the object. */
                if(TurretType == 10){
                    Turret.add(new FireTurret(x*30, y*30));
                }
                else if ( TurretType == 9)
                {
                    Turret.add(new BasicTurret(x*30,y*30));
                }
                 else if ( TurretType == 8)
                {
                    Turret.add(new FastTurret(x*30,y*30));
                }
            }
        }
        return Turret;
    }
    
}
