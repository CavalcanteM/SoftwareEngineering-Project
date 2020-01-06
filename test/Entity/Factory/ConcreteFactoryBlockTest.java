package Entity.Factory;

import Entity.Block;
import Entity.Entity;
import Entity.Factory.ConcreteFactoryBlock;
import Entity.Reward;
import org.junit.*;
import static org.junit.Assert.*;

public class ConcreteFactoryBlockTest {

    @Test
    public void createTest() {
        int x = 0, y = 0;
        ConcreteFactoryBlock factory = new ConcreteFactoryBlock();

        Assert.assertTrue(factory.create(x, y) instanceof Block);

        x = 10;
        y = 10;

        Assert.assertTrue(factory.create(x, y) instanceof Block);

        x = 5;
        y = -5;

        Assert.assertTrue(factory.create(x, y) instanceof Block);

        x = -5;
        y = 5;

        Assert.assertTrue(factory.create(x, y) instanceof Block);

        x = -5;
        y = -5;

        Assert.assertTrue(factory.create(x, y) instanceof Block);

    }

}
