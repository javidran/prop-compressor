// Creado por Joan Gamez Rodriguez
package Drivers;

import Controllers.CtrlProcesos;
import DomainLayer.Algoritmos.Algoritmo;

import java.util.Scanner;

public class CtrlProcesosDriver {
    public static void main(String[] args) {
        System.out.print("Bienvenido al driver de CtrlProcesos.\n\n");
        CtrlProcesos ctrlProcesos = CtrlProcesos.getInstance();
        boolean b = true;
        while (b) {
            try {
                Algoritmo tipoCompresor;
                System.out.print("Introduzca uno de los siguientes comandos disponibles:\n\n1. comprimir\n2. descomprimir\n3. comprimirYdescomprimir\n4. salir\n");
                Scanner scanner = new Scanner(System.in);
                String comando = scanner.nextLine();
                String s;
                switch (comando) {
                    case "comprimir":
                    case "1":
                        if (args.length == 0) {
                            System.out.println("Escriba el nombre del fichero a comprimir (.txt o .ppm!)");
                            s = System.getProperty("user.dir");
                            s += s.contains("/")?"/":"\\";
                            s += scanner.nextLine();
                        } else s = args[0];
                        if (s.endsWith(".txt")) {
                            System.out.print("Escriba el algoritmo de compresión que quiera usar, de entre los siguientes:\npredeterminado\nlzss\nlz78\nlzw\n");
                            String algoritmoComp = scanner.nextLine();
                            switch (algoritmoComp) {
                                case "lzss":
                                    tipoCompresor = Algoritmo.LZSS;
                                    break;
                                case "lzw":
                                    tipoCompresor = Algoritmo.LZW;
                                    break;
                                case "lz78":
                                    tipoCompresor = Algoritmo.LZ78;
                                    break;
                                case "predeterminado":
                                    tipoCompresor = Algoritmo.PREDETERMINADO;
                                    break;
                                default:
                                    throw new EnumConstantNotPresentException(Algoritmo.class, "El tipo de compresor " + algoritmoComp + " no existe o no está disponible para un archivo .txt\n");
                            }
                            System.out.println("Se inicia el proceso");
                            ctrlProcesos.comprimirArchivo(s, tipoCompresor);
                            System.out.println("El archivo " + s + " se ha comprimido correctamente!\n");
                        } else if (s.endsWith(".ppm")) {
                            System.out.println("Indique la calidad de compresión a usar (del 1 al 7)");
                            ctrlProcesos.setCalidadJPEG(scanner.nextInt());
                            System.out.println("Se inicia el proceso");
                            ctrlProcesos.comprimirArchivo(s, Algoritmo.JPEG);
                            System.out.println("El archivo " + s + " se ha comprimido correctamente!\n");
                        } else System.out.println("El formato del fichero debe de ser .txt o .ppm!");
                        break;
                    case "descomprimir":
                    case "2":
                        if (args.length == 0) {
                            System.out.println("Escriba el nombre del fichero a descomprimir (.lzss, .lz78, lzw o .imgc!)");
                            s = System.getProperty("user.dir");
                            s += s.contains("/")?"/":"\\";
                            s += scanner.nextLine();
                        } else s = args[0];
                        if (s.endsWith(".lz78") || s.endsWith(".lzss") ||  s.endsWith(".lzw") ||  s.endsWith(".imgc")) {
                            System.out.println("Se inicia el proceso");
                            ctrlProcesos.descomprimirArchivo(s);
                            System.out.println("El archivo se ha descomprimido correctamente!\n");
                        } else System.out.println("El formato del fichero debe de ser .lz78, .lzss, .lzw o .imgc!");
                        break;
                    case "compdesc":
                    case "3":
                        if (args.length == 0) {
                            System.out.println("Escriba el nombre del fichero a comprimir (.txt o .ppm!)");
                            s = System.getProperty("user.dir");
                            s += s.contains("/")?"/":"\\";
                            s += scanner.nextLine();
                        } else s = args[0];
                        if (s.endsWith(".txt")) {
                            System.out.print("Escriba el algoritmo de compresión que quiera usar, de entre los siguientes:\npredeterminado\nlzss\nlz78\nlzw\n");
                            String algoritmoComp = scanner.nextLine();
                            switch (algoritmoComp) {
                                case "lzss":
                                    tipoCompresor = Algoritmo.LZSS;
                                    break;
                                case "lzw":
                                    tipoCompresor = Algoritmo.LZW;
                                    break;
                                case "lz78":
                                    tipoCompresor = Algoritmo.LZ78;
                                    break;
                                case "predeterminado":
                                    tipoCompresor = Algoritmo.PREDETERMINADO;
                                    break;
                                default:
                                    throw new EnumConstantNotPresentException(Algoritmo.class, "El tipo de compresor " + algoritmoComp + " no existe o no está disponible para un archivo .txt\n");
                            }
                            System.out.println("Se inicia el proceso");
                            ctrlProcesos.comprimirDescomprimirArchivo(s, tipoCompresor);
                            System.out.println("El archivo " + s + " se ha comprimido y descomprimido correctamente!\n");
                        } else if (s.endsWith(".ppm")) {
                            System.out.println("Indique la calidad de compresión a usar (del 1 al 7)");
                            ctrlProcesos.setCalidadJPEG(scanner.nextInt());
                            System.out.println("Se inicia el proceso");
                            ctrlProcesos.comprimirDescomprimirArchivo(s, Algoritmo.JPEG);
                            System.out.println("El archivo " + s + " se ha comprimido y descomprimido correctamente!\n");
                        } else System.out.println("El formato del fichero debe de ser .txt o .ppm!");
                        break;
                    case "salir":
                    case "4":
                        b = false;
                        break;
                    default:
                        System.out.print("Comando incorrecto!\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
