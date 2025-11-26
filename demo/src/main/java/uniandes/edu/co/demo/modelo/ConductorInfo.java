package uniandes.edu.co.demo.modelo;

import java.util.List;

public class ConductorInfo {

    private List<Vehiculo> vehiculos;

    public ConductorInfo() {
    }

    public ConductorInfo(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
