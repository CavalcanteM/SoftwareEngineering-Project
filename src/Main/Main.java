package Main;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

public class Main {
    public static void main(String[] args) {
        try {
            Window window = Window.getWindow();
            window.showWindow();
            
        } catch (SlickException ex) {
            Logger.getLogger(GameIsaac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}