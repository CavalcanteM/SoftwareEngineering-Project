package Upgrades;

import IsaacMain.Player;

/**
 * The extraLife upgrades
 */
public class ExtraLifeDecorator extends UpgradeDecorator{

    /**
     * this constructor call the UpgradeDecorator constructor
     * @param player 
     */
    public ExtraLifeDecorator(Player player) {
        super();
    }
    
    /**
     * Set an extraLife to the player
     */
    @Override
    public void activation(){
        if(super.getNumHearts() > super.getNumVoidHearts() - 2){
            super.setNumVoidHearts(super.getNumVoidHearts() + 2);
        }
        super.setNumHearts(super.getNumHearts() + 2);
    }
}