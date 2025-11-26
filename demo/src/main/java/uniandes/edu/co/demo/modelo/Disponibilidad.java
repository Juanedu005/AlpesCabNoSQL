package uniandes.edu.co.demo.modelo;

import java.util.List;

public class Disponibilidad {

    private int diaSemana;          
    private String horaInicio;         
    private String horaFin;          
    private List<String> tiposServicio; 
    private String nivelPasajeros;     

    public Disponibilidad() {
    }

    public Disponibilidad(int diaSemana, String horaInicio, String horaFin,
                          List<String> tiposServicio, String nivelPasajeros) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tiposServicio = tiposServicio;
        this.nivelPasajeros = nivelPasajeros;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
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

    public List<String> getTiposServicio() {
        return tiposServicio;
    }

    public void setTiposServicio(List<String> tiposServicio) {
        this.tiposServicio = tiposServicio;
    }

    public String getNivelPasajeros() {
        return nivelPasajeros;
    }

    public void setNivelPasajeros(String nivelPasajeros) {
        this.nivelPasajeros = nivelPasajeros;
    }
}
