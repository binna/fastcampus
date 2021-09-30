package com.fastcompaus.mycontact.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Block {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
}
