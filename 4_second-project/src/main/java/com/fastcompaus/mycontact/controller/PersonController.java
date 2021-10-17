package com.fastcompaus.mycontact.controller;

import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.reporitory.PersonRepository;
import com.fastcompaus.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    public PersonService personService;
    @Autowired
    public PersonRepository personRepository;

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person) {
        personService.put(person);

        log.info("person -> {}", personRepository.findAll());
    }

}
