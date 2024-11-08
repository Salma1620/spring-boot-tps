package com.tp2.crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "description is empty")
    @Size(max = 255, message = "max size is 255")
    private String description;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "creationDate could not be null")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "echeanceDate could not be null")
    private Date echeanceDate;


}
