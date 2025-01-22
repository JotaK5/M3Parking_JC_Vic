/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


/**
 *
 * @author bueni
 */

import java.util.ArrayList;

public class ParkingClass {
    private int idParking;
    private String nombreParking;
    private String direccionParking;
    private String telefonoParking;
    private int numeroPisos;
    private int plazasPorPiso;
    private ArrayList<PlazaClass> plazas;
    private double tarifaCamionPorMin;
    private double tarifaMotoPorMin;
    private double tarifaCochePorMin;

    public ParkingClass(int id, String nombre, String direccion, String telefono, int numeroPisos, int plazasPorPiso, double tarifaCamion, double tarifaMoto, double tarifaCoche) {
        this.idParking = id;
        this.nombreParking = nombre;
        this.direccionParking = direccion;
        this.telefonoParking = telefono;
        this.numeroPisos = numeroPisos;
        this.plazasPorPiso = plazasPorPiso;
        this.tarifaCamionPorMin = tarifaCamion;
        this.tarifaMotoPorMin = tarifaMoto;
        this.tarifaCochePorMin = tarifaCoche;
        this.plazas = new ArrayList<>(numeroPisos * plazasPorPiso);
        inicializarPlazas();
    }

    private void inicializarPlazas() {
        for (int piso = 0; piso < numeroPisos; piso++) {
            for (int plazaNumero = 1; plazaNumero <= plazasPorPiso; plazaNumero++) {
                int plazaId = piso * plazasPorPiso + plazaNumero;
                String tipoDeVehiculo;
                if (plazaNumero <= 6) {
                    tipoDeVehiculo = "Camion";
                } else if (plazaNumero <= 26) {
                    tipoDeVehiculo = "Moto";
                } else {
                    tipoDeVehiculo = "Coche";
                }
                PlazaClass plaza = new PlazaClass(plazaId, tipoDeVehiculo, "libre", obtenerTarifaPorMin(tipoDeVehiculo));
                plazas.add(plaza);
            }
        }
    }

    public double obtenerTarifaPorMin(String tipoDeVehiculo) {
        switch (tipoDeVehiculo) {
            case "Camion":
                return tarifaCamionPorMin;
            case "Moto":
                return tarifaMotoPorMin;
            case "Coche":
                return tarifaCochePorMin;
            default:
                return 0.0;
        }
    }

    public int getIdParking() {
        return idParking;
    }

    public ArrayList<PlazaClass> getPlazas() {
        return plazas;
    }

    public PlazaClass obtenerPlazaPorId(int idPlaza) {
        for (PlazaClass plaza : plazas) {
            if (plaza.getIdPlaza() == idPlaza) {
                return plaza;
            }
        }
        return null;
    }

    public PlazaClass asignarPlaza(VehicleClass vehiculo) {
        for (PlazaClass plaza : plazas) {
            if (plaza.getTipoDeVehiculo().equals(vehiculo.getTipoDeVehiculo()) && plaza.getEstado().equals("libre")) {
                plaza.asignarVehiculo(vehiculo);
                int planta = (plaza.getIdPlaza() - 1) / plazasPorPiso + 1;
                System.out.println("Plaza asignada: " + plaza.getIdPlaza() + " en Planta: " + planta);
                return plaza;
            }
        }
        System.out.println("No hay plazas disponibles para el tipo de vehículo: " + vehiculo.getTipoDeVehiculo());
        return null;
    }

    public void retirarVehiculo(int idPlaza) {
        PlazaClass plaza = obtenerPlazaPorId(idPlaza);
        if (plaza != null && plaza.getEstado().equals("ocupado")) {
            plaza.liberarPlaza();
            System.out.println("Vehículo retirado de la plaza: " + plaza.getIdPlaza());
        } else {
            System.out.println("No se encontró un vehículo en la plaza: " + idPlaza);
        }
    }

    public void mostrarPlazasDisponibles(String tipoDeVehiculo) {
        for (PlazaClass plaza : plazas) {
            if (plaza.getTipoDeVehiculo().equals(tipoDeVehiculo) && plaza.getEstado().equals("libre")) {
                System.out.println("Plaza libre: " + plaza.getIdPlaza());
            }
        }
    }
}
