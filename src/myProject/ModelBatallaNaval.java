package myProject;

import javax.swing.*;
import java.awt.*;
import java.beans.VetoableChangeListener;
import java.util.Random;
import java.util.Vector;


public class ModelBatallaNaval {

    public int estado;
    public int ataque;
    private Barco miBarco = new Barco();
    public static int posicionando;
    public static int parteColocandoDestructores = 0;
    public static int parteColocandoSubmarino = 0;
    public static int parteColocandoPortaaviones = 0;
    public int primerox, segundox, tercerox;
    public int primeroy, segundoy, terceroy;
    public boolean submarinoVertical;
    public Vector<Vector<Integer>> vectoresABorrarHorizontal = new Vector<Vector<Integer>>(2);
    public Vector<Vector<Integer>> vectoresABorrarVertical = new Vector<Vector<Integer>>(2);
    public Vector<Integer> posicionAuxiliar = new Vector<Integer>(2);
    public Vector<Integer> posicionPrimera = new Vector<Integer>(2);
    public Vector<Integer> posicionSegunda = new Vector<Integer>(2);
    public Vector<Integer> posicionTercera = new Vector<Integer>(2);
    public Vector<Vector<Integer>> posicionesPosibles = new Vector<Vector<Integer>>();

    /*
    posicionando = 0 - Fragatas
    posicionando = 1 - Submarinos
    posicionando = 2 - Destructores
    posicionando = 3 - Portaaviones
    */
    /*
    verticalUHorizontalSubmarino = 0 - Vertical
    verticalUHorizontalSubmarino = 1 - Horizontal
    */

    public ModelBatallaNaval() {

    }

    public void calcularPosicionando() {
        if (miBarco.getContadorFragatas() < 4) {
            posicionando = 0;
        } else if (miBarco.getContadorDestructores() < 6) {
            posicionando = 1;
        } else if (miBarco.getContadorSubmarinos() < 6) {
            posicionando = 2;
        } else if (miBarco.getContadorPortaaviones() < 4) {
            posicionando = 3;
        } else {
            posicionando = 4;
        }
    }

    public void calcularEstado() {
        if (miBarco.barcosPosicionados() == false) {
            estado = 0;
        } else if (miBarco.barcosPosicionados() == true) {
            estado = 1;
        }
    }

