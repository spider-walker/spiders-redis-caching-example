package com.emrys.learnelastcicache.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDao {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Cacheable("persons")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public List<AddressView> getAddressByState(String state) {
        return addressRepository.getAddressByState(state);
    }

    @Cacheable(value = "person", key = "#id", condition = "#notcached==true")
    public Optional<Person> findById(String id, boolean notcached) {
        return personRepository.findById(id);
    }

    @CacheEvict(value = "persons")
    public void invalidatePersons() {
    }

    @CachePut(value = "person", key = "#person.id")
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @CacheEvict(value = "person", key = "#person.id")
    public void delete(Person person) {
        personRepository.delete(person);
    }
}
