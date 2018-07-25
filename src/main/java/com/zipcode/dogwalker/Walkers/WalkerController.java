package com.zipcode.dogwalker.Walkers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalkerController {

    private WalkerRepository walkerRepository;

    @Autowired
    public WalkerController(WalkerRepository walkerRepository) {
        this.walkerRepository = walkerRepository;
    }

    @RequestMapping(value = "/walkers", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Walker>> index() {
        return new ResponseEntity<>(walkerRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/walkers/{walkerId}", method = RequestMethod.GET)
    public ResponseEntity<Walker> show(@PathVariable Long walkerId) {
        return new ResponseEntity<>(walkerRepository.findById(walkerId).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/walkers", method = RequestMethod.POST)
    public ResponseEntity<Walker> store(@RequestBody Walker walker) {

        Walker savedWalker = walkerRepository.save(walker);
        return new ResponseEntity<>(savedWalker, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/walkers/{walkerId}", method = RequestMethod.PUT)
    public ResponseEntity<Walker> update(@PathVariable Long walkerId, @RequestBody Walker walker) {
        Walker foundWalker = walkerRepository.findById(walkerId).get();

        foundWalker.update(walker);

        Walker savedWalker = walkerRepository.save(foundWalker);

        return new ResponseEntity<>(savedWalker, HttpStatus.OK);
    }

    @RequestMapping(value = "/walkers/{walkerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroy(@PathVariable Long walkerId) {
        walkerRepository.deleteById(walkerId);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
