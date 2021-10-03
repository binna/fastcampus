package com.fastcompaus.mycontact.domain;

import com.fastcompaus.mycontact.domain.dto.Birthday;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;

@Entity
//@Getter
//@Setter
//@ToString(exclude = "phoneNumber")
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
//@EqualsAndHashCode
@Data       // @Getter + @Setter + @RequiredArgsConstructor + @ToString + @EqualsAndHashCode.
            // => Data 어노테이션의 RequiredArgsConstructor 이
            // NoArgsConstructor 가 덮어지면서
            // RequiredArgsConstructor 의 효과가 없어지므로 다시 어노테이션으로 선언해줬다!!!
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
//    private LocalDate birthDate;
    @Valid
    @Embedded
    private Birthday birthDate;

//    @Getter
//    @Setter
    private String job;

    @ToString.Exclude
    private String phoneNumber;

    // fetch = FetchType.EAGER -> 디폴트값! : 1번만!! 한 방에 모든 데이터를!!-----
    // select
    //      person0_.id as id1_1_0_, person0_.address as address2_1_0_, person0_.age as age3_1_0_, person0_.birth_date as birth_da4_1_0_, person0_.block_id as block_i10_1_0_, person0_.blood_type as blood_ty5_1_0_, person0_.hobby as hobby6_1_0_, person0_.job as job7_1_0_, person0_.name as name8_1_0_, person0_.phone_number as phone_nu9_1_0_, block1_.id as id1_0_1_, block1_.end_date as end_date2_0_1_, block1_.name as name3_0_1_, block1_.reason as reason4_0_1_, block1_.start_date as start_da5_0_1_
    // from person person0_
    // left outer join block block1_
    // on person0_.block_id=block1_.id
    // where person0_.id=?
    // ------------------------------------------------------------------

    // fetch = FetchType.LAZY : 2번!! -> 필요할때 그때 그때!! -----------------
    // select
    //      person0_.id as id1_1_0_, person0_.address as address2_1_0_, person0_.age as age3_1_0_, person0_.birth_date as birth_da4_1_0_, person0_.block_id as block_i10_1_0_, person0_.blood_type as blood_ty5_1_0_, person0_.hobby as hobby6_1_0_, person0_.job as job7_1_0_, person0_.name as name8_1_0_, person0_.phone_number as phone_nu9_1_0_
    // from person person0_
    // where person0_.id=?
    //
    // select
    //      block0_.id as id1_0_0_, block0_.end_date as end_date2_0_0_, block0_.name as name3_0_0_, block0_.reason as reason4_0_0_, block0_.start_date as start_da5_0_0_
    // from block block0_
    // where block0_.id=?
    // ------------------------------------------------------------------

    // optional = false -> Block 의 값은 항상 필요하다는 의미(Inner Join 수행)
    //                     Block 없는 Person 없다는 제약조건!!

    // orphanRemoval 옵션 -> true: 기존 NULL 처리된 자식을 DELETE
    // PK(JoinColumn)값이 NULL 로 변한 자식은 고아객체라고 하여 연결된 점이 없는 객체이다.

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Block block;

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
