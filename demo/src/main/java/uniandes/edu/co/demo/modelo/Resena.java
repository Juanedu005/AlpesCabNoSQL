package uniandes.edu.co.demo.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reseñas")
public class Resena {

    @Id
    private String id;         // "R001"
    private String tipo;       // CLIENTE_A_CONDUCTOR o CONDUCTOR_A_CLIENTE

    private String servicioId;
    private String autorId;
    private String receptorId;

    private int puntuacion;    // 0–5
    private String comentario;
    private Date fecha;

    public Resena() {
    }

    public Resena(String id, String tipo, String servicioId,
                  String autorId, String receptorId,
                  int puntuacion, String comentario, Date fecha) {
        this.id = id;
        this.tipo = tipo;
        this.servicioId = servicioId;
        this.autorId = autorId;
        this.receptorId = receptorId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getServicioId() {
        return servicioId;
    }

    public void setServicioId(String servicioId) {
        this.servicioId = servicioId;
    }

    public String getAutorId() {
        return autorId;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    public String getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(String receptorId) {
        this.receptorId = receptorId;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
