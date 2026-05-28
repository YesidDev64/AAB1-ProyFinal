package models;
import java.util.ArrayList;
import java.io.Serializable;

public class Cliente implements Serializable{
    private String nombresApellidos, cedula, numTelefono, correo;
    private ArrayList <Mascota> mascota;

    public Cliente(String nombresApellidos, String cedula, String numTelefono, String correo) {
        this.nombresApellidos = nombresApellidos;
        this.cedula = cedula;
        this.numTelefono = numTelefono;
        this.correo = correo;
        this.mascota = new ArrayList<>();
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public String getCedula() {
        return cedula;
    }
    
    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }
    
    public String getNumTelefono() {
        return numTelefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascota;
    }
    
    public Mascota buscarMascota(String nombMascota){
        for (int i = 0; i < mascota.size(); i++) {
            if(mascota.get(i).getNombre().equalsIgnoreCase(nombMascota)){
                return mascota.get(i);
            }
        }
        return null;
    }

    public void agregarMascota(Mascota m){
        mascota.add(m);
    }
    
    
    public String toString(){
        return String.format("Nombres y Apellidos: %s\nCedula: %s\nTelefono: %s\nCorreo: %s\n", 
                            nombresApellidos, cedula, numTelefono, correo);
    }
}