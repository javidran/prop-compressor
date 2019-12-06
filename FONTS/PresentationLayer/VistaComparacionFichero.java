package PresentationLayer;

import DomainLayer.Proceso.DatosProceso;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class VistaComparacionFichero extends JDialog {
    public JTable original;
    private JButton finalizarButton;
    private JPanel panel;
    private JLabel tiempoC;
    private JLabel OldSizeC;
    private JLabel NewSizeC;
    private JLabel DiffSizeC;
    private JLabel RatioC;
    private JLabel tiempoD;
    private JLabel OldSizeD;
    private JLabel NewSizeD;
    private JLabel DiffSizeD;
    private JLabel RatioD;
    private JScrollPane originalPanel;
    private JScrollPane resultadoPanel;
    public JTable resultado;

    public VistaComparacionFichero(Frame owner, DatosProceso[] dp){
        super (owner, "Proceso completado",true);
        setContentPane(panel);
        originalPanel.getVerticalScrollBar().setUnitIncrement(16);
        originalPanel.getHorizontalScrollBar().setUnitIncrement(16);
        resultadoPanel.getVerticalScrollBar().setUnitIncrement(16);
        resultadoPanel.getHorizontalScrollBar().setUnitIncrement(16);

        DecimalFormat df = new DecimalFormat("#.####");
        tiempoC.setText(df.format((double)dp[0].getTiempo() / 1000000000.0));
        OldSizeC.setText(Long.toString(dp[0].getOldSize()));
        NewSizeC.setText(Long.toString(dp[0].getNewSize()));
        DiffSizeC.setText(Long.toString(dp[0].getDiffSize()));
        RatioC.setText(Double.toString(dp[0].getDiffSizePercentage()));

        tiempoD.setText(df.format((double)dp[1].getTiempo() / 1000000000.0));
        OldSizeD.setText(Long.toString(dp[1].getOldSize()));
        NewSizeD.setText(Long.toString(dp[1].getNewSize()));
        DiffSizeD.setText(Long.toString(dp[1].getDiffSize()));
        RatioD.setText(Double.toString(dp[1].getDiffSizePercentage()));

        finalizarButton.addActionListener(e -> dispose());
    }

}
