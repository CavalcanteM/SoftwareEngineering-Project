package IsaacMain;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.Back;
import menu.Button;
import menu.Command;
import menu.Mapping;
import org.lwjgl.input.Mouse;
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
import org.newdawn.slick.KeyListener;

/**
 * This class manages the creation of a settings menu, in which the player can
 * change the keys to move and change gravity, the skin and the volume of the
 * game
 *
 * @author danya
 */
public class OptionMenu extends BasicGameState implements Serializable {

    final private int hButton = 50;
    final private int lButton = 150;
    final private int xSave = 100;
    final private int ySave = 500;
    final private int xKey = 300;
    final private int yKey = 100;
    final private int hKey = 30;
    final private int lKey = 70;
    final private int xVolume = 325;
    final private int yVolume = 350;
    final private int offsetVolumeX = 55;

    private Mapping options;
    private Integer volume;
    private String move;
    private String keys;
    private String commandToChange;
    private Button saveBack;
    private Mylistener ml;
    private Image frecciaDx;
    private Image frecciaSx;

    private ArrayList<Button> saveButtons;
    private ArrayList<Shape> saveShapes;
    private ArrayList<Shape> keyShapes;
    public static int previousState = -1;
    private Image[] skins;
    private static final int NUM_SKINS = 5;
    private static int currentSkinIndex;
    private Button nextSkin;
    private Button previousSkin;
    private boolean isChangingSkin;

    /**
     * Constructor of the class OptionMenu
     *
     * @throws SlickException
     */
    public OptionMenu() throws SlickException {

        this.options = new Mapping();
        Player.getPlayerInstance().setCommands(options);
        ml = new Mylistener();
        saveButtons = new ArrayList<>();
        saveShapes = new ArrayList<>();
        keyShapes = new ArrayList<>();
        move = "";
        keys = "";

        // creation of button to save changes
        saveBack = new Button(hButton, lButton, new Back(), "Back");

        // add the save button to the list to print
        saveButtons.add(saveBack);

        // creation of buttons to change the volume
        frecciaDx = new Image("./src/graphics/png/freccia.png");
        frecciaSx = frecciaDx.getFlippedCopy(true, false);

    }
    
    /**
     * Getter method for the parameter move
     * @return 
     */
    public String getMove() {
        return move;
    }
    
    /**
     * Method getID inherited from BasicGameState
     * @return 
     */
    @Override
    public int getID() {
        return 2; //state id di option
    }
    
    /**
     * Method init inherited from BasicGameState
     * @param gc
     * @param sbg 
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {

        this.volume = (int) gc.getMusicVolume() * 10;

        int tempSave = this.ySave;
        for (Button b : this.saveButtons) {
            saveShapes.add(new Rectangle(this.xSave, tempSave, b.getL(), b.getH()));
            tempSave += 70;
        }
        int tempKeys = this.yKey;

        for (String b : options.getCommandMap().keySet()) {
            if (b.equals("skinIndex")) {
            } else {
                keyShapes.add(new Rectangle(this.xKey, tempKeys, lKey, hKey));
                tempKeys += 60;
            }
        }
        keyShapes.add(new Rectangle(this.xKey, tempKeys, lKey, hKey));

        this.aggiornavalori();

        // Init del cambio skin
        this.skins = new Image[NUM_SKINS];
        try {
            this.skins[0] = new Image("./graphics/png/Idle (1).png");
            this.skins[1] = new Image("./graphics/adventurer/Idle__000.png");
            this.skins[2] = new Image("./graphics/jack/Idle (1).png");
            this.skins[3] = new Image("./graphics/ninja/Idle__000.png");
            this.skins[4] = new Image("./graphics/santa/Idle (1).png");
            this.currentSkinIndex = options.getCommandMap().get("skinIndex");

            // Initializing the Button to change skin on the left
            this.previousSkin = new Button(30, 30, new Command() {

                public void execute(GameContainer gc, int delta, StateBasedGame sbg) {
                    OptionMenu.currentSkinIndex = ((OptionMenu.currentSkinIndex - 1) + OptionMenu.NUM_SKINS) % OptionMenu.NUM_SKINS;
                }
            }, "<");

            // Initializing the Button to change the skin on the right
            this.nextSkin = new Button(30, 30, new Command() {

                public void execute(GameContainer gc, int delta, StateBasedGame sbg) {
                    OptionMenu.currentSkinIndex = ((OptionMenu.currentSkinIndex + 1) + OptionMenu.NUM_SKINS) % OptionMenu.NUM_SKINS;
                }
            }, ">");

        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Sets the volume of the music of the game
     * @param i the new value of the volume
     */
    public void setMusic(int i) {
        if (volume + i <= 10 && volume + i >= 0) {
            volume += i;
        }
    }
    
    /**
     * Updates the values of the parameters of the commands map 
     */
    protected void aggiornavalori() {
        move = "";
        keys = "";
        for (String s : options.getCommandMap().keySet()) {
            if (s.equals("skinIndex")) {
            } else {
                move += s + "\n\n\n";      // stringa dei movimenti "left,right...."
                keys += (Input.getKeyName(options.getCommandMap().get(s))) + "\n\n\n"; //questo keys contiene il tasto attuale assegnato al movimento
            }
        }
    }
    
