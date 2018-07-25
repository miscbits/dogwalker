package com.zipcode.dogwalker.Walkers;

import com.zipcode.dogwalker.Dogs.Dog;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Walker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer employee_id;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Dog> dogs;

    public Walker(Long id, Integer employee_id, Set<Dog> dogs) {
        this.id = id;
        this.employee_id = employee_id;
        this.dogs = dogs;
    }

    public Walker() {
    }

    public Walker(Integer employee_id, Set<Dog> dogs) {
        this.employee_id = employee_id;
        this.dogs = dogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Set<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(Set<Dog> dogs) {
        this.dogs = dogs;
    }

    public void update(Walker walker) {
        if (walker.getDogs() != null) {
            this.dogs = walker.getDogs();
        }

        if (walker.employee_id != null) {
            this.employee_id = walker.getEmployee_id();
        }
    }
}
