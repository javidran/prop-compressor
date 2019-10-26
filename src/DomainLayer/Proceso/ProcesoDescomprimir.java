package DomainLayer.Proceso;

import DomainLayer.Algoritmos.Algoritmos;
import DomainLayer.Algoritmos.OutputAlgoritmo;

import java.io.File;

public class ProcesoDescomprimir extends ProcesoFichero {

    public ProcesoDescomprimir(File input) throws Exception {
        super(input);
        Algoritmos[] tipos = null;
        if((tipos=tiposPosibles()) !=null) {
            tipoC = tipos[0];
            asignarAlgoritmo();
        } else throw new Exception("No hay ningun tipo de compresor compatible");
    }

    public Algoritmos[] tiposPosibles() {
        if (ficheroIn.getAbsolutePath().endsWith(".txt") ) {
            return new Algoritmos[] {Algoritmos.LZ78, Algoritmos.LZW, Algoritmos.LZSS};
        }
        else if (ficheroIn.getAbsolutePath().endsWith(".imgc")) {
            return new Algoritmos[] {Algoritmos.JPEG};
        }
        return null;
    }

    public void ejecutarProceso() throws Exception {
        if(!procesado) {
            OutputAlgoritmo outputAlgoritmo = compresorDecompresor.descomprimir(ficheroIn);
            ficheroOut = outputAlgoritmo.outputFile;
            procesado = true;
        } else throw new Exception("El fichero ya ha sido descomprimido!");
    }
}
