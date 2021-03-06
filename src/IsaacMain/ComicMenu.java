package IsaacMain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * This class manages the comics that appear at the beginning and at the and of
 * each world
 *
 * @author danya
 */
public class ComicMenu extends BasicGameState {

    private Image imgent;
    private int exitphoto = 0;
    private int index = 0;
    private Shape skipB;
    private final int xSkip = 800;
    private final int ySkip = 650;
    private final int hSkip = 50;
    private final int lSkip = 150;
    private boolean flag = false;

    /**
     * method getID inherited from the class BasicGameState
     *
     * @return the ID of this state
     */
    @Override
    public int getID() {
        return 4;
    }

    /**
     * Method init inherited from the class BasicGameState
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        skipB = new Rectangle(xSkip, ySkip, lSkip, hSkip);

    }

    /**
     * Method render inherited from the class BasicGameState
     *
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(imgent, 0, 0);

        g.setColor(Color.white);
        g.fill(skipB);
        g.draw(skipB);
        g.setColor(Color.black);
        g.drawString("Skip", xSkip + (lSkip / 2) - (g.getFont().getWidth("Skip") / 2), ySkip + (hSkip / 2) - (g.getFont().getHeight("skip") / 2));
    }

    /**
     * method inherited from the class BasicGameState
     *
     * @param gc
     * @param sbg
     * @param i
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        if (flag == false) {
            flag = true;
            exitphoto = index + 1;
            imgent = new Image("./graphics/comic/comic_world" + GameIsaac.loadedWorld + "level" + GameIsaac.loadedLevel + "enter" + index + ".jpg");
            imgent = imgent.getScaledCopy(960, 720);
        }

        int posX = gc.getInput().getMouseX();
        int posY = gc.getInput().getMouseY();

        if (gc.getInput().isKeyPressed(Input.KEY_ENTER) || gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            if (GameIsaac.loadedWorld == 0 && index < 2) {
                index++;
                imgent = new Image("./graphics/comic/comic_world" + GameIsaac.loadedWorld + "level" + GameIsaac.loadedLevel + "enter" + index + ".jpg");
                imgent = imgent.getScaledCopy(960, 720);
            } else if (GameIsaac.loadedWorld != 0 && index < exitphoto) {
                index++;
                imgent = new Image("./graphics/comic/comic_world" + GameIsaac.loadedWorld + "level" + GameIsaac.loadedLevel + "enter" + index + ".jpg");
                imgent = imgent.getScaledCopy(960, 720);
                
            } else if (GameIsaac.loadedLevel == 3 && GameIsaac.loadedWorld == 3) {
                    index = 0;
                    flag = false;
                    sbg.enterState(0);
                } 
            else {

                flag = false;
                index = 0;
                gc.getInput().clearKeyPressedRecord();
                sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
            }
            
        }

        if (gc.getInput().isMouseButtonDown(0) && posX > this.xSkip && posX < (this.xSkip + lSkip)
                && posY > ySkip && posY < (ySkip + hSkip)) {
            flag = false;
            index = 0;
            gc.getInput().clearKeyPressedRecord();
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }

    /**
     * Deserializion of saves from file
     *
     * @return an object of the class Saves that contains the last world played
     * and the last level played
     * @throws SlickException
     */
    private Saves getSaves() throws SlickException {
        Saves saves;
        try {
            FileInputStream fis = new FileInputStream("save.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            saves = (Saves) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            saves = new Saves();
            saves.setLastLevel(0);
            saves.setLastWorld(0);
            imgent = new Image("./graphics/comic/comic_world" + index + "enter" + index + ".jpg");
            imgent = imgent.getScaledCopy(960, 720);
        }
        return saves;
    }
}
