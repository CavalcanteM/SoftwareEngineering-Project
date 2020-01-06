package StaticEnemy.Factory;

import StaticEnemy.OneHeartSpike;
import org.junit.*;
import StaticEnemy.StaticDamage;

public class OneHeartSpikeFactoryTest {

    @Test
    public void createTest() {
        int x, y, difficulty;

        x = 5;
        y = 5;
        difficulty = 1;
        OneHeartSpikeFactory factory = new OneHeartSpikeFactory(difficulty);
        StaticDamage spike = factory.create(x, y);
        Assert.assertTrue(spike instanceof OneHeartSpike);
    }
}
