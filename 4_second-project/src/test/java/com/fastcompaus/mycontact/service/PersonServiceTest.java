package com.fastcompaus.mycontact.service;

import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.reporitory.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
//    @Autowired
//    private BlockRepository blockRepository;

//    @Test
//    void getPeopleExcludeBlocks() {
////        givenPerson();
////        givenBlock();
//
//        List<Person> result = personService.getPeopleExcludeBlocks();
//
////        System.out.println(result);
////        result.forEach(System.out::println);
//        assertThat(result.size()).isEqualTo(3);
//        assertThat(result.get(0).getName()).isEqualTo("martin");
//        assertThat(result.get(1).getName()).isEqualTo("david");
//        assertThat(result.get(2).getName()).isEqualTo("benny");
//    }

    @Test
    void getPeopleByName() {
//        givenPerson();

        List<Person> result = personService.getPeopleByName("martin");

//        result.forEach(System.out::println);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("martin");
    }

//    @Test
//    void cascadeTest() {
//        givenPerson();
//
//        List<Person> result = personRepository.findAll();
//
//        result.forEach(System.out::println);
//
//
//        Person person = result.get(3);
//
//        person.getBlock().setStartDate(LocalDate.now());
//        person.getBlock().setEndDate(LocalDate.now());
//
//        personRepository.save(person);
//        personRepository.findAll().forEach(System.out::println);
//
////        personRepository.delete(person);
////        personRepository.findAll().forEach(System.out::println);
////        blockRepository.findAll().forEach(System.out::println);
//
//        person.setBlock(null);
//        personRepository.save(person);
//        personRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);
//    }

    @Test
    void getPerson() {
//        givenPerson();

        Person person = personService.getPerson(3L);

//        System.out.println(person);
        assertThat(person.getName()).isEqualTo("dennis");
    }
//    private void givenBlock() {
//        givenBlock("martin");
//    }
//    private Block givenBlock(String name) {
//        return blockRepository.save(new Block(name));
//    }

//    private void givenPerson() {
//        givenPerson("martin", 10, "A");
//        givenPerson("david", 9, "B");
//        givenBlockPerson("dennis", 7, "O");
//        givenBlockPerson("martin", 11, "AB");
//    }

//    private void givenPerson(String name, int age, String bloodType) {
//        personRepository.save(new Person(name, age, bloodType));
//    }

//    private void givenBlockPerson(String name, int age, String bloodType) {
//        Person blockPerson = new Person(name, age, bloodType);
////        blockPerson.setBlock(givenBlock(name));
//        blockPerson.setBlock(new Block(name));
//
//        personRepository.save(blockPerson);
//    }

}