package uniandes.edu.co.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Ciudad;
import uniandes.edu.co.demo.repository.CiudadRepository;

@RestController
@RequestMapping("/ciudades")
public class CiudadesController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("")
    public ResponseEntity<Collection<Ciudad>> obtenerTodasLasCiudades() {
        try {
            return ResponseEntity.ok(ciudadRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> obtenerCiudadPorId(@PathVariable("id") @NonNull String id) {
        try {
            return ciudadRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> crearCiudad(@RequestBody @NonNull Ciudad ciudad) {
        try {
            ciudadRepository.save(ciudad);
            return new ResponseEntity<>("Ciudad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarCiudad(@PathVariable("id") @NonNull String id,
                                                   @RequestBody @NonNull Ciudad ciudad) {
        try {
            ciudad.setId(id);
            ciudadRepository.save(ciudad);
            return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCiudad(@PathVariable("id") @NonNull String id) {
        try {
            ciudadRepository.deleteById(id);
            return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
