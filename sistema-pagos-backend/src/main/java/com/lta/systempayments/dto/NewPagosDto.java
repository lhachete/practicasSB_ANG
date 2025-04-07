package com.lta.systempayments.dto;

import java.time.LocalDate;

import com.lta.systempayments.enums.TypePago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NewPagosDto {
    
    private double cantidad;
    private TypePago typePago;
    private LocalDate date;
    private String codigoEstudiante;
}
