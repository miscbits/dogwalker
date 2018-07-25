package com.zipcode.dogwalker.Dogs;

import com.zipcode.dogwalker.Walkers.Walker;
import com.zipcode.dogwalker.Walkers.WalkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DogController {

    private DogRepository dogRepository;
    private WalkerRepository walkerRepository;

    @Autowired
    public DogController(DogRepository dogRepository, WalkerRepository walkerRepository) {
        this.dogRepository = dogRepository;
        this.walkerRepository = walkerRepository;
    }

    @RequestMapping(value = "/dogs", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Dog>> index() {
        return new ResponseEntity<>(dogRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/dogs/{dogId}", method = RequestMethod.GET)
    public ResponseEntity<Dog> show(@PathVariable Long dogId) {
        return new ResponseEntity<>(dogRepository.findById(dogId).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/dogs", method = RequestMethod.POST)
    public ResponseEntity<Dog> store(@RequestBody Dog dog) {
        Dog savedDog = dogRepository.save(dog);
        return new ResponseEntity<>(savedDog, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/dogs/{dogId}", method = RequestMethod.PUT)
    public ResponseEntity<Dog> update(@PathVariable Long dogId, @RequestBody Dog dog) {
        Dog foundDog = dogRepository.findById(dogId).get();

        foundDog.update(dog);

        Dog savedDog = dogRepository.save(foundDog);

        return new ResponseEntity<>(savedDog, HttpStatus.OK);
    }

    @RequestMapping(value = "/dogs/{dogId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroy(@PathVariable Long dogId) {
        dogRepository.deleteById(dogId);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/dogs/{dogId}/walkers", method = RequestMethod.GET)
    public ResponseEntity<Walker> getWalker(@PathVariable Long dogId) {
        Long walkerId = dogRepository.getWalkerIdByDogId(dogId);
        Walker walker = walkerRepository.findById(walkerId).get();

        return new ResponseEntity<>(walker, HttpStatus.OK);
    }

}
