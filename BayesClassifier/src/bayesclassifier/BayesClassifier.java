package bayesclassifier;
import Controlador.Controlador;
import Vista.Vista;
/**
 *
 * @author peluc
 */
public class BayesClassifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Invocar al constructor de la clase ModeloBD
        new Modelo.ModeloDB("bayes");

        //Crear un objeto de la clase Vista
        Vista vista = new Vista();

        //Crear un objeto de la clase Controlador
        Controlador controlador  = new Controlador(vista);

        //Vincular la vista y el controlador
        vista.connectController(controlador);
    }
    
}
