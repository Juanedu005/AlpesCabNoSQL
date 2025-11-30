package uniandes.edu.co.demo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Resena;
import uniandes.edu.co.demo.repository.ResenaRepository;

@RestController
@RequestMapping("/resenas")
public class ResenasController {

    @Autowired
    private ResenaRepository resenaRepository;

    @GetMapping("")
    public ResponseEntity<Collection<Resena>> obtenerTodasLasResenas() {
        try {
            return ResponseEntity.ok(resenaRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerResenaPorId(@PathVariable("id") @NonNull String id) {
        try {
            return resenaRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/servicio/{servicioId}")
    public ResponseEntity<List<Resena>> obtenerResenasPorServicio(
            @PathVariable("servicioId") @NonNull String servicioId) {
        try {
            List<Resena> resenas = resenaRepository.buscarResenasPorServicio(servicioId);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/receptor/{usuarioId}")
    public ResponseEntity<List<Resena>> obtenerResenasPorReceptor(
            @PathVariable("usuarioId") @NonNull String usuarioId) {
        try {
            List<Resena> resenas = resenaRepository.buscarResenasPorReceptor(usuarioId);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/autor/{usuarioId}")
    public ResponseEntity<List<Resena>> obtenerResenasPorAutor(
            @PathVariable("usuarioId") @NonNull String usuarioId) {
        try {
            List<Resena> resenas = resenaRepository.buscarResenasPorAutor(usuarioId);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> crearResena(@RequestBody @NonNull Resena resena) {
        try {
            resenaRepository.save(resena);
            return new ResponseEntity<>("Reseña creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la reseña", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarResena(@PathVariable("id") @NonNull String id,
                                                   @RequestBody @NonNull Resena resena) {
        try {
            resena.setId(id);
            resenaRepository.save(resena);
            return new ResponseEntity<>("Reseña actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la reseña", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarResena(@PathVariable("id") @NonNull String id) {
        try {
            resenaRepository.deleteById(id);
            return new ResponseEntity<>("Reseña eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la reseña", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
