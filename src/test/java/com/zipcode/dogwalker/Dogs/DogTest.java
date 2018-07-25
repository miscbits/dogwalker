package com.zipcode.dogwalker.Dogs;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DogTest {

    @Test
    public void instantiation() {
        Dog dog1 = new Dog(1L, "Woofer", 3, Breed.Boxer);
        Dog dog2 = new Dog();
        Dog dog3 = new Dog( "Woofer", 3, Breed.Boxer);

        Assert.assertNotNull(dog1);
        Assert.assertNotNull(dog2);
        Assert.assertNotNull(dog3);
    }

    @Test
    public void getId() {
        Dog dog = new Dog(1L, "Woofer", 3, Breed.Boxer);

        Long expected = 1L;
        Long actual = dog.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setId() {
        Dog dog = new Dog();
        dog.setId(1L);

        Long expected = 1L;
        Long actual = dog.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getName() {
        Dog dog = new Dog(1L, "Woofer", 3, Breed.Boxer);

        Assert.assertEquals("Woofer", dog.getName());
    }

    @Test
    public void setName() {
        Dog dog = new Dog();

        dog.setName("Woofer");

        Assert.assertEquals("Woofer", dog.getName());

    }

    @Test
    public void getAge() {
        Dog dog = new Dog(1L, "Woofer", 3, Breed.Boxer);

        Integer expected = 3;
        Integer actual = dog.getAge();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setAge() {
        Dog dog = new Dog();

        dog.setAge(3);

        Integer expected = 3;
        Integer actual = dog.getAge();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getBreed() {
        Dog dog = new Dog(1L, "Woofer", 3, Breed.Boxer);

        Breed expected = Breed.Boxer;
        Breed actual = dog.getBreed();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBreed() {
        Dog dog = new Dog();
        dog.setBreed(Breed.Boxer);

        Breed expected = Breed.Boxer;
        Breed actual = dog.getBreed();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void update() {
        Dog dog1 = new Dog(1L, "Woofer", 3, Breed.Boxer);
        Dog updateDog = new Dog(2L, "Fluffer", 1, Breed.Golden_Retriever);

        dog1.update(updateDog);

        Assert.assertEquals(updateDog.getAge(), dog1.getAge());
        Assert.assertEquals(updateDog.getName(), dog1.getName());
        Assert.assertEquals(updateDog.getBreed(), dog1.getBreed());
        Assert.assertNotEquals(updateDog.getId(), dog1.getId());
    }
}