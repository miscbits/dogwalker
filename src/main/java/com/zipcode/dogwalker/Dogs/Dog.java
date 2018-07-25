package com.zipcode.dogwalker.Dogs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private Breed breed;

    public Dog() {
    }


    public Dog(Long id, String name, Integer age, Breed breed) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }


    public Dog(String name, Integer age, Breed breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public void update(Dog dog) {
        if(dog.getAge() != null) {
            this.age = dog.getAge();
        }

        if(dog.getBreed() != null) {
            this.breed = dog.getBreed();
        }

        if(dog.getName() != null) {
            this.name = dog.getName();
        }
    }
}
