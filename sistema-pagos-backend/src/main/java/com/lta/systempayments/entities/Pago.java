package com.lta.systempayments.entities;

import java.time.LocalDate;

import com.lta.systempayments.enums.PagoStatus;
import com.lta.systempayments.enums.TypePago;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pago {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Aquí le estamos diciendo que este campo se va a generar automáticamente en la BD
    private String id;

    private LocalDate fecha;

    private double cantidad;

    private TypePago type;

    private PagoStatus status;

    private String file;

    @ManyToOne // Un estudiante puede tener asociados muchos pagos.
    private Estudiante estudiante;

}
