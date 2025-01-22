/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model.view;

/**
 *
 * autor bueni
 */

import model.ParkingClass;
import model.PlazaClass;
import model.VehicleClass;
import model.TicketClass;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    private static ParkingClass parking;
    private static TicketClass ticket;

    // ANSI escape codes para color en la consola
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m"; // Añadir código para color azul

    public static void main(String[] args) {
        // Crear un parking con 120 plazas en 2 plantas
        parking = new ParkingClass(1, "MiParking", "Dirección del Parking", "123456789", 2, 60, 0.10, 0.05, 0.03);

        // Inicializar plazas aleatoriamente como ocupadas o no
        inicializarPlazasAleatoriamente();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menú Principal");
            System.out.println("1. Seleccionar tipo de vehículo");
            System.out.println("2. Mostrar plazas y su estado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    seleccionarTipoDeVehiculo(scanner);
                    System.out.println("\n");
                    break;
                case 2:
                    mostrarPlazasYEstado();
                    System.out.println("\n");
                    break;
                case 3:
                    System.out.println("Saliendo del programa...\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.\n");
            }
        }
    }

    private static void seleccionarTipoDeVehiculo(Scanner scanner) {
        System.out.println("Seleccione el tipo de vehículo");
        System.out.println("1. Moto");
        System.out.println("2. Coche");
        System.out.println("3. Camión");
        System.out.print("Seleccione una opción: ");
        int tipoVehiculo = scanner.nextInt();

        String tipo = "";
        switch (tipoVehiculo) {
            case 1:
                tipo = "Moto";
                break;
            case 2:
                tipo = "Coche";
                break;
            case 3:
                tipo = "Camion";
                break;
            default:
                System.out.println("Opción inválida. Regresando al menú principal.\n");
                return;
        }

        System.out.print("Ingrese la matrícula del vehículo: ");
        String matricula = scanner.next();
        System.out.print("Ingrese el NIF del propietario: ");
        String nif = scanner.next();
        System.out.print("Ingrese el color del vehículo: ");
        String color = scanner.next();

        VehicleClass vehiculo = new VehicleClass(matricula, nif, 1, color, tipo);
        PlazaClass plazaAsignada = parking.asignarPlaza(vehiculo);

        if (plazaAsignada != null) {
            // Generar un ticket
            ticket = new TicketClass(1, parking.getIdParking(), plazaAsignada.getIdPlaza(), LocalDateTime.now());
            System.out.println(ticket.generarTicket(plazaAsignada));
        }
        System.out.println("\n");
    }

    private static void mostrarPlazasYEstado() {
        System.out.println();

        int totalPlazas = 120;
        int plazasMotos = 40;
        int plazasCoches = 68;
        int plazasCamiones = 12;

        int disponiblesTotal = 0;
        int ocupadasTotal = 0;
        int disponiblesMotos = 0;
        int ocupadasMotos = 0;
        int disponiblesCoches = 0;
        int ocupadasCoches = 0;
        int disponiblesCamiones = 0;
        int ocupadasCamiones = 0;

        for (PlazaClass plaza : parking.getPlazas()) {
            if (plaza.getEstado().equals("libre")) {
                disponiblesTotal++;
                switch (plaza.getTipoDeVehiculo()) {
                    case "Moto":
                        disponiblesMotos++;
                        break;
                    case "Coche":
                        disponiblesCoches++;
                        break;
                    case "Camion":
                        disponiblesCamiones++;
                        break;
                }
            } else {
                ocupadasTotal++;
                switch (plaza.getTipoDeVehiculo()) {
                    case "Moto":
                        ocupadasMotos++;
                        break;
                    case "Coche":
                        ocupadasCoches++;
                        break;
                    case "Camion":
                        ocupadasCamiones++;
                        break;
                }
            }
        }

        System.out.println(ANSI_BLUE + "Plazas Totales: " + totalPlazas + ANSI_RESET);
        System.out.println(" - Disponibles: " + ANSI_GREEN + disponiblesTotal + ANSI_RESET);
        System.out.println(" - Ocupadas: " + ANSI_RED + ocupadasTotal + ANSI_RESET);
        System.out.println();

        System.out.println(ANSI_BLUE + "Plazas de Moto: " + plazasMotos + ANSI_RESET);
        System.out.println(" - Disponibles: " + ANSI_GREEN + disponiblesMotos + ANSI_RESET);
        System.out.println(" - Ocupadas: " + ANSI_RED + ocupadasMotos + ANSI_RESET);
        System.out.println();

        System.out.println(ANSI_BLUE + "Plazas de Coche: " + plazasCoches + ANSI_RESET);
        System.out.println(" - Disponibles: " + ANSI_GREEN + disponiblesCoches + ANSI_RESET);
        System.out.println(" - Ocupadas: " + ANSI_RED + ocupadasCoches + ANSI_RESET);
        System.out.println();

        System.out.println(ANSI_BLUE + "Plazas de Camion: " + plazasCamiones + ANSI_RESET);
        System.out.println(" - Disponibles: " + ANSI_GREEN + disponiblesCamiones + ANSI_RESET);
        System.out.println(" - Ocupadas: " + ANSI_RED + ocupadasCamiones + ANSI_RESET);
        System.out.println();

        int plazasPorFila = 10;

        // Mostrar plazas para la planta 1
        System.out.println("Planta 1:");
        mostrarCuadriculaPlantas(0, 60, plazasPorFila);

        // Añadir un enter antes de imprimir "Planta 2"
        System.out.println();

        // Mostrar plazas para la planta 2
        System.out.println("Planta 2:");
        mostrarCuadriculaPlantas(60, 120, plazasPorFila);
    }

    private static void mostrarCuadriculaPlantas(int inicio, int fin, int plazasPorFila) {
        int contador = 0;

        for (int i = inicio; i < fin; i++) {
            PlazaClass plaza = parking.getPlazas().get(i);
            String estado = plaza.getEstado().equals("ocupado") ? ANSI_RED : ANSI_GREEN;
            String contenido = String.format("P%d/%s", plaza.getIdPlaza(), plaza.getTipoDeVehiculo().toUpperCase());

            // Ajustar el contenido para que todas las celdas tengan la misma longitud
            contenido = String.format("%-13s", contenido); 
            
            String cuadro = String.format("| %s%s%s |", estado, contenido, ANSI_RESET);
            System.out.print(cuadro);
            contador++;
            if (contador % plazasPorFila == 0) {
                System.out.println();
                for (int j = 0; j < plazasPorFila; j++) {
                    System.out.print("---------------- ");
                }
                System.out.println();
            }
        }

        // Finaliza con una línea de bordes si quedan plazas sin bordes inferiores
        if (contador % plazasPorFila != 0) {
            System.out.println();
            for (int j = 0; j < plazasPorFila; j++) {
                System.out.print("---------------- ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    private static void inicializarPlazasAleatoriamente() {
        Random random = new Random();
        for (PlazaClass plaza : parking.getPlazas()) {
            boolean ocupada = random.nextBoolean();
            if (ocupada) {
                plaza.asignarVehiculo(new VehicleClass("N/A", "N/A", plaza.getIdPlaza(), "N/A", plaza.getTipoDeVehiculo()));
            }
        }
        System.out.println("\n");
    }
}
