package Entity.Factory;

import Entity.Block;
import Entity.Entity;
import Entity.Factory.ConcreteFactoryUpgrade;
import Entity.Reward;
import Entity.Upgrade;
import org.junit.*;
import static org.junit.Assert.*;

public class ConcreteFactoryUpgradeTest {

    @Test
    public void createTest() {
        int x = 0, y = 0;
        ConcreteFactoryUpgrade factory = new ConcreteFactoryUpgrade();

        Assert.assertTrue(factory.create(x, y) instanceof Upgrade);

        x = 10;
        y = 10;

        Assert.assertTrue(factory.create(x, y) instanceof Upgrade);

        x = 5;
        y = -5;

        Assert.assertTrue(factory.create(x, y) instanceof Upgrade);

        x = -5;
        y = 5;

        Assert.assertTrue(factory.create(x, y) instanceof Upgrade);

        x = -5;
        y = -5;

        Assert.assertTrue(factory.create(x, y) instanceof Upgrade);

    }

}
