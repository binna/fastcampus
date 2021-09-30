package com.fastcompaus.mycontact.service;

import com.fastcompaus.mycontact.domain.Block;
import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.reporitory.BlockRepository;
import com.fastcompaus.mycontact.reporitory.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks() {
        List<Person> people = personRepository.findAll();
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
        return people
                .stream()
                .filter(person -> person.getBlock() == null)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)     // 저장하는 쿼리는 없고 조회하는 쿼리만 있을때 사용 중요!
    public Person getPerson(Long id) {
        Person person = personRepository.findById(id).get();

        System.out.println("person : " + person);
        log.info("person : {}", person);

        return person;
    }
}
