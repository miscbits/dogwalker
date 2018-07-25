package com.zipcode.dogwalker.Walkers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkerRepository extends CrudRepository<Walker, Long> {
}
