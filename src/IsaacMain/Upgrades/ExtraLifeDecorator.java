package IsaacMain.Upgrades;

import IsaacMain.Player;

public class ExtraLifeDecorator extends UpgradeDecorator{

    public ExtraLifeDecorator(Player player) {
        super();
    }
    
    @Override
    public void activation(){
        if(super.getNumHearts() > super.getNumVoidHearts() - 2){
            super.setNumVoidHearts(super.getNumVoidHearts() + 2);
        }
        super.setNumHearts(super.getNumHearts() + 2);
    }
}