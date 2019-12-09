/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick;

import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

//Questa classe viene inizializzata nel costruttore di player (riga 55) e 
//ne tiene un riferimento come parametro di classe (riga 50).
//è stato commentato il metodo collidesWith in player in riga 527
//e sono stati modificati gli if in riga 463 e 479
/**
 * Manages collisions within the map. In this class the character's life and points 
 * are updated, with relative generation of subsequent rewards.
 */
public class Collision {
    //It keeps a reference for all objects that can cause collisions
    private HashMap<String,Integer> damageMap = new HashMap();
    private Player playerInstance;
    private StaticLevel level;
    private ArrayList<Shape> rtl;
    private Points pts;
    private Shape rwd;
    private ArrayList<Shape> spikes;
    
    /**
     * Inizialize all the instance of the class
     * @param level     
     */
    public Collision(StaticLevel level){
        this.damageMap.put("Obj", 0);
        this.damageMap.put("Rwd", 0);
        this.damageMap.put("Spk", 2);//Chiedere ai ragazzi se il danno deve essere 1 o 2
        this.level = level;
        this.rtl = level.getRtl();
        this.pts = level.getPts();
        this.spikes = new ArrayList<Shape>();//Aggiungere get delle spikes
        spikes.add(new Rectangle(30,30,30,30));//Creazione di uno spuntone fake
        this.playerInstance = Player.getPlayerInstance();
        this.playerInstance.setCollision(this);
        if(pts!=null){
            rwd = pts.iterator().next();
        }
    }
    
    
    /**
     * This method has to detect the collisions of the player
     * with the map's objects. This method call the increasing points function
     * if the player collides with a reward and also call the deacrising life 
     * function when the player collides with a spike
     * 
     * @return false if collides with a not stopping object or not collides, 
     * true if collides with a stopping object
     */
    public boolean collidesWith(){
        int i;
        if(pts != null){
            getReward();
        }
        
        //Check if the player collides with a spike
        if(spikes != null){
            for(i=0; i < spikes.size(); i++){
                if(playerInstance.getPlayer().intersects(spikes.get(i))){
                    playerInstance.getDamaged(damageMap.get("Spk"));
                }
            }
        }
        //check if the player collides with a obstacle
        if(rtl != null){
            for(i = 0; i < rtl.size(); i++){
                if(playerInstance.getPlayer().intersects(rtl.get(i))){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    /**
     * This method has the scope of the detection of the collision between Isaac 
     * and the Shape of the current reward that Isacc has to collect
     */
    private void getReward(){
        if(playerInstance.getPlayer().intersects(this.rwd)){
            this.rwd = pts.iterator().next();
        }
    }

}
