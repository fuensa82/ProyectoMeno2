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
import poryectomeno.Beans.PacienteBean;
import poryectomeno.utils.ConectorBD;
import poryectomeno.utils.FechasUtils;

/**
 *
 * @author vPalomo
 */
public class GestionPacientesBD {
    public static ArrayList<PacienteBean> getListaPacientes() {
        ArrayList<PacienteBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PacienteBean paciente;
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idPaciente,Apellidos,Nombre,FechaNac FROM paciente "
                    + "ORDER BY Apellidos");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                paciente = new PacienteBean();
                //FechasUtils.fecha(resultado.getString(2));
                paciente.setIdPaciente(resultado.getString(1));
                paciente.setFechaNac(FechasUtils.fecha(resultado.getString(4)));
                paciente.setNombre(resultado.getString(3));
                paciente.setApellidos(resultado.getString(2));
                result.add(paciente);
                
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

    public static PacienteBean getPaciente(String idPaciente) {
        System.out.println("idPaciente:" +idPaciente);
        PacienteBean pacienteResult = new PacienteBean();
        Connection conexion = null;
        System.out.println("gatPaciente");
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idPaciente,Apellidos,Nombre,FechaNac FROM paciente "
                    + "WHERE idPaciente=?");
            consulta.setString(1, idPaciente);

            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                System.out.println("next");
                pacienteResult.setIdPaciente(resultado.getString(1));
                pacienteResult.setNombre(resultado.getString(2));
                pacienteResult.setApellidos(resultado.getString(3));
                pacienteResult.setFechaNac(FechasUtils.fecha(resultado.getString(4)));
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
        return pacienteResult;
    }
}
