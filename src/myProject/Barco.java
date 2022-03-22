package myProject;

import java.util.Vector;

public class Barco {

    public int fragatasx[] = new int[4];
    public int fragatasy[] = new int[4];
    static int contadorFragatas;

    public int destructoresx[] = new int[6];
    public int destructoresy[] = new int[6];
    static int contadorDestructores;

    public int submarinosx[] = new int[6];
    public int submarinosy[] = new int[6];
    static int contadorSubmarinos;

    public int portaAvionesx[] = new int[4];
    public int portaAvionesy[] = new int[4];
    static int contadorPortaaviones;

    public static int vida;

    public int array[] = new int[2];

    public Barco() {
        contadorFragatas = 0;
        contadorDestructores = 0;
        contadorSubmarinos = 0;
        contadorPortaaviones = 0;
        vida = ModelBatallaNaval.posicionando;
    }

    public boolean barcosPosicionados() {
        if (contadorPortaaviones == 4) {
            return true;
        } else {
            return false;
        }
    }

    public static int getContadorFragatas() {
        return contadorFragatas;
    }

    public static int getContadorDestructores() {
        return contadorDestructores;
    }

    public static int getContadorSubmarinos() {
        return contadorSubmarinos;
    }

    public static int getContadorPortaaviones() {
        return contadorPortaaviones;
    }

    public void asignarPosicionFragatas(int x, int y) {
        array[0] = x;
        array[1] = y;
        contadorFragatas = contadorFragatas + 1;
        System.out.println("Fragatas");
        for (int i = 0; i < fragatasx.length; i++) {
            if (fragatasx[i] == 0) {
                fragatasx[i] = array[0];
                fragatasy[i] = array[1];
                //System.out.println(fragatasx[i] + ", " + fragatasy[i]);
                i = fragatasx.length + 1;
            }
        }
    }

    public void asignarPosicionesDestructores(int x, int y) {
        array[0] = x;
        array[1] = y;
        contadorDestructores = contadorDestructores + 1;
        System.out.println("Destructores");
        for (int i = 0; i < destructoresx.length; i++) {
            if (destructoresx[i] == 0) {
                destructoresx[i] = array[0];
                destructoresy[i] = array[1];
                //System.out.println(destructoresx[i] + ", " + destructoresy[i]);
                i = destructoresx.length + 1;
            }
        }
    }

    public void asignarPosicionesSubmarinos(int x, int y) {
        array[0] = x;
        array[1] = y;
        contadorSubmarinos = contadorSubmarinos + 1;
        System.out.println("Submarinos");
        for (int i = 0; i < submarinosx.length; i++) {
            if (submarinosx[i] == 0) {
                submarinosx[i] = array[0];
                submarinosy[i] = array[1];
                //System.out.println(submarinosx[i] + ", " + submarinosy[i]);
                i = submarinosx.length + 1;
            }
        }
    }

    public void asignarPosicionesPortaaviones(int x, int y) {
        array[0] = x;
        array[1] = y;
        contadorPortaaviones = contadorPortaaviones + 1;
        System.out.println("Portaaviones");
        for (int i = 0; i < portaAvionesx.length; i++) {
            if (portaAvionesx[i] == 0) {
                portaAvionesx[i] = array[0];
                portaAvionesy[i] = array[1];
                //System.out.println(portaAvionesx[i] + ", " + portaAvionesy[i]);
                i = portaAvionesx.length + 1;
            }
        }
    }
}
