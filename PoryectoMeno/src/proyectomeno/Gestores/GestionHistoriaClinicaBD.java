/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomeno.Gestores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import proyectomeno.Beans.HistoriaClinicaBean;
import proyectomeno.utils.ConectorBD;
import proyectomeno.utils.FechasUtils;

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
    
    public static boolean guardarHistoria(HistoriaClinicaBean historia){
        boolean result = false;
        Connection conexion = null;

        try {
            conexion = ConectorBD.getConnection();

            PreparedStatement insert1 = conexion.prepareStatement("INSERT INTO `menopausia`.`histclinica` (`idPaciente`, `fechaAlta`, `P1`, `P2`, `P3`, `P4`, `P5`, `P6`, `P7`, `P8`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            
            insert1.setString(1, historia.getIdPaciente());
            insert1.setString(2, FechasUtils.fechaHoyParaMysql());
            insert1.setString(3, historia.getP1()); //P1
            insert1.setString(4, historia.getP2()); //P1
            insert1.setString(5, historia.getP3()); //P1
            insert1.setString(6, historia.getP4()); //P1
            insert1.setString(7, historia.getP5()); //P1
            insert1.setString(8, historia.getP6()); //P1
            insert1.setString(9, historia.getP7()); //P1
            insert1.setString(10, historia.getP8()); //P1
            
            System.out.println(insert1.toString());
            insert1.executeUpdate();

            return true;

        } catch (NamingException ex) {
            Logger.getLogger(GestionHistoriaClinicaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestionHistoriaClinicaBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    
        
    }

}
