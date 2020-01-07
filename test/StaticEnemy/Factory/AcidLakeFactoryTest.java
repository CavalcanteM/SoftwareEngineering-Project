package StaticEnemy.Factory;

import StaticEnemy.AcidLake;
import org.junit.*;
import StaticEnemy.StaticDamage;

public class AcidLakeFactoryTest {

    @Test
    public void createTest() {
        int x, y, difficulty;

        x = 5;
        y = 5;
        difficulty = 1;
        AcidLakeFactory factory = new AcidLakeFactory(difficulty);
        StaticDamage acidlake = factory.create(x, y, 1);
        Assert.assertTrue(acidlake instanceof AcidLake);
    }
}
