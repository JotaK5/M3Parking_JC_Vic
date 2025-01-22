/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


/**
 *
 * @author bueni
 */



public class PlazaClass {
    private int idPlaza;
    private String tipoDeVehiculo;
    private String estado;
    private double tarifaPorMin;
    private VehicleClass vehiculo;

    public PlazaClass(int id, String tipoVehiculo, String estado, double tarifa) {
        this.idPlaza = id;
        this.tipoDeVehiculo = tipoVehiculo;
        this.estado = estado;
        this.tarifaPorMin = tarifa;
    }

    public void asignarVehiculo(VehicleClass vehiculo) {
        this.vehiculo = vehiculo;
        this.estado = "ocupado";
    }

    public void liberarPlaza() {
        this.vehiculo = null;
        this.estado = "libre";
    }

    public double calcularTarifa(long minutos) {
        return tarifaPorMin * minutos;
    }

    // Getters y Setters adicionales si es necesario
    public int getIdPlaza() {
        return idPlaza;
    }

    public String getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }

    public String getEstado() {
        return estado;
    }
}
