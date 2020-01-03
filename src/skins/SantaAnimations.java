/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skins;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danya
 */
public class SantaAnimations extends BasicAnimations{
    
    public SantaAnimations(int width, int height, int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(width, height, runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    /**
     * 
     * @param index
     * @return 
     * @throws org.newdawn.slick.SlickException 
     */
    @Override
    public Image setRunAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/santa/Run (" + (index+1) + ").png");
    }
    
    /**
     * 
     * @param index
     * @return 
     * @throws org.newdawn.slick.SlickException 
     */
    @Override
    public Image setIdleAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/santa/Dead (" + (index+1) + ").png");
    }
    
    /**
     * 
     * @param index
     * @throws org.newdawn.slick.SlickException
     * @return 
     */
    @Override
    public Image setDeathAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/santa/Idle (" + (index+1) + ").png");
    }
}
