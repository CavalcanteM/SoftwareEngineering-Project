package skins;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AdventurerAnimations extends BasicAnimations {
    
    public AdventurerAnimations(int width, int height, int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(width, height, runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    /**
     * Loads the frame for the run animation at the specified index 
     * @param index
     * @return 
     */
    @Override
    public Image setRunAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/adventurer/Run__00" + index + ".png"); 
    }
    
    /**
     * Loads the frame for the idle animation at the specified index 
     * @param index
     * @return 
     */
    @Override
    public Image setIdleAnimationFrame(int index) throws SlickException{
        return new Image("./graphics/adventurer/Idle__00" + index + ".png");
    }
    
    /**
     * Loads the frame for the death animation at the specified index
     * @param index
     * @return 
     */
    @Override
    public Image setDeathAnimationFrame(int index) throws SlickException{
        super.setDimensions(68, 70);
        return new Image("./graphics/adventurer/Dead__00" + index + ".png");
    }    
}