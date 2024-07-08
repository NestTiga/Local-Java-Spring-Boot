package com.tigasinestor.local.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "customers",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email"
                /*
                diferencia con el unique de la anotación @Column: se tiene más contro sobre la restricción,
                en este caso se puede personalizar el nombre de la restricción.
                 */
        ))
@Builder
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence"
    )
    @GeneratedValue(
            generator = "customer_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long customerId;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    @Length(min = 3, max = 50, message = "First name must be between 3 and 50 characters")
    @NotBlank(message = "First name must not be empty")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    @Length(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
    @NotBlank(message = "Last name must not be empty")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Length(max = 100, message = "Email must be less than 100 characters")
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be a valid email address")
    @Column(name = "email", nullable = false, length = 100)
    private String email;
}
