package com.example.demo.Services;


import com.example.demo.Entities.Mutant;
import com.example.demo.Repositories.MutantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MutantService {
    private final MutantRepository mutantRepository;
    // Inyección de dependencias por constructor
    public MutantService(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    private static final int SEQUENCE_LENGTH = 4; // Número de letras iguales en secuencia para determinar un mutante
    private static int n; // Tamaño del ADN (NxN)

    // Chequeamos si el registro ya existe, si no lo guardamos
    public boolean checkDna(String[] dna){
        // Pasamos el arreglo a un String, para que pueda ser persistido
        String dnaString = String.join(",", dna);
        //Buscamos al registro específico
        Optional<Mutant> existingMutant = mutantRepository.findByDna(dnaString);

        //Si el ADN ya existe y ha sido analizado anteriormente, retornamos el resultado guardado
        if (existingMutant.isPresent()){
            return existingMutant.get().isMutant();
        }

        //Si el ADN es nuevo, guardamos el resultado para poder setearlo en la entidad
        boolean isMutant = isMutant(dna);
        Mutant mutant = Mutant.builder()
                .dna(dnaString)
                .isMutant(isMutant)
                .build();

        //Guardamos la entidad y retornamos el valor que devuelva el metodo isMutant
        mutantRepository.save(mutant);
        return isMutant(dna);
    }

    public static boolean isMutant(String[] dna) {
        n = dna.length;

        int sequenceCount = 0;  // Contador de secuencias mutantes encontradas


        //BUSCAMOS COINCIDENCIAS EN CADA DIRECCIÓN (horizontal, vertical, diagonal derecha e izquierda)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //Cada coincidencia encontrada se sumará al contador
                sequenceCount += checkHorizontal(dna, i, j) + checkVertical(dna, i, j)
                        + checkDiagonalRight(dna, i, j) + checkDiagonalLeft(dna, i, j);
                // Si encontramos más de una secuencia, es mutante y se corta la iteración
                if (sequenceCount > 1) return true;

            }
        }
        return false;  // Si no encontramos más de una coincidencia, no es mutante
    }

    // Verifica secuencia horizontal
    private static int checkHorizontal(String[] dna, int row, int col) {
        if (col + SEQUENCE_LENGTH > n) return 0; //Evita búsquedas innecesarias

        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row].charAt(col) != dna[row].charAt(col + i)) {
                return 0;  // Si los caracteres no coinciden, no es una secuencia válida
            }
        }
        return 1;  // Se encontró una secuencia válida
    }

    // Verifica secuencia vertical
    private static int checkVertical(String[] dna, int row, int col) {
        if (row + SEQUENCE_LENGTH > n) return 0; //Evita búsquedas innecesarias

        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row].charAt(col) != dna[row + i].charAt(col)) {
                return 0;  // Si los caracteres no coinciden, no es una secuencia válida
            }
        }
        return 1;  // Se encontró una secuencia válida
    }

    // Verifica secuencia diagonal hacia la derecha ↘
    private static int checkDiagonalRight(String[] dna, int row, int col) {
        if (row + SEQUENCE_LENGTH > n || col + SEQUENCE_LENGTH > n) return 0;  //Evita búsquedas innecesarias

        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row].charAt(col) != dna[row + i].charAt(col + i)) {
                return 0;  // Si los caracteres no coinciden, no es una secuencia válida
            }
        }
        return 1;  // Se encontró una secuencia válida
    }

    // Verifica secuencia diagonal hacia la izquierda ↙
    private static int checkDiagonalLeft(String[] dna, int row, int col) {
        if (row + SEQUENCE_LENGTH > n || col - SEQUENCE_LENGTH + 1 < 0) return 0;   //Evita búsquedas innecesarias

        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row].charAt(col) != dna[row + i].charAt(col - i)) {
                return 0;  // Si los caracteres no coinciden, no es una secuencia válida
            }
        }
        return 1;  // Se encontró una secuencia válida
    }
}
