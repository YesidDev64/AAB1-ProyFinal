package view;
import controllers.ClinicaVeterinaria;
import controllers.ArchivoClinica;
import java.util.Scanner;

public class MenuVeterinaria {
    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        ClinicaVeterinaria clinica = new ClinicaVeterinaria("Veterinaria Rodriguez", "1104775845001", 
                                                       "Avenida Benjamin y Calle Perez", "0986845784");
        clinica.cargarInventario();
        clinica.cargarVeterinarios();
        ArchivoClinica archivo = new ArchivoClinica();
        int opcion;
        do {            
            System.out.printf("\nClinica Veterinaria Rodriguez"
                            + "\n[1] Registrar cliente:"
                            + "\n[2] Registrar mascota:"
                            + "\n[3] Registrar antecedente medico de la mascota:"
                            + "\n[4] Agendar cita medica:"
                            + "\n[5] Atender cita y facturar:"
                            + "\n[6] Mostrar clientes:"
                            + "\n[7] Mostrar veterinarios:"
                            + "\n[8] Mostrar inventario:"
                            + "\n[9] Mostrar citas:"
                            + "\n[10] Mostrar expediente del cliente:"
                            + "\n[11] Mostrar estadisticas por mes:"
                            + "\n[12] Guardar datos .dat:"
                            + "\n[13] Cargar datos .dat:"
                            + "\n[14] Exportar reporte en .txt:"
                            + "\n[0] Salir:"
                            + "\nIngrese una opcion: ");
            opcion = tcl.nextInt();
            tcl.nextLine();
            switch(opcion){
                case 1 -> {
                    System.out.println("===== REGISTRO CLIENTE =====");
                    System.out.print("Dime nombres y apellidos: ");
                    String nombs = tcl.nextLine();
                    System.out.printf("Dime su cedula: ");
                    String cedula = tcl.nextLine();
                    System.out.print("Dime su numero de telefono: ");
                    String numTelefono = tcl.nextLine();
                    System.out.print("Dime su correo: ");
                    String correo = tcl.nextLine();
                    System.out.print(clinica.registrarCliente(nombs, cedula, 
                                                                  numTelefono, correo));
                }
                case 2 -> {
                    System.out.println("===== REGISTRO MASCOTA =====");
                    System.out.printf("Dime la cedula del cliente: ");
                    String cedula = tcl.nextLine();
                    System.out.print("Dime el nombre de la mascota: ");
                    String nomb = tcl.nextLine();
                    System.out.printf("Dime su especie: ");
                    String especie = tcl.nextLine();
                    System.out.print("Dime su raza: ");
                    String raza = tcl.nextLine();
                    System.out.print("Dime su genero: ");
                    char genero = tcl.nextLine().charAt(0);
                    System.out.print("Dime su edad en meses: ");
                    int edad = tcl.nextInt();
                    System.out.print("Dime su peso en kg: ");
                    double peso = tcl.nextDouble();
                    tcl.nextLine();
                    System.out.print(clinica.registrarMascota(cedula, nomb, 
                                                                especie, raza, genero, 
                                                                edad, peso));        
                }
                case 3 -> {
                    System.out.println("===== REGISTRO ANTECEDENTE MEDICO MASCOTA =====");
                    System.out.printf("Dime la cedula del cliente: ");
                    String cedula = tcl.nextLine();
                    System.out.print("Dime el nombre de la mascota: ");
                    String nomb = tcl.nextLine();
                    System.out.printf("Dime la enfermedad: ");
                    String enfermedad = tcl.nextLine();
                    System.out.print("Dime la fecha del diagnostico(dd-mm-aaaa): ");
                    String fecha = tcl.nextLine();
                    System.out.printf("Dime su tratamiento: ");
                    String tratamiento = tcl.nextLine();
                    System.out.print(clinica.registrarAntecedentes(cedula, nomb, 
                                                                    enfermedad, fecha, tratamiento));
                }
                case 4 -> {
                    System.out.println("===== AGENDAR CITA =====");
                    System.out.printf("Dime la cedula del cliente: ");
                    String cedula = tcl.nextLine();
                    System.out.print("Dime el nombre de la mascota: ");
                    String nomb = tcl.nextLine();
                    System.out.printf("Dime la cedula del veterinario: ");
                    String cedulaVet = tcl.nextLine();
                    System.out.print("Dime la razon de la cita: ");
                    String razon = tcl.nextLine();
                    System.out.print("Dime la fecha de la cita(dd-mm-aaaa): ");
                    String fecha = tcl.nextLine();
                    System.out.printf("Dime la hora de la cita: ");
                    String hora = tcl.nextLine();
                    System.out.print(clinica.registrarCita(cedula, nomb,
                                              cedulaVet, razon, 
                                                             fecha, hora));                
                }
                case 5 -> {
                    System.out.println("\n===== ATENDER CITA Y FACTURAR =====");
                    System.out.print("Dime el numero de cita: ");
                    int numCita = tcl.nextInt();
                    tcl.nextLine();
                    System.out.print("Dime su diagnostico: ");
                    String diagnostico = tcl.nextLine();
                    System.out.print("Dime su enfermedad: ");
                    String enfermedad = tcl.nextLine();
                    System.out.print("Dime la cantidad de examenes realizados: ");
                    int cantExamenes = tcl.nextInt();
                    tcl.nextLine();
                    System.out.print("Dime el medicamento recetado o ninguno: ");
                    String medicamento = tcl.nextLine();
                    System.out.print("Dime la cantidad del medicamento: ");
                    int cantMedicamento = tcl.nextInt();
                    tcl.nextLine();
                    System.out.print(clinica.atenderCita(numCita, diagnostico, 
                            enfermedad, cantExamenes, medicamento, cantMedicamento)); 
                }
                case 6 -> {
                    System.out.println("\n===== LISTA CLIENTES =====");
                    System.out.print(clinica.mostrarClientes());               
                }
                case 7 -> {
                    System.out.println("\n===== LISTA VETERINARIOS =====");
                    System.out.print(clinica.mostrarVeterinarios()); 
                }
                case 8 -> {
                    System.out.println("\n===== LISTA INVENTARIO =====");
                    System.out.print(clinica.mostrarInventario());                    
                }
                case 9 -> {
                    System.out.println("===== LISTA CITAS MEDICAS =====");
                    System.out.print(clinica.mostrarCitas());                
                }
                case 10 -> {
                    System.out.println("\n===== EXPEDIENTE CLIENTE =====");
                    System.out.printf("Dime la cedula del cliente: ");
                    String cedula = tcl.nextLine();
                    System.out.print(clinica.mostrarExpedienteCliente(cedula));                       
                }
                case 11 -> {
                    System.out.println("\n===== ESTADISTICAS POR MES =====");
                    System.out.printf("Dime el mes(Ejemplo: 05): ");
                    String mes = tcl.nextLine();
                    System.out.print(clinica.mostrarEstadisticas(mes)); 
                }
                case 12 -> {
                    System.out.println("\n===== GUARDAR EN ARCHIVO .DAT=====");
                    System.out.print("Dime el nombre para el archivo (name.dat): ");
                    String nombreFile = tcl.nextLine();
                    archivo.escrituraArchivoBinario(clinica, nombreFile);
                }
                case 13 -> {
                    System.out.println("\n===== CARGAR ARCHIVO .DAT=====");
                    System.out.print("Dime el nombre del archivo (name.dat): ");
                    String nombreFile = tcl.nextLine();
                    ClinicaVeterinaria clinicaCarg = archivo.lecturaArchivoBinario(nombreFile);
                    if(clinicaCarg != null)
                        clinica = clinicaCarg;
                }
                case 14 -> {
                    System.out.println("\n===== GUARDAR EN ARCHIVO DE TEXTO=====");
                    System.out.printf("Dime el mes para las estadisticas(Ejemplo: 05): ");
                    String mes = tcl.nextLine();
                    System.out.print("Dime el nombre para el archivo (name.txt): ");
                    String nombreFile = tcl.nextLine();
                    archivo.generarArchivoTexto(clinica.reporteGeneral(mes), nombreFile);
                }
                case 0 -> {
                    System.out.println("Saliendo del programa...");
                }
                default -> {
                    System.out.println("Opcion no disponible...");
                }  
            }
        } while (opcion != 0);
    }
}