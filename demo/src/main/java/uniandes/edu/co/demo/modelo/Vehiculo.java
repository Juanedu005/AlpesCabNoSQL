package uniandes.edu.co.demo.modelo;

import java.util.List;

public class Vehiculo {

    private String idVehiculo;      // "V101", etc.
    private String tipo;            // CARRO, MOTO, CAMIONETA
    private String marca;
    private String modelo;
    private String color;
    private String placa;
    private int capacidad;

    private String ciudadId;        // "CBOG"
    private String ciudadNombre;    // "Bogotá"

    private List<Disponibilidad> disponibilidades;

    public Vehiculo() {
    }

    public Vehiculo(String idVehiculo, String tipo, String marca, String modelo,
                    String color, String placa, int capacidad,
                    String ciudadId, String ciudadNombre) {
        this.idVehiculo = idVehiculo;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.placa = placa;
        this.capacidad = capacidad;
        this.ciudadId = ciudadId;
        this.ciudadNombre = ciudadNombre;
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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

    public List<Disponibilidad> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<Disponibilidad> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }
}
