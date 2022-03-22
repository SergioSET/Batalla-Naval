package myProject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class tableroCPU extends JPanel {

    public ModelBatallaNaval miModeloBatallaNaval = new ModelBatallaNaval();
    public Barco miBarco = new Barco();
    public JLabel[][] matrizLabelCPU = new JLabel[11][11];

    public int fragatasCPUx[] = new int[4];
    public int fragatasCPUy[] = new int[4];
    static int contadorFragataCPUs;

    public int destructoresCPUx[] = new int[6];
    public int destructoresCPUy[] = new int[6];
    static int contadorDestructoresCPU;

    public int submarinosCPUx[] = new int[6];
    public int submarinosCPUy[] = new int[6];
    static int contadorSubmarinosCPU;

    public int portaAvionesCPUx[] = new int[4];
    public int portaAvionesCPUy[] = new int[4];
    static int contadorPortaavionesCPU;

    public int posicionando;

    public Color colorPoniendo;

    public int array[] = new int[2];

    public static int parteColocandoDestructores = 0;
    public static int parteColocandoSubmarino = 0;
    public static int parteColocandoPortaaviones = 0;
    public int primerox, segundox, tercerox;
    public int primeroy, segundoy, terceroy;
    public boolean barcoVertical;
    public Vector<Vector<Integer>> vectorABorrarHorizontal = new Vector<Vector<Integer>>(1);
    public Vector<Vector<Integer>> vectorABorrarVertical = new Vector<Vector<Integer>>(1);
    public Vector<Vector<Integer>> vectorABorrarHorizontal2 = new Vector<Vector<Integer>>(1);
    public Vector<Vector<Integer>> vectorABorrarVertical2 = new Vector<Vector<Integer>>(1);
    public Vector<Integer> posicionAuxiliar = new Vector<Integer>(2);
    public Vector<Integer> posicionPrimera = new Vector<Integer>(2);
    public Vector<Integer> posicionSegunda = new Vector<Integer>(2);
    public Vector<Integer> posicionTercera = new Vector<Integer>(2);
    public Vector<Vector<Integer>> posicionesPosibles = new Vector<Vector<Integer>>();


    public tableroCPU() {

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

    public void generarBarcos() {
        generarFragatas();
        generarDestructores();
        generarSubmarinos();
        generarPortaaviones();
    }

    public void generarFragatas() {

        Random aleatorio = new Random();
        int numeroRandomI = aleatorio.nextInt(10) + 1;
        int numeroRandomJ = aleatorio.nextInt(10) + 1;

        if (sePuedePosicionar(numeroRandomI, numeroRandomJ) == true) {
            matrizLabelCPU[numeroRandomI][numeroRandomJ].setBackground(Color.black);
            asignarPosicionFragatas(numeroRandomI, numeroRandomJ);
        }
        System.out.println(numeroRandomI + "numero aleatorio" + numeroRandomJ);
    }

    public void generarDestructores() {

    }

    public void generarSubmarinos() {

    }

    public void generarPortaaviones() {

    }

    public void asignarPosicionFragatas(int x, int y) {
        array[0] = x;
        array[1] = y;
        contadorFragataCPUs = contadorFragataCPUs+ 1;
        System.out.println("Fragatas");
        for (int i = 0; i < fragatasCPUx.length; i++) {
            if (fragatasCPUx[i] == 0) {
                fragatasCPUx[i] = array[0];
                fragatasCPUy[i] = array[1];
                //System.out.println(fragatasx[i] + ", " + fragatasy[i]);
                i = fragatasCPUx.length + 1;
            }
        }
    }

    public void calcularPosicionando() {
        if (contadorFragataCPUs < 4) {
            posicionando = 0;
            colorPoniendo = Color.black;
        } else if (contadorDestructoresCPU < 6) {
            posicionando = 1;
            colorPoniendo = Color.white;
        } else if (contadorSubmarinosCPU < 6) {
            posicionando = 2;
            colorPoniendo = Color.green;
        } else if (contadorPortaavionesCPU < 4) {
            posicionando = 3;
            colorPoniendo = Color.yellow;
        } else {
            posicionando = 4;
        }
    }


    public boolean sePuedePosicionar(int i, int j) {
        if (matrizLabelCPU[i][j].getBackground() == Color.cyan && tieneVecinosDisponibles(i, j) == true) {
            if (posicionPresionadaDisponible(i, j) == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean tieneVecinosDisponibles(int i, int j) {
        boolean valor = false;
        switch (posicionando) {
            case 0:
                valor = true;
                break;
            case 1:
                if (verificarArriba(i, j, 1) == true || verificarAbajo(i, j, 1) == true || verificarDerecha(i, j, 1) == true || verificarIzquierda(i, j, 1) == true) {
                    valor = true;
                } else {
                    valor = false;
                }
                break;
            case 2:
                if (parteColocandoSubmarino == 0) {
                    if ((verificarArriba(i, j, 1) == true && verificarArriba(i, j, 2) == true) || (verificarAbajo(i, j, 1) == true && verificarAbajo(i, j, 2) == true)) {
                        valor = true;
                    } else {
                        if ((verificarDerecha(i, j, 1) == true && verificarDerecha(i, j, 2) == true) || (verificarIzquierda(i, j, 1) == true && verificarIzquierda(i, j, 2) == true)) {
                            valor = true;
                        } else {
                            if (verificarArriba(i, j, 1) == true && verificarAbajo(i, j, 1) == true) {
                                valor = true;
                            } else if (verificarDerecha(i, j, 1) == true && verificarIzquierda(i, j, 1) == true) {
                                valor = true;
                            } else {
                                valor = false;
                            }
                        }
                    }
                } else if (parteColocandoSubmarino == 1) {
                    if (barcoVertical) {
                        if (verificarArriba(i, j, 1) == true || verificarAbajo(i, j, 1) == true) {
                            return true;
                        } else {
                            if ((verificarArriba(i, j, 1) == true && verificarAbajo(i, j, 2) == true) || (verificarArriba(i, j, 2) == true && verificarAbajo(i, j, 1) == true)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else {
                        if (verificarDerecha(i, j, 1) == true || verificarIzquierda(i, j, 1) == true) {
                            return true;
                        } else {
                            if ((verificarDerecha(i, j, 1) == true && verificarIzquierda(i, j, 2) == true) || (verificarDerecha(i, j, 2) == true && verificarIzquierda(i, j, 1) == true)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                } else if (parteColocandoSubmarino == 2) {
                    if (barcoVertical) {
                        if (verificarArriba(i, j, 1) == true || verificarAbajo(i, j, 1) == true) {
                            valor = true;
                        } else {
                            valor = false;
                        }
                    } else {
                        if (verificarDerecha(i, j, 1) == true || verificarIzquierda(i, j, 1) == true) {
                            valor = true;
                        } else {
                            valor = false;
                        }
                    }
                }
                break;
            case 3:
                if (parteColocandoPortaaviones == 0) {
                    if ((verificarArriba(i, j, 1) == true && verificarArriba(i, j, 2) == true && verificarArriba(i, j, 3) == true) || (verificarAbajo(i, j, 1) == true && verificarAbajo(i, j, 2) == true && verificarAbajo(i, j, 3) == true)) {
                        valor = true;
                    } else {
                        if ((verificarDerecha(i, j, 1) == true && verificarDerecha(i, j, 2) == true && verificarDerecha(i, j, 3) == true) || (verificarIzquierda(i, j, 1) == true && verificarIzquierda(i, j, 2) == true && verificarIzquierda(i, j, 3) == true)) {
                            valor = true;
                        } else {
                            if ((verificarDerecha(i, j, 1) == true && verificarDerecha(i, j, 2) == true && verificarDerecha(i, j, 3) == true) || (verificarIzquierda(i, j, 1) == true && verificarIzquierda(i, j, 2) == true && verificarIzquierda(i, j, 3) == true)) {
                                valor = true;
                            } else {
                                if ((verificarArriba(i, j, 1) == true && verificarAbajo(i, j, 2) == true) || (verificarArriba(i, j, 2) == true && verificarAbajo(i, j, 1) == true)) {
                                    valor = true;
                                } else if ((verificarDerecha(i, j, 1) == true && verificarIzquierda(i, j, 2) == true) || (verificarDerecha(i, j, 2) == true && verificarIzquierda(i, j, 1) == true)) {
                                    valor = true;
                                } else {
                                    valor = false;
                                }
                            }
                        }

                    }
                } else if (parteColocandoPortaaviones == 1) {
                    if (barcoVertical) {
                        if (verificarArriba(i, j, 1) == true || verificarAbajo(i, j, 1) == true) {
                            return true;
                        } else {
                            if ((verificarArriba(i, j, 1) == true && verificarAbajo(i, j, 3) == true) || (verificarArriba(i, j, 3) == true && verificarAbajo(i, j, 1) == true)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else {
                        if (verificarDerecha(i, j, 1) == true || verificarIzquierda(i, j, 1) == true) {
                            return true;
                        } else {
                            if ((verificarDerecha(i, j, 1) == true && verificarIzquierda(i, j, 3) == true) || (verificarDerecha(i, j, 3) == true && verificarIzquierda(i, j, 1) == true)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                } else if (parteColocandoPortaaviones == 2) {
                    if (barcoVertical) {
                        if (verificarArriba(i, j, 1) == true || verificarAbajo(i, j, 1) == true) {
                            return true;
                        } else {
                            if ((verificarArriba(i, j, 1) == true && verificarAbajo(i, j, 2) == true) || (verificarArriba(i, j, 2) == true && verificarAbajo(i, j, 1) == true)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else {
                        if (verificarDerecha(i, j, 1) == true || verificarIzquierda(i, j, 1) == true) {
                            return true;
                        } else {
                            if ((verificarDerecha(i, j, 1) == true && verificarIzquierda(i, j, 2) == true) || (verificarDerecha(i, j, 2) == true && verificarIzquierda(i, j, 1) == true)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                } else if (parteColocandoPortaaviones == 3) {
                    if (barcoVertical) {
                        if (verificarArriba(i, j, 1) == true || verificarAbajo(i, j, 1) == true) {
                            valor = true;
                        } else {
                            valor = false;
                        }
                    } else {
                        if (verificarDerecha(i, j, 1) == true || verificarIzquierda(i, j, 1) == true) {
                            valor = true;
                        } else {
                            valor = false;
                        }
                    }
                }
                break;
            default:
                break;
        }
        System.out.println("Tiene vecinos diponibles: " + valor);
        return valor;
    }

    public boolean verificarArriba(int i, int j, int numero) {
        if (i + numero > 10) {
            return false;
        } else {
            if (tableroPersonal.matrizLabel[i + numero][j].getBackground() == Color.cyan) {
                return true;
            } else if (tableroPersonal.matrizLabel[i + numero][j].getBackground() == colorPoniendo) {
                System.out.println("Tiene disponible arriba");
                return true;
            } else {
                System.out.println("No tiene disponible arriba");
                return false;
            }
        }
    }

    public boolean verificarAbajo(int i, int j, int numero) {
        if (i - numero < 1) {
            return false;
        } else {
            if (tableroPersonal.matrizLabel[i - numero][j].getBackground() == Color.cyan) {
                return true;
            } else if (tableroPersonal.matrizLabel[i - numero][j].getBackground() == colorPoniendo) {
                System.out.println("Tiene disponible abajo");
                return true;
            } else {
                System.out.println("No tiene disponible abajo");
                return false;
            }
        }
    }

    public boolean verificarDerecha(int i, int j, int numero) {
        if (j + numero > 10) {
            return false;
        } else {
            if (tableroPersonal.matrizLabel[i][j + numero].getBackground() == Color.cyan) {
                return true;
            } else if (tableroPersonal.matrizLabel[i][j + numero].getBackground() == colorPoniendo) {
                System.out.println("Tiene disponible derecha");
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean verificarIzquierda(int i, int j, int numero) {
        if (j - numero < 1) {
            return false;
        } else {
            if (tableroPersonal.matrizLabel[i][j - numero].getBackground() == Color.cyan) {
                return true;
            } else if (tableroPersonal.matrizLabel[i][j - numero].getBackground() == colorPoniendo) {
                System.out.println("Tiene disponible izquierda");
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean posicionPresionadaDisponible(int i, int j) {

        boolean valor = true;
        Vector<Integer> posibleArriba = new Vector<Integer>();
        Vector<Integer> posibleAbajo = new Vector<Integer>();
        Vector<Integer> posibleDerecha = new Vector<Integer>();
        Vector<Integer> posibleIzquierda = new Vector<Integer>();

        switch (posicionando) {
            case 0:
                valor = true;
                break;
            case 1:
                if (parteColocandoDestructores == 0) {
                    primerox = i;
                    primeroy = j;
                    valor = true;
                    if (primerox + 1 < 11) {
                        posibleArriba.add(primerox + 1);
                        posibleArriba.add(primeroy);
                        posicionesPosibles.add(posibleArriba);
                    }
                    if (primerox - 1 > 0) {
                        posibleAbajo.add(primerox - 1);
                        posibleAbajo.add(primeroy);
                        posicionesPosibles.add(posibleAbajo);
                    }
                    if (primeroy + 1 < 11) {
                        posibleDerecha.add(primerox);
                        posibleDerecha.add(primeroy + 1);
                        posicionesPosibles.add(posibleDerecha);
                    }
                    if (primeroy - 1 > 0) {
                        posibleIzquierda.add(primerox);
                        posibleIzquierda.add(primeroy - 1);
                        posicionesPosibles.add(posibleIzquierda);
                    }
                } else if (parteColocandoDestructores == 1) {
                    posicionAuxiliar.add(i);
                    posicionAuxiliar.add(j);
                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        valor = true;
                        posibleArriba.clear();
                        posibleAbajo.clear();
                        posibleDerecha.clear();
                        posibleIzquierda.clear();
                        posicionesPosibles.clear();
                    } else {
                        valor = false;
                    }
                } else {
                    System.out.println("Error");
                }
                posicionAuxiliar.clear();
                break;
            case 2:
                if (parteColocandoSubmarino == 0) {
                    valor = true;

                    primerox = i;
                    primeroy = j;

                    posicionPrimera.clear();

                    posicionPrimera.add(primerox);
                    posicionPrimera.add(primeroy);

                    if (primerox + 1 < 11) {
                        posibleArriba.add(primerox + 1);
                        posibleArriba.add(primeroy);
                        vectorABorrarVertical.add(posibleArriba);
                        posicionesPosibles.add(posibleArriba);
                    }
                    if (primerox - 1 > 0) {
                        posibleAbajo.add(primerox - 1);
                        posibleAbajo.add(primeroy);
                        vectorABorrarVertical2.add(posibleAbajo);
                        posicionesPosibles.add(posibleAbajo);
                    }
                    if (primeroy + 1 < 11) {
                        posibleDerecha.add(primerox);
                        posibleDerecha.add(primeroy + 1);
                        vectorABorrarHorizontal.add(posibleDerecha);
                        posicionesPosibles.add(posibleDerecha);
                    }
                    if (primeroy - 1 > 0) {
                        posibleIzquierda.add(primerox);
                        posibleIzquierda.add(primeroy - 1);
                        vectorABorrarHorizontal2.add(posibleIzquierda);
                        posicionesPosibles.add(posibleIzquierda);
                    }
                    System.out.println(posicionesPosibles);

                } else if (parteColocandoSubmarino == 1) {

                    posicionAuxiliar.add(i);
                    posicionAuxiliar.add(j);

                    segundox = i;
                    segundoy = j;

                    posicionSegunda.add(i);
                    posicionSegunda.add(j);

                    if (primeroy == segundoy) {
                        if (vectorABorrarHorizontal.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarHorizontal.get(0));
                        }
                        if (vectorABorrarHorizontal2.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarHorizontal2.get(0));
                        }
                        System.out.println("Es vertical");
                        barcoVertical = true;
                    } else {
                        if (vectorABorrarVertical.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarVertical.get(0));
                        }
                        if (vectorABorrarVertical2.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarVertical2.get(0));
                        }
                        System.out.println("Es horizontal");
                        barcoVertical = false;
                    }

                    vectorABorrarHorizontal.clear();
                    vectorABorrarHorizontal2.clear();
                    vectorABorrarVertical.clear();
                    vectorABorrarVertical2.clear();

                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        valor = true;
                        if (barcoVertical == true) {
                            if (segundox + 1 < 11) {
                                posibleArriba.add(segundox + 1);
                                posibleArriba.add(segundoy);
                                vectorABorrarVertical.add(posibleArriba);
                                posicionesPosibles.add(posibleArriba);
                            }
                            if (segundox - 1 > 0) {
                                posibleAbajo.add(segundox - 1);
                                posibleAbajo.add(segundoy);
                                vectorABorrarVertical2.add(posibleAbajo);
                                posicionesPosibles.add(posibleAbajo);
                            }
                        } else {
                            if (segundoy + 1 < 11) {
                                posibleDerecha.add(segundox);
                                posibleDerecha.add(segundoy + 1);
                                vectorABorrarHorizontal.add(posibleDerecha);
                                posicionesPosibles.add(posibleDerecha);
                            }
                            if (segundoy - 1 > 0) {
                                posibleIzquierda.add(segundox);
                                posibleIzquierda.add(segundoy - 1);
                                vectorABorrarHorizontal2.add(posibleIzquierda);
                                posicionesPosibles.add(posibleIzquierda);
                            }
                        }

                        posicionesPosibles.remove(posicionAuxiliar);
                        System.out.println("La posición primera es: " + posicionPrimera);
                        posicionesPosibles.remove(posicionPrimera);
                        posicionesPosibles.remove(posicionSegunda);
                        System.out.println(posicionAuxiliar);
                        System.out.println(posicionesPosibles);
                    } else {
                        valor = false;
                    }
                } else if (parteColocandoSubmarino == 2) {
                    posicionAuxiliar.add(i);
                    posicionAuxiliar.add(j);
                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        valor = true;
                        System.out.println("Terminaste un Submarino");
                        posibleArriba.clear();
                        posibleAbajo.clear();
                        posibleDerecha.clear();
                        posibleIzquierda.clear();
                        posicionesPosibles.clear();
                        vectorABorrarVertical.clear();
                        vectorABorrarVertical2.clear();
                        vectorABorrarHorizontal.clear();
                        vectorABorrarHorizontal2.clear();
                    } else {
                        valor = false;
                    }
                } else {
                    System.out.println("Error");
                }
                posicionAuxiliar.clear();
                break;
            case 3:
                if (parteColocandoPortaaviones == 0) {
                    primerox = i;
                    primeroy = j;

                    posicionPrimera.clear();
                    posicionPrimera.add(primerox);
                    posicionPrimera.add(primeroy);
                    valor = true;
                    if (primerox + 1 < 11) {
                        posibleArriba.add(primerox + 1);
                        posibleArriba.add(primeroy);
                        vectorABorrarVertical.add(posibleArriba);
                        posicionesPosibles.add(posibleArriba);
                    }
                    if (primerox - 1 > 0) {
                        posibleAbajo.add(primerox - 1);
                        posibleAbajo.add(primeroy);
                        vectorABorrarVertical2.add(posibleAbajo);
                        posicionesPosibles.add(posibleAbajo);
                    }
                    if (primeroy + 1 < 11) {
                        posibleDerecha.add(primerox);
                        posibleDerecha.add(primeroy + 1);
                        vectorABorrarHorizontal.add(posibleDerecha);
                        posicionesPosibles.add(posibleDerecha);
                    }
                    if (primeroy - 1 > 0) {
                        posibleIzquierda.add(primerox);
                        posibleIzquierda.add(primeroy - 1);
                        vectorABorrarHorizontal2.add(posibleIzquierda);
                        posicionesPosibles.add(posibleIzquierda);
                    }
                    System.out.println(posicionesPosibles);
                } else if (parteColocandoPortaaviones == 1) {

                    posicionAuxiliar.add(i);
                    posicionAuxiliar.add(j);

                    segundox = i;
                    segundoy = j;

                    posicionSegunda.clear();
                    posicionSegunda.add(segundox);
                    posicionSegunda.add(segundoy);

                    if (primeroy == segundoy) {
                        if (vectorABorrarHorizontal.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarHorizontal.get(0));
                        }
                        if (vectorABorrarHorizontal2.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarHorizontal2.get(0));
                        }
                        System.out.println("Es vertical");
                        barcoVertical = true;
                    } else {
                        if (vectorABorrarVertical.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarVertical.get(0));
                        }
                        if (vectorABorrarVertical2.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarVertical2.get(0));
                        }
                        System.out.println("Es horizontal");
                        barcoVertical = false;
                    }
                    vectorABorrarHorizontal.clear();
                    vectorABorrarHorizontal2.clear();
                    vectorABorrarVertical.clear();
                    vectorABorrarVertical2.clear();

                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        posicionesPosibles.remove(posicionAuxiliar);
                        valor = true;
                        if (barcoVertical == true) {
                            if (segundox + 1 < 11) {
                                posibleArriba.add(segundox + 1);
                                posibleArriba.add(segundoy);
                                vectorABorrarVertical.add(posibleArriba);
                                posicionesPosibles.add(posibleArriba);
                            }
                            if (segundox - 1 > 0) {
                                posibleAbajo.add(segundox - 1);
                                posibleAbajo.add(segundoy);
                                vectorABorrarVertical2.add(posibleAbajo);
                                posicionesPosibles.add(posibleAbajo);
                            }
                        } else {
                            if (segundoy + 1 < 11) {
                                posibleDerecha.add(segundox);
                                posibleDerecha.add(segundoy + 1);
                                vectorABorrarHorizontal.add(posibleDerecha);
                                posicionesPosibles.add(posibleDerecha);
                            }
                            if (segundoy - 1 > 0) {
                                posibleIzquierda.add(segundox);
                                posibleIzquierda.add(segundoy - 1);
                                vectorABorrarHorizontal2.add(posibleIzquierda);
                                posicionesPosibles.add(posibleIzquierda);
                            }
                        }
                        posicionesPosibles.remove(posicionPrimera);
                        posicionesPosibles.remove(posicionSegunda);
                        System.out.println(posicionAuxiliar);
                        System.out.println(posicionesPosibles);

                    } else {
                        valor = false;
                    }
                } else if (parteColocandoPortaaviones == 2) {

                    posicionAuxiliar.add(i);
                    posicionAuxiliar.add(j);

                    tercerox = i;
                    terceroy = j;
                    posicionTercera.clear();
                    posicionTercera.add(i);
                    posicionTercera.add(j);

                    if (primeroy == segundoy) {
                        if (vectorABorrarHorizontal.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarHorizontal.get(0));
                        }
                        if (vectorABorrarHorizontal2.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarHorizontal2.get(0));
                        }
                        System.out.println("Es vertical");
                        barcoVertical = true;
                    } else {
                        if (vectorABorrarVertical.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarVertical.get(0));
                        }
                        if (vectorABorrarVertical2.isEmpty() == false) {
                            posicionesPosibles.remove(vectorABorrarVertical2.get(0));
                        }
                        System.out.println("Es horizontal");
                        barcoVertical = false;
                    }

                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        posicionesPosibles.remove(posicionAuxiliar);
                        valor = true;
                        if (barcoVertical == true) {
                            if (tercerox + 1 < 11) {
                                posibleArriba.add(tercerox + 1);
                                posibleArriba.add(terceroy);
                                posicionesPosibles.add(posibleArriba);
                            }
                            if (tercerox - 1 > 0) {
                                posibleAbajo.add(tercerox - 1);
                                posibleAbajo.add(terceroy);
                                posicionesPosibles.add(posibleAbajo);
                            }
                        } else {
                            if (terceroy + 1 < 11) {
                                posibleDerecha.add(tercerox);
                                posibleDerecha.add(terceroy + 1);
                                posicionesPosibles.add(posibleDerecha);
                            }
                            if (terceroy - 1 > 0) {
                                posibleIzquierda.add(tercerox);
                                posibleIzquierda.add(terceroy - 1);
                                posicionesPosibles.add(posibleIzquierda);
                            }
                        }
                        posicionesPosibles.remove(posicionPrimera);
                        posicionesPosibles.remove(posicionSegunda);
                        posicionesPosibles.remove(posicionTercera);
                        System.out.println(posicionAuxiliar);
                        System.out.println(posicionesPosibles);

                    } else {
                        valor = false;
                    }
                } else if (parteColocandoPortaaviones == 3) {
                    posicionAuxiliar.add(i);
                    posicionAuxiliar.add(j);
                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        valor = true;
                        System.out.println("Terminaste un Portaviones");
                        posibleArriba.clear();
                        posibleAbajo.clear();
                        posibleDerecha.clear();
                        posibleIzquierda.clear();
                        posicionAuxiliar.clear();
                        posicionesPosibles.clear();
                        vectorABorrarVertical.clear();
                        vectorABorrarVertical2.clear();
                        vectorABorrarHorizontal.clear();
                        vectorABorrarHorizontal2.clear();
                    } else {
                        valor = false;
                    }
                } else {
                    System.out.println("Error");
                }
                posicionAuxiliar.clear();
                break;
            default:
                break;
        }
        return valor;
    }


}
