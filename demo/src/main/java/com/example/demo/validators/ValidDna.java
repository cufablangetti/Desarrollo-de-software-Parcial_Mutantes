package com.example.demo.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DnaValidator.class)   // Define qué clase (DnaValidator) va a manejar la lógica de validación
@Target({ElementType.FIELD})    // Define que la anotación se puede aplicar a campos
@Retention(RetentionPolicy.RUNTIME)  // Define que la anotación estará disponible sólo en tiempo de ejecución
public @interface ValidDna {
    String message() default "Secuencia de ADN inválida";  // Mensaje por defecto si falla la validación
    Class<?>[] groups() default {}; //Permite especificar diferentes grupos de validación
    Class<? extends Payload>[] payload() default {};    //Se podrán pasar metadatos adicionales junto con la validación
}
