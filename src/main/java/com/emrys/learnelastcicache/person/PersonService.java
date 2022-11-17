package com.emrys.learnelastcicache.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    public static Logger logger = LogManager.getLogger();
    @Autowired
    PersonDao personDao;

    public Person updatePerson(String id, @RequestBody Person patch) {
        DateTime start = new DateTime();
        logger.info("ID: " + id);
        Person patched = personDao.findById(id, false).get();
        patched.setFirstName(patch.getFirstName());
        patched.setLastName(patch.getLastName());

        patched.setAddress(patch.getAddress());
        Person n = personDao.save(patched);
        DateTime end = new DateTime();
        Duration duration = new Duration(start, end);
        logger.info("Took By ID: " + duration.getMillis());
        personDao.invalidatePersons();
        return n;
    }

    public void createPerson( Person patch) {
        DateTime start = new DateTime();
        logger.info("Saving:" ,patch);
        patch.setId(UUID.randomUUID().toString());
        patch.getAddress().setId(UUID.randomUUID().toString());
        patch.getAddress().setPerson(patch);
        Person n = personDao.save(patch);
        DateTime end = new DateTime();
        Duration duration = new Duration(start, end);
        logger.info("Took By ID: " + duration.getMillis());
        personDao.invalidatePersons();
    }

    public List<AddressView> getAddressByState(String state) {
        return personDao.getAddressByState(state);
    }
    public List<Person> getPersons() {
        DateTime start = new DateTime();
        List<Person> persons = personDao.findAll();
        DateTime end = new DateTime();
        Duration duration = new Duration(start, end);
        logger.info("Took: " + duration.getMillis());
        return persons;
    }

    public Person getPersonById(String id) {
        DateTime start = new DateTime();
        logger.info("ID: " + id);
        Person person = personDao.findById(id, true).get();
        DateTime end = new DateTime();
        Duration duration = new Duration(start, end);
        logger.info("Took By ID: " + duration.getMillis());
        return person;
    }

    public void deletePersonById(String id) {
        DateTime start = new DateTime();
        logger.info("ID: " + id);
        Person toDelete = personDao.findById(id, false).get();
        personDao.delete(toDelete);
        DateTime end = new DateTime();
        Duration duration = new Duration(start, end);
        logger.info("Delete By ID: " + duration.getMillis());
        personDao.invalidatePersons();
    }
}
