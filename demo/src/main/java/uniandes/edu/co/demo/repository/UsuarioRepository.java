package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    @Query("{}")
    List<Usuario> buscarTodosLosUsuarios();

    @Query("{ _id: ?0 }")
    Usuario buscarUsuarioPorId(String id);

    @Query("{ 'roles': ?0 }")
    List<Usuario> buscarUsuariosPorRol(String rol);

    @Query("{ 'roles': 'CONDUCTOR' }")
    List<Usuario> buscarTodosLosConductores();
}
