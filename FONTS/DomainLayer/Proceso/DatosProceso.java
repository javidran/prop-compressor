// Creado por Jan Escorza Fuertes
package DomainLayer.Proceso;

/**
 * DatosProceso es una clase que contiene los datos relevantes para la generación de estadísticas de un proceso fichero
 * <p>
 *     Esta clase permite generar estadísticas a partir de los datos que almanecena, tanto a nivel de proceso individual como al poder ser usado por el generador de estadísticas globales
 * </p>
 */
public class DatosProceso {
    /**
     * Tiempo de ejecución del proceso de compresión o descompresión de un fichero
     */
    private long tiempo;
    /**
     * Tamaño del fichero tratado antes del proceso de compresión o descompresión
     */
    private long oldSize;
    /**
     * Tamaño del fichero tratado tras la ejecución del proceso de compresión o descompresión
     */
    private long newSize;
    /**
     * Indicación para saber si es un proceso de compresión o descompresión
     */
    boolean esCompresion;

    /**
     * Constructora de la clase, la cual crea una instancia de DatosProceso con un tiempo, oldSize, newSize y esCompresion asignados
     * @param time El tiempo de ejecución del proceso de compresión o descompresión de un fichero
     * @param oldSize Tamaño del fichero tratado antes del proceso de compresión o descompresión
     * @param newSize Tamaño del fichero tratado tras la ejecución del proceso de compresión o descompresión
     * @param esCompresion Indicación para saber si es un proceso de compresión o descompresión
     * @throws Exception En el caso que la compresión haya sido perjudicial porque el tamaño resultante ha sido mayor al original
     */
    protected DatosProceso(long time, long oldSize, long newSize, boolean esCompresion) throws Exception {
        tiempo = time;
        this.oldSize = oldSize;
        this.newSize = newSize;
        this.esCompresion = esCompresion;
        System.out.println("El proceso ha tardado " + time/1000000000.0 + "s. El cambio de tamaño pasa de " + oldSize + "B a " + newSize + "B con diferencia de " + getDiffSize() + "B / " + getDiffSizePercentage() + "%");
        if(getDiffSize() < 0) {
            System.out.println("El proceso de" + (esCompresion?"compresión":"descompresión") + " no ha resultado satisfactorio ya que ocupa " + (esCompresion?"más":"menos") + " que el archivo original. Se guardará igualmente.");
            throw new Exception("El proceso no ha sido satisfactorio");
        }
    }

    /**
     * Devuelve la diferencia entre el original size y el new size.
     * @return La diferencia entre el antiguo tamaño y el nuevo.
     */
    public long getDiffSize() {
        if(esCompresion) return oldSize - newSize;
        else return newSize - oldSize;
    }
    /**
     * Devuelve la diferencia entre el original size y el new size en forma de porcentaje.
     * @return  La diferencia entre el antiguo tamaño y el nuevo en forma de porcentaje.
     */
    public double getDiffSizePercentage() {
        if(esCompresion) return Math.floor((newSize /(double) oldSize)*100);
        else return Math.floor((oldSize /(double) newSize)*100);
    }

    /**
     * Obtiene el nuevo tamaño del fichero sobre el que trabaja el proceso
     * @return El tamaño que tiene el archivo una vez se ha realizado el proceso
     */
    public long getTiempo() { return tiempo; }
    public long getNewSize() { return newSize; }

    /**
     * Obtiene el antiguo tamaño del fichero sobre el que trabaja el proceso
     * @return El tamaño que tenía el archivo antes de que se realizara el proceso
     */
    public long getOldSize() { return oldSize; }
}
