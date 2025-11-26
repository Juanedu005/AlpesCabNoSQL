package uniandes.edu.co.demo.modelo;

public class DetalleDomicilio {

    private String restaurante;
    private String orden;

    public DetalleDomicilio() {
    }

    public DetalleDomicilio(String restaurante, String orden) {
        this.restaurante = restaurante;
        this.orden = orden;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
}
