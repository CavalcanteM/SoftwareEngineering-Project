package StaticEnemy.Factory;

import StaticEnemy.Barrel;
import org.junit.*;
import StaticEnemy.StaticDamage;

public class BarrelFactoryTest {

    @Test
    public void createTest() {
        int x, y, difficulty;

        x = 5;
        y = 5;
        difficulty = 1;
        BarrelFactory factory = new BarrelFactory(difficulty);
        StaticDamage barrel = factory.create(x, y, 1);
        Assert.assertTrue(barrel instanceof Barrel);
    }
}
