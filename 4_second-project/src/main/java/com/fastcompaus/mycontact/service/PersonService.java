package com.fastcompaus.mycontact.service;

import com.fastcompaus.mycontact.controller.dto.PersonDTO;
import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.reporitory.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleByName(String name) {
        return personRepository.findByName(name);
    }

    @Transactional(readOnly = true)     // 저장하는 쿼리는 없고 조회하는 쿼리만 있을때 사용 중요!
    public Person getPerson(Long id) {
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

        if (!person.getName().equals(personDTO.getName())) {
            throw new RuntimeException("이름이 다릅니다.");
        }

        person.set(personDTO);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setName(name);

        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id) {
        // 1. DB 데이터 삭제
        // 첫번째 방법
//        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));
//        personRepository.delete(person);

        // 두번째 방법
//        personRepository.deleteById(id);


        // 2. deleted의 값을 true로 변경
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));
        person.setDeleted(true);
        personRepository.save(person);
    }

}
