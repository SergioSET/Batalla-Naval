package myProject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class tableroPersonal extends JPanel {

    public static JLabel[][] matrizLabel = new JLabel[11][11];

    public tableroPersonal() {


        this.setLayout(null);
        this.setPreferredSize(new Dimension(390, 390));
        this.setSize(390, 390);
        this.setBorder(BorderFactory.createTitledBorder("Tu tablero"));

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                matrizLabel[i][j] = new JLabel("", SwingConstants.CENTER);
                matrizLabel[i][j].setBorder(new LineBorder(Color.BLACK));
                matrizLabel[i][j].setOpaque(true);
                matrizLabel[i][j].setBounds(30 + j * 30, 30 + i * 30, 30, 30);
                matrizLabel[i][j].setBackground(Color.cyan);
                matrizLabel[i][j].setPreferredSize(new Dimension(30, 30));
                if (i == 0 || j == 0) {
                } else {
                    matrizLabel[i][j].addMouseListener(GUIGridBagLayout.escucha);
                }
                this.add(matrizLabel[i][j]);
            }
        }
        matrizLabel[0][0].setText("*");
        matrizLabel[0][0].setBackground(Color.gray);
        matrizLabel[0][1].setText("A");
        matrizLabel[0][1].setBackground(Color.gray);
        matrizLabel[0][2].setText("B");
        matrizLabel[0][2].setBackground(Color.gray);
        matrizLabel[0][3].setText("C");
        matrizLabel[0][3].setBackground(Color.gray);
        matrizLabel[0][4].setText("D");
        matrizLabel[0][4].setBackground(Color.gray);
        matrizLabel[0][5].setText("E");
        matrizLabel[0][5].setBackground(Color.gray);
        matrizLabel[0][6].setText("F");
        matrizLabel[0][6].setBackground(Color.gray);
        matrizLabel[0][7].setText("G");
        matrizLabel[0][7].setBackground(Color.gray);
        matrizLabel[0][8].setText("H");
        matrizLabel[0][8].setBackground(Color.gray);
        matrizLabel[0][9].setText("I");
        matrizLabel[0][9].setBackground(Color.gray);
        matrizLabel[0][10].setText("J");
        matrizLabel[0][10].setBackground(Color.gray);

        for (int i = 1; i < 11; i++) {
            matrizLabel[i][0].setText(i + "");
            matrizLabel[i][0].setBackground(Color.gray);
        }

    }
}