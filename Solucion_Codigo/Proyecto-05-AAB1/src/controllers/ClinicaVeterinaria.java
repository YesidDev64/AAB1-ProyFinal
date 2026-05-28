package controllers;
import models.*;
import java.util.ArrayList;
import java.io.Serializable;

public class ClinicaVeterinaria implements Serializable{
    private String nombreClinica, RUC, direccion, telefono;
    private ArrayList <InventarioClinica> inventario;
    private ArrayList <CitaMedica> citas;
    private ArrayList <Cliente> clientes;
    private ArrayList <Veterinario> veterinarios;

    public ClinicaVeterinaria(String nombreClinica, String RUC, String direccion, String telefono) {
        this.nombreClinica = nombreClinica;
        this.RUC = RUC;
        this.direccion = direccion;
        this.telefono = telefono;
        this.inventario = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
    }
    
    public void cargarInventario(){
        inventario.add(new InventarioClinica("Amoxicilina", 1.50));
        inventario.add(new InventarioClinica("Oclacitinib", 3.00));
        inventario.add(new InventarioClinica("Maropitant", 7.00));
        inventario.add(new InventarioClinica("Doxiciclina", 1.00));
        inventario.add(new InventarioClinica("Fluralaner", 40.00));
    }
    
    public void cargarVeterinarios(){
        veterinarios.add(new Veterinario("Carlos Mendoza", "1314859620", "0984123765", "Gastroenterologo"));
        veterinarios.add(new Veterinario("Maria Espinoza", "1725948312", "0995874123", "Dermatologo"));
        veterinarios.add(new Veterinario("Juan Andrade", "1104769831", "0961592834", "Nefrologo"));
        veterinarios.add(new Veterinario("Ana Rodriguez", "0928374156", "0939485712", "Internista"));
        veterinarios.add(new Veterinario("Luis Cardenas", "0105634298", "0972614958", "Epidemiologo"));
    }     
    
