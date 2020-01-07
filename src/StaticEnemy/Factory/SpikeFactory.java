package StaticEnemy.Factory;

import StaticEnemy.StaticDamage;
import StaticEnemy.Spike;

public class SpikeFactory extends StaticEnemyFactory {

    public SpikeFactory(int difficulty) {
        super(difficulty);
    }

    @Override
    public StaticDamage create(int x, int y, int damage) {
        return new Spike(x, y, super.difficulty, damage);

    }
}
