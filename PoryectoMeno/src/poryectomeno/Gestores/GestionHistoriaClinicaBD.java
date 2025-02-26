/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poryectomeno.Gestores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import poryectomeno.Beans.HistoriaClinicaBean;
import poryectomeno.utils.ConectorBD;

/**
 *
 * @author vPalomo
 */
public class GestionHistoriaClinicaBD {

    public static ArrayList<HistoriaClinicaBean> getHistoriaClinica(String idPaciente) {
        ArrayList<HistoriaClinicaBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            HistoriaClinicaBean historia;
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT `idLineaHistClinica`, `idPaciente`, `fechaAlta`, `P1`, `P2`, `P3`, `P4`, `P5`, `P6`, `P7`, `P8` FROM histclinica WHERE idPaciente=? ORDER BY idLineaHistClinica desc");
            consulta.setString(1, idPaciente);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                historia = new HistoriaClinicaBean();
                historia.setIdLineaHistClinica(resultado.getString("idLineaHistClinica"));
                historia.setIdPaciente(resultado.getString("idPaciente"));
                historia.setFechaAlta(resultado.getString("fechaAlta"));
                historia.setP1(resultado.getString("P1"));
                historia.setP2(resultado.getString("P2"));
                historia.setP3(resultado.getString("P3"));
                historia.setP4(resultado.getString("P4"));
                historia.setP5(resultado.getString("P5"));
                historia.setP6(resultado.getString("P6"));
                historia.setP7(resultado.getString("P7"));
                historia.setP8(resultado.getString("P8"));
                result.add(historia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            }
        }
        return result;
    }

}
