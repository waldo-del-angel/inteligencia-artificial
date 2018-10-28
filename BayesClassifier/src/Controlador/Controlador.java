/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloDB;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author peluc
 */
public class Controlador implements ActionListener {

    private final Vista vista;

    // Objeto para ejecuta los comandos SQL sin procedimientos almacenados
    PreparedStatement ps;
    // Objeto para recoger los datos devueltos por ps
    ResultSet rs;

    //Se declaran contadores para calcular el total de peliculas de cada categoria.
    double totalterror = 0;
    double totalcomedia = 0;
    double totalaccion = 0;
    double totalcifi = 0;
    double totalinfantil = 0;
    double totalromantica = 0;

    //Es un contador para elegir la categoria segun el estado de animo
    double estadoterror = 0;
    double estadocomedia = 0;
    double estadoaccion = 0;
    double estadocifi = 0;
    double estadoinfantil = 0;
    double estadoromantica = 0;

    //Es un contador para elegir la categoria segun la comida
    double comidaterror = 0;
    double comidacomedia = 0;
    double comidaaccion = 0;
    double comidacifi = 0;
    double comidainfantil = 0;
    double comidaromantica = 0;

    //Es un contador para elegir la categoria segun la compania
    double compaterror = 0;
    double compacomedia = 0;
    double compaaccion = 0;
    double compacifi = 0;
    double compainfantil = 0;
    double comparomantica = 0;

    //Es un contador para elegir la categoria segun el lugar
    double lugarterror = 0;
    double lugarcomedia = 0;
    double lugaraccion = 0;
    double lugarcifi = 0;
    double lugarinfantil = 0;
    double lugarromantica = 0;

    //Es un contador para elegir la categoria segun el clima
    double climaterror = 0;
    double climacomedia = 0;
    double climaaccion = 0;
    double climacifi = 0;
    double climainfantil = 0;
    double climaromantica = 0;

    //Es un contador para elegir la categoria segun el color
    double colorterror = 0;
    double colorcomedia = 0;
    double coloraccion = 0;
    double colorcifi = 0;
    double colorinfantil = 0;
    double colorromantica = 0;

    //declaras una variable global para el total de registros    
    double totalr;

    //Arreglo que Guarda las categorias para comparar el total de veces que se repite cada categoria
    String[] categoria = {"terror", "comedia", "accion", "cifi", "infantil", "romantica"};

    // Bandera para comprobar que el usuario ha hecho una consulta
    boolean band = false;

    String animo, comida, compania, lugar, clima, color, cat;

