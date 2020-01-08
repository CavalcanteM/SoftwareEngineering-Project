package StaticEnemy.Factory;

import StaticEnemy.Barrel;
import StaticEnemy.StaticDamage;

public class BarrelFactory extends StaticEnemyFactory {

    public BarrelFactory(int difficulty) {
        super(difficulty);
    }
/**
     * Method create inherited from the class EntityFactory
     * @param x indicates the x position of the barrel that will be created
     * @param y indicates the y position of the barrel that will be created
     * @param damage indicates the damage of the barrel
     * @return 
     */
    @Override
    public StaticDamage create(int x, int y, int damage) {
        return new Barrel(x, y, super.difficulty, damage);
    }
}
