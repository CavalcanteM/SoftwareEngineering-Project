/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsaacMain;

import java.util.ArrayList;
import menu.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author danya
 */
public class MainMenu extends BasicGameState {

    private Menu menu;
    private ArrayList<Button> buttons;
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        buttons = new ArrayList<Button>();
        buttons.add(new Button(50, 150, new ContinueGame(), "Continue Game"));
        buttons.add(new Button(50, 150, new SelectLevel(), "Select Level"));
        buttons.add(new Button(50, 150, new ChangeControls(), "Change Controls"));
        buttons.add(new Button(50, 150, new Exit(), "Quit"));
        menu = new Menu(buttons);
        menu.init(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        menu.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        menu.update(gc, i, sbg);
    }
}