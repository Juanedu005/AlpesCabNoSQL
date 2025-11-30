package uniandes.edu.co.demo.modelo;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "servicios")
public class Servicio {

    @Id
    private String id;             // "S001"

    private String tipoServicio;   // PASAJEROS, DOMICILIO, MERCANCIAS
    private String nivelPasajeros; // solo si PASAJEROS

    private String clienteId;      // referencia a Usuario
    private String conductorId;    // referencia a Usuario
    private String vehiculoId;     // "V101"

    private String ciudadId;       // "CBOG"
    private String ciudadNombre;   // "Bogotá"

    private Puntos puntos;

    private String horaInicio;
    private String horaFin;
    private int duracionMinutos;
    private double distanciaKm;
    private double tarifaPorKm;
    private double costoTotal;
    private String estado;         

    private DetalleDomicilio detalleDomicilio;
    private DetalleMercancias detalleMercancias;

    public Servicio() {
    }

    public Servicio(String id, String tipoServicio, String clienteId,
                    String conductorId, String vehiculoId,
                    String ciudadId, String ciudadNombre) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.clienteId = clienteId;
        this.conductorId = conductorId;
        this.vehiculoId = vehiculoId;
        this.ciudadId = ciudadId;
        this.ciudadNombre = ciudadNombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getNivelPasajeros() {
        return nivelPasajeros;
    }

    public void setNivelPasajeros(String nivelPasajeros) {
        this.nivelPasajeros = nivelPasajeros;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getConductorId() {
        return conductorId;
    }

    public void setConductorId(String conductorId) {
        this.conductorId = conductorId;
    }

    public String getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(String vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(String ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public Puntos getPuntos() {
        return puntos;
    }

    public void setPuntos(Puntos puntos) {
        this.puntos = puntos;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public double getTarifaPorKm() {
        return tarifaPorKm;
    }

    public void setTarifaPorKm(double tarifaPorKm) {
        this.tarifaPorKm = tarifaPorKm;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DetalleDomicilio getDetalleDomicilio() {
        return detalleDomicilio;
    }

    public void setDetalleDomicilio(DetalleDomicilio detalleDomicilio) {
        this.detalleDomicilio = detalleDomicilio;
    }

    public DetalleMercancias getDetalleMercancias() {
        return detalleMercancias;
    }

    public void setDetalleMercancias(DetalleMercancias detalleMercancias) {
        this.detalleMercancias = detalleMercancias;
    }
}
