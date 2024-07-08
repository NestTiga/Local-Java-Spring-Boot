package com.tigasinestor.local.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locals")
@Builder
public class Local {
    @Id
    @SequenceGenerator(
            name = "local_sequence",
            sequenceName = "local_sequence"
    )
    @GeneratedValue(
            generator = "local_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long localId;

    @NotEmpty(message = "Name must not be empty")
    @Length(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "Floor must not be empty")
    @Length(min = 3, max = 100, message = "Floor must be between 3 and 100 characters")
    @Column(name = "floor", nullable = false, length = 100)
    private String floor;


    @NotNull(message = "Local number must not be null")
    @Min(value = 1, message = "Local number must be greater than 0")
    @Column(nullable = false, unique = true)
    private Integer localNumber;
}