    public Controlador(Vista vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Comando ejecutado
        String comando = e.getActionCommand();

        //asignacion del valor del combo box a las variables
        animo = this.vista.cmb_animo.getSelectedItem().toString();
        comida = this.vista.cmb_comida.getSelectedItem().toString();
        compania = this.vista.cmb_compania.getSelectedItem().toString();
        lugar = this.vista.cmb_lugar.getSelectedItem().toString();
        clima = this.vista.cmb_clima.getSelectedItem().toString();
        color = this.vista.cmb_color.getSelectedItem().toString();
        cat = this.vista.cmb_categoria.getSelectedItem().toString();

        switch (comando) {
            case "RESULTADO":
                band = true;
                elegirCategoria();
                catEdoAnimo(animo);
                catComida(comida);
                catCompania(compania);
                catLugar(lugar);
                catClima(clima);
                catColor(color);

                //calcular el total de registros
                totalr = totalterror + totalcomedia + totalaccion + totalcifi + totalinfantil + totalromantica;

                //calcular categoria terror ccterror
                float ccterror = (float) ((estadoterror / totalterror) * (comidaterror / totalterror) 
                        * (lugarterror / totalterror)
                        * (compaterror / totalterror) * (climaterror / totalterror) 
                        * (colorterror / totalterror) * (totalterror / totalr));

                //calcular categoria comedia cccomedia
                float cccomedia = (float) ((estadocomedia / totalcomedia) * (comidacomedia / totalcomedia) * (lugarcomedia / totalcomedia)
                        * (compacomedia / totalcomedia) * (climacomedia / totalcomedia) * (colorcomedia / totalcomedia) * (totalcomedia / totalr));

                //calcular categoria accion ccaccion
                float ccaccion = (float) ((estadoaccion / totalaccion) * (comidaaccion / totalaccion) * (lugaraccion / totalaccion)
                        * (compaaccion / totalaccion) * (climaaccion / totalaccion) * (coloraccion / totalaccion) * (totalaccion / totalr));

                //calcular categoria cifi cccifi
                float cccifi = (float) ((estadocifi / totalcifi) * (comidacifi / totalcifi) * (lugarcifi / totalcifi)
                        * (compacifi / totalcifi) * (climacifi / totalcifi) * (colorcifi / totalcifi) * (totalcifi / totalr));

                //calcular categoria infantil
                float ccinfantil = (float) ((estadoinfantil / totalinfantil) * (comidainfantil / totalinfantil) * (lugarinfantil / totalinfantil)
                        * (compainfantil / totalinfantil) * (climainfantil / totalinfantil) * (colorinfantil / totalinfantil) * (totalinfantil / totalr));

                //calcular categoria romantica
                float ccromantica = (float) ((estadoromantica / totalromantica) * (comidaromantica / totalromantica) * (lugarromantica / totalromantica)
                        * (comparomantica / totalromantica) * (climaromantica / totalromantica) * (colorromantica / totalromantica) * (totalromantica / totalr));

                //normalizar categorias
                float terrornormal = ccterror / (ccterror + cccomedia + ccaccion + cccifi + ccinfantil + ccromantica);
                float comedianormal = cccomedia / (ccterror + cccomedia + ccaccion + cccifi + ccinfantil + ccromantica);
                float accionnormal = ccaccion / (ccterror + cccomedia + ccaccion + cccifi + ccinfantil + ccromantica);
                float cifinormal = cccifi / (ccterror + cccomedia + ccaccion + cccifi + ccinfantil + ccromantica);
                float infantilnormal = ccinfantil / (ccterror + cccomedia + ccaccion + cccifi + ccinfantil + ccromantica);
                float romanticanormal = ccromantica / (ccterror + cccomedia + ccaccion + cccifi + ccinfantil + ccromantica);

                //Obtiene el maximo de los resultados
                float numeromax = Math.max(terrornormal, Math.max(0, comedianormal));
                float numeromax2 = Math.max(numeromax, Math.max(accionnormal, cifinormal));
                float numeromax3 = Math.max(numeromax2, Math.max(infantilnormal, romanticanormal));
                System.out.println("el mayor es=" + numeromax3);

                //Compara el mayor
                if (numeromax3 == terrornormal) {
                    this.vista.cmb_categoria.setSelectedItem("terror");
                    this.vista.lblResultado.setText("Terror");
                }
                if (numeromax3 == comedianormal) {
                    this.vista.cmb_categoria.setSelectedItem("comedia");
                    this.vista.lblResultado.setText("Comedia");
                }
                if (numeromax3 == accionnormal) {
                    this.vista.cmb_categoria.setSelectedItem("accion");
                    this.vista.lblResultado.setText("Acción");
                }

                if (numeromax3 == cifinormal) {
                    this.vista.cmb_categoria.setSelectedItem("cifi");
                    this.vista.lblResultado.setText("Ciencia ficción");
                }
                if (numeromax3 == infantilnormal) {
                    this.vista.cmb_categoria.setSelectedItem("infantil");
                    this.vista.lblResultado.setText("Infantil");
                }
                if (numeromax3 == romanticanormal) {
                    this.vista.cmb_categoria.setSelectedItem("romantica");
                    this.vista.lblResultado.setText("Romántica");
                }
                break;
            case "GUARDAR":
                if (band == true) {
                    // Cambiamos la bandera a false
                    band  = false;
                    //Hace la insercion a la tabla segun para que no se repitan.
                    try {
                        ps = ModeloDB.getConexion().
                                prepareStatement("insert into datos (estado,comida,compania,lugar,clima,color,pelicula)"
                                        + " SELECT '" + animo + "','" + comida + "','" + compania + "','" + lugar + "','"
                                        + clima + "','" + color + "','" + cat + "' FROM dual WHERE NOT EXISTS ("
                                        + "SELECT estado, comida, compania, lugar, clima, color, pelicula "
                                        + "FROM datos WHERE estado='" + animo + "' AND comida='" + comida + "' AND compania='" + compania + "'"
                                        + " AND lugar='" + lugar + "' AND clima='" + clima + "' AND color='" + color + "'"
                                        + " AND pelicula='" + cat + "')");
                        ps.execute();
                        JOptionPane.showMessageDialog(null, "datos guardados", "Correcto :3", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException error) {
                        JOptionPane.showMessageDialog(null, "error al guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Primero realice una consulta", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                break;
        }
    }

    /**
     * Método para elegir la categoria
     */
    private void elegirCategoria() {
        for (int i = 0; i < 6; i++) {
            try {
                ps = ModeloDB.getConexion().prepareStatement("SELECT COUNT(pelicula) FROM datos WHERE pelicula='" + categoria[i] + "'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (categoria[i].equals("terror")) {
                        totalterror = rs.getDouble(1);
                    }
                    if (categoria[i].equals("comedia")) {
                        totalcomedia = rs.getDouble(1);
                    }
                    if (categoria[i].equals("accion")) {
                        totalaccion = rs.getDouble(1);
                    }
                    if (categoria[i].equals("cifi")) {
                        totalcifi = rs.getDouble(1);
                    }
                    if (categoria[i].equals("infantil")) {
                        totalinfantil = rs.getDouble(1);
                    }
                    if (categoria[i].equals("romantica")) {
                        totalromantica = rs.getDouble(1);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * Método para elegir la categoria segun el estado de animo
     *
     * @param animo
     */
    private void catEdoAnimo(String animo) {
        for (int i = 0; i < 6; i++) {
            try {
                ps = ModeloDB.getConexion().prepareStatement("SELECT COUNT(estado) FROM datos WHERE estado='" + animo + "' and pelicula='" + categoria[i] + "'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (categoria[i].equals("terror")) {
                        estadoterror = rs.getDouble(1);
                        estadoterror += 0.1;

                    }
                    if (categoria[i].equals("comedia")) {
                        estadocomedia = rs.getDouble(1);
                        estadocomedia += 0.1;

                    }
                    if (categoria[i].equals("accion")) {
                        estadoaccion = rs.getDouble(1);
                        estadoaccion += 0.1;
                    }
                    if (categoria[i].equals("cifi")) {
                        estadocifi = rs.getDouble(1);
                        estadocifi += 0.1;
                    }
                    if (categoria[i].equals("infantil")) {
                        estadoinfantil = rs.getDouble(1);
                        estadoinfantil += 0.1;
                    }
                    if (categoria[i].equals("romantica")) {
                        estadoromantica = rs.getDouble(1);
                        estadoromantica += 0.1;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * Método para elegir la categoria según la comida.
     *
     * @param comida
     */
    private void catComida(String comida) {
        for (int i = 0; i < 6; i++) {
            try {
                ps = ModeloDB.getConexion().prepareStatement("SELECT COUNT(comida) FROM datos WHERE comida='" + comida + "' AND pelicula='" + categoria[i] + "'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (categoria[i].equals("terror")) {
                        comidaterror = rs.getDouble(1);
                        comidaterror += 0.1;
                    }
                    if (categoria[i].equals("comedia")) {
                        comidacomedia = rs.getDouble(1);
                        comidacomedia += 0.1;
                    }
                    if (categoria[i].equals("accion")) {
                        comidaaccion = rs.getDouble(1);
                        comidaaccion += 0.1;
                    }
                    if (categoria[i].equals("cifi")) {
                        comidacifi = rs.getDouble(1);
                        comidacifi += 0.1;
                    }
                    if (categoria[i].equals("infantil")) {
                        comidainfantil = rs.getDouble(1);
                        comidainfantil += 0.1;
                    }
                    if (categoria[i].equals("romantica")) {
                        comidaromantica = rs.getDouble(1);
                        comidaromantica += 0.1;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * Método para elegir la categoria según la compañia.
     *
     * @param compania
     */
    private void catCompania(String compania) {
        for (int i = 0; i < 6; i++) {
            try {
                ps = ModeloDB.getConexion().prepareStatement("SELECT COUNT(compania) FROM datos WHERE compania='" + compania + "' AND pelicula='" + categoria[i] + "'");
                rs = ps.executeQuery();

                while (rs.next()) {
                    if (categoria[i].equals("terror")) {
                        compaterror = rs.getDouble(1);
                        compaterror += 0.1;
                    }
                    if (categoria[i].equals("comedia")) {
                        compacomedia = rs.getDouble(1);
                        compacomedia += 0.1;
                    }
                    if (categoria[i].equals("accion")) {
                        compaaccion = rs.getDouble(1);
                        compaaccion += 0.1;
                    }
                    if (categoria[i].equals("cifi")) {
                        compacifi = rs.getDouble(1);
                        compacifi += 0.1;
                    }
                    if (categoria[i].equals("infantil")) {
                        compainfantil = rs.getDouble(1);
                        compainfantil += 0.1;
                    }
                    if (categoria[i].equals("romantica")) {
                        comparomantica = rs.getDouble(1);
                        comparomantica += 0.1;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * Método para elegir la categoria segun el lugar
     *
     * @param lugar
     */
    private void catLugar(String lugar) {
        for (int i = 0; i < 6; i++) {
            try {
                ps = ModeloDB.getConexion().prepareStatement("select count(lugar) from datos where lugar='" + lugar + "' and pelicula='" + categoria[i] + "'");
                rs = ps.executeQuery();

                while (rs.next()) {
                    if (categoria[i].equals("terror")) {
                        lugarterror = rs.getDouble(1);
                        lugarterror += 0.1;
                    }
                    if (categoria[i].equals("comedia")) {
                        lugarcomedia = rs.getDouble(1);
                        lugarcomedia += 0.1;
                    }
                    if (categoria[i].equals("accion")) {
                        lugaraccion = rs.getDouble(1);
                        lugaraccion += 0.1;
                    }
                    if (categoria[i].equals("cifi")) {
                        lugarcifi = rs.getDouble(1);
                        lugarcifi += 0.1;
                    }
                    if (categoria[i].equals("infantil")) {
                        lugarinfantil = rs.getDouble(1);
                        lugarinfantil += 0.1;
                    }
                    if (categoria[i].equals("romantica")) {
                        lugarromantica = rs.getDouble(1);
                        lugarromantica += 0.1;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * Método para elegir la categoria segun el clima
     *
     * @param clima
     */
    private void catClima(String clima) {
        for (int i = 0; i < 6; i++) {
            try {
                ps = ModeloDB.getConexion().prepareStatement("select count(clima) from datos where clima='" + clima + "' and pelicula='" + categoria[i] + "'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (categoria[i].equals("terror")) {
                        climaterror = rs.getDouble(1);
                        climaterror += 0.1;
                    }
                    if (categoria[i].equals("comedia")) {
                        climacomedia = rs.getDouble(1);
                        climacomedia += 0.1;
                    }
                    if (categoria[i].equals("accion")) {
                        climaaccion = rs.getDouble(1);
                        climaaccion += 0.1;
                    }
                    if (categoria[i].equals("cifi")) {
                        climacifi = rs.getDouble(1);
                        climacifi += 0.1;
                    }
                    if (categoria[i].equals("infantil")) {
                        climainfantil = rs.getDouble(1);
                        climainfantil += 0.1;
                    }
                    if (categoria[i].equals("romantica")) {
                        climaromantica = rs.getDouble(1);
                        climaromantica += 0.1;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * Método para elegir la categoria según el color
     *
     * @param color
     */
    private void catColor(String color) {
        for (int i = 0; i < 6; i++) {
            try {
                ps = ModeloDB.getConexion().prepareStatement("select count(color) from datos where color='" + color + "' and pelicula='" + categoria[i] + "'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (categoria[i].equals("terror")) {
                        colorterror = rs.getDouble(1);
                        colorterror += 0.1;

                    }
                    if (categoria[i].equals("comedia")) {
                        colorcomedia = rs.getDouble(1);
                        colorcomedia += 0.1;
                    }
                    if (categoria[i].equals("accion")) {
                        coloraccion = rs.getDouble(1);
                        coloraccion += 0.1;
                    }
                    if (categoria[i].equals("cifi")) {
                        colorcifi = rs.getDouble(1);
                        colorcifi += 0.1;
                    }
                    if (categoria[i].equals("infantil")) {
                        colorinfantil = rs.getDouble(1);
                        colorinfantil += 0.1;
                    }
                    if (categoria[i].equals("romantica")) {
                        colorromantica = rs.getDouble(1);
                        colorromantica += 0.1;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
}
