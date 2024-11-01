package com.example.demo.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import com.example.demo.validators.ValidDna;

@Getter
@Setter
public class MutantRequestDto {
    @Valid
    @ValidDna
    private String[] dna;
}
