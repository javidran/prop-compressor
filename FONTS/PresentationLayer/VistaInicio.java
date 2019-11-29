package PresentationLayer;

import Controllers.CtrlPresentacion;
import Controllers.CtrlProcesos;
import Enumeration.Algoritmo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;


public class VistaInicio extends JFrame {
    private CtrlPresentacion ctrlPresentacion;

    private JFrame myself;
    private JPanel panel;
    private JButton salirButton;

    private JTextField pathEntrada;
    private JButton explorarButton;

    private JButton comprimirButton;
    private JButton descomprimirButton;
    private JButton comprimirYDescomprimirButton;

    private JButton estadisticasButton;
    private JButton escogerPrederterminadoButton;
    private JTextField mostrarPredeterminado;

    public VistaInicio() {
        super ("PROPresor");

        this.ctrlPresentacion = CtrlPresentacion.getInstance();

        myself = this;
        setContentPane(panel);

        explorarButton.addActionListener(actionEvent -> seleccionDeArchivo());

        salirButton.addActionListener(e -> {
            ctrlPresentacion.cerrarVistaInicio();
            dispose();
        });

        pathEntrada.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                ctrlPresentacion.pathCambiado(pathEntrada.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                ctrlPresentacion.pathCambiado(pathEntrada.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                ctrlPresentacion.pathCambiado(pathEntrada.getText());
            }
        });

        comprimirButton.addActionListener(actionEvent -> ctrlPresentacion.crearVistaSeleccionAlgoritmo(true));

        descomprimirButton.addActionListener(actionEvent -> ctrlPresentacion.crearVistaSeleccionAlgoritmo(true));

        comprimirYDescomprimirButton.addActionListener(actionEvent -> ctrlPresentacion.crearVistaSeleccionAlgoritmo(false));

        estadisticasButton.addActionListener(e -> {
            JDialog dialog;
            dialog = new VistaEstadisticas(myself);
            Dimension dimension = new Dimension(600, 300);
            dialog.setSize(dimension);
            dimension = new Dimension(600, 300);
            dialog.setMinimumSize(dimension);
            dialog.setLocationRelativeTo(myself);
            dialog.setResizable(true);
            dialog.setVisible(true);
        });

        escogerPrederterminadoButton.addActionListener(e -> ctrlPresentacion.escogerPredeterminadoPulsado());

    }

    private void seleccionDeArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isDirectory()) return true;

                String path = file.getName();
                String[] splitP = path.split("\\.");
                String type = splitP[splitP.length-1];

                switch (type) {
                    case "txt":
                    case "ppm":
                    case "imgc":
                    case "lzss":
                    case "lz78":
                    case "lzw":
                    case "comp":
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public String getDescription() {
                return "*.txt,*.ppm,*.imgc,*.lzss,*.lz78,*.lzw,*.comp";
            }
        };

        chooser.setFileFilter(fileFilter);
        int result = chooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
            pathEntrada.setText(chooser.getSelectedFile().getAbsolutePath());
    }

    public void botonComprimir(boolean habilitar){
        comprimirButton.setEnabled(habilitar);
        descomprimirButton.setEnabled(!habilitar);
    }

    public void botoncomprimirYDescomprimir(boolean habilitar){
        comprimirYDescomprimirButton.setEnabled(habilitar);
    }

    public void deshabilitarBotones() {
        comprimirYDescomprimirButton.setEnabled(false);
        comprimirButton.setEnabled(false);
        descomprimirButton.setEnabled(false);
    }

    public void algoritmoPredeterminado(Algoritmo algoritmo) {
        mostrarPredeterminado.setText(String.valueOf(algoritmo));
    }

}

