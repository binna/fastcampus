package com.fastcompaus.mycontact.service;

import com.fastcompaus.mycontact.domain.Block;
import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.reporitory.BlockRepository;
import com.fastcompaus.mycontact.reporitory.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}
