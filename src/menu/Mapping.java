package menu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import org.newdawn.slick.Input;

/**
 *
 * @author Marco
 */
public class Mapping {
    
    private HashMap<String, Integer> commandMap;

    public Mapping() {
        loadCommands();
    }

    public HashMap<String, Integer> getCommandMap() {
        return commandMap;
    }

    public void setCommandMap(HashMap<String, Integer> commandMap) {
        this.commandMap = commandMap;
    }
    
    /**
     * Load of the commands from a file. If the file not exists, 
     */
    public void loadCommands(){
        try {
            ObjectInputStream s = new ObjectInputStream(new FileInputStream("options"));   //check se il file esiste
            commandMap = (HashMap<String, Integer>) s.readObject();
            s.close();
        } catch (IOException | ClassNotFoundException ex) {
        }
        if (commandMap == null){
            // se il file non esiste imposto i tasti di default
            commandMap = new HashMap<>();
            commandMap.put("right", Input.KEY_D);
            commandMap.put("left", Input.KEY_A);
            commandMap.put("gravity",Input.KEY_SPACE);
            commandMap.put("dash",Input.KEY_LSHIFT);
            commandMap.put("skinIndex", 0);
        }
    }
    
    public void saveCommands(){
        try{
            FileOutputStream out = new FileOutputStream("options");
            ObjectOutputStream s = new ObjectOutputStream(out);
            s.writeObject(this.commandMap);
            s.close();
        }catch(IOException e){
        }
    }
    
    public void setValue(String s,Integer newKey){
        if(s.equals("skinIndex")){
            commandMap.replace(s, newKey);
        }else if(!commandMap.containsValue(newKey)){
            commandMap.put(s, newKey);
        }
        this.saveCommands();
    }
    
}
