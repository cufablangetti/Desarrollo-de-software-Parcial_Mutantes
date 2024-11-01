package com.example.demo;


import com.example.demo.Services.MutantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutantServiceTest {

    //Chequea coincidencias en filas
    @Test
    public void testRows(){
        String[] dna = {
                "CCCCTA",
                "TGCAGT",
                "GCTTCC",
                "CCCCTG",
                "GTAGTC",
                "AGTCAC"
        };
        Assertions.assertTrue(MutantService.isMutant(dna));
    }

    //Chequea coincidencias en columnas
    @Test
    public void testColumns(){
        String[] dna = {
                "AGAATG",
                "TGCAGT",
                "GCTTCC",
                "GTCCTC",
                "GTAGTC",
                "GGTCAC"
        };
        assertTrue(MutantService.isMutant(dna));
    }

    //Chequea ambas diagonales principales
    @Test
    public void testMainDiagonals(){
        String[] dna = {
                "AGAATG",
                "TACAGT",
                "GCAGCC",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(MutantService.isMutant(dna));
    }

    //Chequea diagonales derecha ↘
    @Test
    public void testDiagonalRight(){
        String[] dna = {
                "ATAATG",
                "GTTAGT",
                "GGCTCG",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(MutantService.isMutant(dna));
    }

    //Chequea diagonales izquierda ↙
    @Test
    public void testDiagonalLeft(){
        String[] dna = {
                "ATAATG",
                "GTATGA",
                "GCTTAG",
                "TTTAGG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(MutantService.isMutant(dna));
    }

    //Chequea filas y columnas
    @Test
    public void testRowsCols(){
        String[] dna = {
                "CGATCA",
                "GATGCT",
                "TCCCCT",
                "TACAGT",
                "GTAACT",
                "ACCGTA"
        };
        assertTrue(MutantService.isMutant(dna));
    }

    //Chequea coincidencias en todas las direcciones
    @Test
    public void testAllDirections(){
        String[] dna = {
                "GGTGTG",
                "TCGCCG",
                "CCAAAA",
                "ACTGAT",
                "GCCAGC",
                "CTACTA"
        };
        assertTrue(MutantService.isMutant(dna));
    }

    //Chequea coincidencia no mutante
    @Test
    public void testNonMutant(){
        String[] dna = {
                "ATCGAT",
                "CTCTTG",
                "CAAGGC",
                "GGTATT",
                "ATCGAT",
                "AAGTCC"
        };
        assertFalse(MutantService.isMutant(dna));
    }
}
