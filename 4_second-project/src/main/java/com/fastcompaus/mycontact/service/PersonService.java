package com.fastcompaus.mycontact.service;

import com.fastcompaus.mycontact.controller.dto.PersonDTO;
import com.fastcompaus.mycontact.domain.Block;
import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.domain.dto.Birthday;
import com.fastcompaus.mycontact.reporitory.BlockRepository;
import com.fastcompaus.mycontact.reporitory.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks() {
//        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();
//        List<String> blockNames = blocks
//                .stream()
//                .map(Block::getName)
//                .collect(Collectors.toList());
//
//        return people
//                .stream()
//                .filter(person -> !blockNames.contains(person.getName()))
//                .collect(Collectors.toList());
//        return people
//                .stream()
//                .filter(person -> person.getBlock() == null)
//                .collect(Collectors.toList());
        return personRepository.findByBlockIsNull();
    }

    public List<Person> getPeopleByName(String name) {
//        List<Person> people = personRepository.findAll();
//
//        return people
//                .stream()
//                .filter(person -> person.getName().equals(name))
//                .collect(Collectors.toList());
        return personRepository.findByName(name);
    }

    @Transactional(readOnly = true)     // 저장하는 쿼리는 없고 조회하는 쿼리만 있을때 사용 중요!
    public Person getPerson(Long id) {
//        Person person = personRepository.findById(id).get();

//        Optional<Person> person = personRepository.findById(id);
//
//        if (person.isPresent()) {
//            return person.get();
//        } else {
//            return null;
//        }
        Person person = personRepository.findById(id).orElse(null);

        System.out.println("person : " + person);
        log.info("person : {}", person);

        return person;
    }

    @Transactional
    public void put(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDTO personDTO) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        if (!person.getName().equals(person.getName())) {
            throw new RuntimeException("이름이 다릅니다.");
        }

//        person.setName(personDTO.getName());
//        personAtDb.setPhoneNumber(personDTO.getPhoneNumber());
//        personAtDb.setJob(personDTO.getJob());

        if (personDTO.getBirthday() != null) {
            person.setBirthDate(new Birthday(personDTO.getBirthday()));
        }

//        personAtDb.setAddress(personDTO.getAddress());
//        personAtDb.setBloodType(personDTO.getBloodType());
//        personAtDb.setHobby(personDTO.getHobby());
//        personAtDb.setAge(personDTO.getAge());

        person.set(personDTO);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setName(name);

        personRepository.save(person);
    }

}
