package menu;

import IsaacMain.GalaxyComponent;
import IsaacMain.GameIsaac;
import IsaacMain.Level;
import IsaacMain.Player;
import IsaacMain.Saves;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NextLevel implements Command{

	private GameIsaac gs;
	private Saves saves;
        private int actualWorld;
        
	public NextLevel(GameIsaac gs, int actualLevel, int actualWorld) {
		this.gs = gs;
		this.saves=new Saves();
		this.saves=saves.loadProgress();
                this.actualWorld = actualWorld;
	}

	@Override
	public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
		//Check if the actualLevel is the last level of the world
		System.out.println("NextLevel-> LoadedWorld: "+GameIsaac.loadedWorld+" LoadedLevel: "+GameIsaac.loadedLevel);
		if (gs.getGalaxy().getChild(GameIsaac.loadedWorld).getChildren().size() == GameIsaac.loadedLevel + 1) {
			//Check if the actualWorld is the last world of the galaxy
			if (gs.getGalaxy().getChildren().size() == GameIsaac.loadedWorld + 1) {
				//Game complete da modificare
				gc.exit();
			} else {
				GameIsaac.loadedWorld++;
				GameIsaac.loadedLevel = 0;
				saves.setLastWorld(GameIsaac.loadedWorld);
				saves.setLastLevel(GameIsaac.loadedLevel);                       
			}
		} else {
			GameIsaac.loadedLevel++;
			saves.setLastLevel(GameIsaac.loadedLevel);
		}
		saves.saveProgress();
		System.out.println("NextLevel-> Mondo: "+saves.getLastWorld()+" - Livello: "+saves.getLastLevel());
		GalaxyComponent world = gs.getGalaxy().getChild(GameIsaac.loadedWorld);
		Level level = (Level) world.getChild(GameIsaac.loadedLevel);
		gs.setLevel(level);
		gs.getLevel().init(gc);
		Player.getPlayerInstance().resetStats();
		Player.getPlayerInstance().init(gc);
		gc.setMusicOn(false);
		gs.getCollisionManager().setParameters(gs.getLevel());
		gc.resume();
                if(actualWorld != GameIsaac.loadedWorld){
                    sbg.enterState(4);
                }
	}

}
