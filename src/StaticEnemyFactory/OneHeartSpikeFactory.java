package StaticEnemyFactory;

import StaticEnemy.OneHeartSpike;
import StaticEnemy.StaticDamage;

public class OneHeartSpikeFactory extends StaticEnemyFactory {
    
    public OneHeartSpikeFactory(int difficulty){
        super(difficulty);
    }
    
    @Override
    public StaticDamage create(int x, int y) {
        return new OneHeartSpike(x, y, super.difficulty);

    }
}