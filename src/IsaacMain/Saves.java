package IsaacMain;

import java.io.Serializable;

public class Saves implements Serializable {
    
    private int lastWorld;
    private int lastLevel;

    public Saves(int lastWorld, int lastLevel) {
	this.lastWorld = lastWorld;
	this.lastLevel = lastLevel;
    }

    public Saves(){
	this.lastWorld = 0;
	this.lastLevel = 1;
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
}