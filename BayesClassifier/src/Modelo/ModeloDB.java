package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author peluc
 */
public class ModeloDB {

    private String usuario = "root";
    private String clave = "angeloski1"; // Cambiar por su contraseña de su BD
    private String servidor = "127.0.0.1";
    private static Connection conexion = null;

    public ModeloDB(String baseDatos) {
        this.servidor = "jdbc:mysql://127.0.0.1/" + baseDatos;
        //Registrar el driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Mostrar mensaje de error al usuario final
            JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR EL DRIVER!", "ERROR", JOptionPane.ERROR_MESSAGE);
            // Terminar la aplicación
            System.exit(0);
        }

        //Establecer la conexión con el servidor
        try {
            conexion = DriverManager.getConnection(this.servidor, this.usuario, this.clave);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR CON EL SERVIDOR", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            // Terminar la aplicación
            System.exit(0);
        }
        System.out.println("\033[32mConectado a " + baseDatos);
        //JOptionPane.showMessageDialog(null, "Conectado a "+baseDatos, "BIENVENIDO", JOptionPane.INFORMATION_MESSAGE);
    }

    //Devuelve el objeto Connection que se usará en la clase Controller
    public static Connection getConexion() {
        return conexion;
    }
}
