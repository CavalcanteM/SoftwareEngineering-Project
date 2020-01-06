package Entity.Factory;

import Entity.Block;
import Entity.Entity;
import Entity.Factory.ConcreteFactoryReward;
import Entity.Reward;
import org.junit.*;
import static org.junit.Assert.*;

public class ConcreteFactoryRewardTest {

    @Test
    public void createTest() {
        int x = 0, y = 0;

        ConcreteFactoryReward factory = new ConcreteFactoryReward();

        Assert.assertTrue(factory.create(x, y) instanceof Reward);

        x = 10;
        y = 10;

        Assert.assertTrue(factory.create(x, y) instanceof Reward);

        x = 5;
        y = -5;

        Assert.assertTrue(factory.create(x, y) instanceof Reward);

        x = -5;
        y = 5;

        Assert.assertTrue(factory.create(x, y) instanceof Reward);

        x = -5;
        y = -5;

        Assert.assertTrue(factory.create(x, y) instanceof Reward);

    }

}
