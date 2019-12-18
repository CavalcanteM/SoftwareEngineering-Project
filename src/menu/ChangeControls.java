package menu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class ChangeControls implements Command{

	@Override
	public void execute(GameContainer gc, int delta, StateBasedGame sbg) {
//		HashMap<String, Integer> map = new HashMap<>();
//        BufferedWriter b = null;
//		Iterator it = map.entrySet().iterator();
//		
//		
//		
//		
//		
//        try{
//            b=new BufferedWriter(new FileWriter("commands.txt")) ;
//			while(it.hasNext()){
//				Map.Entry<String, Integer> mapElement = (Map.Entry)it.next(); 
//				b.write(mapElement.getKey()+";"+mapElement.getValue()+";");
//			}
//			if(b != null) b.close();
//        } catch (IOException ex) {
//		}
	}
}