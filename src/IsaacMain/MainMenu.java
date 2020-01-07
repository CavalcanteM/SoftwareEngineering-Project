package IsaacMain;


import java.util.ArrayList;
import menu.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {

    private Menu menu;
    private ArrayList<Button> buttons;
    private Sound music;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        buttons = new ArrayList<Button>();
        buttons.add(new Button(50, 150, new NewGame(), "New Game"));
        buttons.add(new Button(50, 150, new ContinueGame(), "Continue Game"));
        buttons.add(new Button(50, 150, new ChooseLevel(), "Select Level"));
        buttons.add(new Button(50, 150, new ChangeControls(this.getID()), "Settings"));
        buttons.add(new Button(50, 150, new Exit(), "Quit"));
        menu = new Menu(buttons, true);
        music = new Sound("./src/sound/mainmenu.wav");
        music.play();
        menu.init(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image background = new Image("./graphics/background.png");
        background = background.getScaledCopy(960, 720);
        background.draw(0,0);
        menu.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        menu.update(gc, i, sbg);
    }
}
