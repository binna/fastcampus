package com.fastcompaus.mycontact.reporitory;

import com.fastcompaus.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
