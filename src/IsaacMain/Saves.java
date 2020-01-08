package IsaacMain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Saves implements Serializable {

    private int lastWorld;
    private int lastLevel;

    public Saves(int lastWorld, int lastLevel) {
        this.lastWorld = lastWorld;
        this.lastLevel = lastLevel;
    }

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

    public Saves loadProgress() {
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
        }
        return saves;
    }
}