    public boolean sePuedePosicionar(int i, int j) {
        if (GUIGridBagLayout.matrizLabel[i][j].getBackground() == Color.cyan && posicionPresionadaDisponible(i, j) == true) {
            return true;
        } else {
            System.out.println("No se puede posicionar");
            return false;
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
                    primerox = i;
                    primeroy = j;
                    posicionPrimera.add(primerox);
                    posicionPrimera.add(primeroy);
                    valor = true;
                    if (primerox + 1 < 11) {
                        posibleArriba.add(primerox + 1);
                        posibleArriba.add(primeroy);
                        vectoresABorrarVertical.add(posibleArriba);
                        posicionesPosibles.add(posibleArriba);
                    }
                    if (primerox - 1 > 0) {
                        posibleAbajo.add(primerox - 1);
                        posibleAbajo.add(primeroy);
                        vectoresABorrarVertical.add(posibleAbajo);
                        posicionesPosibles.add(posibleAbajo);
                    }
                    if (primeroy + 1 < 11) {
                        posibleDerecha.add(primerox);
                        posibleDerecha.add(primeroy + 1);
                        vectoresABorrarHorizontal.add(posibleDerecha);
                        posicionesPosibles.add(posibleDerecha);
                    }
                    if (primeroy - 1 > 0) {
                        posibleIzquierda.add(primerox);
                        posibleIzquierda.add(primeroy - 1);
                        vectoresABorrarHorizontal.add(posibleIzquierda);
                        posicionesPosibles.add(posibleIzquierda);
                    }
                    System.out.println(posicionesPosibles);
                } else if (parteColocandoSubmarino == 1) {

                    posicionAuxiliar.add(i);
                    posicionAuxiliar.add(j);

                    segundox = i;
                    segundoy = j;

                    if (primeroy == segundoy) {
                        posicionesPosibles.remove(vectoresABorrarHorizontal.get(0));
                        if (vectoresABorrarHorizontal.lastElement() != vectoresABorrarHorizontal.get(0)) {
                            posicionesPosibles.remove(vectoresABorrarHorizontal.get(1));
                        }
                        System.out.println("Es vertical");
                        submarinoVertical = true;
                    } else {
                        posicionesPosibles.remove(vectoresABorrarVertical.get(0));
                        if (vectoresABorrarVertical.lastElement() != vectoresABorrarHorizontal.get(0)) {
                            posicionesPosibles.remove(vectoresABorrarVertical.get(1));
                        }
                        System.out.println("Es horizontal");
                        submarinoVertical = false;
                    }

                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        posicionesPosibles.remove(posicionAuxiliar);
                        valor = true;
                        if (submarinoVertical == true) {
                            if (segundox + 1 < 11) {
                                posibleArriba.add(segundox + 1);
                                posibleArriba.add(segundoy);
                                posicionesPosibles.add(posibleArriba);
                            }
                            if (segundox - 1 > 0) {
                                posibleAbajo.add(segundox - 1);
                                posibleAbajo.add(segundoy);
                                posicionesPosibles.add(posibleAbajo);
                            }
                        } else {
                            if (segundoy + 1 < 11) {
                                posibleDerecha.add(segundox);
                                posibleDerecha.add(segundoy + 1);
                                posicionesPosibles.add(posibleDerecha);
                            }
                            if (segundoy - 1 > 0) {
                                posibleIzquierda.add(segundox);
                                posibleIzquierda.add(segundoy - 1);
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
                        posicionAuxiliar.clear();
                        posicionesPosibles.clear();
                        vectoresABorrarVertical.clear();
                        vectoresABorrarHorizontal.clear();
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
                    posicionPrimera.add(primerox);
                    posicionPrimera.add(primeroy);
                    valor = true;
                    if (primerox + 1 < 11) {
                        posibleArriba.add(primerox + 1);
                        posibleArriba.add(primeroy);
                        vectoresABorrarVertical.add(posibleArriba);
                        posicionesPosibles.add(posibleArriba);
                    }
                    if (primerox - 1 > 0) {
                        posibleAbajo.add(primerox - 1);
                        posibleAbajo.add(primeroy);
                        vectoresABorrarVertical.add(posibleAbajo);
                        posicionesPosibles.add(posibleAbajo);
                    }
                    if (primeroy + 1 < 11) {
                        posibleDerecha.add(primerox);
                        posibleDerecha.add(primeroy + 1);
                        vectoresABorrarHorizontal.add(posibleDerecha);
                        posicionesPosibles.add(posibleDerecha);
                    }
                    if (primeroy - 1 > 0) {
                        posibleIzquierda.add(primerox);
                        posibleIzquierda.add(primeroy - 1);
                        vectoresABorrarHorizontal.add(posibleIzquierda);
                        posicionesPosibles.add(posibleIzquierda);
                    }
                    System.out.println(posicionesPosibles);
                } else if (parteColocandoPortaaviones == 1) {

                    posicionAuxiliar.add(i);
                    posicionAuxiliar.add(j);

                    segundox = i;
                    segundoy = j;

                    posicionSegunda.add(segundox);
                    posicionSegunda.add(segundoy);

                    if (primeroy == segundoy) {
                        posicionesPosibles.remove(vectoresABorrarHorizontal.get(0));
                        if (vectoresABorrarHorizontal.lastElement() != vectoresABorrarHorizontal.get(0)) {
                            posicionesPosibles.remove(vectoresABorrarHorizontal.get(1));
                        }
                        System.out.println("Es vertical");
                        submarinoVertical = true;
                    } else {
                        posicionesPosibles.remove(vectoresABorrarVertical.get(0));
                        if (vectoresABorrarVertical.lastElement() != vectoresABorrarHorizontal.get(0)) {
                            posicionesPosibles.remove(vectoresABorrarVertical.get(1));
                        }
                        System.out.println("Es horizontal");
                        submarinoVertical = false;
                    }

                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        posicionesPosibles.remove(posicionAuxiliar);
                        valor = true;
                        if (submarinoVertical == true) {
                            if (segundox + 1 < 11) {
                                posibleArriba.add(segundox + 1);
                                posibleArriba.add(segundoy);
                                posicionesPosibles.add(posibleArriba);
                            }
                            if (segundox - 1 > 0) {
                                posibleAbajo.add(segundox - 1);
                                posibleAbajo.add(segundoy);
                                posicionesPosibles.add(posibleAbajo);
                            }
                        } else {
                            if (segundoy + 1 < 11) {
                                posibleDerecha.add(segundox);
                                posibleDerecha.add(segundoy + 1);
                                posicionesPosibles.add(posibleDerecha);
                            }
                            if (segundoy - 1 > 0) {
                                posibleIzquierda.add(segundox);
                                posibleIzquierda.add(segundoy - 1);
                                posicionesPosibles.add(posibleIzquierda);
                            }
                        }
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

                    if (primeroy == segundoy) {
                        posicionesPosibles.remove(vectoresABorrarHorizontal.get(0));
                        if (vectoresABorrarHorizontal.lastElement() != vectoresABorrarHorizontal.get(0)) {
                            posicionesPosibles.remove(vectoresABorrarHorizontal.get(1));
                        }
                        System.out.println("Es vertical");
                        submarinoVertical = true;
                    } else {
                        posicionesPosibles.remove(vectoresABorrarVertical.get(0));
                        if (vectoresABorrarVertical.lastElement() != vectoresABorrarHorizontal.get(0)) {
                            posicionesPosibles.remove(vectoresABorrarVertical.get(1));
                        }
                        System.out.println("Es horizontal");
                        submarinoVertical = false;
                    }

                    if (posicionesPosibles.contains(posicionAuxiliar)) {
                        posicionesPosibles.remove(posicionAuxiliar);
                        valor = true;
                        if (submarinoVertical == true) {
                            if (segundox + 1 < 11) {
                                posibleArriba.add(segundox + 1);
                                posibleArriba.add(segundoy);
                                posicionesPosibles.add(posibleArriba);
                            }
                            if (segundox - 1 > 0) {
                                posibleAbajo.add(segundox - 1);
                                posibleAbajo.add(segundoy);
                                posicionesPosibles.add(posibleAbajo);
                            }
                        } else {
                            if (segundoy + 1 < 11) {
                                posibleDerecha.add(segundox);
                                posibleDerecha.add(segundoy + 1);
                                posicionesPosibles.add(posibleDerecha);
                            }
                            if (segundoy - 1 > 0) {
                                posibleIzquierda.add(segundox);
                                posibleIzquierda.add(segundoy - 1);
                                posicionesPosibles.add(posibleIzquierda);
                            }
                        }
                        posicionesPosibles.remove(posicionSegunda);
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
                        vectoresABorrarVertical.clear();
                        vectoresABorrarHorizontal.clear();
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

//    public boolean vecinosDisponibles(int i, int j) {
//
//        int numeroArriba = i + 1;
//        int numeroAbajo = i - 1;
//        int numeroDerecha = j + 1;
//        int numeroIzquierda = j - 1;
//        boolean valor = false;
//
//        switch (posicionando) {
//            case 0:
//                if (GUIGridBagLayout.matrizLabel[i][j].getBackground() == Color.cyan) {
//                    valor = true;
//                } else {
//                    valor = false;
//                }
//                break;
//            case 1:
//                if (numeroArriba > 10 || GUIGridBagLayout.matrizLabel[numeroArriba][j].getBackground() != Color.cyan) {
//                    valor = false;
//                } else {
//                    System.out.println(numeroArriba + "," + j);
//                    posicion.add(numeroArriba);
//                    posicion.add(j);
//                    posicionesPosibles.add(posicion);
//                    valor = true;
//                }
//                if (numeroArriba < 1 || GUIGridBagLayout.matrizLabel[numeroAbajo][j].getBackground() != Color.cyan) {
//                    valor = false;
//                } else {
//                    System.out.println(numeroAbajo + "," + j);
//                    valor = true;
//                }
//
//                if (numeroDerecha > 10 || GUIGridBagLayout.matrizLabel[i][numeroDerecha].getBackground() != Color.cyan) {
//                    valor = false;
//                } else {
//                    System.out.println(i + "," + numeroDerecha);
//                    valor = true;
//                }
//                if (numeroIzquierda < 1 || GUIGridBagLayout.matrizLabel[i][numeroIzquierda].getBackground() != Color.cyan) {
//                    valor = false;
//                } else {
//                    System.out.println(i + "," + numeroIzquierda);
//                    valor = true;
//                }
//
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            default:
//                break;
//        }
//        return valor;
//    }
/*
    public void ataqueCPU() {
        Random aleatorio = new Random();
        ataque = aleatorio.nextInt(10) + 1;
        for (int i = ataque; i < 10; i++) {
            for (int j = ataque; j < 1; j++) {

                Barco.getVida() - 1;

            }
        }
    }
*/
}


