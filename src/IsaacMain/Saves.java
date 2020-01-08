package IsaacMain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements the progress save functionality on file
 * In this obect will be saved the first level not completed and it's world
 */
public class Saves implements Serializable {

    private int lastWorld;
    private int lastLevel;

    /**
     * Creates a new Save with the given parameters
     * @param lastWorld
     * @param lastLevel 
     */
    public Saves(int lastWorld, int lastLevel) {
        this.lastWorld = lastWorld;
        this.lastLevel = lastLevel;
    }

    /**
     * Creates a new Save on the first level
     */
    public Saves() {
        this.lastWorld = 0;
        this.lastLevel = 0;
    }

    public int getLastWorld() {
        return lastWorld;
    }

    public int getLastLevel() {
        return lastLevel;
    }

    public void setLastWorld(int lastWorld) {
        this.lastWorld = lastWorld;
    }

    public void setLastLevel(int lastLevel) {
        this.lastLevel = lastLevel;
    }

    /**
     * Saves the object on the file "save.txt"
     */
    public void saveProgress() {
        try {
            FileOutputStream fos = new FileOutputStream("save.txt");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the object contained in the "save.txt" file, a blank one if file missing
     * @return a Saves object
     */
    public Saves loadProgress() {
        Saves saves;
        try {
            FileInputStream fis = new FileInputStream("save.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            saves = (Saves) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            saves = new Saves();
        }
        return saves;
    }
}
