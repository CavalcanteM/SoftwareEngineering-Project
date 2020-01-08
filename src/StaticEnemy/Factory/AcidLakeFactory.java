package StaticEnemy.Factory;

import StaticEnemy.AcidLake;
import StaticEnemy.StaticDamage;

public class AcidLakeFactory extends StaticEnemyFactory {

    public AcidLakeFactory(int difficulty) {
        super(difficulty);
    }
/**
     * Method create inherited from the class EntityFactory
     * @param x indicates the x position of the acid lake that will be created
     * @param y indicates the y position of the acid lake that will be created
     * @param damage indicates the damage of the acid lake
     * @return 
     */
    @Override
    public StaticDamage create(int x, int y, int damage) {
        return new AcidLake(x, y, super.difficulty, damage);
    }
}
