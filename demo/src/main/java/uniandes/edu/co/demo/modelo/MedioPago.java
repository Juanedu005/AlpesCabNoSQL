package uniandes.edu.co.demo.modelo;

public class MedioPago {

    private String nombreTc;
    private String numeroTc;          
    private String fechaVencimiento;  
    private String cv;
    private boolean activa;

    public MedioPago() {
    }

    public MedioPago(String nombreTc, String numeroTc,
                     String fechaVencimiento, String cv, boolean activa) {
        this.nombreTc = nombreTc;
        this.numeroTc = numeroTc;
        this.fechaVencimiento = fechaVencimiento;
        this.cv = cv;
        this.activa = activa;
    }

    public String getNombreTc() {
        return nombreTc;
    }

    public void setNombreTc(String nombreTc) {
        this.nombreTc = nombreTc;
    }

    public String getNumeroTc() {
        return numeroTc;
    }

    public void setNumeroTc(String numeroTc) {
        this.numeroTc = numeroTc;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
