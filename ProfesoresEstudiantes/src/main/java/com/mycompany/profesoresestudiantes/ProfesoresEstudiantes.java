package com.mycompany.profesoresestudiantes;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La clase ProfesoresEstudiantes administra la relación entre profesores y estudiantes.
 * Permite registrar, consultar y almacenar datos de profesores y estudiantes.
 */
public class ProfesoresEstudiantes {
    private ArrayList<String> listaProfesores = new ArrayList<>();
    private ArrayList<ArrayList<String>> listaEstudiantes = new ArrayList<>();

    /**
     * Método para registrar profesores. Se asegura de que los nombres de los profesores sean únicos.
     */
    public void registrarProfesores() {
        Scanner scanner = new Scanner(System.in);
        int cantidadProfesores = 0;

        // Solicita al usuario la cantidad de profesores a registrar
        while (true) {
            try {
                System.out.print("Ingrese la cantidad de profesores a registrar: ");
                cantidadProfesores = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                if (cantidadProfesores <= 0) {
                    throw new InputMismatchException("El número debe ser positivo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }

        // Registra los nombres de los profesores asegurando que no se repitan
        for (int i = 0; i < cantidadProfesores; i++) {
            String nombreProfesor;
            while (true) {
                System.out.print("Ingrese el nombre del profesor " + (i + 1) + ": ");
                nombreProfesor = scanner.nextLine();
                if (!listaProfesores.contains(nombreProfesor)) {
                    break;
                } else {
                    System.out.println("El nombre del profesor ya existe. Ingrese un nombre diferente.");
                }
            }
            listaProfesores.add(nombreProfesor);
            listaEstudiantes.add(new ArrayList<>());
        }
    }

    /**
     * Método para registrar estudiantes asociados a un profesor.
     * Verifica que los nombres de los estudiantes sean únicos para cada profesor.
     */
    public void registrarEstudiantes() {
        if (listaProfesores.isEmpty()) {
            System.out.println("Debe registrar profesores antes de registrar estudiantes.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del profesor: ");
        String nombreProfesor = scanner.nextLine();
        int indiceProfesor = listaProfesores.indexOf(nombreProfesor);

        if (indiceProfesor == -1) {
            System.out.println("Profesor no encontrado.");
            return;
        }

        System.out.print("Ingrese la cantidad de estudiantes a registrar para " + nombreProfesor + ": ");
        int cantidadEstudiantes = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        ArrayList<String> estudiantes = listaEstudiantes.get(indiceProfesor);

        // Registra los nombres de los estudiantes asegurando que no se repitan
        for (int i = 0; i < cantidadEstudiantes; i++) {
            String nombreEstudiante;
            while (true) {
                System.out.print("Ingrese el nombre del estudiante " + (i + 1) + ": ");
                nombreEstudiante = scanner.nextLine();
                if (!estudiantes.contains(nombreEstudiante)) {
                    break;
                } else {
                    System.out.println("El nombre del estudiante ya existe. Ingrese un nombre diferente.");
                }
            }
            estudiantes.add(nombreEstudiante);
        }
    }

    /**
     * Método para mostrar la lista de profesores y sus estudiantes asociados.
     */
    public void mostrarProfesoresYEstudiantes() {
        if (listaProfesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
            return;
        }

        System.out.println("Lista de profesores y sus estudiantes:");
        for (int i = 0; i < listaProfesores.size(); i++) {
            System.out.println("Profesor: " + listaProfesores.get(i));
            System.out.println("Estudiantes:");
            for (String estudiante : listaEstudiantes.get(i)) {
                System.out.println("- " + estudiante);
            }
            System.out.println();
        }
    }

    /**
     * Método para consultar los estudiantes asociados a un profesor específico.
     */
    public void consultarProfesor() {
        if (listaProfesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del profesor: ");
        String nombreProfesor = scanner.nextLine();
        int indiceProfesor = listaProfesores.indexOf(nombreProfesor);

        if (indiceProfesor == -1) {
            System.out.println("Profesor no encontrado.");
            return;
        }

        System.out.println("Estudiantes del profesor " + nombreProfesor + ":");
        for (String estudiante : listaEstudiantes.get(indiceProfesor)) {
            System.out.println("- " + estudiante);
        }
    }

    /**
     * Método para consultar la cantidad de estudiantes asociados a un profesor específico.
     */
    public void cantidadEstudiantesPorProfesor() {
        if (listaProfesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del profesor: ");
        String nombreProfesor = scanner.nextLine();
        int indiceProfesor = listaProfesores.indexOf(nombreProfesor);

        if (indiceProfesor == -1) {
            System.out.println("Profesor no encontrado.");
            return;
        }

        int cantidadEstudiantes = listaEstudiantes.get(indiceProfesor).size();
        System.out.println("El profesor " + nombreProfesor + " tiene " + cantidadEstudiantes + " estudiantes.");
    }

    /**
     * Método para consultar el profesor asociado a un estudiante específico.
     */
    public void consultarEstudiante() {
        if (listaProfesores.isEmpty() || listaEstudiantes.isEmpty()) {
            System.out.println("Debe registrar profesores y estudiantes antes de realizar esta consulta.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombreEstudiante = scanner.nextLine();

        for (int i = 0; i < listaEstudiantes.size(); i++) {
            if (listaEstudiantes.get(i).contains(nombreEstudiante)) {
                System.out.println("El estudiante " + nombreEstudiante + " está asociado al profesor " + listaProfesores.get(i) + ".");
                return;
            }
        }

        System.out.println("Estudiante no encontrado.");
    }

    /**
     * Método principal que presenta un menú para interactuar con el usuario.
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        ProfesoresEstudiantes app = new ProfesoresEstudiantes();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Presenta el menú de opciones al usuario
        do {
            System.out.println("Menú:");
            System.out.println("1. Registrar profesores.");
            System.out.println("2. Registrar estudiantes asociados al profesor.");
            System.out.println("3. Consultar lista total de profesores y sus estudiantes asociados.");
            System.out.println("4. Consultar un profesor e imprimir la lista de estudiantes asociados.");
            System.out.println("5. Consultar un profesor e indicar la cantidad de estudiantes asociados.");
            System.out.println("6. Consultar un estudiante e indicar cual es su director de grupo.");
            System.out.println("7. Salir.");
            System.out.print("Seleccione una opción: ");
            while (true) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                    scanner.nextLine(); // Limpiar el buffer
                }
            }

            // Ejecuta la acción correspondiente a la opción seleccionada
            switch (opcion) {
                case 1:
                    app.registrarProfesores();
                    break;
                case 2:
                    app.registrarEstudiantes();
                    break;
                case 3:
                    app.mostrarProfesoresYEstudiantes();
                    break;
                case 4:
                    app.consultarProfesor();
                    break;
                case 5:
                    app.cantidadEstudiantesPorProfesor();
                    break;
                case 6:
                    app.consultarEstudiante();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }
}
