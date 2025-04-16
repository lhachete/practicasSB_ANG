package com.lta.sistemapagos.dtos;

import java.time.LocalDate;

import com.lta.sistemapagos.enums.TypePago;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPagoDto {
    
    private double cantidad;
    private TypePago typePago;
    private LocalDate date;
    private String codigoEstudiante;

}
