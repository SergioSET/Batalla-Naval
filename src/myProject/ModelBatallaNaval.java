package myProject;

import javax.swing.*;
import java.awt.*;
import java.beans.VetoableChangeListener;
import java.util.Random;
import java.util.Vector;


public class ModelBatallaNaval {

    public int estado;
    public int ataque;
    public Color colorPoniendo;
    private Barco miBarco = new Barco();
    public static int posicionando;
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

    public tableroCPU mitableroCPU = new tableroCPU();

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
            colorPoniendo = Color.black;
        } else if (miBarco.getContadorDestructores() < 6) {
            posicionando = 1;
            colorPoniendo = Color.white;
        } else if (miBarco.getContadorSubmarinos() < 6) {
            posicionando = 2;
            colorPoniendo = Color.green;
        } else if (miBarco.getContadorPortaaviones() < 4) {
            posicionando = 3;
            colorPoniendo = Color.yellow;
        } else {
            mitableroCPU.generarBarcos();
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
        if (tableroPersonal.matrizLabel[i][j].getBackground() == Color.cyan && tieneVecinosDisponibles(i, j) == true) {
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
                    if (verificarArriba(i, j, 1)) {
                        if (verificarArriba(i, j, 2)) {
                            valor = true;
                        }else{
                            valor = false;
                        }
                    }
                    if (verificarAbajo(i, j, 1)) {
                        if (verificarAbajo(i, j, 2)) {
                            valor = true;
                        }else{
                            valor = false;
                        }
                    }
                    if (verificarDerecha(i, j, 1)) {
                        if (verificarDerecha(i, j, 2)) {
                            valor = true;
                        }else{
                            valor = false;
                        }
                    }
                    if (verificarIzquierda(i, j, 1)) {
                        if (verificarIzquierda(i, j, 2)) {
                            valor = true;
                        }else{
                            valor = false;
                        }
                    }
                }
//                if (parteColocandoSubmarino == 0) {
//                    if ((verificarArriba(i, j, 1) == true && verificarArriba(i, j, 2) == true) || (verificarAbajo(i, j, 1) == true && verificarAbajo(i, j, 2) == true)) {
//                        valor = true;
//                    } else {
//                        if ((verificarDerecha(i, j, 1) == true && verificarDerecha(i, j, 2) == true) || (verificarIzquierda(i, j, 1) == true && verificarIzquierda(i, j, 2) == true)) {
//                            valor = true;
//                        } else {
//                            if (verificarArriba(i, j, 1) == true && verificarAbajo(i, j, 1) == true) {
//                                valor = true;
//                            } else if (verificarDerecha(i, j, 1) == true && verificarIzquierda(i, j, 1) == true) {
//                                valor = true;
//                            } else {
//                                valor = false;
//                            }
//                        }
//                    }
//                } else if (parteColocandoSubmarino == 1) {
//                    if (barcoVertical) {
//                        if (verificarArriba(i, j, 1) == true || verificarAbajo(i, j, 1) == true) {
//                            return true;
//                        } else {
//                            if ((verificarArriba(i, j, 1) == true && verificarAbajo(i, j, 2) == true) || (verificarArriba(i, j, 2) == true && verificarAbajo(i, j, 1) == true)) {
//                                return true;
//                            } else {
//                                return false;
//                            }
//                        }
//                    } else {
//                        if (verificarDerecha(i, j, 1) == true || verificarIzquierda(i, j, 1) == true) {
//                            return true;
//                        } else {
//                            if ((verificarDerecha(i, j, 1) == true && verificarIzquierda(i, j, 2) == true) || (verificarDerecha(i, j, 2) == true && verificarIzquierda(i, j, 1) == true)) {
//                                return true;
//                            } else {
//                                return false;
//                            }
//                        }
//                    }
//                } else if (parteColocandoSubmarino == 2) {
//                    if (barcoVertical) {
//                        if (verificarArriba(i, j, 1) == true || verificarAbajo(i, j, 1) == true) {
//                            valor = true;
//                        } else {
//                            valor = false;
//                        }
//                    } else {
//                        if (verificarDerecha(i, j, 1) == true || verificarIzquierda(i, j, 1) == true) {
//                            valor = true;
//                        } else {
//                            valor = false;
//                        }
//                    }
//                }
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

//    public void ataqueCPU() {
//        Random aleatorio = new Random();
//        ataque = aleatorio.nextInt(10) + 1;
//        for (int i = ataque; i < 10; i++) {
//            for (int j = ataque; j < 1; j++) {
//
//                Barco.getVida() - 1;
//
//            }
//        }
//    }
}


