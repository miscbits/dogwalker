package com.zipcode.dogwalker.Dogs;

import com.zipcode.dogwalker.Walkers.Walker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {

    @Query(value = "SELECT w.id FROM walker_dogs wd, walker w WHERE wd.dogs_id=?1", nativeQuery = true)
    Long getWalkerIdByDogId(Long dogId);
}
