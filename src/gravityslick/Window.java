package gravityslick;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * This class represent the the game's window and use the Singleton pattern that allows
 * you to allocate a single instance of this class
*/
public class Window {
    
    /** 
     * Instance that guarantees the existence of only one instance of this class, 
     * according to the Singleton pattern
     */
    private static Window window = null;
    
    /** 
     * Instance of a SLICK2D's library and represent the
     * real game's window
     */
    private AppGameContainer gameContainer;
    
    /**
     * The other parameter represent the features of the game window
    */
    private int width = 960;
    private int height = 720;
    private boolean fullscreen = false;
    private int frameRate = 60;
    
    /**
     * The class' constructor is private in according to the Singleton pattern
     */
    private Window() throws SlickException{
        gameContainer = new AppGameContainer(new GameIsaac());
    }
    
    /**
     * This method return the game window, if it does not exist, it allocates one
     * @return the window
     * @throws org.newdawn.slick.SlickException
     */
    public static Window getWindow() throws SlickException{
        if(window==null){
            window = new Window();
        }
        return window;
    }
    
    /*--------------------
     * Getter methods
     *--------------------*/
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public int getFrameRate() {
        return frameRate;
    }

    /*--------------------
     * Setter methods
     *--------------------*/
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
    
    /**
     * This method use the AppGameContainer's methods to show the game window
     * @throws org.newdawn.slick.SlickException
     */
    public void showWindow() throws SlickException{
        gameContainer.setDisplayMode(this.width , this.height, this.fullscreen);
        gameContainer.setTargetFrameRate(frameRate);
        gameContainer.start();
    }    
}