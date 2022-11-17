package com.emrys.learnelastcicache.person;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findAll();

    Optional<Person> findById(String id);

    void deleteById(String id);
}