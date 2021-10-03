package com.fastcompaus.mycontact.reporitory;

import com.fastcompaus.mycontact.domain.Person;
import com.fastcompaus.mycontact.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud() {
        Person person = new Person();
        person.setName("martin");
        person.setAge(10);
        person.setBloodType("A");

        personRepository.save(person);

        System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("martin");
        assertThat(people.get(0).getAge()).isEqualTo(10);
        assertThat(people.get(0).getBloodType()).isEqualTo("A");
    }

//    @Test
//    void allArgsConstructor() {
//        Person person = new Person(1L, "martin", 10, "reading", "A",
//                "분당", LocalDate.of(2019, 1, 1), "programmer", "010-1111-1111");
//    }
//    @Test
//    void constructorTest() {
//        Person person = new Person("martin", 10);
//    }

    @Test
    void hashCodeAndEquals() {
        Person person1 = new Person("martin", 10, "A");
        Person person2 = new Person("martin", 10,"A");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

    @Test
    void findByBloodType() {
        givenPerson("martin", 10, "A");
        givenPerson("david", 9, "B");
        givenPerson("denis", 8,"O");
        givenPerson("sophia", 8,"AB");
        givenPerson("benny", 6, "A");
        givenPerson("john", 5, "A");

//        Person result = personRepository.findByBloodType("A");
//        System.out.println(result);

        List<Person> result = personRepository.findByBloodType("A");
        result.forEach(System.out::println);
    }

    @Test
    void findByBirthDayBetween() {
        givenPerson("martin", 10, "A", LocalDate.of(1991, 8, 15));
        givenPerson("david", 9, "B", LocalDate.of(1992, 7, 10));
        givenPerson("denis", 8,"O", LocalDate.of(1993, 1, 5));
        givenPerson("sophia", 8,"AB", LocalDate.of(1994, 6, 30));
        givenPerson("benny", 6, "A", LocalDate.of(1995, 8, 30));

        List<Person> result = personRepository
//                .findByBirthDateBetween(
//                        LocalDate.of(1991, 8, 1),
//                        LocalDate.of(1991, 8, 31)
//                );
                .findByMonthOfBirthday(8);
//                .findByMonthOfBirthdayAndDayOfBirthday(8, 30);

        result.forEach(System.out::println);
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthDay) {
        Person person = new Person(name, age, bloodType);

        person.setBirthDate(
//                new Birthday(
//                        birthDay.getYear(),
//                        birthDay.getMonthValue(),
//                        birthDay.getDayOfMonth())
                new Birthday(birthDay)
        );

        personRepository.save(person);
    }

}