package StaticEnemy.Factory;

import StaticEnemy.HalfHeartSpike;
import org.junit.*;
import StaticEnemy.StaticDamage;

public class HalfHeartSpikeFactoryTest {

    @Test
    public void createTest() {
        int x, y, difficulty;

        x = 5;
        y = 5;
        difficulty = 1;
        HalfHeartSpikeFactory factory = new HalfHeartSpikeFactory(difficulty);
        StaticDamage spike = factory.create(x, y);
        Assert.assertTrue(spike instanceof HalfHeartSpike);
    }
}
