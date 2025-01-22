/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


/**
 *
 * @author bueni
 */


public class VehicleClass {
    private String matricula;
    private String nifProp;
    private int idPlaza;
    private String color;
    private String tipoDeVehiculo;

    public VehicleClass(String matricula, String nif, int idPlaza, String color, String tipoDeVehiculo) {
        this.matricula = matricula;
        this.nifProp = nif;
        this.idPlaza = idPlaza;
        this.color = color;
        this.tipoDeVehiculo = tipoDeVehiculo;
    }

    // Getters y Setters
    public String getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }
}

