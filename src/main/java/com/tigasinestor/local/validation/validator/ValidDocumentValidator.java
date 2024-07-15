package com.tigasinestor.local.validation.validator;

import com.tigasinestor.local.validation.anotation.ValidDocument;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDocumentValidator implements ConstraintValidator<ValidDocument, String> {
    @Override
    public void initialize(ValidDocument constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String document, ConstraintValidatorContext constraintValidatorContext) {
        int suma = 0;
        if (document.length() != 10 || document.equals("0000000000")) {
            return false;
        } else {
            int a[] = new int[document.length() / 2];
            int b[] = new int[(document.length() / 2)];
            int c = 0;
            int d = 1;
            for (int i = 0; i < document.length() / 2; i++) {
                a[i] = Integer.parseInt(String.valueOf(document.charAt(c)));
                c = c + 2;
                if (i < (document.length() / 2) - 1) {
                    b[i] = Integer.parseInt(String.valueOf(document.charAt(d)));
                    d = d + 2;
                }
            }

            for (int i = 0; i < a.length; i++) {
                a[i] = a[i] * 2;
                if (a[i] > 9) {
                    a[i] = a[i] - 9;
                }
                suma = suma + a[i] + b[i];
            }
            int aux = suma / 10;
            int dec = (aux + 1) * 10;
            if ((dec - suma) == Integer.parseInt(String.valueOf(document.charAt(document.length() - 1))))
                return true;
            else if (suma % 10 == 0 && document.charAt(document.length() - 1) == '0') {
                return true;
            } else {
                return false;
            }

        }
    }
}
