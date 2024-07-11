package com.tigasinestor.local.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Name must not be empty") //no permite que ingrese solo espacios a diferencia de @NotEmpty
    @Length(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Floor must not be empty")
    @Length(min = 3, max = 100, message = "Floor must be between 3 and 100 characters")
    @Column(name = "floor", nullable = false, length = 100)
    private String floor;


    @NotNull(message = "Local number must not be null")
    @Min(value = 1, message = "Local number must be greater than 0")
    @Column(nullable = false, unique = true)
    private Integer localNumber;

    @NotNull(message = "Manager must not be null")
    @OneToOne(optional = false) //en esta ralación el fetch por defecto es EAGER
    @JoinColumn(
            name = "manager_id",
            referencedColumnName = "managerId",
            foreignKey = @ForeignKey(
                    name = "manager_local_fk"
            )
    )
    private Manager manager;
}
