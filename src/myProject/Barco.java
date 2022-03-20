package myProject;

public class Barco {

    String portaAviones[] = new String[1];
    String submarinos[] = new String[2];
    String destructores[] = new String[3];
    String fragatas[] = new String[4];

    public Barco() {

    }

    public boolean barcosPosionados() {

        if (portaAviones.length == 0 || submarinos.length == 0 || destructores.length == 0 || fragatas.length == 0) {
            return false;
        } else {
            return true;
        }
    }
}
