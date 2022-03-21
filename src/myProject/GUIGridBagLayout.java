package myProject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class GUIGridBagLayout extends JFrame {

    public Escucha escucha;
    private Header headerProject;
    private JPanel tableroJugador, tableroMaquina;
    public ModelBatallaNaval mimodeloBatallaNaval;
    private Barco miBarcoGui;
    public JLabel labelAyudante = new JLabel();
    public static int posicionx;
    public static int posiciony;
    public Vector<Integer> posiciones = new Vector<Integer>(2);

    public static JLabel[][] matrizLabel = new JLabel[11][11];

    public GUIGridBagLayout() {

        initGUI();

        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        //this.setBackground(new Color(255, 255, 255, 0));
        this.setSize(new Dimension(1200, 600));
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        //Set up JFrame Container's Layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object or Control Object
        escucha = new Escucha();
        mimodeloBatallaNaval = new ModelBatallaNaval();
        miBarcoGui = new Barco();
        //Set up JComponents

        headerProject = new Header("Batalla Naval", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(headerProject, constraints);

        {
            tableroJugador = new JPanel();
            tableroJugador.setLayout(null);
            tableroJugador.setPreferredSize(new Dimension(390, 390));
            tableroJugador.setSize(390, 390);
            tableroJugador.setBorder(BorderFactory.createTitledBorder("Tu tablero"));
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            {
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
                            matrizLabel[i][j].addMouseListener(escucha);
                        }
                        tableroJugador.add(matrizLabel[i][j]);
                    }
                }
                matrizLabel[0][0].setText("*");
                matrizLabel[0][1].setText("A");
                matrizLabel[0][2].setText("B");
                matrizLabel[0][3].setText("C");
                matrizLabel[0][4].setText("D");
                matrizLabel[0][5].setText("E");
                matrizLabel[0][6].setText("F");
                matrizLabel[0][7].setText("G");
                matrizLabel[0][8].setText("H");
                matrizLabel[0][9].setText("I");
                matrizLabel[0][10].setText("J");

                matrizLabel[1][0].setText("1");
                matrizLabel[2][0].setText("2");
                matrizLabel[3][0].setText("3");
                matrizLabel[4][0].setText("4");
                matrizLabel[5][0].setText("5");
                matrizLabel[6][0].setText("6");
                matrizLabel[7][0].setText("7");
                matrizLabel[8][0].setText("8");
                matrizLabel[9][0].setText("9");
                matrizLabel[10][0].setText("10");
            }

            add(tableroJugador, constraints);
        }

        {
            tableroMaquina = new JPanel();
            tableroMaquina.setPreferredSize(new Dimension(390, 390));
            tableroMaquina.setBorder(BorderFactory.createTitledBorder("Tablero CPU"));
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            add(tableroMaquina, constraints);
        }

        //mimodeloBatallaNaval.inicializarPosicionesLibres();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    private class Escucha implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

            /*
            Fragatas = Color.BLACK
            Submarinos = Color.WHITE
            Destructores = Color.GREEN
            Portaaviones = Color.YELLOW
            */

            mimodeloBatallaNaval.calcularEstado();

            if (mimodeloBatallaNaval.estado == 0) {

                for (int i = 0; i < 11; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (e.getSource().equals(matrizLabel[i][j])) {
                            posiciones.clear();
                            posicionx = i;
                            posiciony = j;
                            posiciones.add(i);
                            posiciones.add(j);
                        }
                    }
                }

                if (mimodeloBatallaNaval.posicionando == 0) {
                    if (mimodeloBatallaNaval.sePuedePosicionar(posicionx, posiciony) == true) {
                        matrizLabel[posicionx][posiciony].setBackground(Color.black);
                        miBarcoGui.asignarPosicionFragatas(posicionx, posiciony);
                    }
                } else if (mimodeloBatallaNaval.posicionando == 1) {
                    if (mimodeloBatallaNaval.sePuedePosicionar(posicionx, posiciony) == true) {
                        matrizLabel[posicionx][posiciony].setBackground(Color.white);
                        miBarcoGui.asignarPosicionesDestructores(posicionx, posiciony);
                        if (mimodeloBatallaNaval.parteColocandoDestructores == 0) {
                            mimodeloBatallaNaval.parteColocandoDestructores = 1;
                        } else {
                            mimodeloBatallaNaval.parteColocandoDestructores = 0;
                        }
                    }
                } else if (mimodeloBatallaNaval.posicionando == 2) {
                    if (mimodeloBatallaNaval.sePuedePosicionar(posicionx, posiciony) == true) {
                        matrizLabel[posicionx][posiciony].setBackground(Color.green);
                        miBarcoGui.asignarPosicionesSubmarinos(posicionx, posiciony);
                        mimodeloBatallaNaval.parteColocandoSubmarino = mimodeloBatallaNaval.parteColocandoSubmarino + 1;
                        if (mimodeloBatallaNaval.parteColocandoSubmarino == 3) {
                            mimodeloBatallaNaval.parteColocandoSubmarino = 0;
                        }
                    }
                } else if (mimodeloBatallaNaval.posicionando == 3) {
                    if (mimodeloBatallaNaval.sePuedePosicionar(posicionx, posiciony) == true) {
                        matrizLabel[posicionx][posiciony].setBackground(Color.YELLOW);
                        miBarcoGui.asignarPosicionesPortaaviones(posicionx, posiciony);
                        mimodeloBatallaNaval.parteColocandoPortaaviones = mimodeloBatallaNaval.parteColocandoPortaaviones + 1;
                        if (mimodeloBatallaNaval.parteColocandoPortaaviones == 4) {
                            mimodeloBatallaNaval.parteColocandoPortaaviones = 0;
                        }
                    }
                }
                mimodeloBatallaNaval.calcularPosicionando();
            } else if (mimodeloBatallaNaval.estado == 1) {
                JOptionPane.showMessageDialog(null, "Hola, estás en el estado 1");
            } else {

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
