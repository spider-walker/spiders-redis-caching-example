package com.emrys.learnelastcicache.person;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HKandie
 */
@RestController
@RequestMapping("/api")
public class PersonController {

    public static Logger logger = LogManager.getLogger();

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Person> index() {
        return personService.getPersons();
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> create( @RequestBody Person patch) {
        personService.createPerson(patch);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Person getPersonById(@PathVariable("id") String id) {
        return personService.getPersonById(id);
    }

    @RequestMapping(value = "/persons/search-by-state", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<AddressView> search(@RequestParam("search") String state) {
        return personService.getAddressByState(state);
    }


    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity<Person> update(@PathVariable("id") String id, @RequestBody Person patch) {
        return ResponseEntity.ok(personService.updatePerson(id, patch));
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable("id") String id) {
        personService.deletePersonById(id);
    }

}
