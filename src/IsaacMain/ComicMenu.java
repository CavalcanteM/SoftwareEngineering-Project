/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author pando
 */
public class ComicMenu extends BasicGameState {

    private Saves saves;
    private Image imgent;
    private int exitphoto = 0;
    private int index = 0;
    private Shape skipB;
    private final int xSkip = 800;
    private final int ySkip = 650;
    private final int hSkip = 50;
    private final int lSkip = 150;
    private boolean flag = false;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        skipB = new Rectangle(xSkip, ySkip, lSkip, hSkip);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(imgent, 0, 0);

        g.setColor(Color.white);
        g.fill(skipB);
        g.draw(skipB);
        g.setColor(Color.black);
        g.drawString("Skip", xSkip + (lSkip / 2) - (g.getFont().getWidth("Skip") / 2), ySkip + (hSkip / 2) - (g.getFont().getHeight("skip") / 2));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        if (flag == false) {
            this.saves = getSaves();
            flag = true;
            exitphoto = index + 1;
            imgent = new Image("./graphics/comic/comic_world" + saves.getLastWorld() + "enter" + index + ".jpg");
            imgent = imgent.getScaledCopy(960, 720);
        }

        int posX = gc.getInput().getMouseX();
        int posY = gc.getInput().getMouseY();

        if (gc.getInput().isKeyPressed(Input.KEY_ENTER) || gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            if (saves.getLastWorld() == 0 && index < 2) {
                index++;
                imgent = new Image("./graphics/comic/comic_world" + saves.getLastWorld() + "enter" + index + ".jpg");
                imgent = imgent.getScaledCopy(960, 720);
            } else if (saves.getLastWorld() != 0 && index < exitphoto) {
                index++;
                imgent = new Image("./graphics/comic/comic_world" + saves.getLastWorld() + "enter" + index + ".jpg");
                imgent = imgent.getScaledCopy(960, 720);

            } else {
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
     * 
     * @return
     * @throws SlickException 
     */
    private Saves getSaves() throws SlickException {
        Saves saves;
        try {
            System.out.println("caricamento file");
            FileInputStream fis = new FileInputStream("save.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            saves = (Saves) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("creazione file");
            saves = new Saves();
            saves.setLastLevel(0);
            saves.setLastWorld(0);
            imgent = new Image("./graphics/comic/comic_world" + index + "enter" + index + ".jpg");
            imgent = imgent.getScaledCopy(960, 720);
        }

        System.out.println("ho letto dal file il mondo " + saves.getLastWorld());
        return saves;
    }
}
