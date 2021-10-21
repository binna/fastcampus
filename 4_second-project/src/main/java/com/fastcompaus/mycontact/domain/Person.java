package com.fastcompaus.mycontact.domain;

import com.fastcompaus.mycontact.controller.dto.PersonDTO;
import com.fastcompaus.mycontact.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
@Where(clause = "deleted = false")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull        // import lombok.NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    private String address;

    @Valid
    @Embedded
    private Birthday birthDate;

    private String job;

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
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private Block block;

    @ColumnDefault("0")
    private boolean deleted;

    public void set(PersonDTO personDTO) {

        if(!ObjectUtils.isEmpty(personDTO.getHobby())) {
            this.setHobby(personDTO.getHobby());
        }

        if (!ObjectUtils.isEmpty(personDTO.getAddress())) {
            this.setAddress(personDTO.getAddress());
        }

        if(!ObjectUtils.isEmpty(personDTO.getJob())) {
            this.setJob(personDTO.getJob());
        }

        if(!ObjectUtils.isEmpty(personDTO.getPhoneNumber())) {
            this.setPhoneNumber(personDTO.getPhoneNumber());
        }

        if(personDTO.getBirthday() != null) {
            this.setBirthDate(Birthday.of(personDTO.getBirthday()));
        }
    }


    private Integer getAge() {
        if (this.birthDate != null) {
            return LocalDate.now().getYear() - this.birthDate.getYearOfBirthday() + 1;
        }
        return null;
    }
    public boolean isBirthDayToday() {
        return LocalDate.now().equals(
                LocalDate.of(
                        this.birthDate.getYearOfBirthday(),
                        this.birthDate.getMonthOfBirthday(),
                        this.birthDate.getDayOfBirthday()
                )
        );
    }

}
