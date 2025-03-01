package genc18_2502;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.*;

public class LibretaDeNotas {
    static Map<String, ArrayList<Double>> notas = new HashMap<>();
    static double notaAprobacion = 4.0; // Nota mínima definida para aprobar.

    public static void agregarNota(String nombre, ArrayList<Double> notasAlumno) {
        notas.put(nombre, notasAlumno);
    }

    //Promedio de notas por estudiante.
    public static void mostrarPromedios() {
        for (String nombre : notas.keySet()) {
            ArrayList<Double> listaNotas = notas.get(nombre);
            double promedio = calcularPromedio(listaNotas);
            System.out.println(nombre + ": " + promedio);
        }
    }

    //Nota máxima y mínima de un estudiante, en las instrucciones del ejercicio nunca se usa.
    public static void mostrarMaxMin() {

        for (String nombre : notas.keySet()) {
            ArrayList<Double> listaNotas = notas.get(nombre);
            double max = Collections.max(listaNotas);
            double min = Collections.min(listaNotas);
            System.out.println(nombre + " Máxima: " + max +
                    "\nMínima: " + min);
        }
    }

    public static double calcularPromedio(ArrayList<Double> listaNotas) {
        double suma = 0;
        for (double nota : listaNotas) {
            suma += nota;
        }
        return suma / listaNotas.size();
    }

    //Calcular el promedio del curso.
    public static double promedioCurso() {
        double total = 0;
        int cantidadNotas = 0;

        for (ArrayList<Double> listaNotas : notas.values()) {
            for (double nota : listaNotas) {
                total += nota;
                cantidadNotas++;
            }
        }

        return total / cantidadNotas;
    }

    //Evaluar si una nota es aprobatoria o reprobatoria.
    public static void evaluarAprobacion(Scanner sc) {
        System.out.print("\nIngrese el nombre del estudiante: ");
        String nombre = sc.next();
        //Validación para comprobar que el estudiante se encuentra en el Map.
        //Lo puse en las funciones que requieren el nombre del estudiante.
        if (!notas.containsKey(nombre)) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.print("Ingrese la nota a evaluar: ");
        double nota = sc.nextDouble();
        if (nota >= notaAprobacion) {
            System.out.println("La nota es aprobatoria.");
        } else {
            System.out.println("La nota es reprobatoria.");
        }
    }

    //Comparar una nota con el promedio del curso
    public static void compararConPromedioCurso(Scanner sc) {
        System.out.print("\nIngrese el nombre del estudiante: ");
        String nombre = sc.next();
        if (!notas.containsKey(nombre)) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.print("Ingrese la nota a comparar: ");
        double nota = sc.nextDouble();
        double promedioCurso = promedioCurso();

        if (nota > promedioCurso) {
            System.out.println("La nota está sobre el promedio del curso.");
        } else {
            System.out.println("La nota está debajo del promedio del curso.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de alumnos: ");
        int numAlumnos = sc.nextInt();
        //Comprobación para que se ingrese una cantidad de alumnos mayor a 0.
        while (numAlumnos <= 0) {
            System.out.print("Número inválido. Ingrese nuevamente: ");
            numAlumnos = sc.nextInt();
        }

        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int notasPorAlumno = sc.nextInt();
        //Comprobación para que se ingrese una cantidad de notas por alumno mayor a 0.
        while (notasPorAlumno <= 0) {
            System.out.print("Número inválido. Ingrese nuevamente: ");
            notasPorAlumno = sc.nextInt();
        }

        for (int i = 0; i < numAlumnos; i++) {
            System.out.print("\nIngrese el nombre del alumno " + (i + 1) + ": ");
            String nombreAlumno = sc.next();
            ArrayList<Double> notasAlumno = new ArrayList<>();

            for (int j = 1; j <= notasPorAlumno; j++) {
                double nota;
                do {
                    System.out.print("Ingrese la nota " + j + " de " + nombreAlumno + ": ");
                    nota = sc.nextDouble();
                    if (nota < 1 || nota > 7) {
                        System.out.println("Nota fuera de rango. Debe estar entre 1 y 7.");
                    }
                } while (nota < 1 || nota > 7);
                notasAlumno.add(nota);
            }

            agregarNota(nombreAlumno, notasAlumno);
        }

        int opcion;
        do {
            System.out.println("**** Menú ****");
            System.out.println("0. Salir" +
                    "\n1. Mostrar promedio de notas por estudiante" +
                    "\n2. Evaluar si una nota es aprobatoria o reprobatoria" +
                    "\n3. Comparar nota de estudiante con el promedio del curso" +
                    "\nSeleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    mostrarPromedios();
                    break;
                case 2:
                    evaluarAprobacion(sc);
                    break;
                case 3:
                    compararConPromedioCurso(sc);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida. \nIntente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }
}

