/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author bueni
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketClass {
    private int idTicket;
    private int idParking;
    private int idPlaza;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    private double totalPagar;

    public TicketClass(int idTicket, int idParking, int idPlaza, LocalDateTime fechaHoraEntrada) {
        this.idTicket = idTicket;
        this.idParking = idParking;
        this.idPlaza = idPlaza;
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public String generarTicket(PlazaClass plaza) {
        String tipoVehiculo = plaza.getTipoDeVehiculo();
        String identificadorTipo;

        switch (tipoVehiculo.toLowerCase()) {
            case "camion":
                identificadorTipo = "CAM";
                break;
            case "moto":
                identificadorTipo = "MO";
                break;
            case "coche":
                identificadorTipo = "CO";
                break;
            default:
                identificadorTipo = "UNK"; // Por si acaso
        }

        int planta = (idPlaza - 1) / 60 + 1; // Calcula la planta basada en el número de plaza y plazas por piso
        String ticketId = String.format("%s_%d_%d", identificadorTipo, planta, idPlaza);

        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        String fechaEntrada = fechaHoraEntrada.format(formatterFecha);
        String horaEntrada = fechaHoraEntrada.format(formatterHora);

        // Crear un formato centrado para el ticket con ancho fijo
        String formatLine = "| %-34s |";
        String lineSeparator = "+------------------------------------+";

        String ticketFormat = String.join("\n",
            lineSeparator,
            String.format(formatLine, "TICKET DE ENTRADA"),
            String.format(formatLine, "Parking Los Duros"),
            String.format(formatLine, "C/ Monlau 6, Barcelona"),
            String.format(formatLine, "+34 666 66 66"),
            String.format(formatLine, ticketId),
            String.format(formatLine, "Fecha de Entrada: " + fechaEntrada),
            String.format(formatLine, "Hora de Entrada: " + horaEntrada),
            lineSeparator
        );

        return ticketFormat;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    // Getters y Setters adicionales si es necesario
}
