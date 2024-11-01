package com.example.demo.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, String[]> {

    private static final String VALID_CHARACTERS = "ACTG";

    @Override
    public void initialize(ValidDna constraintAnnotation) {
    }

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext constraintValidatorContext) {
        // Chequea que la sequencia no sea nula
        if (dna == null) {
            System.out.println("El ADN es nulo");
            return false;
        }

        // Chequea que la sequencia no esté vacía
        int n = dna.length;
        if (n == 0) {
            System.out.println("El ADN está vacío");
            return false;
        }

        // Chequea que la sequencia sea una matriz NxN
        for (String sequence : dna) {
            if (sequence == null || sequence.length() != n) {
                System.out.println("El ADN no es una matriz NxN");
                return false;
            }
            // Chequea que la secuencia no tenga letras inválidas
            for (char c : sequence.toCharArray()) {
                if (VALID_CHARACTERS.indexOf(c) == -1) {
                    System.out.println("El ADN contiene letras inválidas");
                    return false;
                }
            }
        }

        return true;
    }
}
