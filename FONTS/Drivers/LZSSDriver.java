package Drivers;// Creado por Joan Gamez Rodriguez

import Controllers.CtrlProcesos;
import DomainLayer.Algoritmos.Algoritmo;

import java.util.Scanner;

public class LZSSDriver {
    public static void main(String[] args) throws Exception {
        System.out.print("Bienvenido al driver para el algoritmo de LZSS\n\n");
        CtrlProcesos ctrlProcesos = CtrlProcesos.getInstance();
        boolean b = true;
        while (b) {
            System.out.print("Introduzca uno de los siguientes comandos disponibles:\n\n1. comprimir\n2. descomprimir\n3. comprimirYdescomprimir\n4. salir\n");
            Scanner scanner = new Scanner(System.in);
            String comando = scanner.nextLine();
            String s;
            switch (comando) {
                case "comprimir":
                case "1":
                    System.out.println("Escriba el path absoluto del fichero .txt a comprimir:");
                    s = scanner.nextLine();
                    if (s.endsWith(".txt")) {
                        System.out.println("Se inicia el proceso");
                        ctrlProcesos.comprimirArchivo(s, Algoritmo.LZSS);
                        System.out.println("El archivo " + s + " se ha comprimido correctamente!\n");
                    } else System.out.println("El formato del fichero debe de ser .txt!");
                    break;
                case "descomprimir":
                case "2":
                    System.out.println("Escriba el path absoluto del fichero .lzSS a descomprimir:");
                    s = scanner.nextLine();
                    System.out.println("Se inicia el proceso");
                    ctrlProcesos.descomprimirArchivo(s);
                    System.out.println("El archivo se ha descomprimido correctamente!\n");
                    break;
                case "comprimirYdescomprimir":
                case "3":
                    System.out.println("Escriba el path absoluto del fichero .txt a comprimir");
                    s = scanner.nextLine();
                    if (s.endsWith(".txt")) {
                        ctrlProcesos.comprimirDescomprimirArchivo(s, Algoritmos.LZSS);
                        String[] ss = s.split("/");
                        System.out.println("El archivo " + ss[ss.length - 1] + " se ha comprimido y descomprimido correctamente!\n");
                    } else System.out.println("El formato del fichero debe de ser .txt");
                    break;
                case "salir":
                case "4":
                    b = false;
                    break;
                default:
                    System.out.print("Comando incorrecto!\n");
            }
        }
    }
}

