package models;
import java.io.Serializable;
        
public class AntecedentesMedicos implements Serializable{
    private String enfermedad, fechaDiagnostico, tratamiento;

    public AntecedentesMedicos(String enfermedad, String fechaDiagnostico, String tratamiento) {
        this.enfermedad = enfermedad;
        this.fechaDiagnostico = fechaDiagnostico;
        this.tratamiento = tratamiento;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public String getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }
    
    public String toString(){
        return String.format("Enfermedad: %s\nFecha: %s\nTratamiento: %s\n", 
                             enfermedad, fechaDiagnostico, tratamiento);
    }
}