package controllers;
import java.io.*;

public class ArchivoClinica {
    private String rutaArchivo;  
    
    public ArchivoClinica(){
        rutaArchivo = String.format("Archivos/");
    }
    
    public boolean verificarRuta(String nombFile){
        File archivo = new File(rutaArchivo + nombFile);
        File carpeta = archivo.getParentFile();
        
        if(carpeta != null && !carpeta.exists()){
            carpeta.mkdirs();
           } 
        
        return true;
    }    
    
    public void escrituraArchivoBinario(ClinicaVeterinaria clinica, String nombFile){
        if(!verificarRuta(nombFile)) return;        
        
        try {
            FileOutputStream archivoOut = new FileOutputStream(rutaArchivo + nombFile);
            ObjectOutputStream objetoOut = new ObjectOutputStream(archivoOut);
            
            objetoOut.writeObject(clinica);
            System.out.printf("Datos guardados correctamente en %s\n", nombFile);
            
            objetoOut.close();
            archivoOut.close();
        } catch (IOException e) {
            System.out.printf("Hubo un error en la escritura o creacion del archivo .dat...\n%s\n", 
                              e.getMessage());
        }
    }
    
    public ClinicaVeterinaria lecturaArchivoBinario(String nombFile){
        if(!verificarRuta(nombFile)) return null;
        
        File file = new File(rutaArchivo + nombFile);
        
        if(!file.exists()){
            System.out.println("El archivo no existe...");
            return null;
        }
        try {
            FileInputStream archivoIn = new FileInputStream(rutaArchivo + nombFile);
            ObjectInputStream objetoIn = new ObjectInputStream(archivoIn);
            
            ClinicaVeterinaria clinica = (ClinicaVeterinaria) objetoIn.readObject();
            
            objetoIn.close();
            archivoIn.close();
            
            System.out.printf("Datos cargados correctamente desde %s\n", nombFile);
            return clinica;
        } catch (IOException e) {
            System.out.printf("Hubo un error en la lectura del archivo binario...\n%s\n", 
                              e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.printf("No se reconocio el tipo de objeto guardado...\n%s\n", 
                              e.getMessage());
            return null;
        }
    }
    
    public void generarArchivoTexto(String reporte, String nombFile){
        if(!verificarRuta(nombFile)) return;
        
        try {
            FileWriter archivoOut = new FileWriter(rutaArchivo + nombFile);
            BufferedWriter archivoWriter = new BufferedWriter(archivoOut);
            
            archivoWriter.write(reporte);
            
            archivoWriter.close();            
            archivoOut.close();
            
            System.out.printf("Archivo de texto %s creado correctamente\n", nombFile);
        } catch (IOException e) {
            System.out.printf("Hubo un error en la escritura o creacion del archivo...\n%s\n", 
                              e.getMessage());
        }
    }
}