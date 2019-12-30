package IsaacMain;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.Back;
import menu.Button;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.KeyListener;

public class OptionMenu extends BasicGameState implements Serializable{
    
    final private int hButton=50;
    final private int lButton = 150;
    final private int xSave = 100;
    final private int ySave = 500;
    final private int xKey = 300;
    final private int yKey = 100;
    final private int hKey = 30;
    final private int lKey = 70;
    
    private HashMap <String,Integer> options;
    private Integer volume;
    private String move;
    private String keys;
    private String commandToChange;
    private Button saveBack;
    private Button saveContinue;
    private Mylistener ml;
    
    private ArrayList<Button> saveButtons;
    private ArrayList<Shape> saveShapes;
    private ArrayList<Shape> keyShapes;
    public static int previousState = -1;
    
    public OptionMenu() {
        ml= new Mylistener();
        saveButtons = new ArrayList<>();
        saveShapes = new ArrayList<>();
        keyShapes = new ArrayList<>();
        options = null;
        move = "";
        keys = "";
        
        //creo i tasti per i save 
        saveBack = new Button(hButton, lButton, new Back(), "Back");
        //saveContinue = new Button(hButton, lButton, new ContinueGame(), "Save&Continue");//button che implementa save & continue game
        
        //aggiungo i tasti save alla lista per la stampa
        saveButtons.add(saveBack);
        //saveButtons.add(saveContinue);
        
        
        //PER QUANTO RIGUARDA I KEYS
        //creo i tasti keys
        
        
    }
    
    public String getMove() {
        return move;
    }
    
    
    @Override
    public int getID() {
        return 2; //state id di option
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg){
        try {
             ObjectInputStream s = new ObjectInputStream(new FileInputStream("options"));   //check se il file esiste
             options = (HashMap) s.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        this.volume=(int)gc.getMusicVolume()*10;
        if (options == null){
            // se il file non esiste imposto i tasti di default
            options = new HashMap();
            options.put("right", Input.KEY_D);
            options.put("left", Input.KEY_A);
            options.put("gravity",Input.KEY_SPACE);
            options.put("dash",Input.KEY_LSHIFT);
        }
        
        int tempSave = this.ySave;
        for (Button b : this.saveButtons){
            saveShapes.add(new Rectangle(this.xSave,tempSave,b.getL(),b.getH()));
            tempSave+=70;
	}
        int tempKeys = this.yKey;
        for (String b : options.keySet()){
            keyShapes.add(new Rectangle(this.xKey,tempKeys,lKey,hKey));
            tempKeys+=60;
	}
        keyShapes.add(new Rectangle(this.xKey,tempKeys,lKey,hKey));

        
        this.aggiornavalori();
        }
    
    public void setMusic(int newVolume){
        volume = newVolume;
    }
    
    public void setValue(String s,Integer newKey){
        if(!options.containsValue(newKey))
        options.put(s, newKey);
        //dovrebbe essere richiamato quando premo sul tasto associato ad un movimento passando come parametro l'attuale key e che lo modifica con un Input.getKey pressed tipo
        
    }
    protected void aggiornavalori(){
        move = "";
        keys = "";
        for(String s : options.keySet()){
            move +=  s + "\n\n\n";      // stringa dei movimenti "left,right...."
            keys += (Input.getKeyName(options.get(s))) + "\n\n\n"; //questo keys contiene il tasto attuale assegnato al movimento
            }
        
    }
    
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        //stampa lista move e keys
        g.drawString(move, 100, 100);
        int keyTemp = this.yKey;
        for(int i=0;i<keyShapes.size();i++){
            g.draw(keyShapes.get(i));
            keyTemp += 100;
        }
        g.drawString(keys, keyShapes.get(0).getX(), keyShapes.get(0).getY());
        //stampa save button
        int saveTemp=ySave;
        
        for(int i=0;i<saveButtons.size();i++){
            saveButtons.get(i).render(gc, g,xSave, saveTemp, saveShapes.get(i));
            saveTemp+=70;
        }
        g.setColor(Color.white);
        g.drawString("Volume (0-9)", 100, 350);
        g.drawString(volume.toString(), 330, 345);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        //gestire il click su tutti i tasti come in menu dove si usa x e y dei button non con 400 come io faccio da coglione
        
        //if (gc.getInput().isMouseButtonDown(0) && Mouse.getX()>400 && Mouse.getX()<(400+saveBack.getL()) && gc.getHeight()-Mouse.getY()>100 && gc.getHeight()-Mouse.getY()<(100+saveBack.getH())){
        int saveTemp=this.ySave;
        // if the user change the music's volume ir the effect's volume change the attribute volume of the gc
        if((float)this.volume/10!=gc.getMusicVolume()){
            gc.setMusicVolume((float)this.volume/10);
        }
        //CHECK SE IL MOUSE è SOPRA IL BUTTON BACK SI DEVE ELMINARE IL CICLO
        for (Button b : this.saveButtons){
            if (gc.getInput().isMouseButtonDown(0) && posX>this.xSave && posX<(this.xSave+b.getL()) &&
		gc.getHeight()-posY>saveTemp && gc.getHeight()-posY<(saveTemp+b.getH())){
		try {
                    FileOutputStream out = new FileOutputStream("options");
                    ObjectOutputStream s = new ObjectOutputStream(out);
                    s.writeObject(options);
                }catch (IOException ex) {
                Logger.getLogger(OptionMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
                gc.getInput().removeAllKeyListeners();
                
                b.update(gc, delta,sbg);
                
            }
            
	saveTemp+=70;
	}  
        
        //CHECK SE IL MOUSE è SU UNO DEI MOVEMENT COMMAND
        int keyTemp = this.yKey;
        for (String s : options.keySet()){
            if (gc.getInput().isMouseButtonDown(0) && posX>this.xKey && posX<(this.xKey+lKey) &&
		gc.getHeight()-posY>keyTemp && gc.getHeight()-posY<(keyTemp+hKey))
            {   commandToChange=s;
                gc.getInput().addKeyListener(new Mylistener());
            }
            keyTemp += 60;
        }
        //CHECK SE IL MOUSE è SUL VOLUME BUTTON
        if (gc.getInput().isMouseButtonDown(0) && posX>this.xKey && posX<(this.xKey+lKey) &&
		gc.getHeight()-posY>350 && gc.getHeight()-posY<(380)){
            gc.getInput().addKeyListener(new VolumeListener());
        }
        
    }

    private String getCommandToChange() {
        return this.commandToChange;
    }
    
    private class Mylistener implements KeyListener {
        
        @Override
        public void keyPressed(int i, char c) {
            String key = OptionMenu.this.commandToChange;
            OptionMenu.this.setValue(key, i);
            Player.getPlayerInstance().getCommands().replace(key, i);
            OptionMenu.this.aggiornavalori();//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    private class VolumeListener implements KeyListener {
        
        @Override
        public void keyPressed(int i, char c) {
                if(i > 1 && i< 12){
                    if(i == 11)
                        OptionMenu.this.setMusic(i-11);
                    else
                        OptionMenu.this.setMusic(i-1);
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

