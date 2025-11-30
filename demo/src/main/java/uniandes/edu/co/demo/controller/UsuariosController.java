package uniandes.edu.co.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.modelo.ConductorInfo;
import uniandes.edu.co.demo.modelo.Vehiculo;
import uniandes.edu.co.demo.modelo.Disponibilidad;
import uniandes.edu.co.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("")
    public ResponseEntity<Collection<Usuario>> obtenerTodosLosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioRepository.buscarTodosLosUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") @NonNull String id) {
        try {
            Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorRol(@PathVariable("rol") @NonNull String rol) {
        try {
            List<Usuario> usuarios = usuarioRepository.buscarUsuariosPorRol(rol);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/conductores")
    public ResponseEntity<List<Usuario>> obtenerTodosLosConductores() {
        try {
            List<Usuario> conductores = usuarioRepository.buscarTodosLosConductores();
            return ResponseEntity.ok(conductores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> crearUsuario(@RequestBody @NonNull Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarUsuario(@PathVariable("id") @NonNull String id,
                                                    @RequestBody @NonNull Usuario usuario) {
        try {
            usuario.setId(id);
            usuarioRepository.save(usuario);
            return new ResponseEntity<>("Usuario actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") @NonNull String id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // RF3: Registrar vehículo para un conductor existente
    @PostMapping("/{id}/vehiculos/new/save")
    public ResponseEntity<String> registrarVehiculoParaConductor(@PathVariable("id") @NonNull String id,
                                                                 @RequestBody @NonNull Vehiculo vehiculo) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }

            ConductorInfo conductorInfo = usuario.getConductorInfo();
            if (conductorInfo == null) {
                conductorInfo = new ConductorInfo();
                usuario.setConductorInfo(conductorInfo);
            }

            List<Vehiculo> vehiculos = conductorInfo.getVehiculos();
            if (vehiculos == null) {
                vehiculos = new ArrayList<>();
                conductorInfo.setVehiculos(vehiculos);
            }

            vehiculos.add(vehiculo);
            usuarioRepository.save(usuario);
            return new ResponseEntity<>("Vehículo registrado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar el vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // RF4: Registrar disponibilidad para un vehículo
    @PostMapping("/{id}/vehiculos/{vehiculoId}/disponibilidades/new/save")
    public ResponseEntity<String> registrarDisponibilidadVehiculo(@PathVariable("id") @NonNull String id,
                                                                  @PathVariable("vehiculoId") @NonNull String vehiculoId,
                                                                  @RequestBody @NonNull Disponibilidad disponibilidad) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }

            ConductorInfo conductorInfo = usuario.getConductorInfo();
            if (conductorInfo == null || conductorInfo.getVehiculos() == null) {
                return new ResponseEntity<>("El usuario no tiene vehículos registrados", HttpStatus.BAD_REQUEST);
            }

            Vehiculo vehiculo = conductorInfo.getVehiculos().stream()
                    .filter(v -> vehiculoId.equals(v.getIdVehiculo()))
                    .findFirst()
                    .orElse(null);

            if (vehiculo == null) {
                return new ResponseEntity<>("Vehículo no encontrado", HttpStatus.NOT_FOUND);
            }

            List<Disponibilidad> disponibilidades = vehiculo.getDisponibilidades();
            if (disponibilidades == null) {
                disponibilidades = new ArrayList<>();
                vehiculo.setDisponibilidades(disponibilidades);
            }

            disponibilidades.add(disponibilidad);
            usuarioRepository.save(usuario);
            return new ResponseEntity<>("Disponibilidad registrada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // RF5: Modificar disponibilidad de un vehículo (por índice en la lista)
    @PostMapping("/{id}/vehiculos/{vehiculoId}/disponibilidades/{indice}/edit/save")
    public ResponseEntity<String> actualizarDisponibilidadVehiculo(@PathVariable("id") @NonNull String id,
                                                                   @PathVariable("vehiculoId") @NonNull String vehiculoId,
                                                                   @PathVariable("indice") int indice,
                                                                   @RequestBody @NonNull Disponibilidad disponibilidad) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }

            ConductorInfo conductorInfo = usuario.getConductorInfo();
            if (conductorInfo == null || conductorInfo.getVehiculos() == null) {
                return new ResponseEntity<>("El usuario no tiene vehículos registrados", HttpStatus.BAD_REQUEST);
            }

            Vehiculo vehiculo = conductorInfo.getVehiculos().stream()
                    .filter(v -> vehiculoId.equals(v.getIdVehiculo()))
                    .findFirst()
                    .orElse(null);

            if (vehiculo == null) {
                return new ResponseEntity<>("Vehículo no encontrado", HttpStatus.NOT_FOUND);
            }

            List<Disponibilidad> disponibilidades = vehiculo.getDisponibilidades();
            if (disponibilidades == null || indice < 0 || indice >= disponibilidades.size()) {
                return new ResponseEntity<>("Índice de disponibilidad inválido", HttpStatus.BAD_REQUEST);
            }

            disponibilidades.set(indice, disponibilidad);
            usuarioRepository.save(usuario);
            return new ResponseEntity<>("Disponibilidad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
