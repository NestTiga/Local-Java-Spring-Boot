package com.tigasinestor.local.validation.anotation;

import com.tigasinestor.local.validation.validator.ValidDocumentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // Se indica que la anotación debe ser documentada
@Constraint(validatedBy = ValidDocumentValidator.class) // Se indica la clase que se encargará de validar la anotación
@Retention(RetentionPolicy.RUNTIME) // Se indica que la anotación estará disponible en tiempo de ejecución
@Target({ElementType.FIELD, ElementType.METHOD}) // Se indica que la anotación se puede aplicar a campos y métodos
public @interface ValidDocument {
    String message() default "{custom.validDocument.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
