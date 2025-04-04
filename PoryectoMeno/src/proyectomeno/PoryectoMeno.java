/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectomeno;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import proyectomeno.Beans.HistoriaClinicaBean;
import proyectomeno.Beans.HistoriaClinicaFamiliarBean;
import proyectomeno.Beans.HistoriaClinicaFijaBean;
import proyectomeno.Beans.PacienteBean;
import proyectomeno.Gestores.GestionHistoriaClinicaBD;
import proyectomeno.Gestores.GestionPacientesBD;
import proyectomeno.utils.FechasUtils;

/**
 *
 * @author vPalomo
 */
public class PoryectoMeno extends javax.swing.JFrame {

    private static String NUEVO = "NUEVO";
    private static String MTTO = "MTTO";

    private ArrayList<PacienteBean> listaPacientes;
    private ArrayList<JComboBox> listaCombosHist;
    private HashMap<String, Object> mapaCombosHist;

    private ArrayList<JComboBox> listaCombosHistFam;
    private HashMap<String, Object> mapaCombosHistFamDD;
    private ArrayList<JTextField> listaJTextField;
    private HashMap<String, Object> mapaJTextFieldDD;
    public String estado = NUEVO;
    private PacienteBean pacienteSel = new PacienteBean();

    /**
     * Creates new form PoryectoMeno
     */
    public PoryectoMeno() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PoryectoMeno.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();

