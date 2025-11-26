package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Resena;

public interface ResenaRepository extends MongoRepository<Resena, String> {

    @Query("{ 'servicioId': ?0 }")
    List<Resena> buscarResenasPorServicio(String servicioId);

    @Query("{ 'receptorId': ?0 }")
    List<Resena> buscarResenasPorReceptor(String usuarioId);

    @Query("{ 'autorId': ?0 }")
    List<Resena> buscarResenasPorAutor(String usuarioId);
}
