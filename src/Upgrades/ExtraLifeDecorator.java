package Upgrades;

/**
 * The extraLife upgrades
 */
public class ExtraLifeDecorator extends UpgradeDecorator{


    /**
     * this constructor call the UpgradeDecorator constructor
     * @param player 
     */
    public ExtraLifeDecorator(UpgradeComponent player) {
        super(player);
    }
    
    /**
     * Set an extraLife to the player
     */
    @Override
    public void execute(){
        if(super.getNumHearts() > super.getNumVoidHearts() - 2){
            super.setNumVoidHearts(super.getNumVoidHearts() + 2);
        }
        super.setNumHearts(super.getNumHearts() + 2);
    }
}