        creaListaMapaHist();
        cargarTablaPacientes();
        ponListenerTablapacientes();
        ponListenerA(listaCombosHist);
    }

    /**
     *
     */
    private void ponListenerTablapacientes() {
        jTablePacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cargarNuevo();
                estado = MTTO;
                if (e.getClickCount() == 1) {
                    pacienteSel.setIdPaciente((String) jTablePacientes.getValueAt(jTablePacientes.getSelectedRow(), 0));
                    cargarDatosPersonalesPaciente(pacienteSel.getIdPaciente());
                    pacienteSel.setIdHistClinicaFija(cargarDatosHistClinicaFija(pacienteSel.getIdPaciente()));
                    cargarDatosHistClinicaFamilia(pacienteSel.getIdPaciente());
                    cargarDatosHistClinica(pacienteSel.getIdPaciente());
                    vaciarHistoriaClinica();
                    System.out.println("Se ha hecho un click");
                }
                if (e.getClickCount() == 2) {
                    System.out.println("Se ha hecho doble click");
                }
            }
        });
    }

    /**
     * Carga la tabla de datos del paciente
     *
     * @param idPaciente
     */
    private void cargarDatosHistClinica(String idPaciente) {
        ArrayList<HistoriaClinicaBean> listaHistClinic = GestionHistoriaClinicaBD.getHistoriaClinica(idPaciente);
        DefaultTableModel datosTabla = (DefaultTableModel) jTableHistoriaClinica.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            datosTabla.removeRow(i - 1);
        }
        for (HistoriaClinicaBean linea : listaHistClinic) {
            datosTabla.addRow(new Object[]{
                linea.getFechaAlta(),
                linea.getP1(),
                linea.getP2() + "/" + linea.getP3(),
                linea.getP4(),
                linea.getP5(),
                linea.getP6(),
                linea.getP7(),
                linea.getP8()
            });
        }
    }

    private void cargarDatosHistClinicaFamilia(String idPaciente) {
        HistoriaClinicaFamiliarBean histClinic = GestionHistoriaClinicaBD.getHistoriaClinicaFamiliar(idPaciente);
        marcaComboSiNo("P27", histClinic.getP27());
        jTextE27.setText(histClinic.getE27());
        marcaComboSiNo("P28", histClinic.getP28());
        jTextE28.setText(histClinic.getE28());
        marcaComboSiNo("P29", histClinic.getP29());
        jTextE29.setText(histClinic.getE29());
        marcaComboSiNo("P30", histClinic.getP30());
        jTextE30.setText(histClinic.getE30());
        jTextP31.setText(histClinic.getP31());
        marcaComboSiNo("P32", histClinic.getP32());
    }

    private String cargarDatosHistClinicaFija(String idPaciente) {
        HistoriaClinicaFijaBean histClinic = GestionHistoriaClinicaBD.getHistoriaClinicaFija(idPaciente);
        jTextP1a.setText(histClinic.getP1());
        jTextP1.setText(FechasUtils.getMeses(histClinic.getP1()));
        jTextP2a.setText(histClinic.getP2());
        jTextP2.setText(FechasUtils.getMeses(histClinic.getP2()));
        jTextP3.setText(histClinic.getP3());
        marcaComboSiNo("P4", histClinic.getP4());
        marcaComboSiNo("P5", histClinic.getP5());
        marcaComboSiNo("P6", histClinic.getP6());
        marcaComboSiNo("P7", histClinic.getP7());
        marcaComboSiNo("P8", histClinic.getP8());
        marcaComboSiNo("P9", histClinic.getP9());
        marcaComboSiNo("P10", histClinic.getP10());
        marcaComboSiNo("P11", histClinic.getP11());
        marcaComboSiNo("P12", histClinic.getP12());
        marcaComboSiNo("P13", histClinic.getP13());
        marcaComboSiNo("P14", histClinic.getP14());
        marcaComboSiNo("P15", histClinic.getP15());
        marcaComboSiNo("P16", histClinic.getP16());
        marcaComboSiNo("P17", histClinic.getP17());
        marcaComboSiNo("P18", histClinic.getP18());
        marcaComboSiNo("P19", histClinic.getP19());
        jTextE19.setText(histClinic.getE19());
        jTextD19.setText(histClinic.getD19());
        marcaComboSiNo("P20", histClinic.getP20());
        jTextE20A.setText(histClinic.getE20A());
        jTextE20B.setText(histClinic.getE20B());
        jTextP21.setText(histClinic.getP21());
        jTextP22.setText(histClinic.getP22());
        marcaComboSiNo("P23", histClinic.getP23());
        jTextE23.setText(histClinic.getE23());
        marcaComboSiNo("P24", histClinic.getP24());
        marcaComboSiNo("P25", histClinic.getP25());
        marcaComboSiNo("P26", histClinic.getP26());
        jTextE25.setText(histClinic.getE25());
        return histClinic.getIdHistClinicaFija();
    }

    private void cargarDatosPersonalesPaciente(String idPaciente) {
        PacienteBean datosPaciente = GestionPacientesBD.getPaciente(idPaciente);
        jTextNombre.setText(datosPaciente.getNombre());
        jTextApellidos.setText(datosPaciente.getApellidos());
        jTextFechaNac.setText(datosPaciente.getFechaNac());
        jTextEdad.setText(FechasUtils.getEdad(datosPaciente.getFechaNac()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTextNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextEdad = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jTextApellidos = new javax.swing.JTextField();
        jTextFechaNac = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePacientes = new javax.swing.JTable();
        jLabelLogo = new javax.swing.JLabel();
        jLabelTituloApp = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextP1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextP1a = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextP2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextP2a = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextP3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboP4 = new javax.swing.JComboBox<>();
        jComboP6 = new javax.swing.JComboBox<>();
        jComboP8 = new javax.swing.JComboBox<>();
        jComboP10 = new javax.swing.JComboBox<>();
        jComboP12 = new javax.swing.JComboBox<>();
        jComboP14 = new javax.swing.JComboBox<>();
        jComboP16 = new javax.swing.JComboBox<>();
        jComboP5 = new javax.swing.JComboBox<>();
        jComboP7 = new javax.swing.JComboBox<>();
        jComboP9 = new javax.swing.JComboBox<>();
        jComboP11 = new javax.swing.JComboBox<>();
        jComboP13 = new javax.swing.JComboBox<>();
        jComboP15 = new javax.swing.JComboBox<>();
        jComboP17 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jComboP18 = new javax.swing.JComboBox<>();
        jComboP19 = new javax.swing.JComboBox<>();
        jComboP20 = new javax.swing.JComboBox<>();
        jComboP23 = new javax.swing.JComboBox<>();
        jTextE18 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextE20A = new javax.swing.JTextField();
        jTextP21 = new javax.swing.JTextField();
        jTextP22 = new javax.swing.JTextField();
        jTextE23 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextE19 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextE20B = new javax.swing.JTextField();
        jTextD19 = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jComboP24 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jComboP25 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jComboP26 = new javax.swing.JComboBox<>();
        jTextE25 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jComboP27 = new javax.swing.JComboBox<>();
        jTextE27 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextP31 = new javax.swing.JTextField();
        jComboP28 = new javax.swing.JComboBox<>();
        jComboP29 = new javax.swing.JComboBox<>();
        jComboP30 = new javax.swing.JComboBox<>();
        jTextE28 = new javax.swing.JTextField();
        jTextE29 = new javax.swing.JTextField();
        jTextE30 = new javax.swing.JTextField();
        jComboP32 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextHCP2 = new javax.swing.JTextField();
        jTextHCP3 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jTextHCP5 = new javax.swing.JTextField();
        jTextHCP6 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jTextHCP8 = new javax.swing.JTextField();
        jLabelHCP5 = new javax.swing.JLabel();
        jLabelHCP6 = new javax.swing.JLabel();
        jLabelHCP7 = new javax.swing.JLabel();
        jLabelHCP8 = new javax.swing.JLabel();
        jTextHCP7 = new javax.swing.JFormattedTextField();
        jLabel52 = new javax.swing.JLabel();
        jTextHCP9 = new javax.swing.JTextField();
        jLabelHCP9 = new javax.swing.JLabel();
        jTextHCP4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHistoriaClinica = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextHCFecha = new javax.swing.JFormattedTextField();
        jTextHCP1 = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Paciente"));

        jLabel1.setText("Nombre");

        jLabel2.setText("Fecha nacimiento");

        jLabel3.setText("Edad");

        jTextEdad.setEditable(false);

        jLabel57.setText("Apellidos");

        jTextApellidos.setToolTipText("");

        try {
            jTextFechaNac.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jTextEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(jTextApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTablePacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "Apellidos", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePacientes);
        jTablePacientes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTablePacientes.getColumnModel().getColumnCount() > 0) {
            jTablePacientes.getColumnModel().getColumn(0).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(0).setPreferredWidth(25);
            jTablePacientes.getColumnModel().getColumn(1).setResizable(false);
            jTablePacientes.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectomeno/multimedia/LogoFarmacia2019x100.png"))); // NOI18N

        jLabelTituloApp.setFont(new java.awt.Font("Segoe Print", 1, 36)); // NOI18N
        jLabelTituloApp.setForeground(new java.awt.Color(255, 132, 0));
        jLabelTituloApp.setText("Consulta menopausia");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("1. ¿Cuanto tiempo ha pasado desde que empezaron los síntomas que le preocupan?");

        jTextP1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextP1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextP1FocusLost(evt);
            }
        });
        jTextP1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextP1InputMethodTextChanged(evt);
            }
        });
        jTextP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextP1ActionPerformed(evt);
            }
        });

        jLabel6.setText("en meses");

        jTextP1a.setEditable(false);

        jLabel7.setText("2. Meses con irregularidad en los ciclos");

        jTextP2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextP2FocusLost(evt);
            }
        });

        jLabel8.setText("en meses");

        jTextP2a.setEditable(false);

        jLabel9.setText("3. Fecha última regla");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "¿Tiene problemas con alguno de estos sistemas?"));

        jLabel4.setText("4. Gastrointestinal");

        jLabel10.setText("6. Nervioso");

        jLabel11.setText("8. Endocrino (glándulas)");

        jLabel12.setText("10. Oído/nariz/garganta");

        jLabel13.setText("12. Urinario");

        jLabel14.setText("14. Sangre/Linfa");

        jLabel15.setText("16. Cardiovascular");

        jLabel16.setText("5. Muscular/Óseo");

        jLabel17.setText("7. Alérgio/Inmunológico");

        jLabel18.setText("9. Respiratorio");

        jLabel19.setText("11. Integumentario (piel)");

        jLabel20.setText("13. Dolores de cabeza");

        jLabel21.setText("15. Presión sanguínea alta");

        jLabel22.setText("17. Ojos");

        jComboP4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP4ActionPerformed(evt);
            }
        });

        jComboP6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP6ActionPerformed(evt);
            }
        });

        jComboP8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP8ActionPerformed(evt);
            }
        });

        jComboP10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP10ActionPerformed(evt);
            }
        });

        jComboP12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP12ActionPerformed(evt);
            }
        });

        jComboP14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP14ActionPerformed(evt);
            }
        });

        jComboP16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP16ActionPerformed(evt);
            }
        });

        jComboP5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP5ActionPerformed(evt);
            }
        });

        jComboP7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP7ActionPerformed(evt);
            }
        });

        jComboP9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP9ActionPerformed(evt);
            }
        });

        jComboP11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP11ActionPerformed(evt);
            }
        });

        jComboP13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP13ActionPerformed(evt);
            }
        });

        jComboP15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP15ActionPerformed(evt);
            }
        });

        jComboP17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP17ActionPerformed(evt);
            }
        });

        jLabel24.setText("18. Mental");

        jLabel25.setText("19. Diabetes");

        jLabel26.setText("20. ¿Alergia a medicamentos?");

        jLabel27.setText("21. Otros problemas de salud");

        jLabel28.setText("22. Medicación actual");

        jLabel29.setText("23. Operaciones");

        jComboP18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP18ActionPerformed(evt);
            }
        });

        jComboP19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP19ActionPerformed(evt);
            }
        });

        jComboP20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP20ActionPerformed(evt);
            }
        });

        jComboP23.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP23ActionPerformed(evt);
            }
        });

        jLabel30.setText("Fecha diagnostico");

        jLabel39.setText("Tipo");

        jLabel42.setText("¿Reacciones?");

        try {
            jTextD19.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboP12, javax.swing.GroupLayout.Alignment.LEADING, 0, 43, Short.MAX_VALUE)
                    .addComponent(jComboP10, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                    .addComponent(jComboP8, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                    .addComponent(jComboP6, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                    .addComponent(jComboP14, 0, 1, Short.MAX_VALUE)
                    .addComponent(jComboP16, 0, 1, Short.MAX_VALUE)
                    .addComponent(jComboP4, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboP13, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jComboP11, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                    .addComponent(jComboP9, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jComboP7, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                    .addComponent(jComboP5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboP15, 0, 0, Short.MAX_VALUE)
                    .addComponent(jComboP17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboP20, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                    .addComponent(jComboP19, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                    .addComponent(jComboP18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextE19, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextD19, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextE18, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                    .addComponent(jTextE20A)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextE20B))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboP23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextE23, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                            .addComponent(jTextP22)
                            .addComponent(jTextP21, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel16)
                            .addComponent(jComboP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel17)
                            .addComponent(jComboP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel18)
                            .addComponent(jComboP8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel19)
                            .addComponent(jComboP10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel20)
                            .addComponent(jComboP12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel21)
                            .addComponent(jComboP14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel22)
                            .addComponent(jComboP16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboP7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboP9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboP18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextE18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboP19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel39)
                                    .addComponent(jTextE19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextD19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboP20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextE20A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboP11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)
                            .addComponent(jTextE20B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboP13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboP15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboP17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(jTextP21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(jTextP22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(jComboP23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextE23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Hábitos"));

        jLabel31.setText("Consumo de alcohol y/o drogas");

        jComboP24.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP24ActionPerformed(evt);
            }
        });

        jLabel32.setText("Practica algún deporte");

        jComboP25.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP25ActionPerformed(evt);
            }
        });

        jLabel33.setText("Dieta equilibrada");

        jComboP26.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboP24, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboP25, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextE25, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboP26, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jComboP24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jComboP25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jComboP26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextE25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jButton1.setText("Guardar cambios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Descartar cambios");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Historia clínica familiar"));

        jLabel23.setText("Presión sanguínea alta");

        jComboP27.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP27ActionPerformed(evt);
            }
        });

        jLabel34.setText("Diabetes");

        jLabel35.setText("Obesidad");

        jLabel36.setText("Cancer");

        jLabel37.setText("Edad menopausia madre");

        jLabel38.setText("Caída o fractura de la madre o abuela");

        jComboP28.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP28ActionPerformed(evt);
            }
        });

        jComboP29.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP29ActionPerformed(evt);
            }
        });

        jComboP30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP30ActionPerformed(evt);
            }
        });

        jComboP32.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Sí" }));
        jComboP32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboP32ActionPerformed(evt);
            }
        });

        jLabel40.setForeground(new java.awt.Color(153, 153, 153));
        jLabel40.setText("Parentesco");

        jLabel41.setForeground(new java.awt.Color(153, 153, 153));
        jLabel41.setText("Parentesco");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jComboP28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextE28))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jComboP27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextE27, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel40)))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboP30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jComboP29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextE29, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jTextE30))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboP32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(76, 76, 76)
                                .addComponent(jTextP31, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel41)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboP27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextE27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel37)
                    .addComponent(jTextP31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboP29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextE29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36)
                    .addComponent(jLabel38)
                    .addComponent(jComboP28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboP30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextE28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextE30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboP32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jButton4.setText("Nuevo paciente");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 624, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextP2)
                            .addComponent(jTextP1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jTextP3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextP1a, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextP2a, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextP1a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextP2a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Historia clínica", jPanel1);

        jLabel43.setText("Peso:");

        jLabel44.setText("Presión arteial:");

        jTextHCP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextHCP3ActionPerformed(evt);
            }
        });

        jLabel45.setText("Perímetro cintura:");

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Analítica reciente"));

        jLabel46.setText("Triglicéridos en plasma");

        jLabel47.setText("Glucemia");

        jTextHCP5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextHCP5FocusLost(evt);
            }
        });

        jTextHCP6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextHCP6FocusLost(evt);
            }
        });

        jLabel48.setText("FSH");

        jLabel49.setText("Estradiol");

        jTextHCP8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextHCP8FocusLost(evt);
            }
        });
        jTextHCP8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextHCP8ActionPerformed(evt);
            }
        });

        jLabelHCP5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jLabelHCP6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jLabelHCP7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jLabelHCP8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jTextHCP7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0"))));
        jTextHCP7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextHCP7FocusLost(evt);
            }
        });

        jLabel52.setText("HDL");

        jTextHCP9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextHCP9FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextHCP8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHCP8, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextHCP5)
                            .addComponent(jTextHCP6, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHCP5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelHCP6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextHCP7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHCP7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextHCP9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHCP9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHCP5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46)
                        .addComponent(jTextHCP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHCP6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextHCP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextHCP9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelHCP9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(jTextHCP7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelHCP7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHCP8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextHCP8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel49)))
                .addContainerGap())
        );

        jTextHCP4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextHCP4FocusLost(evt);
            }
        });

        jTableHistoriaClinica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Peso", "Presión arterial", "Cintura", "Triglicéridos", "Glucemia", "FSH", "Estradiol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableHistoriaClinica);
        if (jTableHistoriaClinica.getColumnModel().getColumnCount() > 0) {
            jTableHistoriaClinica.getColumnModel().getColumn(0).setResizable(false);
            jTableHistoriaClinica.getColumnModel().getColumn(1).setResizable(false);
            jTableHistoriaClinica.getColumnModel().getColumn(2).setResizable(false);
            jTableHistoriaClinica.getColumnModel().getColumn(3).setResizable(false);
            jTableHistoriaClinica.getColumnModel().getColumn(4).setResizable(false);
            jTableHistoriaClinica.getColumnModel().getColumn(5).setResizable(false);
            jTableHistoriaClinica.getColumnModel().getColumn(6).setResizable(false);
            jTableHistoriaClinica.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel50.setText(" ");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel51.setText(" ");

        jLabel56.setText("Fecha datos:");

        try {
            jTextHCFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton3.setText("Guardar datos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel44)
                                .addComponent(jLabel45)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel56))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jTextHCP2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextHCP3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addComponent(jTextHCP4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                .addComponent(jTextHCFecha)
                                .addComponent(jTextHCP1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jTextHCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(jTextHCP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(jTextHCP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextHCP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel45)
                                .addComponent(jTextHCP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Toma de datos", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1034, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel7);

        jButton5.setText("Nuevo Paciente");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelTituloApp))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabelTituloApp)))
                        .addGap(12, 12, 12)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextP1ActionPerformed

    }//GEN-LAST:event_jTextP1ActionPerformed

    private void jTextP1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextP1FocusLost
        jTextP1a.setText(FechasUtils.sumaMesesHoy(Integer.parseInt(jTextP1.getText())));
    }//GEN-LAST:event_jTextP1FocusLost

    private void jTextP2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextP2FocusLost
        jTextP2a.setText(FechasUtils.sumaMesesHoy(Integer.parseInt(jTextP2.getText())));
    }//GEN-LAST:event_jTextP2FocusLost

    private void jComboP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP4ActionPerformed

    private void jComboP6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP6ActionPerformed

    private void jComboP8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP8ActionPerformed

    private void jComboP10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP10ActionPerformed

    private void jComboP12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP12ActionPerformed

    private void jComboP14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP14ActionPerformed

    private void jComboP16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP16ActionPerformed

    private void jComboP5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP5ActionPerformed

    private void jComboP7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP7ActionPerformed

    private void jComboP9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP9ActionPerformed

    private void jComboP11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP11ActionPerformed

    private void jComboP13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP13ActionPerformed

    private void jComboP15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP15ActionPerformed

    private void jComboP17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP17ActionPerformed

    private void jComboP18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP18ActionPerformed

    private void jComboP19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP19ActionPerformed

    private void jComboP20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP20ActionPerformed

    private void jComboP23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP23ActionPerformed

    private void jComboP24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP24ActionPerformed

    private void jComboP25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP25ActionPerformed

    private void jComboP26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP26ActionPerformed

    private void jComboP32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP32ActionPerformed

    private void jComboP30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP30ActionPerformed

    private void jComboP29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP29ActionPerformed

    private void jComboP28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP28ActionPerformed

    private void jComboP27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboP27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboP27ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardarDatos();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextP1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextP1InputMethodTextChanged
        System.out.println("Cambioooooooooooooo*************************");        // TODO add your handling code here:
    }//GEN-LAST:event_jTextP1InputMethodTextChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        HistoriaClinicaBean hist = new HistoriaClinicaBean();
        String idPaciente = (String) jTablePacientes.getValueAt(jTablePacientes.getSelectedRow(), 0);
        hist.setIdPaciente(idPaciente);
        hist.setP1(jTextHCP1.getText());
        hist.setP2(jTextHCP2.getText());
        hist.setP3(jTextHCP3.getText());
        hist.setP4(jTextHCP4.getText());
        hist.setP5(jTextHCP5.getText());
        hist.setP6(jTextHCP6.getText());
        hist.setP7(jTextHCP7.getText());
        hist.setP8(jTextHCP8.getText());
        hist.setIdPaciente(idPaciente);
        GestionHistoriaClinicaBD.guardarHistoria(hist);
        cargarDatosHistClinica(idPaciente);
        vaciarHistoriaClinica();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextHCP4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextHCP4FocusLost
        try {
            int i = Integer.parseInt(jTextHCP4.getText());
            if (i >= 88) {
                jLabel51.setText("RIESGO SINDROME METABOLICO");
            } else {
                jLabel51.setText("OK");
            }
        } catch (Exception e) {
            jLabel51.setText("Debe introducir un valor numérico");
        }
    }//GEN-LAST:event_jTextHCP4FocusLost

    private void jTextHCP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextHCP3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextHCP3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextHCP5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextHCP5FocusLost
        try {
            int i = Integer.parseInt(jTextHCP5.getText());
            if (i >= 150) {
                jLabelHCP5.setText("RIESGO SINDROME METABÓLICO");
            } else {
                jLabelHCP5.setText("");
            }
        } catch (Exception e) {
            jLabelHCP5.setText("Debe ser valor numérico");
        }
    }//GEN-LAST:event_jTextHCP5FocusLost

    private void jTextHCP6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextHCP6FocusLost
        try {
            int i = Integer.parseInt(jTextHCP6.getText());
            if (i >= 100) {
                jLabelHCP6.setText("RIESGO SINDROME METABÓLICO");
            } else {
                jLabelHCP6.setText("");
            }
        } catch (Exception e) {
            jLabelHCP6.setText("Debe ser valor numérico");
        }
    }//GEN-LAST:event_jTextHCP6FocusLost

    private void jTextHCP8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextHCP8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextHCP8ActionPerformed

    private void jTextHCP7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextHCP7FocusLost
        try {
            
            float i = Float.parseFloat(jTextHCP7.getText().replaceFirst(",", "."));
            if (i > 25.8) {
                jLabelHCP7.setText("Post menopausia");
            } else {
                jLabelHCP7.setText("Pre menopausia");
            }
        } catch (Exception e) {
            jLabelHCP7.setText("Debe ser valor numérico");
        }
    }//GEN-LAST:event_jTextHCP7FocusLost

    private void jTextHCP9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextHCP9FocusLost
        try {
            int i = Integer.parseInt(jTextHCP9.getText());
            if (i > 50) {
                jLabelHCP9.setText("");
            } else {
                jLabelHCP9.setText("VALORES BAJOS -> RIESGO S. M.");        // TODO add your handling code here:
            }
        } catch (Exception e) {
            jLabelHCP9.setText("Debe ser valor numérico");
        }
    }//GEN-LAST:event_jTextHCP9FocusLost

    private void jTextHCP8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextHCP8FocusLost
        try {
            int i = Integer.parseInt(jTextHCP8.getText());
            if (i >= 30) {
                jLabelHCP8.setText("");
            } else {
                jLabelHCP8.setText("Menopausia");
            }
        } catch (Exception e) {
            jLabelHCP8.setText("Debe ser valor numérico");
        }
    }//GEN-LAST:event_jTextHCP8FocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        estado = NUEVO;
        cargarNuevo();
        jTablePacientes.clearSelection();
    }//GEN-LAST:event_jButton5ActionPerformed
    public void vaciarHistoriaClinica() {
        jTextHCP1.setText("");
        jTextHCP2.setText("");
        jTextHCP3.setText("");
        jTextHCP4.setText("");
        jTextHCP5.setText("");
        jTextHCP6.setText("");
        jTextHCP7.setText("");
        jTextHCP8.setText("");
        jLabel50.setText("");
        jLabel51.setText("");
        jLabelHCP5.setText("");
        jLabelHCP6.setText("");
        jLabelHCP7.setText("");
        jLabelHCP8.setText("");
        jLabelHCP9.setText("");
        jTextHCFecha.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PoryectoMeno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PoryectoMeno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PoryectoMeno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PoryectoMeno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PoryectoMeno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboP10;
    private javax.swing.JComboBox<String> jComboP11;
    private javax.swing.JComboBox<String> jComboP12;
    private javax.swing.JComboBox<String> jComboP13;
    private javax.swing.JComboBox<String> jComboP14;
    private javax.swing.JComboBox<String> jComboP15;
    private javax.swing.JComboBox<String> jComboP16;
    private javax.swing.JComboBox<String> jComboP17;
    private javax.swing.JComboBox<String> jComboP18;
    private javax.swing.JComboBox<String> jComboP19;
    private javax.swing.JComboBox<String> jComboP20;
    private javax.swing.JComboBox<String> jComboP23;
    private javax.swing.JComboBox<String> jComboP24;
    private javax.swing.JComboBox<String> jComboP25;
    private javax.swing.JComboBox<String> jComboP26;
    private javax.swing.JComboBox<String> jComboP27;
    private javax.swing.JComboBox<String> jComboP28;
    private javax.swing.JComboBox<String> jComboP29;
    private javax.swing.JComboBox<String> jComboP30;
    private javax.swing.JComboBox<String> jComboP32;
    private javax.swing.JComboBox<String> jComboP4;
    private javax.swing.JComboBox<String> jComboP5;
    private javax.swing.JComboBox<String> jComboP6;
    private javax.swing.JComboBox<String> jComboP7;
    private javax.swing.JComboBox<String> jComboP8;
    private javax.swing.JComboBox<String> jComboP9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHCP5;
    private javax.swing.JLabel jLabelHCP6;
    private javax.swing.JLabel jLabelHCP7;
    private javax.swing.JLabel jLabelHCP8;
    private javax.swing.JLabel jLabelHCP9;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTituloApp;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableHistoriaClinica;
    private javax.swing.JTable jTablePacientes;
    private javax.swing.JTextField jTextApellidos;
    private javax.swing.JFormattedTextField jTextD19;
    private javax.swing.JTextField jTextE18;
    private javax.swing.JTextField jTextE19;
    private javax.swing.JTextField jTextE20A;
    private javax.swing.JTextField jTextE20B;
    private javax.swing.JTextField jTextE23;
    private javax.swing.JTextField jTextE25;
    private javax.swing.JTextField jTextE27;
    private javax.swing.JTextField jTextE28;
    private javax.swing.JTextField jTextE29;
    private javax.swing.JTextField jTextE30;
    private javax.swing.JTextField jTextEdad;
    private javax.swing.JFormattedTextField jTextFechaNac;
    private javax.swing.JFormattedTextField jTextHCFecha;
    private javax.swing.JFormattedTextField jTextHCP1;
    private javax.swing.JTextField jTextHCP2;
    private javax.swing.JTextField jTextHCP3;
    private javax.swing.JTextField jTextHCP4;
    private javax.swing.JTextField jTextHCP5;
    private javax.swing.JTextField jTextHCP6;
    private javax.swing.JFormattedTextField jTextHCP7;
    private javax.swing.JTextField jTextHCP8;
    private javax.swing.JTextField jTextHCP9;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextP1;
    private javax.swing.JTextField jTextP1a;
    private javax.swing.JTextField jTextP2;
    private javax.swing.JTextField jTextP21;
    private javax.swing.JTextField jTextP22;
    private javax.swing.JTextField jTextP2a;
    private javax.swing.JTextField jTextP3;
    private javax.swing.JTextField jTextP31;
    // End of variables declaration//GEN-END:variables

    private void cargarTablaPacientes() {
        listaPacientes = GestionPacientesBD.getListaPacientes();
        DefaultTableModel datosTabla = (DefaultTableModel) jTablePacientes.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            datosTabla.removeRow(i - 1);
        }
        for (PacienteBean paciente : listaPacientes) {
            datosTabla.addRow(new Object[]{
                paciente.getIdPaciente(),
                paciente.getApellidos(),
                paciente.getNombre()
            });
        }
        //labelTotal.setText("" + listaPersonas.size());
    }

    private void creaListaMapaHist() {
        listaCombosHist = new ArrayList<>();
        mapaCombosHist = new HashMap<>();
        listaJTextField = new ArrayList<>();

        listaCombosHist.add(jComboP4);
        listaCombosHist.add(jComboP5);
        listaCombosHist.add(jComboP6);
        listaCombosHist.add(jComboP7);
        listaCombosHist.add(jComboP8);
        listaCombosHist.add(jComboP9);
        listaCombosHist.add(jComboP10);
        listaCombosHist.add(jComboP11);
        listaCombosHist.add(jComboP12);
        listaCombosHist.add(jComboP13);
        listaCombosHist.add(jComboP14);
        listaCombosHist.add(jComboP15);
        listaCombosHist.add(jComboP16);
        listaCombosHist.add(jComboP17);
        listaCombosHist.add(jComboP18);
        listaCombosHist.add(jComboP19);
        listaCombosHist.add(jComboP20);
        listaCombosHist.add(jComboP23);
        listaCombosHist.add(jComboP24);
        listaCombosHist.add(jComboP25);
        listaCombosHist.add(jComboP26);
        listaCombosHist.add(jComboP27);
        listaCombosHist.add(jComboP28);
        listaCombosHist.add(jComboP29);
        listaCombosHist.add(jComboP30);
        listaCombosHist.add(jComboP32);

        listaJTextField.add(jTextP1);
        listaJTextField.add(jTextP1a);
        listaJTextField.add(jTextP2);
        listaJTextField.add(jTextP21);
        listaJTextField.add(jTextP22);
        listaJTextField.add(jTextP2a);
        listaJTextField.add(jTextP3);
        listaJTextField.add(jTextP31);
        listaJTextField.add(jTextE18);
        listaJTextField.add(jTextE19);
        listaJTextField.add(jTextD19);
        listaJTextField.add(jTextE20A);
        listaJTextField.add(jTextE20B);
        listaJTextField.add(jTextP21);
        listaJTextField.add(jTextP22);
        listaJTextField.add(jTextE23);
        listaJTextField.add(jTextE25);
        listaJTextField.add(jTextNombre);
        listaJTextField.add(jTextFechaNac);
        listaJTextField.add(jTextEdad);
        listaJTextField.add(jTextE27);
        listaJTextField.add(jTextE28);
        listaJTextField.add(jTextE29);
        listaJTextField.add(jTextE30);
        listaJTextField.add(jTextHCP1);
        listaJTextField.add(jTextHCP2);
        listaJTextField.add(jTextHCP3);
        listaJTextField.add(jTextHCP4);
        listaJTextField.add(jTextHCP5);
        listaJTextField.add(jTextHCP6);
        listaJTextField.add(jTextHCP7);
        listaJTextField.add(jTextHCP8);
        listaJTextField.add(jTextHCP9);
        listaJTextField.add(jTextHCFecha);

        mapaCombosHist.put("P4", jComboP4);
        mapaCombosHist.put("P5", jComboP5);
        mapaCombosHist.put("P6", jComboP6);
        mapaCombosHist.put("P7", jComboP7);
        mapaCombosHist.put("P8", jComboP8);
        mapaCombosHist.put("P9", jComboP9);
        mapaCombosHist.put("P10", jComboP10);
        mapaCombosHist.put("P11", jComboP11);
        mapaCombosHist.put("P12", jComboP12);
        mapaCombosHist.put("P13", jComboP13);
        mapaCombosHist.put("P14", jComboP14);
        mapaCombosHist.put("P15", jComboP15);
        mapaCombosHist.put("P16", jComboP16);
        mapaCombosHist.put("P17", jComboP17);
        mapaCombosHist.put("P18", jComboP18);
        mapaCombosHist.put("P19", jComboP19);
        mapaCombosHist.put("P20", jComboP20);
        mapaCombosHist.put("P23", jComboP23);
        mapaCombosHist.put("P24", jComboP24);
        mapaCombosHist.put("P25", jComboP25);
        mapaCombosHist.put("P26", jComboP26);
        mapaCombosHist.put("P27", jComboP27);
        mapaCombosHist.put("P28", jComboP28);
        mapaCombosHist.put("P29", jComboP29);
        mapaCombosHist.put("P30", jComboP30);
        mapaCombosHist.put("P32", jComboP32);
    }

    /**
     * Marca el combo almacenado en el HashMap de historia clinica fija
     * mapaCombosHist(de la llista de combos) y le marca con el valor que
     * pasamos como parámetro
     *
     * @param nombre Nombre del combo en el HashMap
     * @param valor Valor que se quiere marcar
     */
    private void marcaComboSiNo(String nombre, String valor) {
        // filtroViaje = comboViaje.getModel().getElementAt(comboViaje.getSelectedIndex()).split(" - ")[0];
        JComboBox<String> combo = (JComboBox<String>) mapaCombosHist.get(nombre);
        if ("S".equalsIgnoreCase(valor)) {
            combo.setSelectedIndex(1);
        } else {
            combo.setSelectedIndex(0);
        }
    }

    private void ponListenerA(ArrayList<JComboBox> listaCombos) {
        for (JComboBox combo : listaCombos) {
            combo.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String seleccion = (String) e.getItem();
                        System.out.println("Selección cambiada a: " + seleccion);
                    }
                }
            });
        }

        JComboBox name = listaCombosHist.get(0);
        name.addActionListener(name);
    }

    private void cargarNuevo() {
        estado = NUEVO;
        for (JTextField jText : listaJTextField) {
            jText.setText("");
        }
        for (JComboBox jCombo : listaCombosHist) {
            jCombo.setSelectedIndex(0);
        }
        /*for (JComboBox jCombo : listaCombosHistFam) {
            jCombo.setSelectedIndex(0);
        }*/
    }
    
    
    private void guardarDatos() {
        //HistoriaClinicaBean histClin=new HistoriaClinicaBean();
        HistoriaClinicaFijaBean histClinFija = new HistoriaClinicaFijaBean();
        if (estado.equals(NUEVO)) {
            PacienteBean paciente = new PacienteBean(jTextNombre.getText(), jTextApellidos.getText(), jTextFechaNac.getText());
            int idPacienteNuevo = GestionPacientesBD.nuevoPaciente(paciente);
            histClinFija.setP1(jTextP1a.getText());
            histClinFija.setP2(jTextP2a.getText());
            histClinFija.setP3(jTextP3.getText());
            histClinFija.setP4(jComboP4.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP5(jComboP5.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP6(jComboP6.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP7(jComboP7.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP8(jComboP8.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP9(jComboP9.getSelectedIndex() == 0 ? "N" : "S");

            histClinFija.setP10(jComboP10.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP11(jComboP11.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP12(jComboP12.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP13(jComboP13.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP14(jComboP14.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP15(jComboP15.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP16(jComboP16.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP17(jComboP17.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP18(jComboP18.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP19(jComboP19.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP20(jComboP20.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP23(jComboP23.getSelectedIndex() == 0 ? "N" : "S");

            histClinFija.setE18(jTextE18.getText());
            histClinFija.setE19(jTextE19.getText());
            histClinFija.setD19(jTextD19.getText());
            histClinFija.setE20A(jTextE20A.getText());
            histClinFija.setE20B(jTextE20B.getText());
            
            histClinFija.setP21(jTextP21.getText());
            histClinFija.setP22(jTextP22.getText());
            
            histClinFija.setE23(jTextE23.getText());
            histClinFija.setE25(jTextE25.getText());

            histClinFija.setIdPaciente("" + idPacienteNuevo);
            GestionHistoriaClinicaBD.guardarNuevaHistoriaClinicaFija(histClinFija);

        } else if (estado.equals(MTTO)) {
            histClinFija.setP1(jTextP1a.getText());
            histClinFija.setP2(jTextP2a.getText());
            histClinFija.setP3(jTextP3.getText());
            histClinFija.setP4(jComboP4.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP5(jComboP5.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP6(jComboP6.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP7(jComboP7.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP8(jComboP8.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP9(jComboP9.getSelectedIndex() == 0 ? "N" : "S");

            histClinFija.setP10(jComboP10.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP11(jComboP11.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP12(jComboP12.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP13(jComboP13.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP14(jComboP14.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP15(jComboP15.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP16(jComboP16.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP17(jComboP17.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP18(jComboP18.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP19(jComboP19.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP20(jComboP20.getSelectedIndex() == 0 ? "N" : "S");
            histClinFija.setP23(jComboP23.getSelectedIndex() == 0 ? "N" : "S");

            histClinFija.setE18(jTextE18.getText());
            histClinFija.setE19(jTextE19.getText());
            histClinFija.setD19(jTextD19.getText());
            histClinFija.setE20A(jTextE20A.getText());
            histClinFija.setE20B(jTextE20B.getText());
            histClinFija.setE23(jTextE23.getText());
            histClinFija.setE25(jTextE25.getText());

            //jTextNombre.getText(), jTextApellidos.getText(), jTextFechaNac.getText()
            pacienteSel.setApellidos(jTextApellidos.getText());
            pacienteSel.setNombre(jTextNombre.getText());
            pacienteSel.setFechaNac(jTextFechaNac.getText());
            GestionPacientesBD.modificarPaciente(pacienteSel);

            //histClinFija.setIdPaciente("" + idPacienteSel);
            System.out.println("Se va a guardar " + pacienteSel);
            //*** hay que hacer un método con un update ++++ tambien falta pasar el is de la historia clinica fija
            System.out.println(pacienteSel);
            GestionHistoriaClinicaBD.guardarHistoriaClinicaFija(histClinFija, pacienteSel);
        }
    }

}
