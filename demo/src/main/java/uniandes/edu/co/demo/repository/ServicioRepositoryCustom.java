package uniandes.edu.co.demo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ServicioRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public ServicioRepositoryCustom(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Document> obtenerTop20ConductoresConMasServicios() {

        List<Document> pipeline = List.of(
            new Document("$match", new Document("estado", "FINALIZADO")),
            new Document("$group", new Document()
                .append("_id", "$conductorId")
                .append("totalServicios", new Document("$sum", 1))
            ),
            new Document("$sort", new Document("totalServicios", -1)),
            new Document("$limit", 20),
            new Document("$lookup", new Document()
                .append("from", "usuarios")
                .append("localField", "_id")
                .append("foreignField", "_id")
                .append("as", "conductor")
            ),
            new Document("$unwind", "$conductor"),
            new Document("$project", new Document()
                .append("_id", 0)
                .append("conductorId", "$_id")
                .append("nombre", "$conductor.nombre")
                .append("email", "$conductor.email")
                .append("telefono", "$conductor.telefono")
                .append("totalServicios", 1)
            )
        );

        return mongoTemplate
                .getCollection("servicios")
                .aggregate(pipeline)
                .into(new ArrayList<>());
    }

    public List<Document> obtenerEstadisticasServiciosPorCiudadYRangoFechas(
            String ciudadId, Date fechaInicio, Date fechaFin) {

        Document match = new Document("ciudadId", ciudadId)
                .append("horaInicio", new Document("$gte", fechaInicio).append("$lte", fechaFin));

        List<Document> pipeline = List.of(
            new Document("$match", match),
            new Document("$group", new Document()
                .append("_id", new Document()
                    .append("tipoServicio", "$tipoServicio")
                    .append("nivelPasajeros", "$nivelPasajeros")
                )
                .append("cantidad", new Document("$sum", 1))
            ),
            new Document("$sort", new Document("cantidad", -1))
        );

        return mongoTemplate
                .getCollection("servicios")
                .aggregate(pipeline)
                .into(new ArrayList<>());
    }
}
