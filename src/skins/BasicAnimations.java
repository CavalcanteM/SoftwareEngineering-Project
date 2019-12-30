/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skins;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danya
 */
public abstract class BasicAnimations implements Animations{
    
    protected Animation rightAnimation;
    protected Animation leftAnimation;
    protected Animation idleAnimationRight;
    protected Animation idleAnimationLeft;
    protected Animation deathAnimationRight;
    protected Animation deathAnimationLeft;
    public final int RUN_ANIMATION_LENGTH;
    public final int IDLE_ANIMATION_LENGTH;
    public final int DEATH_ANIMATION_LENGTH;
    
    public BasicAnimations(int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        this.RUN_ANIMATION_LENGTH = runAnimationLength;
        this.IDLE_ANIMATION_LENGTH = idleAnimationLength;
        this.DEATH_ANIMATION_LENGTH = deathAnimationLength;
    }
    
    @Override
    public Animation getRightAnimation(){
        return rightAnimation;
    }
    
    @Override
    public Animation getLeftAnimation(){
        return leftAnimation;
    }
    
    @Override
    public Animation getIdleAnimationRight(){
        return idleAnimationRight;
    }
    
    @Override
    public Animation getIdleAnimationLeft(){
        return idleAnimationLeft;
    }
    
    @Override
    public Animation getDeathAnimationRight(){
        return deathAnimationRight;
    }
    
    @Override
    public Animation getDeathAnimationLeft(){
        return deathAnimationLeft;
    }
    
    @Override
    public int getRunAnimationLength(){
        return RUN_ANIMATION_LENGTH;
    }
    
    @Override
    public int getIdleAnimationLength(){
        return IDLE_ANIMATION_LENGTH;
    }
    
    @Override
    public int getDeathAnimationLength(){
        return DEATH_ANIMATION_LENGTH;
    }    
    
    /**
     * Creates the animations for the character
     * @throws SlickException
     */
    @Override
    public abstract void createAnimations() throws SlickException;
}
