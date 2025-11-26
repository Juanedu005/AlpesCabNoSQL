package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.demo.modelo.Ciudad;

public interface CiudadRepository extends MongoRepository<Ciudad, String> {

}
