/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomeno.Beans;

/**
 *
 * @author vPalomo
 */
public class PacienteBean {
    private String idPaciente;
    private String nombre;
    private String apellidos;
    private String fechaNac;
    private String idHistClinicaFija;

    public String toString(){
        return "nom: "+nombre+" ape: "+apellidos+" idPaciente:"+idPaciente+" idHistClinicaFija:"+idHistClinicaFija;
    }
    public String getIdHistClinicaFija() {
        return idHistClinicaFija;
    }

    public void setIdHistClinicaFija(String idHistClinicaFija) {
        this.idHistClinicaFija = idHistClinicaFija;
    }

    public PacienteBean() {
    }

    public PacienteBean(String nombre, String apellidos, String fechaNac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
}
