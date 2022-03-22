package myProject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class GUIGridBagLayout extends JFrame {

    public static Escucha escucha;
    private Header headerProject;
    private JPanel tableroJugador, tableroMaquina;
    public ModelBatallaNaval mimodeloBatallaNaval;
    private Barco miBarcoGui;
    private tableroPersonal miTableroPersonal;
    private tableroCPU miTableroCPU;
    public JLabel labelAyudante = new JLabel();
    public static int posicionx;
    public static int posiciony;
    public Vector<Integer> posiciones = new Vector<Integer>(2);
    public JButton generarTableroCPU;

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
        miTableroCPU = new tableroCPU();
        miTableroPersonal = new tableroPersonal();
        //Set up JComponents

        headerProject = new Header("Batalla Naval", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(headerProject, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(miTableroPersonal, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(miTableroCPU, constraints);

        generarTableroCPU = new JButton("Generar");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        generarTableroCPU.addMouseListener(escucha);
        add(generarTableroCPU, constraints);

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
                        if (e.getSource().equals(tableroPersonal.matrizLabel[i][j])) {
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
                        tableroPersonal.matrizLabel[posicionx][posiciony].setBackground(Color.black);
                        miBarcoGui.asignarPosicionFragatas(posicionx, posiciony);
                    }
                } else if (mimodeloBatallaNaval.posicionando == 1) {
                    if (mimodeloBatallaNaval.sePuedePosicionar(posicionx, posiciony) == true) {
                        tableroPersonal.matrizLabel[posicionx][posiciony].setBackground(Color.white);
                        miBarcoGui.asignarPosicionesDestructores(posicionx, posiciony);
                        if (mimodeloBatallaNaval.parteColocandoDestructores == 0) {
                            mimodeloBatallaNaval.parteColocandoDestructores = 1;
                        } else {
                            mimodeloBatallaNaval.parteColocandoDestructores = 0;
                        }
                    }
                } else if (mimodeloBatallaNaval.posicionando == 2) {
                    if (mimodeloBatallaNaval.sePuedePosicionar(posicionx, posiciony) == true) {
                        tableroPersonal.matrizLabel[posicionx][posiciony].setBackground(Color.green);
                        miBarcoGui.asignarPosicionesSubmarinos(posicionx, posiciony);
                        mimodeloBatallaNaval.parteColocandoSubmarino = mimodeloBatallaNaval.parteColocandoSubmarino + 1;
                        if (mimodeloBatallaNaval.parteColocandoSubmarino == 3) {
                            mimodeloBatallaNaval.parteColocandoSubmarino = 0;
                        }
                    }
                } else if (mimodeloBatallaNaval.posicionando == 3) {
                    if (mimodeloBatallaNaval.sePuedePosicionar(posicionx, posiciony) == true) {
                        tableroPersonal.matrizLabel[posicionx][posiciony].setBackground(Color.YELLOW);
                        miBarcoGui.asignarPosicionesPortaaviones(posicionx, posiciony);
                        mimodeloBatallaNaval.parteColocandoPortaaviones = mimodeloBatallaNaval.parteColocandoPortaaviones + 1;
                        if (mimodeloBatallaNaval.parteColocandoPortaaviones == 4) {
                            mimodeloBatallaNaval.parteColocandoPortaaviones = 0;
                        }
                    }
                }
                mimodeloBatallaNaval.calcularPosicionando();
            } else if (mimodeloBatallaNaval.estado == 1) {

            } else {

            }
            if (e.getSource() == generarTableroCPU) {
                miTableroCPU.generarBarcos();
//                while(miTableroCPU.contadorDestructoresCPU<6){
//
//                }
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
