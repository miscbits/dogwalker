package com.zipcode.dogwalker.Walkers;

import com.zipcode.dogwalker.Dogs.Dog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class WalkerTest {

    Walker walker1;
    Walker walker2;
    Walker walker3;

    @Before
    public void setUp() {
        this.walker1 = new Walker();
        this.walker2 = new Walker(1L, 1, new HashSet<>());
        this.walker3 = new Walker( 1, new HashSet<>());

    }

    @Test
    public void instantiation() {
        Assert.assertNotNull(walker1);
        Assert.assertNotNull(walker2);
        Assert.assertNotNull(walker3);
    }

    @Test
    public void getId() {
        Long expected = 1L;
        Assert.assertEquals(expected, walker2.getId());
    }

    @Test
    public void setId() {
        Long expected = 2L;
        walker2.setId(2L);
        Assert.assertEquals(expected, walker2.getId());

    }

    @Test
    public void getEmployee_id() {
        Integer expected = 1;
        Assert.assertEquals(expected, walker3.getEmployee_id());
    }

    @Test
    public void setEmployee_id() {
        Integer expected = 2;
        walker3.setEmployee_id(2);
        Assert.assertEquals(expected, walker3.getEmployee_id());
    }

    @Test
    public void getDogs() {
        Assert.assertEquals(new HashSet<Dog>(), walker2.getDogs());
    }

    @Test
    public void setDogs() {
        Dog dog = new Dog();
        Set<Dog> dogs = new HashSet<>();
        dogs.add(dog);

        walker1.setDogs(dogs);

        Assert.assertEquals(dogs, walker1.getDogs());
    }

    @Test
    public void update() {
        Set<Dog> dogs = new HashSet<>();
        dogs.add(new Dog());
        Walker updateWalker = new Walker(2L, 2, dogs);

        walker2.update(updateWalker);

        Assert.assertEquals(walker2.getDogs(), updateWalker.getDogs());
        Assert.assertEquals(walker2.getEmployee_id(), updateWalker.getEmployee_id());
        Assert.assertNotEquals(walker2.getId(), updateWalker.getId());

    }
}