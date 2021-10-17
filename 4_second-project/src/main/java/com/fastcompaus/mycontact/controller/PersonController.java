package com.fastcompaus.mycontact.controller;

import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    public PersonService personService;

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    public void postPerson(Person person) {
//        personService.
    }
}
