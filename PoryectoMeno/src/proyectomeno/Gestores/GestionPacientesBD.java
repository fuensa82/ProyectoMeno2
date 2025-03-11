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
import javax.naming.NamingException;
import proyectomeno.Beans.HistoriaClinicaFamiliarBean;
import proyectomeno.Beans.HistoriaClinicaFijaBean;
import proyectomeno.Beans.PacienteBean;
import proyectomeno.utils.ConectorBD;
import proyectomeno.utils.FechasUtils;

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

    public static HistoriaClinicaFijaBean getHistoriaClinicaFija(String idPaciente){
        HistoriaClinicaFijaBean histResult = new HistoriaClinicaFijaBean();
        Connection conexion = null;
        System.out.println("getHistoriaClinicaFija");
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT `idHistClinicaFija`, `idPaciente`, `fechaHistClinica`, `P1`,"+
                    " `P2`, `P3`, `P4`, `P5`, `P6`, `P7`, `P8`, `P9`, `P10`, `P11`, `P12`,"+
                    " `P13`, `P14`, `P15`, `P16`, `P17`, `P18`, `E18`, `P19`, `E19`, `D19`,"+
                    " `P20`, `E20A`, `E20B`, `P21`, `P22`, `P23`, `E23`, `P24`, `P25`, `P26` FROM `menopausia`.`histclinicafija` "+
                    "WHERE idPaciente=?");
            consulta.setString(1, idPaciente);

            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                System.out.println("next");
                histResult.setIdHistClinicaFija(resultado.getString("idHistClinicaFija"));
                histResult.setIdPaciente(resultado.getString("idPaciente"));
                histResult.setFechaHistClinica(FechasUtils.fecha(resultado.getString("fechaHistClinica")));
                histResult.setP1(FechasUtils.fecha(resultado.getString("P1")));
                histResult.setP2(FechasUtils.fecha(resultado.getString("P2")));
                histResult.setP3(FechasUtils.fecha(resultado.getString("P3")));
                histResult.setP4(resultado.getString("P4"));
                histResult.setP5(resultado.getString("P5"));
                histResult.setP6(resultado.getString("P6"));
                histResult.setP7(resultado.getString("P7"));
                histResult.setP8(resultado.getString("P8"));
                histResult.setP9(resultado.getString("P9"));
                histResult.setP10(resultado.getString("P10"));
                histResult.setP11(resultado.getString("P11"));
                histResult.setP12(resultado.getString("P12"));
                histResult.setP13(resultado.getString("P13"));
                histResult.setP14(resultado.getString("P14"));
                histResult.setP15(resultado.getString("P15"));
                histResult.setP16(resultado.getString("P16"));
                histResult.setP17(resultado.getString("P17"));
                histResult.setP18(resultado.getString("P18"));
                histResult.setP19(resultado.getString("P19"));
                histResult.setE19(resultado.getString("E19"));
                histResult.setD19(resultado.getString("D19"));
                
                histResult.setP20(resultado.getString("P20"));
                histResult.setE20A(resultado.getString("E20A"));
                histResult.setE20B(resultado.getString("E20B"));
                
                histResult.setP21(resultado.getString("P21"));
                histResult.setP22(resultado.getString("P22"));
                histResult.setP23(resultado.getString("P23"));
                histResult.setE23(resultado.getString("E23"));
                histResult.setP24(resultado.getString("P24"));
                histResult.setP25(resultado.getString("P25"));
                histResult.setP26(resultado.getString("P26"));
                
                System.out.println(histResult.toString());
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
        return histResult;
    }
     public static HistoriaClinicaFamiliarBean getHistoriaClinicaFamiliar(String idPaciente){
        HistoriaClinicaFamiliarBean histResult = new HistoriaClinicaFamiliarBean();
        Connection conexion = null;
        System.out.println("getHistoriaClinicaFija");
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT `idHistClinicaFam`, `idPaciente`, `fechaAlta`, `P27`, `E27`, `P28`, `E28`, `P29`, `E29`, `P30`,"+
                    " `E30`, `P31`, `P32` FROM `menopausia`.`histclinicafamiliar` WHERE  `idPaciente`=? ORDER BY idHistClinicaFam desc");
            consulta.setString(1, idPaciente);

            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                System.out.println("next");
                histResult.setIdHistClinicaFam(resultado.getString("idHistClinicaFam"));
                histResult.setIdPaciente(resultado.getString("idPaciente"));
                histResult.setFechaAlta(FechasUtils.fecha(resultado.getString("fechaAlta")));
                histResult.setP27(resultado.getString("P27"));
                histResult.setP28(resultado.getString("P28"));
                histResult.setP29(resultado.getString("P29"));
                histResult.setP30(resultado.getString("P30"));
                histResult.setP31(resultado.getString("P31"));
                histResult.setP32(resultado.getString("P32"));
                
                histResult.setE27(resultado.getString("E27"));
                histResult.setE28(resultado.getString("E28"));
                histResult.setE29(resultado.getString("E29"));
                histResult.setE30(resultado.getString("E30"));
                
                
                System.out.println(histResult.toString());
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
        return histResult;
    }
    /**
     * Devuelve los datos personales de un paciente. Nombre, apelluidos, edad
     *
     * @param idPaciente
     * @return
     */
    public static PacienteBean getPaciente(String idPaciente) {
        System.out.println("idPaciente:" + idPaciente);
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
