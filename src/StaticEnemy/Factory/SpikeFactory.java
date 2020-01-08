package StaticEnemy.Factory;

import StaticEnemy.StaticDamage;
import StaticEnemy.Spike;

public class SpikeFactory extends StaticEnemyFactory {

    public SpikeFactory(int difficulty) {
        super(difficulty);
    }
/**
     * Method create inherited from the class EntityFactory
     * @param x indicates the x position of the spike that will be created
     * @param y indicates the y position of the spike that will be created
     * @param damage indicates the damage of the spike
     * @return 
     */
    @Override
    public StaticDamage create(int x, int y, int damage) {
        return new Spike(x, y, super.difficulty, damage);

    }
}
