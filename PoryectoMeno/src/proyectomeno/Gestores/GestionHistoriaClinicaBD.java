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
import proyectomeno.Beans.HistoriaClinicaFamiliarBean;
import proyectomeno.Beans.HistoriaClinicaFijaBean;
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
    public static HistoriaClinicaFamiliarBean getHistoriaClinicaFamiliar(String idPaciente) {
        HistoriaClinicaFamiliarBean histResult = new HistoriaClinicaFamiliarBean();
        Connection conexion = null;
        System.out.println("getHistoriaClinicaFija");
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT `idHistClinicaFam`, `idPaciente`, `fechaAlta`, `P27`, `E27`, `P28`, `E28`, `P29`, `E29`, `P30`,"
                    + " `E30`, `P31`, `P32` FROM `menopausia`.`histclinicafamiliar` WHERE  `idPaciente`=? ORDER BY idHistClinicaFam desc");
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
    
    public static HistoriaClinicaFijaBean getHistoriaClinicaFija(String idPaciente) {
        HistoriaClinicaFijaBean histResult = new HistoriaClinicaFijaBean();
        Connection conexion = null;
        System.out.println("getHistoriaClinicaFija");
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT `idHistClinicaFija`, `idPaciente`, `fechaHistClinica`, `P1`,"
                    + " `P2`, `P3`, `P4`, `P5`, `P6`, `P7`, `P8`, `P9`, `P10`, `P11`, `P12`,"
                    + " `P13`, `P14`, `P15`, `P16`, `P17`, `P18`, `E18`, `P19`, `E19`, `D19`,"
                    + " `P20`, `E20A`, `E20B`, `P21`, `P22`, `P23`, `E23`, `P24`, `P25`,`E25`, `P26` FROM `menopausia`.`histclinicafija` "
                    + "WHERE idPaciente=?");
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
                histResult.setE25(resultado.getString("E25"));
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
    
    public static int guardarHistoriaClinicaFija(HistoriaClinicaFijaBean histResult) {
        int resultado = 0;
        Connection conexion = null;
        System.out.println("guardarHistoriaClinicaFija");
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert = conexion.prepareStatement(
                    "INSERT INTO `menopausia`.`histclinicafija` (`idPaciente`, `fechaHistClinica`, `P1`, `P2`, `P3`, `P4`, `P5`, `P6`, `P7`, "
                    + "`P8`, `P9`, `P10`, `P11`, `P12`, `P13`, `P14`, `P15`, `P16`, `P17`, `P18`, `E18`, `P19`, `E19`, `D19`, `P20`, `E20A`,"
                    + " `E20B`, `P21`, `P22`, `P23`, `E23`, `P24`, `P25`, `P26`) VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            insert.setString(1, histResult.getIdPaciente());
            insert.setString(2, FechasUtils.fechaParaMysql(histResult.getFechaHistClinica()));
            insert.setString(3, FechasUtils.fechaParaMysql(histResult.getP1()));
            insert.setString(4, FechasUtils.fechaParaMysql(histResult.getP2()));
            insert.setString(5, FechasUtils.fechaParaMysql(histResult.getP3()));
            insert.setString(6, histResult.getP4());
            insert.setString(7, histResult.getP5());
            insert.setString(8, histResult.getP6());
            insert.setString(9, histResult.getP7());
            insert.setString(10, histResult.getP8());
            insert.setString(11, histResult.getP9());
            insert.setString(12, histResult.getP10());
            insert.setString(13, histResult.getP11());
            insert.setString(14, histResult.getP12());
            insert.setString(15, histResult.getP13());
            insert.setString(16, histResult.getP14());
            insert.setString(17, histResult.getP15());
            insert.setString(18, histResult.getP16());
            insert.setString(19, histResult.getP17());
            insert.setString(20, histResult.getP18());
            insert.setString(21, histResult.getE18());
            insert.setString(22, histResult.getP19());
            insert.setString(23, histResult.getE19());
            insert.setString(24, FechasUtils.fechaParaMysql(histResult.getD19()));
            insert.setString(25, histResult.getP20());
            insert.setString(26, histResult.getE20A());
            insert.setString(27, histResult.getE20B());
            insert.setString(28, histResult.getP21());
            insert.setString(29, histResult.getP22());
            insert.setString(30, histResult.getP23());
            insert.setString(31, histResult.getE23());
            insert.setString(32, histResult.getP24());
            insert.setString(33, histResult.getP25());
            insert.setString(34, histResult.getP26());

            resultado = insert.executeUpdate();

            return resultado;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            }
        }
        return resultado;
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
