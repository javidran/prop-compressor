package Controllers;

import DataLayer.GestorArchivo;
import DataLayer.GestorEstadisticas;
import Enumeration.Algoritmo;
import DomainLayer.Proceso.DatosProceso;

import java.io.*;

public class CtrlDatos {
    /**
     * Instancia de CtrlDatos para garantizar que es una clase Singleton
     */
    private static CtrlDatos instance = null;
    /**
     * Getter de la instancia Singleton de CtrlDatoos
     * @return La instancia Singleton de CtrlDatos
     */
    public static CtrlDatos getInstance()
    {
        if (instance == null)
            instance = new CtrlDatos();

        return instance;
    }

    /**
     * Llama a la clase GestorArchivo para que lea el archivo del path.
     * <p>
     *     El path del archivo debe seguir el formato general de cualquier tipo de path de archivo y puede ser relativo o absoluto.
     * </p>
     *
     * @param path El path del archivo que se quiere leer.
     * @return Un byte[] del contenido del archivo del path.
     * @throws IOException No hay ningún archivo en el path que se pasa o no se ha leído bien.
     */
    public byte[] leerArchivo (String path) throws IOException {
        return GestorArchivo.leeArchivo(path);
    }

    /**
     * Llama a la clase GestorArchivo para que guarde el archivo del path.
     * <p>
     *     El path del archivo debe seguir el formato general de cualquier tipo de path de archivo y puede ser relativo o absoluto.
     * </p>
     * @param data Los datos que se guardaran en el archivo.
     * @param path El path del archivo donde se quiere guardar.
     * @param algoritmo El algoritmo con el qual se ha comprimido o descomprimido el archivo.
     * @param esCompresion Un boolean que indica si se ha hecho compresión o descompresión.
     * @param sobreescribir Un boolean que indica si se va a sobreescribir.
     * @throws IOException No se ha podido escribir en el archivo por el path indicado.
     */
    public void guardaArchivo (byte[] data, String path, Algoritmo algoritmo, boolean esCompresion, boolean sobreescribir) throws IOException {
        String [] pathSplitted = path.split("\\.");
        switch (algoritmo) {
            case LZW:
                if(esCompresion) path = path.replace(".txt", ".lzw");
                else path = path.replace("."+pathSplitted[pathSplitted.length - 1], "_out.txt");
                break;
            case LZSS:
                if(esCompresion) path = path.replace(".txt", ".lzss");
                else path = path.replace("."+pathSplitted[pathSplitted.length - 1], "_out.txt");
                break;
            case LZ78:
                if(esCompresion) path = path.replace(".txt", ".lz78");
                else path = path.replace("."+pathSplitted[pathSplitted.length - 1], "_out.txt");
                break;
            case JPEG:
                if(esCompresion) path = path.replace(".ppm", ".imgc");
                else path = path.replace("."+pathSplitted[pathSplitted.length - 1], "_out.ppm");
                break;
        }
        GestorArchivo.guardaArchivo(data, path, sobreescribir);
    }

    /**
     * Actualiza el fichero de estadística de compresión o descompresión del algoritmo correspondiente con los datos del nuevo archivo procesado.
     * @param dp DatosProceso para conseguir la información estadística del proceso.
     * @param algoritmo Enumeration.Algoritmo del que actualizamos el fichero estadística.
     * @param esCompresion Indica si el fichero estadística es de compresión o descompresión.
     */
    public void actualizaEstadistica(DatosProceso dp, Algoritmo algoritmo, boolean esCompresion) {
        GestorEstadisticas.actualizarEstadistica(dp,algoritmo,esCompresion);
    }

    /**
     * Obtiene el número de elementos que contiene la estadística para compresión o descompresión del algoritmo seleccionado.
     * @param algoritmo El algoritmo que se quiere consultar.
     * @param esCompresion El tipo de proceso que se quiere, consultar, si de compresión o descompresión.
     * @return El número de elementos que contiene la estadística indicada.
     * @throws IOException Ha existido algún tipo de problema accediendo al archivo o no existe.
     */
    public int getNumeroElementos(Algoritmo algoritmo, boolean esCompresion) throws IOException {
        return GestorEstadisticas.getNumeroElementos(algoritmo, esCompresion);
    }

    /**
     * Obtiene el tiempo medio de la estadística para compresión o descompresión del algoritmo seleccionado.
     * @param algoritmo El algoritmo que se quiere consultar.
     * @param esCompresion El tipo de proceso que se quiere, consultar, si de compresión o descompresión.
     * @return El tiempo medio de todos los procesos de la estadística indicada.
     * @throws IOException Ha existido algún tipo de problema accediendo al archivo o no existe.
     */
    public long getTiempoMedio(Algoritmo algoritmo, boolean esCompresion) throws IOException {
        return GestorEstadisticas.getTiempoMedio(algoritmo, esCompresion);
    }

    /**
     * Obtiene el porcentaje medio que representan los archivos procesados respecto al original de la estadística para compresión o descompresión del algoritmo seleccionado.
     * @param algoritmo El algoritmo que se quiere consultar.
     * @param esCompresion El tipo de proceso que se quiere, consultar, si de compresión o descompresión.
     * @return El porcentaje medio que representan los archivos procesados respecto al original.
     * @throws IOException Ha existido algún tipo de problema accediendo al archivo o no existe.
     */
    public double getPorcentajeAhorradoMedio(Algoritmo algoritmo, boolean esCompresion) throws IOException {
        return GestorEstadisticas.getPorcentajeAhorradoMedio(algoritmo,esCompresion);
    }
}
