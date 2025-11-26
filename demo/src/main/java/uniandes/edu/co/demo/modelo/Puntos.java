package uniandes.edu.co.demo.modelo;

import java.util.List;

public class Puntos {

    private Punto origen;
    private List<Punto> destinos;

    public Puntos() {
    }

    public Puntos(Punto origen, List<Punto> destinos) {
        this.origen = origen;
        this.destinos = destinos;
    }

    public Punto getOrigen() {
        return origen;
    }

    public void setOrigen(Punto origen) {
        this.origen = origen;
    }

    public List<Punto> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<Punto> destinos) {
        this.destinos = destinos;
    }
}
