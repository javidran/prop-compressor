package Drivers;

import DomainLayer.Algoritmos.LZSS;
import DomainLayer.Algoritmos.OutputAlgoritmo;

import java.io.*;
import java.util.Scanner;

public class LZSSDriver {

    private static long getDiffSize(boolean esCompresion, long oldSize, long newSize) {
        if(esCompresion) return oldSize - newSize;
        else return newSize - oldSize;
    }

    private static double getDiffSizePercentage(boolean esCompresion, long oldSize, long newSize) {
        if(newSize==oldSize) return 100;
        if(esCompresion) return Math.floor((newSize /(double) oldSize)*100);
        else return Math.floor((oldSize /(double) newSize)*100);
    }

    public static void main(String[] args) {
        System.out.print("Bienvenido al driver para el algoritmo de LZSS\n\n");
        boolean b = true;
        while (b) {
            try {
                System.out.print("Introduzca uno de los siguientes comandos disponibles:\n\n1. comprimir\n2. descomprimir\n3. salir\n");
                Scanner scanner = new Scanner(System.in);
                String comando = scanner.nextLine();
                String s;
                switch (comando) {
                    case "comprimir":
                    case "1":
                        System.out.println("Escriba el path relativo de un fichero .txt a comprimir:");
                        s = System.getProperty("user.dir");
                        s += s.contains("/")?"/":"\\";
                        s += scanner.nextLine();
                        if (s.endsWith(".txt")) {
                            System.out.println("Se inicia el proceso");
                            File fileIn = new File(s);
                            FileInputStream in = new FileInputStream(fileIn);
                            BufferedInputStream entrada = new BufferedInputStream(in);
                            byte[] data = new byte[(int)fileIn.length()];
                            entrada.read(data);
                            entrada.close();
                            OutputAlgoritmo oa = LZSS.getInstance().comprimir(data);
                            String newpath = s.replace(".txt", ".lzss");
                            File fileOut = new File(newpath);
                            if (!fileOut.exists()) fileOut.createNewFile();
                            FileOutputStream out = new FileOutputStream(fileOut);
                            BufferedOutputStream salida = new BufferedOutputStream(out);
                            salida.write(oa.output);
                            salida.close();
                            long oldSize = data.length, newSize = oa.output.length;
                            double timeSeconds = (double)oa.tiempo / 1000000000;
                            System.out.println("El archivo " + s + " se ha comprimido correctamente!\n" +
                                    "Ha tardado "+timeSeconds+"s y se ha guardado en " + newpath +".\n"+
                                    "El cambio de tamaño pasa de " + oldSize + "B a " + newSize + "B con diferencia de " +
                                    getDiffSize(true, oldSize, newSize) + "B que resulta en un " +
                                    getDiffSizePercentage(true, oldSize, newSize) + "% del archivo original.");
                        } else System.out.println("El formato del fichero debe de ser .txt!");

                        break;
                    case "descomprimir":
                    case "2":

                        System.out.println("Escriba el path relativo de un fichero .lzss a descomprimir:");
                        s = System.getProperty("user.dir");
                        s += s.contains("/")?"/":"\\";
                        s += scanner.nextLine();
                        if (s.endsWith(".lzss")) {
                            System.out.println("Se inicia el proceso");
                            File fileIn = new File(s);
                            FileInputStream in = new FileInputStream(fileIn);
                            BufferedInputStream entrada = new BufferedInputStream(in);
                            byte[] data = new byte[(int)fileIn.length()];
                            entrada.read(data);
                            entrada.close();
                            OutputAlgoritmo oa = LZSS.getInstance().descomprimir(data);
                            String [] pathSplit = s.split("\\.");
                            String newpath = s.replace("."+pathSplit[pathSplit.length - 1], "_out.txt");
                            File fileOut = new File(newpath);
                            if (!fileOut.exists()) fileOut.createNewFile();
                            FileOutputStream out = new FileOutputStream(fileOut);
                            BufferedOutputStream salida = new BufferedOutputStream(out);
                            salida.write(oa.output);
                            salida.close();
                            long oldSize = data.length, newSize = oa.output.length;
                            double timeSeconds = (double)oa.tiempo / 1000000000;
                            System.out.println("El archivo " + s + " se ha comprimido correctamente!\n" +
                                    "Ha tardado "+timeSeconds+"s y se ha guardado en " + newpath +".\n"+
                                    "El cambio de tamaño pasa de " + oldSize + "B a " + newSize + "B con diferencia de " +
                                    getDiffSize(false, oldSize, newSize) + "B que resulta en un " +
                                    getDiffSizePercentage(false, oldSize, newSize) + "% del archivo original.");
                        } else System.out.println("El formato del fichero debe de ser .lzss!");
                        break;
                    case "salir":
                    case "3":
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

