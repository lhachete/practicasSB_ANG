package com.lta.sistemapagos.services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lta.sistemapagos.entities.Estudiante;
import com.lta.sistemapagos.entities.Pago;
import com.lta.sistemapagos.enums.PagoStatus;
import com.lta.sistemapagos.enums.TypePago;
import com.lta.sistemapagos.repository.EstudianteRepository;
import com.lta.sistemapagos.repository.PagoRepository;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Pago savePago(MultipartFile file, double cantidad, TypePago type, LocalDate date, String codigoEstudiante)
            throws IOException {
        /*
         * - Creamos ina ruta donde se guardará el archivo
         * - System.getProperty("user.home"): Obtiene la ruta del directorio personal
         * del usuario del S.O
         * - Paths.get(...): Crea un objeto Path apuntando a una carpeta llamada
         * enset/pagos dentro del directorio usuario
         */
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos");

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        String fileName = UUID.randomUUID().toString();

        // Creamos un Path para el archivo PDF que se guardará en enset/data
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos", fileName + ".pdf");

        // files.getInputStream(): Obtiene el flujo de datos del archivo recibido desde
        // la solicitud HTTP
        // Files.copy(...): Copia los datos del archivo al destino filePath
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
         - pago.getFile(): Obtiene la URI del archivo guardado
         - URI.create(...): Convierte la cadena en un objeto URI
         - Path.of(...): Convierte el URI en un Path
         - Files.readAllBytes(): Lee el contenido del archivo y lo devuelve como un array de bytes
        */
        return Files.readAllBytes(Path.of(URI.create(pago.getFile())));
    }

    public Pago actualizarPagoPorStatus(PagoStatus status,Long id){
        Pago pago = pagoRepository.findById(id).get();
        pago.setStatus(status);
        return pagoRepository.save(pago);
    }
}
