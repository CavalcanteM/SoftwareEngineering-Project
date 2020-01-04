/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsaacMain;

import java.io.File;
import javax.swing.JOptionPane;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Adria
 */
public class Tutorial extends BasicGameState {

    Image img;
    int index;
    boolean notshown;

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        notshown = true;
        index = 0;
        img = new Image("./graphics/tutorial/Image_" + index + ".jpg");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
            index++;
            if (index < 2) {
                img = new Image("./graphics/tutorial/Image_" + index + ".jpg");

            } else {
                sbg.enterState(1);
            }
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        if (index == 0 && notshown) {
            show_dialog(gc, sbg, grphcs);
        }
        img.draw();
    }

    public void show_dialog(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        notshown = false;
        if (JOptionPane.showConfirmDialog(null, "If you proceed all the previous achivements will be lost, do you want to proceed?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            File f = new File("./save.txt");
            if(f.delete())
            {
                System.out.println("Hai ricominciato ");
            }
            else
            {
                System.out.println("errore");
            }
            sbg.getState(1).init(gc, sbg);
            
            // Reimposta il salvataggio ad essere quello del livello 0 pianeta 0.
        } else {
            
            sbg.enterState(0);
        }

    }

}