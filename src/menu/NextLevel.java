package menu;

import IsaacMain.GalaxyComponent;
import IsaacMain.GameIsaac;
import IsaacMain.Level;
import IsaacMain.Player;
import IsaacMain.Saves;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NextLevel implements Command{

	private GameIsaac gs;
	private int actualWorld;
	private int actualLevel;
	private Saves saves;

	public NextLevel(GameIsaac gs, int actualLevel, int actualWorld) {
		this.gs = gs;
		this.saves=gs.loadProgress();
		this.actualLevel=actualWorld;
		this.actualLevel=actualLevel;
	}
	
	public void saveProgress(){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try{
            fos = new FileOutputStream("save.txt");
            out = new ObjectOutputStream(fos);
            out.writeObject(saves);
			out.flush();
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

	@Override
	public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
		//Check if the actualLevel is the last level of the world
		if (gs.getGalaxy().getChild(actualWorld).getChildren().size() == actualLevel) {
			//Check if the actualWorld is the last world of the galaxy
			if (gs.getGalaxy().getChildren().size() == actualWorld) {
				//Game complete da modificare
				gc.exit();
			} else {
				actualWorld++;
				actualLevel = 1;
				saves.setLastWorld(actualWorld);
				saves.setLastLevel(actualLevel);
			}
		} else {
			actualLevel++;
			saves.setLastWorld(actualLevel);
		}
		saveProgress();
		System.out.println("Mondo: "+saves.getLastWorld()+" - Livello: "+saves.getLastLevel());
		GalaxyComponent world = gs.getGalaxy().getChild(actualWorld);
		Level level = (Level) world.getChild(actualLevel);
		gs.setLevel(level);
		gs.getLevel().init(gc);
		Player.getPlayerInstance().resetStats();
		Player.getPlayerInstance().init(gc);

		gs.getCollisionManager().setParameters(gs.getLevel());
		gc.resume();
	}

}
