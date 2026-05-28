package models;
import java.io.Serializable;

public class Veterinario implements Serializable{
    private String nombresApellidos, cedula, numTelefono, especialidad;

    public Veterinario(String nombresApellidos, String cedula, String numTelefono, String especialidad) {
        this.nombresApellidos = nombresApellidos;
        this.cedula = cedula;
        this.numTelefono = numTelefono;
        this.especialidad = especialidad;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    
    public String toString(){
        return String.format("Nombres y Apellidos: %s\nCedula: %s\nTelefono: %s\nEspecialidad: %s\n",
                             nombresApellidos, cedula, numTelefono, especialidad);
    }
}