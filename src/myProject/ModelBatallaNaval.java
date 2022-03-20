package myProject;

public class ModelBatallaNaval {

    public int estado;
    public Barco miBarco;
    public int posicionando = 0;

    /*
    posicionando = 0 - Fragatas
    posicionando = 1 - Submarinos
    posicionando = 2 - Destructores
    posicionando = 3 - Portaaviones
    */

    public ModelBatallaNaval() {

        miBarco = new Barco();

        estado = 0;

        if(miBarco.fragatas.length<5){
            posicionando = 0;
        }else if(miBarco.submarinos.length<5){
            posicionando = 1;
        }else if(miBarco.destructores.length<5){
            posicionando = 2;
        }else if(miBarco.portaAviones.length<5){
            posicionando = 3;
        }else{
            posicionando = 0;
        }
    }

    public void calcularEstado() {
//        if (miBarco.barcosPosionados() == false) {
//            estado = 1;
//        } else if (miBarco.barcosPosionados() == true) {
//            estado = 0;
//        }
        estado = 0;
    }
}

