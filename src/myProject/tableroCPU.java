package myProject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class tableroCPU extends JPanel {

    public tableroCPU() {

        JLabel[][] matrizLabelCPU = new JLabel[11][11];

        this.setPreferredSize(new Dimension(390, 390));
        this.setBorder(BorderFactory.createTitledBorder("Tablero CPU"));
        this.setLayout(null);

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                matrizLabelCPU[i][j] = new JLabel("", SwingConstants.CENTER);
                matrizLabelCPU[i][j].setBorder(new LineBorder(Color.BLACK));
                matrizLabelCPU[i][j].setOpaque(true);
                matrizLabelCPU[i][j].setBounds(30 + j * 30, 30 + i * 30, 30, 30);
                matrizLabelCPU[i][j].setBackground(Color.cyan);
                matrizLabelCPU[i][j].setPreferredSize(new Dimension(30, 30));
                if (i == 0 || j == 0) {
                } else {
                    matrizLabelCPU[i][j].addMouseListener(GUIGridBagLayout.escucha);
                }
                this.add(matrizLabelCPU[i][j]);
            }
        }
        matrizLabelCPU[0][0].setText("*");
        matrizLabelCPU[0][0].setBackground(Color.gray);
        matrizLabelCPU[0][1].setText("A");
        matrizLabelCPU[0][1].setBackground(Color.gray);
        matrizLabelCPU[0][2].setText("B");
        matrizLabelCPU[0][2].setBackground(Color.gray);
        matrizLabelCPU[0][3].setText("C");
        matrizLabelCPU[0][3].setBackground(Color.gray);
        matrizLabelCPU[0][4].setText("D");
        matrizLabelCPU[0][4].setBackground(Color.gray);
        matrizLabelCPU[0][5].setText("E");
        matrizLabelCPU[0][5].setBackground(Color.gray);
        matrizLabelCPU[0][6].setText("F");
        matrizLabelCPU[0][6].setBackground(Color.gray);
        matrizLabelCPU[0][7].setText("G");
        matrizLabelCPU[0][7].setBackground(Color.gray);
        matrizLabelCPU[0][8].setText("H");
        matrizLabelCPU[0][8].setBackground(Color.gray);
        matrizLabelCPU[0][9].setText("I");
        matrizLabelCPU[0][9].setBackground(Color.gray);
        matrizLabelCPU[0][10].setText("J");
        matrizLabelCPU[0][10].setBackground(Color.gray);

        for (int i = 1; i < 11; i++) {
            matrizLabelCPU[i][0].setText(i + "");
            matrizLabelCPU[i][0].setBackground(Color.gray);
        }
    }

    public static void generarBarcos(){
        generarFragatas();
        generarDestructores();
        generarSubmarinos();
        generarPortaaviones();
    }

    public static void generarFragatas(){

    }
    public static void generarDestructores(){

    }
    public static void generarSubmarinos(){

    }
    public static void generarPortaaviones(){

    }

}
