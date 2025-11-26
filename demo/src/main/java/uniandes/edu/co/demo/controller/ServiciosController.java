package uniandes.edu.co.demo.controller;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.repository.ServicioRepository;
import uniandes.edu.co.demo.repository.ServicioRepositoryCustom;

@RestController
@RequestMapping("/servicios")
public class ServiciosController {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ServicioRepositoryCustom servicioRepositoryCustom;

    @GetMapping("")
    public ResponseEntity<Collection<Servicio>> obtenerTodosLosServicios() {
        try {
            return ResponseEntity.ok(servicioRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable("id") @NonNull String id) {
        try {
            return servicioRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> crearServicio(@RequestBody @NonNull Servicio servicio) {
        try {
            servicioRepository.save(servicio);
            return new ResponseEntity<>("Servicio creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarServicio(@PathVariable("id") @NonNull String id,
                                                     @RequestBody @NonNull Servicio servicio) {
        try {
            servicio.setId(id);
            servicioRepository.save(servicio);
            return new ResponseEntity<>("Servicio actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarServicio(@PathVariable("id") @NonNull String id) {
        try {
            servicioRepository.deleteById(id);
            return new ResponseEntity<>("Servicio eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Servicio>> obtenerHistoricoPorCliente(
            @PathVariable("clienteId") @NonNull String clienteId) {
        try {
            List<Servicio> servicios = servicioRepository.buscarHistoricoPorCliente(clienteId);
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/conductor/{conductorId}")
    public ResponseEntity<List<Servicio>> obtenerServiciosPorConductor(
            @PathVariable("conductorId") @NonNull String conductorId) {
        try {
            List<Servicio> servicios = servicioRepository.buscarServiciosPorConductor(conductorId);
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/ciudad/{ciudadId}")
    public ResponseEntity<List<Servicio>> obtenerServiciosPorCiudadYRangoFechas(
            @PathVariable("ciudadId") @NonNull String ciudadId,
            @RequestParam("fechaInicio") @NonNull String fechaInicio,
            @RequestParam("fechaFin") @NonNull String fechaFin) {
        try {
            Date inicio = Date.from(Instant.parse(fechaInicio));
            Date fin = Date.from(Instant.parse(fechaFin));
            List<Servicio> servicios = servicioRepository.buscarServiciosPorCiudadYRangoFechas(ciudadId, inicio, fin);
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/top-conductores")
    public ResponseEntity<List<Document>> obtenerTop20Conductores() {
        try {
            List<Document> resultado = servicioRepositoryCustom.obtenerTop20ConductoresConMasServicios();
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<List<Document>> obtenerEstadisticasServiciosPorCiudadYRangoFechas(
            @RequestParam("ciudadId") @NonNull String ciudadId,
            @RequestParam("fechaInicio") @NonNull String fechaInicio,
            @RequestParam("fechaFin") @NonNull String fechaFin) {
        try {
            Date inicio = Date.from(Instant.parse(fechaInicio));
            Date fin = Date.from(Instant.parse(fechaFin));
            List<Document> resultado = servicioRepositoryCustom
                    .obtenerEstadisticasServiciosPorCiudadYRangoFechas(ciudadId, inicio, fin);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
