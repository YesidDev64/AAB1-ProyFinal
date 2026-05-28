package models;
import java.io.Serializable;

public class CitaMedica implements Serializable{
    private String razonCita, fechaCita, horaCita, diagnostico, enfermedad;
    private double costoConsulta, costoExamen, totalCita;
    private InventarioClinica medicamento;
    private Cliente cliente;
    private Mascota mascota;
    private Veterinario veterinario;

    public CitaMedica(String razonCita, String fechaCita, String horaCita, Cliente cliente, Mascota mascota, Veterinario veterinario) {
        this.razonCita = razonCita;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.diagnostico = "Ninguno";
        this.enfermedad = "Ninguno";
        this.costoConsulta = 20;
        this.costoExamen = 10;
        this.cliente = cliente;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.totalCita = 0;
    }
    
    public void setMedicamento(InventarioClinica medicamento) {
        this.medicamento = medicamento;
    }

    public InventarioClinica getMedicamento() {
        return medicamento;
    }

    public String getRazonCita() {
        return razonCita;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public double getCostoConsulta() {
        return costoConsulta;
    }

    public double getCostoExamen() {
        return costoExamen;
    }

    public double getTotalCita() {
        return totalCita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
    
    public void calcularTotal(int cantidadMed, int cantExamenes){
        double costoMed = 0;
        if(medicamento != null)
            costoMed = medicamento.costoTotal(cantidadMed);
        totalCita = costoConsulta + (costoExamen * cantExamenes) + costoMed;
    }
    
    public String toString(){
        return String.format("\nFecha: %s\nHora: %s\nRazon: %s\nDiagnostico: %s\nEnfermedad: %s\nCliente: %s\nMascota: %s\nVeterinario: %s\nTotal: $%.2f\n", 
                            fechaCita, horaCita, razonCita, diagnostico, enfermedad,
                            cliente.getNombresApellidos(), mascota.getNombre(), 
                            veterinario.getNombresApellidos(), totalCita);
    }
}