    public Cliente buscarCliente (String cedula){
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getCedula().equals(cedula))
                return clientes.get(i);
        }
        return null;
    }
    
    public String registrarCliente(String nombres, String cedula, String numTelefono, String correo){
        if(buscarCliente(cedula) != null)
            return String.format("Ya hay un cliente con la cedula %s\n", cedula);
        
        Cliente nuevoCliente = new Cliente(nombres, cedula, numTelefono, correo);
        clientes.add(nuevoCliente);
        return String.format("Cliente registrado correctamente\n");
    }
    
    public String registrarMascota(String cedula, String nombre, String especie, String raza, char genero, int edadMeses, double pesoKg){
        Cliente c = buscarCliente(cedula);
        
        if (c != null){
            Mascota m = new Mascota(nombre, especie, raza, genero, edadMeses, pesoKg);
            c.agregarMascota(m);
            return String.format("Mascota registrada correctamente\n");
        }else
            return String.format("Cliente no encontrado...\n");
    }    
    
    public InventarioClinica buscarMedicamento(String nombre){
        for (int i = 0; i < inventario.size(); i++) {
            if(inventario.get(i).getNombreMedicamento().equalsIgnoreCase(nombre))
                return inventario.get(i);
        }
        return null;       
    }
    
    public String registrarAntecedentes(String cedulaCliente, String nombreMascota, String enfermedad, String fecha, String tratamiento){
        Cliente c = buscarCliente(cedulaCliente);
        
        if(c != null){
            Mascota m = c.buscarMascota(nombreMascota);
            if(m != null){
                AntecedentesMedicos a = new AntecedentesMedicos(enfermedad, fecha, tratamiento);
                m.agregarAntecedentes(a);
            }else
                return String.format("Mascota no registrada...\n");
        }else
            return String.format("Cliente no registrado...\n");
        return String.format("Antecedente registrado correctamente\n");
    }

    public Veterinario buscarVeterinario (String cedula){
        for (int i = 0; i < veterinarios.size(); i++) {
            if(veterinarios.get(i).getCedula().equals(cedula))
                return veterinarios.get(i);
        }
        return null;
    }
    
    public String registrarCita(String cedulaCliente, String nombreMascota, String cedulaVeterinario, String razon, String fecha, String hora){
        Cliente c = buscarCliente(cedulaCliente);
        if(c == null) return String.format("Cliente no encontrado...\n");
        
        Veterinario v = buscarVeterinario(cedulaVeterinario);
        if(v == null) return String.format("Veterinario no encontrado...\n");
        
        Mascota m = c.buscarMascota(nombreMascota);
        if(m == null) return String.format("Mascota no encontrada...\n");
        
        CitaMedica cita = new CitaMedica(razon, fecha, hora, c, m, v);
        citas.add(cita);
        return String.format("Cita registrada correctamente\n");
    }
    
    public String mostrarCitas(){
        String reporte = "";
        if(citas.isEmpty()) return String.format("No hay citas registrada...\n");
        
        for (int i = 0; i < citas.size(); i++) {
            reporte += String.format("Cita N°%d\n%s\n=============================================\n", 
                                    (i+1), citas.get(i).toString());
        }
        return reporte;
    }
    
    public String atenderCita(int numCita, String diagnostico, String enfermedad, int cantExamenes, 
                              String nombMedicamento, int cantMedicamento){
        if(numCita <= 0 || numCita > citas.size()) 
            return String.format("Numero de cita no valido...");
        
        CitaMedica cita = citas.get(numCita - 1);
        cita.setDiagnostico(diagnostico);
        cita.setEnfermedad(enfermedad);
        
        if(!nombMedicamento.equalsIgnoreCase("ninguno")){
            InventarioClinica med = buscarMedicamento(nombMedicamento);
            
            if(med == null) return String.format("No existe ese medicamento...\n");
            
            if(med.getMedicamentosDisponibles() < cantMedicamento) 
                return String.format("No hay suficiente stock del medicamento...\n");
            
            med.setMedicamentosDisponibles(med.getMedicamentosDisponibles() - cantMedicamento);
            cita.setMedicamento(med);  
        }
        cita.calcularTotal(cantMedicamento, cantExamenes);
        return String.format("Cita Atentida correctamente: %s\n", cita.toString());
    }
    
    public String mostrarClientes(){
        String reporte = "";
        if(clientes.isEmpty())
            return String.format("No hay clientes registrados...\n");
        for (int i = 0; i < clientes.size(); i++) {
            reporte += String.format("Cliente %d:\n%s\n=============================================\n", 
                                           (i + 1), clientes.get(i).toString());
        }
        return reporte;
    }
    
    public String mostrarInventario(){
        String reporte = "";
        if(inventario.isEmpty())
            return String.format("No hay medicamentos registrados...\n");
        for (int i = 0; i < inventario.size(); i++) {
            reporte += String.format("Medicamento %d:\n%s\n=============================================\n", 
                                           (i + 1), inventario.get(i).toString());
        }
        return reporte;
    }
    
    public String mostrarVeterinarios(){
        String reporte = "";
        if(veterinarios.isEmpty())
            return String.format("No hay veterinarios registrados...\n");
        for (int i = 0; i < veterinarios.size(); i++) {
            reporte += String.format("Veterinario %d:\n%s\n=============================================\n", 
                                           (i + 1), veterinarios.get(i).toString());
        }
        return reporte;
    }
    
    public String mostrarExpedienteCliente(String cedula){
        Cliente c = buscarCliente(cedula);
        
        if(c == null)
            return String.format("Cliente no encontrado...\n");
        
        String reporte = String.format("---------Expediente del Cliente---------\n");
        reporte += String.format("%s\n",c.toString());
        for (int i = 0; i < c.getMascotas().size(); i++) {
            Mascota m = c.getMascotas().get(i);
            reporte += String.format("Mascota %d\n%s\n", (i + 1), m.toString());
            
            if(m.getAntecedentes().isEmpty())
                reporte += String.format("Sin antecedentes registrados\n");
            else{
                reporte += String.format("Antecedentes:\n");
                for (int j = 0; j < m.getAntecedentes().size(); j++) {
                    reporte += String.format("%s\n", 
                            m.getAntecedentes().get(j).toString());
                }
            }
            reporte += String.format("=============================================\n");
        }
        return reporte;
    }
    
    public String estadisticasEspecies(String mes){
        int perros = 0, gatos = 0, aves = 0, otros= 0;
        for (int i = 0; i < citas.size(); i++) {
            CitaMedica cita = citas.get(i);
            
            if(cita.getFechaCita().substring(3, 5).equals(mes)){
                String especie = cita.getMascota().getEspecie();
                if(especie.equalsIgnoreCase("perro"))
                    perros++;
                else if(especie.equalsIgnoreCase("gato"))
                    gatos++;
                else if(especie.equalsIgnoreCase("ave"))
                    aves++;
                else
                    otros++;
            }
        }
        return String.format("Afluencia por especie en el mes %s\nPerros: %d\nGatos: %d\n"
                           + "Aves: %d\nOtros: %d\n", mes, perros, gatos, aves, otros);
    }
    
    public int buscarEnfermedad(String[] enfermedades, int total, String enfermedad){
        for (int i = 0; i < total; i++) {
            if(enfermedades[i].equalsIgnoreCase(enfermedad)){
                return i;
            }
        }
        return -1;
    }
    
    public String estadisticasPatologias(String mes){
        String[] enfermedades = new String[citas.size()];
        int[] totalEnfermedad = new int[citas.size()];
        int totalEnfermedades = 0;
        
        for (int i = 0; i < citas.size(); i++) {
            CitaMedica cita = citas.get(i);
            if(cita.getFechaCita().substring(3, 5).equals(mes) && cita.getEnfermedad()!= null){
                String enfermedad = cita.getEnfermedad();
                
                if(!enfermedad.equalsIgnoreCase("Ninguna")){                         
                    int posicion = buscarEnfermedad(enfermedades, totalEnfermedades, enfermedad);
                    if(posicion == -1){
                        enfermedades[totalEnfermedades] = enfermedad;
                        totalEnfermedad[totalEnfermedades] = 1;
                        totalEnfermedades++;
                    }else
                        totalEnfermedad[posicion]++;
                }    
            }
        }
        String reporte = String.format("Patologias tratadas en el mes %s\n", mes);
        for (int i = 0; i < totalEnfermedades; i++) 
            reporte += String.format("%s : %d\n", enfermedades[i], totalEnfermedad[i]);
        return reporte;
    }
    
    public String mostrarEstadisticas(String mes){
        return String.format("%s\n%s\n", 
                estadisticasEspecies(mes), estadisticasPatologias(mes));
    }
    
    public String reporteGeneral(String mes){
        return String.format("===== REPORTE GENERAL CLINICA =====\n\n"
                + "===== CLIENTES =====\n%s\n"
                + "===== VETERINARIOS =====\n%s\n"
                + "===== INVENTARIO =====\n%s\n"
                + "===== CITAS MEDICAS =====\n%s\n"
                + "===== ESTADISTICAS =====\n%s\n",
                mostrarClientes(), mostrarVeterinarios(),
                mostrarInventario(), mostrarCitas(), 
                mostrarEstadisticas(mes));
    }
}