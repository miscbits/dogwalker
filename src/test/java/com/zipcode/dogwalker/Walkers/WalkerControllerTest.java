package com.zipcode.dogwalker.Walkers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class WalkerControllerTest {

    private WalkerController walkerController;
    private WalkerRepositoryMock walkerRepositoryMock;

    @Before
    public void setUp() throws Exception {
        List<Walker> walkers = new ArrayList<>();
        walkers.add(new Walker(1L, 1, new HashSet<>()));
        walkers.add(new Walker(2L, 2, new HashSet<>()));
        walkers.add(new Walker(3L, 3, new HashSet<>()));

        this.walkerRepositoryMock = new WalkerRepositoryMock(walkers);

        this.walkerController = new WalkerController(this.walkerRepositoryMock);

    }

    @Test
    public void index() {
        Assert.assertEquals(walkerController.index().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void show() {
        Assert.assertEquals(walkerController.show(1L).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void store() {
        Walker walker = new Walker(4L, 4, new HashSet<>());

        Assert.assertEquals(walkerController.store(walker).getStatusCode(), HttpStatus.CREATED);

        Assert.assertEquals(this.walkerRepositoryMock.walkers.size(), 4);
    }

    @Test
    public void update() {
        Walker walker = new Walker(1L, 6, new HashSet<>());

        Assert.assertEquals(walkerController.update(walker.getId(), walker).getStatusCode(), HttpStatus.OK);

        Assert.assertEquals(this.walkerRepositoryMock.walkers.size(), 3);

        Integer expected = 6;

        Assert.assertEquals(this.walkerRepositoryMock.findById(1L).get().getEmployee_id(), expected);
    }

    @Test
    public void destroy() {
        Assert.assertEquals(walkerController.destroy(1L).getStatusCode(), HttpStatus.OK);

        Assert.assertEquals(this.walkerRepositoryMock.walkers.size(), 2);
    }

    class WalkerRepositoryMock implements WalkerRepository{

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