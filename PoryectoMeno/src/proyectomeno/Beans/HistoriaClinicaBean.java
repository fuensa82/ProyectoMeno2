/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomeno.Beans;

/**
 *
 * @author vPalomo
 */
public class HistoriaClinicaBean {

    private String idLineaHistClinica;
    private String idPaciente;
    private String fechaAlta;
    private String P1;
    private String P2;
    private String P3;
    private String P4;
    private String P5;
    private String P6;
    private String P7;
    private String P8;

    public String getIdLineaHistClinica() {
        return idLineaHistClinica;
    }

    public void setIdLineaHistClinica(String idLineaHistClinica) {
        this.idLineaHistClinica = idLineaHistClinica;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getP1() {
        return P1;
    }

    public void setP1(String P1) {
        this.P1 = P1;
    }

    public String getP2() {
        return P2;
    }

    public void setP2(String P2) {
        this.P2 = P2;
    }

    public String getP3() {
        return P3;
    }

    public void setP3(String P3) {
        this.P3 = P3;
    }

    public String getP4() {
        return P4;
    }

    public void setP4(String P4) {
        this.P4 = P4;
    }

    public String getP5() {
        return P5;
    }

    public void setP5(String P5) {
        this.P5 = P5;
    }

    public String getP6() {
        return P6;
    }

    public void setP6(String P6) {
        this.P6 = P6;
    }

    public String getP7() {
        return P7;
    }

    public void setP7(String P7) {
        this.P7 = P7;
    }

    public String getP8() {
        return P8;
    }

    public void setP8(String P8) {
        this.P8 = P8;
    }

    public String getP7bd() {
        return P7.replace(',', '.');
    }
}
