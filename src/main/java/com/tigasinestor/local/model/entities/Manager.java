package com.tigasinestor.local.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "managers")
@Builder
public class Manager {

    @Id
    @SequenceGenerator(
            name = "manager_sequence",
            sequenceName = "manager_sequence"
    )
    @GeneratedValue(
            generator = "manager_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long managerID;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    @NotBlank(message = "First name must not be empty")
    @Length(min = 3, max = 50, message = "First name must be between 3 and 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    @NotBlank(message = "Last name must not be empty")
    @Length(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Document must not be empty")
    @Length(min = 10, max = 10, message = "Document must be 10 characters")
    @Column(name = "document", nullable = false, unique = true, length = 50) //probando generar una columna unica desde la anotación column
    private String document; // cédula del manager (DNI)
    //NOTA(INCOMPLETO): falta validar el campo document usando una anotacion personalizada
    // tiene que ser una cedula ecuatoriana valida
}
