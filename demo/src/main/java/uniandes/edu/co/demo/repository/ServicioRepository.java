package uniandes.edu.co.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Servicio;

public interface ServicioRepository extends MongoRepository<Servicio, String> {

    @Query(value = "{ 'clienteId': ?0 }", sort = "{ 'horaInicio': -1 }")
    List<Servicio> buscarHistoricoPorCliente(String clienteId);

    @Query(value = "{ 'conductorId': ?0 }", sort = "{ 'horaInicio': -1 }")
    List<Servicio> buscarServiciosPorConductor(String conductorId);

    @Query("{ 'ciudadId': ?0, 'horaInicio': { $gte: ?1, $lte: ?2 } }")
    List<Servicio> buscarServiciosPorCiudadYRangoFechas(String ciudadId, Date fechaInicio, Date fechaFin);

    @Query("{ 'tipoServicio': ?0 }")
    List<Servicio> buscarServiciosPorTipo(String tipoServicio);
}
