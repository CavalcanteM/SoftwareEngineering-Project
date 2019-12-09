package gravityslick.EntityFactory;

import Entities.Entity;
import Entities.Reward;

public class RewardFactory implements InterfaceEntityFactory {

    @Override
    public Entity getEntity(int x, int y) {
        return new Reward(x, y);
    }

}
