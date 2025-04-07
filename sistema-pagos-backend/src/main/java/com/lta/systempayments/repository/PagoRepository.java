package com.lta.systempayments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lta.systempayments.entities.Pago;
import com.lta.systempayments.enums.PagoStatus;
import com.lta.systempayments.enums.TypePago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByEstudianteCodigo(String codigo);

    List<Pago> findByStatus(PagoStatus status); // Para poder buscar una lista de pagos según el status.

    List<Pago> findByType(TypePago typePago); // Para poder buscar una lista de pagos según su tipo.

}
