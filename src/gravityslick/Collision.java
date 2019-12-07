/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.newdawn.slick.geom.Shape;


/**
 * Manages collisions within the map. In this class the character's life and points 
 * are updated, with relative generation of subsequent rewards.
 */
public class Collision {
    //It keeps a reference for all objects that can cause collisions
    private HashMap<String,Integer> damageMap = new HashMap();
    private Player playerInstance;
    private Shape player;
    private StaticLevel level;
    private ArrayList<Shape> rtl;
    private Points pt;
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
        this.pt = level.getPts();
        this.spikes = null;//Aggiungere get delle spikes
        this.playerInstance = Player.getPlayerInstance(level);
        this.player = playerInstance.getPlayer();
        if(pt!=null){
            //rimuovere questa riga da player liin riga 224 e 225
            rwd = pt.iterator().next();
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
        //rimuovere getReward() dalla classe Player
        if(pt != null){
            getReward();
        }
        //Check if the player collides with a spike
        if(spikes != null){
            for(i=0; i < spikes.size(); i++){
                if(player.intersects(spikes.get(i))){
                    playerInstance.getDamaged(damageMap.get(i));
                    //Inserire in Player funzione per far lampeggiare il player
                }
            }
        }
        
        //check if the player collides with a obstacle
        if(rtl != null){
            for(i = 0; i < rtl.size(); i++){
                if(player.intersects(rtl.get(i))){
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
        if(player.intersects(this.rwd)){
            //this.score++;
            this.rwd = level.getPts().iterator().next();
        }
    }

}
