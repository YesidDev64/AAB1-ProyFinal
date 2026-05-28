package models;
import java.util.ArrayList;
import java.io.Serializable;

public class Mascota implements Serializable{
    private String nombre, especie, raza;
    private char genero;    
    private int edadMeses;
    private double pesoKg;
    private ArrayList <AntecedentesMedicos> antecedentes;

    public Mascota(String nombre, String especie, String raza, char genero, int edadMeses, double pesoKg) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.genero = genero;
        this.edadMeses = edadMeses;
        this.antecedentes = new ArrayList<>();
        this.pesoKg = pesoKg;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public char getGenero() {
        return genero;
    }
    
    public void setEdadMeses(int edadMeses) {
        this.edadMeses = edadMeses;
    }

    public int getEdadMeses() {
        return edadMeses;
    }
    
    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }
    
    public double getPesoKg() {
        return pesoKg;
    }

    public ArrayList<AntecedentesMedicos> getAntecedentes() {
        return antecedentes;
    }
    
    public void agregarAntecedentes(AntecedentesMedicos a){
        antecedentes.add(a);
    }
    
    public String toString(){
        return String.format("Nombre: %s\nEspecie: %s\nRaza: %s\nGenero: %c\nEdad(meses): %d\nPeso(kg): %.2f\n", 
                             nombre, especie, raza, genero, edadMeses, pesoKg);
    }
}