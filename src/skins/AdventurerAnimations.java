package skins;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AdventurerAnimations extends BasicAnimations {
    
    public AdventurerAnimations(int width, int height, int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(width, height, runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public Image setRunAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/adventurer/Run__00" + index + ".png"); 
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public Image setIdleAnimationFrame(int index) throws SlickException{
        return new Image("./graphics/adventurer/Idle__00" + index + ".png");
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public Image setDeathAnimationFrame(int index) throws SlickException{
        super.setDimensions(68, 70);
        return new Image("./graphics/adventurer/Dead__00" + index + ".png");
    }
    
    
    
    
}
