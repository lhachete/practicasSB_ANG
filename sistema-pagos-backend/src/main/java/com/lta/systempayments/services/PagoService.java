package com.lta.systempayments.services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lta.systempayments.entities.Estudiante;
import com.lta.systempayments.entities.Pago;
import com.lta.systempayments.enums.PagoStatus;
import com.lta.systempayments.enums.TypePago;
import com.lta.systempayments.repository.EstudianteRepository;
import com.lta.systempayments.repository.PagoRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Pago savePago(MultipartFile file, double cantidad, TypePago type, LocalDate date, String codigoEstudiante) throws IOException{
        /* 
         * Creamos una ruta donde se guardará el archivo.
         * System.getProperty("user.home") --> Obtiene la ruta del directorio personal del usuario del S.O
         * Paths.get(...) --> Crea un objeto Path apuntando a una carpeta llamada enser/pagos dentro del directorio usuario 
         */
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-data","pagos");

        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }

        // Para crear un UUID para evitar conflictos en crear el archivo.
        String fileName = UUID.randomUUID().toString();
        // Creamos un Path para el archivo PDF que se guardará en  enset/data
        Path filePath = Paths.get(System.getProperty("user.home"),"enset-data","pagos", fileName + ".pdf");
        // file.getInputStream() --> Obtiene el flujo de datos del archivo recibido desde la solicitud HTTP
        //File.copy(...) --> Copia los datos del archivo al destino filePath
        Files.copy(file.getInputStream(), filePath);

        Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);

        Pago pago = Pago.builder()
        .type(type)
        .status(PagoStatus.CREADO)
        .fecha(date)
        .estudiante(estudiante)
        .cantidad(cantidad)
        .file(filePath.toUri().toString())
        .build();
        return pagoRepository.save(pago);
    }

    public byte[] getArchivoPorId(Long pagoId) throws IOException{
        Pago pago = pagoRepository.findById(pagoId).get();
        /* 
         * pago.getFIle() -> Obtiene la URI del archivo guardado
         * URI.create(...) -> Convierte la cadena en un objeto URI
         * Path.of(...) -> COnvierte el URI en un Path
         * Files.readAllBytes() -> Lee el contenido del archivo y lo devuelve como un array de bytes
         */
        return Files.readAllBytes(Path.of(URI.create(pago.getFile())));
    }

    public Pago actualizarPagoPorStatus(PagoStatus status, Long id){
        Pago pago = pagoRepository.findById(id).get();
        pago.setStatus(status);
        return pagoRepository.save(pago);
    }
}
