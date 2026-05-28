package models;
import java.io.Serializable;

public class InventarioClinica implements Serializable{
    private String nombreMedicamento;
    private int medicamentosDisponibles;
    private double costoMedicamento;

    public InventarioClinica(String nombreMedicamento, double costoMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
        this.costoMedicamento = costoMedicamento;
        this.medicamentosDisponibles = 100;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public int getMedicamentosDisponibles() {
        return medicamentosDisponibles;
    }

    public void setMedicamentosDisponibles(int medicamentosDisponibles) {
        this.medicamentosDisponibles = medicamentosDisponibles;
    }

    public double getCostoMedicamento() {
        return costoMedicamento;
    }
    
    public double costoTotal(int cantidad) {
        return costoMedicamento * cantidad;
    }
    
    public String toString(){
        return String.format("Nombre: %s\nCantidad Disponible: %d\nCosto: $%.2f", 
                            nombreMedicamento, medicamentosDisponibles, costoMedicamento);
    }
}