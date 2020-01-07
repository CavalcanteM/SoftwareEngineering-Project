package StaticEnemy.Factory;

import StaticEnemy.Spike;
import org.junit.*;
import StaticEnemy.StaticDamage;

public class SpikeFactoryTest {

    @Test
    public void createTest() {
        int x, y, difficulty;

        x = 5;
        y = 5;
        difficulty = 1;
        SpikeFactory factory = new SpikeFactory(difficulty);
        StaticDamage spike = factory.create(x, y, 1);
        Assert.assertTrue(spike instanceof Spike);
    }
}
