package com.fastcompaus.mycontact.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
//@Getter
//@Setter
//@ToString(exclude = "phoneNumber")
//@ToString
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
//@EqualsAndHashCode
@Data       // @Getter + @Setter + @RequiredArgsConstructor + @ToString + @EqualsAndHashCode.
public class Person {

    @Id
    @GeneratedValue
    private Long id;

//    @Getter
//    @Setter
    @NonNull        // import lombok.NonNull
    private String name;

//    @Getter
//    @Setter
    @NonNull        // import lombok.NonNull
    private int age;

//    @Getter
//    @Setter
    private String hobby;

//    @Getter
//    @Setter
    @NonNull
    private String bloodType;

//    @Getter
//    @Setter
    private String address;

//    @Getter
//    @Setter
    private LocalDate birthDate;

//    @Getter
//    @Setter
    private String job;

    @ToString.Exclude
    private String phoneNumber;

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getHobby() {
//        return hobby;
//    }
//    public void setHobby(String hobby) {
//        this.hobby = hobby;
//    }
//
//    public String getBloodType() {
//        return bloodType;
//    }
//    public void setBloodType(String bloodType) {
//        this.bloodType = bloodType;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public String getJob() {
//        return job;
//    }
//    public void setJob(String job) {
//        this.job = job;
//    }

//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }

//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", hobby='" + hobby + '\'' +
//                ", bloodType='" + bloodType + '\'' +
//                ", address='" + address + '\'' +
//                ", birthDate=" + birthDate +
//                ", job='" + job + '\'' +
//                '}';
//    }

//    public Person() {}
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public Person(Long id, String name, int age, String hobby, String bloodType,
//                  String address, LocalDate birthDate, String job, String phoneNumber) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.hobby = hobby;
//        this.bloodType = bloodType;
//        this.address = address;
//        this.birthDate = birthDate;
//        this.job = job;
//        this.phoneNumber = phoneNumber;
//    }

//    public boolean equals(Object object) {
//        if (object == null) {
//            return false;
//        }
//
//        Person person = (Person) object;
//
//        if(!person.getName().equals(this.getName())) {
//            return false;
//        }
//
//        if(person.getAge() != this.getAge()) {
//            return false;
//        }
//
//        return true;
//    }

//    public int hashCode() {
//        return (name + age).hashCode();
//    }
}
