package com.fastcompaus.mycontact.reporitory;

import com.fastcompaus.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository     // JpaRepository를 extends 하는 경우,
                // 자동으로 Repository 빈으로 등록되기 때문에 따로 선언해주지 않아도 된다.
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);
    List<Person> findByBlockIsNull();

//    Person findByBloodType(String bloodType);   // 에러! query did not return a unique result: 3;
    List<Person> findByBloodType(String bloodType);

    @Query(value = "select person from Person person where person.birthDate.monthOfBirthday = ?1")
    List<Person> findByMonthOfBirthday(int monthOfBirthday);
//    @Query(value = "select person " +
//                   "from Person person " +
//                   "where person.birthDate.monthOfBirthday = ?1 and person.birthDate.dayOfBirthday = ?2")
//    List<Person> findByMonthOfBirthdayAndDayOfBirthday(int monthOfBirthday, int dayOfBirthday);
    // jpql
//    @Query(value = "select person " +
//                   "from Person person " +
//                   "where person.birthDate.monthOfBirthday = :monthOfBirthday " +
//                   "       and person.birthDate.dayOfBirthday = :dayOfBirthday")
    @Query(value = "select * " +
                   "from person " +
                   "where month_of_birthday = :monthOfBirthday " +
                   "       and day_of_birthday = :dayOfBirthday",
            nativeQuery = true)
    List<Person> findByMonthOfBirthdayAndDayOfBirthday(@Param("monthOfBirthday") int monthOfBirthday,
                                       @Param("dayOfBirthday") int dayOfBirthday);

//    @Query(value = "select person from Person person where person.deleted = true")
    @Query(value = "select * from person where deleted=true", nativeQuery = true)
    List<Person> findByDeleted();
}