    /**
     * Method render inherited from BasicGameState
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        //stampa lista move e keys
        g.drawString(move, 100, 100);
        for (int i = 0; i < keyShapes.size(); i++) {
            g.draw(keyShapes.get(i));
        }
        g.drawString(keys, keyShapes.get(0).getX(), keyShapes.get(0).getY());
        //stampa save button
        int saveTemp = ySave;

        for (int i = 0; i < saveButtons.size(); i++) {
            saveButtons.get(i).render(gc, g, xSave, saveTemp, saveShapes.get(i));
            saveTemp += 70;
        }

        // Render of the buttons to change skins and the actual skin
        this.previousSkin.render(gc, g, 400, 400, new Rectangle(400, 400, 30, 30));
        this.nextSkin.render(gc, g, 800, 400, new Rectangle(800, 400, 30, 30));
        g.drawImage(this.skins[OptionMenu.currentSkinIndex].getScaledCopy(300, 300), 451, 201);
        if (this.isChangingSkin) {
            try {
                Thread.sleep(200);  // Change if the cahnge is too slow
            } catch (InterruptedException ex) {
                Logger.getLogger(OptionMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.isChangingSkin = false;
        }
        g.setColor(Color.white);
        g.drawString("Volume (0-9)", 100, 350);
        g.drawString(volume.toString(), 330, 345);
        g.drawImage(frecciaDx, xVolume + offsetVolumeX, yVolume);
        g.drawImage(frecciaSx, xVolume - offsetVolumeX, yVolume);
    }
    
    /**
     * Method update inherited from BasicGameState
     * @param gc
     * @param sbg
     * @param delta
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        //gestire il click su tutti i tasti come in menu dove si usa x e y dei button non con 400 come io faccio da coglione

        int saveTemp = this.ySave;
        // if the user change the music's volume ir the effect's volume change the attribute volume of the gc
        if ((float) this.volume / 10 != gc.getMusicVolume()) {
            gc.setMusicVolume((float) this.volume / 10);
        }
        //CHECK SE IL MOUSE è SOPRA IL BUTTON BACK SI DEVE ELIMINARE IL CICLO
        for (Button b : this.saveButtons) {
            if (gc.getInput().isMouseButtonDown(0) && posX > this.xSave && posX < (this.xSave + b.getL())
                    && gc.getHeight() - posY > saveTemp && gc.getHeight() - posY < (saveTemp + b.getH())) {

                options.setValue("skinIndex", currentSkinIndex);
                Player.getPlayerInstance().selectAnimations();
                Player.getPlayerInstance().getAnimations().createAnimations();
                gc.getInput().removeAllKeyListeners();

                b.update(gc, delta, sbg);

            }

            saveTemp += 70;
        }

        //CHECK SE IL MOUSE è SU UNO DEI MOVEMENT COMMAND
        int keyTemp = this.yKey;
        for (String s : options.getCommandMap().keySet()) {
            if (s.equals("skinIndex")) {
            } else {
                if (gc.getInput().isMouseButtonDown(0) && posX > this.xKey && posX < (this.xKey + lKey)
                        && gc.getHeight() - posY > keyTemp && gc.getHeight() - posY < (keyTemp + hKey)) {
                    commandToChange = s;
                    gc.getInput().addKeyListener(new Mylistener());
                }
                keyTemp += 60;
            }

        }
        
        if (gc.getInput().isMouseButtonDown(0) && posX > this.xVolume + offsetVolumeX && posX < (this.xVolume + offsetVolumeX + frecciaDx.getWidth())
                && gc.getHeight() - posY > yVolume && gc.getHeight() - posY < (yVolume + frecciaDx.getHeight())) {
            if (gc.getInput().isMousePressed(0)) {
                setMusic(+1);
            }
        }

        if (gc.getInput().isMouseButtonDown(0) && posX > this.xVolume - offsetVolumeX && posX < (this.xVolume - offsetVolumeX + frecciaSx.getWidth())
                && gc.getHeight() - posY > yVolume && gc.getHeight() - posY < (yVolume + frecciaSx.getHeight())) {
            //gc.getInput().addMouseListener(new VolumeListener());
            if (gc.getInput().isMousePressed(0)) {
                setMusic(-1);
            }
        }

        if (!isChangingSkin) {
            // Manage the backward change skin
            if (gc.getInput().isMouseButtonDown(0) && posX > 400 && posX < (400 + this.previousSkin.getL())
                    && gc.getHeight() - posY > 400 && gc.getHeight() - posY < (400 + this.previousSkin.getH())) {
                this.isChangingSkin = true;
                this.previousSkin.update(gc, delta, sbg);
            }

            // Manage the forward change skin
            if (gc.getInput().isMouseButtonDown(0) && posX > 800 && posX < (800 + this.nextSkin.getL())
                    && gc.getHeight() - posY > 400 && gc.getHeight() - posY < (400 + this.nextSkin.getH())) {
                this.isChangingSkin = true;
                this.nextSkin.update(gc, delta, sbg);
            }
        }
    }
    
    /**
     * Nested class MyListener used to catch the key pressed to change the commands of the player
     */
    private class Mylistener implements KeyListener {

        @Override
        public void keyPressed(int i, char c) {
            String key = OptionMenu.this.commandToChange;
            if (i != Input.KEY_ESCAPE) {
                options.setValue(key, i);
                OptionMenu.this.aggiornavalori();
            }
        }

        @Override
        public void keyReleased(int i, char c) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of
        }

        @Override
        public void setInput(Input input) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isAcceptingInput() {
            return true;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void inputEnded() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void inputStarted() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
