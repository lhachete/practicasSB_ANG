package com.lta.systempayments.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lta.systempayments.entities.Estudiante;
import com.lta.systempayments.entities.Pago;
import com.lta.systempayments.enums.PagoStatus;
import com.lta.systempayments.enums.TypePago;
import com.lta.systempayments.repository.EstudianteRepository;
import com.lta.systempayments.repository.PagoRepository;
import com.lta.systempayments.services.PagoService;

@RestController
@CrossOrigin("*")
public class PagoController {
    
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PagoService pagoService;

    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes(){
        return estudianteRepository.findAll();
    }

    @GetMapping("/estudiante/{codigo}")
    public Estudiante listarEstudiantePorCodigo(@PathVariable String codigo){
        return estudianteRepository.findByCodigo(codigo);
    }

    @GetMapping("/estudiantesPorPrograma")
    public List<Estudiante> listarEstudiantePorPrograma(@RequestParam String programaId){
        return estudianteRepository.findByProgramaId(programaId);
    }

    @GetMapping("/pagos")
    public List<Pago> listarPagos(){
        return pagoRepository.findAll();
    }

    @GetMapping("/pago/{id}")
    public Pago listarPagoPorId(@PathVariable Long id){
        return pagoRepository.findById(id).get();
    }

    @GetMapping("/estudiantes/{codigo}/pagos")
    public List<Pago> listarPagosPorCodigoEstudiante(@PathVariable String codigo){
        return pagoRepository.findByEstudianteCodigo(codigo);
    }

    @GetMapping("/pagosPorStatus")
    public List<Pago> listarPagosPorStatus(@RequestParam PagoStatus status){
        return pagoRepository.findByStatus(status);
    }

    @GetMapping("/pagos/porTipo")
    public List<Pago> listarPagosPorType(@RequestParam TypePago type){
        return pagoRepository.findByType(type);
    }

    @PutMapping("/pagos/{pagoId}/actualizarPago")
    public Pago actualizarStatusDePago(@RequestParam PagoStatus status, @PathVariable Long pagoId){
        return pagoService.actualizarPagoPorStatus(status, pagoId);
    }

    @PostMapping(path = "/pagos",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pago guardarPago(@RequestParam("file") MultipartFile file, double cantidad, TypePago type, LocalDate date, String codigoEstudiante) throws IOException{
        return pagoService.savePago(file, cantidad, type, date, codigoEstudiante);
    }

    @GetMapping(value = "/pago/{pagoId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] listarArchivoPorId(@PathVariable Long pagoId) throws IOException{
        return pagoService.getArchivoPorId(pagoId);
    }

}
