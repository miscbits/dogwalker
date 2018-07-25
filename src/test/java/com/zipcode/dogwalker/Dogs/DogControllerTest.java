package com.zipcode.dogwalker.Dogs;

import com.zipcode.dogwalker.Walkers.Walker;
import com.zipcode.dogwalker.Walkers.WalkerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class DogControllerTest {
    private WalkerRepositoryMock walkerRepositoryMock;
    private DogRepositoryMock dogRepositoryMock;

    private DogController dogController;

    @Before
    public void setUp() throws Exception {
        List<Walker> walkers = new ArrayList<>();
        walkers.add(new Walker(1L, 1, new HashSet<>()));

        this.walkerRepositoryMock = new WalkerRepositoryMock(walkers);

        List<Dog> dogs = new ArrayList<>();

        dogs.add(new Dog(1L, "Woofer", 3, Breed.Boxer));
        dogs.add(new Dog(2L, "Fluffer", 3, Breed.Pit_Bull));
        dogs.add(new Dog(3L, "Kibbles", 7, Breed.Golden_Retriever));
        this.dogRepositoryMock = new DogRepositoryMock(dogs);

        this.dogController = new DogController(this.dogRepositoryMock, this.walkerRepositoryMock);

    }

    @Test
    public void index() {
        Assert.assertEquals(dogController.index().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void show() {
        Assert.assertEquals(dogController.show(1L).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void store() {

        Dog dog = new Dog("Bits", 7, Breed.Boxer);

        Assert.assertEquals(dogController.store(dog).getStatusCode(), HttpStatus.CREATED);

        Assert.assertEquals(this.dogRepositoryMock.dogs.size(), 4);
    }

    @Test
    public void update() {
        Dog dog = new Dog(1L, "Bits", 7, Breed.Boxer);

        Assert.assertEquals(dogController.update(dog.getId(), dog).getStatusCode(), HttpStatus.OK);

        Assert.assertEquals(this.dogRepositoryMock.dogs.size(), 3);

        String expected = "Bits";

        Assert.assertEquals(expected, this.dogRepositoryMock.findById(1L).get().getName());
    }

    @Test
    public void destroy() {
        Assert.assertEquals(dogController.destroy(1L).getStatusCode(), HttpStatus.OK);

        Assert.assertEquals(this.dogRepositoryMock.dogs.size(), 2);
    }

    @Test
    public void getWalker() {
        Assert.assertEquals(dogController.getWalker(1L).getStatusCode(), HttpStatus.OK);
    }

    class DogRepositoryMock implements DogRepository{

        public List<Dog> dogs;

        public DogRepositoryMock(List<Dog> walkers) {
            this.dogs = walkers;
        }

        public Long getMaxId() {
            return dogs.get(dogs.size()-1).getId();
        }

        public Integer findIndexOf(Dog walker) {
            for(int i = 0; i < dogs.size(); i++) {
                if (walker.getId() == dogs.get(i).getId()) {
                    return i;
                }
            }
            return -1;
        }


        @Override
        public Dog save(Dog s) {
            Integer index = findIndexOf(s);
            if(index == -1) {
                s.setId(getMaxId()+1);
                dogs.add(s);
            } else {
                dogs.set(index, s);
            }
            return s;
        }

        @Override
        public Iterable<Dog> saveAll(Iterable iterable) {
            return iterable;
        }

        @Override
        public Optional<Dog> findById(Long aLong) {
            for(Dog walker : dogs) {
                if (walker.getId() == aLong) {
                    return Optional.of(walker);
                }
            }
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            for(Dog walker : dogs) {
                if (walker.getId().equals(aLong)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Iterable<Dog> findAll() {
            return dogs;
        }

        @Override
        public Iterable<Dog> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {
            Dog walker = new Dog();
            walker.setId(aLong);

            Integer index = findIndexOf(walker);

            if (index != -1) {
                dogs.remove((int)index);
            }
        }

        @Override
        public void delete(Dog walker) {

        }

        @Override
        public void deleteAll(Iterable<? extends Dog> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public Long getWalkerIdByDogId(Long dogId) {
            return 1L;
        }
    }


    class WalkerRepositoryMock implements WalkerRepository {

        public List<Walker> walkers;

        public WalkerRepositoryMock(List<Walker> walkers) {
            this.walkers = walkers;
        }

        public Long getMaxId() {
            return walkers.get(walkers.size()-1).getId();
        }

        public Integer findIndexOf(Walker walker) {
            for(int i = 0; i < walkers.size();i++) {
                if (walker.getId() == walkers.get(i).getId()) {
                    return i;
                }
            }
            return -1;
        }


        @Override
        public Walker save(Walker s) {
            Integer index = findIndexOf(s);
            if(index == -1) {
                s.setId(getMaxId()+1);
                walkers.add(s);
            } else {
                walkers.set(index, s);
            }
            return s;
        }

        @Override
        public Iterable<Walker> saveAll(Iterable iterable) {
            return iterable;
        }

        @Override
        public Optional<Walker> findById(Long aLong) {
            for(Walker walker : walkers) {
                if (walker.getId() == aLong) {
                    return Optional.of(walker);
                }
            }
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            for(Walker walker : walkers) {
                if (walker.getId().equals(aLong)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Iterable<Walker> findAll() {
            return walkers;
        }

        @Override
        public Iterable<Walker> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {
            Walker walker = new Walker();
            walker.setId(aLong);

            Integer index = findIndexOf(walker);

            if (index != -1) {
                walkers.remove((int)index);
            }
        }

        @Override
        public void delete(Walker walker) {

        }

        @Override
        public void deleteAll(Iterable<? extends Walker> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    }

}