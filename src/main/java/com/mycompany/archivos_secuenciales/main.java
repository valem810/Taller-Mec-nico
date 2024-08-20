package com.mycompany.archivos_secuenciales;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class main extends javax.swing.JFrame {

    DataOutputStream write;
    DataInputStream read;
    String path = "C:\\Proyecto\\vc.txt";

    Files f;
    contacto cto;
    contacto admin;

    piezas_File pf;
    piezas pi;

    reparaciones_File rf;
    reparaciones rep;

    Vehiculos_Files v;
    Vehiculos vcs;

    VC_File vcf;
    vehiculo_cliente vc;

    Cliente_File fc;
    cliente c;

    String IdUs;

    boolean ban = false;
    boolean ban_vehiculos = false;
    boolean ban_reparaciones = false;
    boolean ban_piezas = false;

    public main() throws IOException {
        initComponents();
        f = new Files();
        pf = new piezas_File();
        rf = new reparaciones_File();
        fc = new Cliente_File();
        v = new Vehiculos_Files();
        vcf = new VC_File();

        admin = new contacto();
        admin.setId(0);
        admin.setNombre("Admin");
        admin.setPaterno("Nistra");
        admin.setMaterno("Dor");
        admin.setUsername("super");
        admin.setTelefono("3359525148");
        admin.setDireccion("plata");
        admin.setPassword("123");
        admin.setPerfil("Admin");

        btn_V_Guardar.setEnabled(false);
        btn_V_Nuevo.setEnabled(true);
        btn_V_Editar.setEnabled(false);
        btn_V_Eliminar.setEnabled(false);
        btn_V_Cancelar.setEnabled(false);

        btn_C_Guardar.setEnabled(false);
        btn_C_Nuevo.setEnabled(true);
        btn_C_Editar.setEnabled(false);
        btn_C_Eliminar.setEnabled(false);
        btn_C_Cancelar.setEnabled(false);

        btn_R_Guardar.setEnabled(false);
        btn_R_Nuevo.setEnabled(true);
        btn_R_Editar.setEnabled(false);
        btn_R_Eliminar.setEnabled(false);
        btn_R_Cancelar.setEnabled(false);

        btn_P_Guardar.setEnabled(false);
        btn_P_Nuevo.setEnabled(true);
        btn_P_Editar.setEnabled(false);
        btn_P_Eliminar.setEnabled(false);
        btn_P_Cancelar.setEnabled(false);

        try {
            if (f.BuscarContacto(admin) == null) {
                f.Guardar(admin);
            }
        } catch (FileNotFoundException ex) {

        }
        tpane.setEnabledAt(1, false);
        tpane.setEnabledAt(2, false);
        tpane.setEnabledAt(3, false);
        tpane.setEnabledAt(4, false);
        tpane.setEnabledAt(5, false);

    }

    public boolean ValidaNum(String dato) {
        return dato.matches("[0-9]*");
    }

    public void cb_vehiculos() {
        cb_V_SeleccioneCliente.removeAllItems();
        cb_V_SeleccioneCliente.addItem("Seleccione");
        String us = "", cl = "";
        try {
            read = new DataInputStream(new FileInputStream(path));

            while (true) {
                us = read.readUTF();
                cl = read.readUTF();
                if (us.equals(IdUs) || "0".equals(IdUs)) {
                    cb_V_SeleccioneCliente.addItem(cl);
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
        }
        try {
            read.close();
        } catch (IOException ex) {

        }
    }

    public void cb_R_vehiculos() {
        String vl = "C:\\Proyecto\\vehiculos.txt";
        cb_R_IdVehiculo.removeAllItems();
        cb_R_IdVehiculo.addItem("Seleccione");
        int id;
        String item = "", cl = "", mat = "", marc = "", mo = "", fe = "";
        try {
            read = new DataInputStream(new FileInputStream(vl));
            while (true) {
                cl = read.readUTF();
                id = read.readInt();
                mat = read.readUTF();
                marc = read.readUTF();
                mo = read.readUTF();
                fe = read.readUTF();

                item = String.valueOf(id);
                cb_R_IdVehiculo.addItem(item);
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
        }
        try {
            read.close();
        } catch (IOException ex) {

        }
    }

    public void cb_R_Pieza() {
        String pz = "C:\\Proyecto\\piezas.txt";
        cb_R_IdPieza.removeAllItems();
        cb_R_IdPieza.addItem("Seleccione");
        int id = 0, stock = 0;
        String item = "", des = "";
        try {
            read = new DataInputStream(new FileInputStream(pz));
            while (true) {
                id = read.readInt();
                des = read.readUTF();
                stock = read.readInt();

                item = String.valueOf(id);
                cb_R_IdPieza.addItem(item);
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
        }
        try {
            read.close();
        } catch (IOException ex) {

        }
    }

    public boolean txt_R_Control() {
        boolean ban = false;
        try {
            pi = new piezas();
            pi.SetPiz(Integer.parseInt(cb_R_IdPieza.getSelectedItem().toString()));
            pi = pf.BuscarPiezas(pi);
            if (Integer.parseInt(txt_R_ControlPiezas.getText()) <= pi.getStock() && Integer.parseInt(txt_R_ControlPiezas.getText()) != 0) {
                ban = true;
            }

        } catch (FileNotFoundException ex) {
        }
        return ban;
    }

    public void Habilitar() {
        txtNombre.setEditable(true);
        txtPaterno.setEditable(true);
        txtMaterno.setEditable(true);
        txtTelefono.setEditable(true);
        txtUsername.setEditable(true);
        cbPerfil.setEditable(true);
        txtDireccion.setEditable(true);
        txtPsw.setEditable(true);
    }

    public void Deshabilitar() {
        txtNombre.setEditable(false);
        txtPaterno.setEditable(false);
        txtMaterno.setEditable(false);
        txtTelefono.setEditable(false);
        txtUsername.setEditable(false);
        cbPerfil.setEditable(false);
        txtDireccion.setEditable(false);
        txtPsw.setEditable(false);

        txtNombre.setText("");
        txtTelefono.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtUsername.setText("");
        cbPerfil.setSelectedItem("");
        txtDireccion.setText("");

    }

    public void Vehiculos_Habilitar() {
        txt_V_Matricula.setEditable(true);
        txt_V_Marca.setEditable(true);
        txt_V_Modelo.setEditable(true);
        jdt_V_Fecha.setEnabled(true);

    }

    public void Reparaciones_Habilitar() {
        txt_R_Falla.setEditable(true);
        txt_R_ControlPiezas.setEditable(true);
        jdt_E_Fecha.setEnabled(true);
        jdt_S_Fecha.setEnabled(true);
    }

    public void Piezas_Habilitar() {
        txt_P_Descripcion.setEditable(true);
        txt_P_Stock.setEditable(true);
    }

    public void Vehiculos_Deshabilitar() {
        txt_V_Matricula.setEditable(false);
        txt_V_Marca.setEditable(false);
        txt_V_Modelo.setEditable(false);
        jdt_V_Fecha.setEnabled(false);

        jdt_V_Fecha.setDate(null);
        jdt_V_Fecha.cleanup();
        
        txt_V_IdVehiculo.setText("");
        txt_V_Matricula.setText("");
        txt_V_Marca.setText("");
        txt_V_Modelo.setText("");
    }

    public void Reparaciones_Deshabilitar() {
        txt_R_Falla.setEditable(false);
        jdt_E_Fecha.setEnabled(false);
        jdt_S_Fecha.setEnabled(false);

        jdt_E_Fecha.setDate(null);
        jdt_E_Fecha.cleanup();

        jdt_S_Fecha.setDate(null);
        jdt_S_Fecha.cleanup();

        int maxID = rf.getMax();
        txt_R_IdReparacion.setText(String.valueOf(maxID));

        txt_R_Falla.setText("");
        txt_R_IdReparacion.setText("");
        txt_R_ControlPiezas.setText("");
    }

    public void Piezas_Deshabilitar() {
        txt_P_Descripcion.setEditable(false);
        txt_P_Stock.setEditable(false);
        
        txt_P_IdPieza.setText("");
        txt_P_Descripcion.setText("");
        txt_P_Stock.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        tpane = new javax.swing.JTabbedPane();
        pnlLogin = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnAutentificar = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        pnlUsuarios = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaterno = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        cbPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtPsw = new javax.swing.JTextField();
        btnRemover = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPaterno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblPassword1 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        pnlClientes = new javax.swing.JPanel();
        lbl_C_IdUsuario = new javax.swing.JLabel();
        lbl_C_IdCliente = new javax.swing.JLabel();
        lbl_C_Id = new javax.swing.JLabel();
        lbl_C_Nombre = new javax.swing.JLabel();
        lbl_C_ApellidoPaterno = new javax.swing.JLabel();
        lbl_C_ApellidoMaterno = new javax.swing.JLabel();
        txt_C_Buscar = new javax.swing.JTextField();
        txt_C_IdCliente = new javax.swing.JTextField();
        txt_C_Nombre = new javax.swing.JTextField();
        txt_C_ApellidoPaterno = new javax.swing.JTextField();
        txt_C_ApellidoMaterno = new javax.swing.JTextField();
        btn_C_Buscar = new javax.swing.JButton();
        btn_C_Nuevo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btn_C_Guardar = new javax.swing.JButton();
        btn_C_Cancelar = new javax.swing.JButton();
        btn_C_Editar = new javax.swing.JButton();
        btn_C_Eliminar = new javax.swing.JButton();
        btn_C_Salir = new javax.swing.JButton();
        txt_C_IdUsuario = new javax.swing.JTextField();
        pnlVehiculos = new javax.swing.JPanel();
        lbl_V_SeleccioneCliente = new javax.swing.JLabel();
        lbl_V_IdVehiculo = new javax.swing.JLabel();
        lbl_V_Matricula = new javax.swing.JLabel();
        lbl_V_Marca = new javax.swing.JLabel();
        lbl_V_Modelo = new javax.swing.JLabel();
        lbl_V_Id = new javax.swing.JLabel();
        lbl_V_Fecha = new javax.swing.JLabel();
        txt_V_Buscar = new javax.swing.JTextField();
        txt_V_IdVehiculo = new javax.swing.JTextField();
        txt_V_Matricula = new javax.swing.JTextField();
        txt_V_Marca = new javax.swing.JTextField();
        txt_V_Modelo = new javax.swing.JTextField();
        cb_V_SeleccioneCliente = new javax.swing.JComboBox<>();
        btn_V_Nuevo = new javax.swing.JButton();
        btn_V_Guardar = new javax.swing.JButton();
        btn_V_Cancelar = new javax.swing.JButton();
        btn_V_Editar = new javax.swing.JButton();
        btn_V_Eliminar = new javax.swing.JButton();
        btn_V_Buscar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jdt_V_Fecha = new com.toedter.calendar.JDateChooser();
        pnlReparaciones = new javax.swing.JPanel();
        lbl_R_Id = new javax.swing.JLabel();
        lbl_R_IdVehiculo = new javax.swing.JLabel();
        lbl_R_IdPieza = new javax.swing.JLabel();
        lbl_R_Id_Reparacion = new javax.swing.JLabel();
        lbl_R_Falla = new javax.swing.JLabel();
        lbl_R_ControlPiezas = new javax.swing.JLabel();
        lbl_R_FechaEntrada = new javax.swing.JLabel();
        lbl_R_FechaSalida = new javax.swing.JLabel();
        cb_R_IdVehiculo = new javax.swing.JComboBox<>();
        cb_R_IdPieza = new javax.swing.JComboBox<>();
        btn_R_Nuevo = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        txt_R_Id = new javax.swing.JTextField();
        txt_R_IdReparacion = new javax.swing.JTextField();
        txt_R_Falla = new javax.swing.JTextField();
        txt_R_ControlPiezas = new javax.swing.JTextField();
        btn_R_Guardar = new javax.swing.JButton();
        btn_R_Cancelar = new javax.swing.JButton();
        btn_R_Editar = new javax.swing.JButton();
        btn_R_Eliminar = new javax.swing.JButton();
        btn_R_Buscar = new javax.swing.JButton();
        btn_R_Salir = new javax.swing.JButton();
        jdt_E_Fecha = new com.toedter.calendar.JDateChooser();
        jdt_S_Fecha = new com.toedter.calendar.JDateChooser();
        pnlPiezas = new javax.swing.JPanel();
        lbl_P_Id = new javax.swing.JLabel();
        lbl_P_IdPieza = new javax.swing.JLabel();
        lbl_P_Descripcion = new javax.swing.JLabel();
        lbl_P_Stock = new javax.swing.JLabel();
        btn_P_Nuevo = new javax.swing.JButton();
        txt_P_Id = new javax.swing.JTextField();
        txt_P_IdPieza = new javax.swing.JTextField();
        txt_P_Descripcion = new javax.swing.JTextField();
        txt_P_Stock = new javax.swing.JTextField();
        btn_P_Guardar = new javax.swing.JButton();
        btn_P_Cancelar = new javax.swing.JButton();
        btn_P_Editar = new javax.swing.JButton();
        btn_P_Eliminar = new javax.swing.JButton();
        btn_P_Buscar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        btn_P_Salir = new javax.swing.JButton();

        jButton3.setText("Nuevo");

        jButton7.setText("jButton1");

        jButton12.setText("jButton1");

        jButton14.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUsuario.setText("Usuario");

        jLabel2.setText("Password");

        btnAutentificar.setText("Autentificar");
        btnAutentificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutentificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAutentificar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAutentificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        tpane.addTab("Login", pnlLogin);

        txtID.setEditable(false);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Guardar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellido Paterno");

        jLabel4.setText("Apellido Materno");

        jLabel5.setText("Username:");

        cbPerfil.setEditable(true);
        cbPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Gerente", "Secretaria", "Mecánico" }));

        jLabel6.setText("Perfil:");

        lblPassword.setText("Password");

        btnRemover.setText("Eliminar");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese ID");

        jLabel8.setText("ID");

        jLabel9.setText("Nombre");

        jLabel10.setText("Telefono");

        lblPassword1.setText("Dirección");

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuariosLayout.createSequentialGroup()
                        .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlUsuariosLayout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                                        .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPsw, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(lblPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPsw, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpane.addTab("Usuarios", pnlUsuarios);

        lbl_C_IdUsuario.setText("ID Usuario");

        lbl_C_IdCliente.setText("ID Cliente");

        lbl_C_Id.setText("ID");

        lbl_C_Nombre.setText("Nombre");

        lbl_C_ApellidoPaterno.setText("Apellido Paterno");

        lbl_C_ApellidoMaterno.setText("Apellido Materno");

        txt_C_IdCliente.setEditable(false);

        txt_C_Nombre.setEditable(false);

        txt_C_ApellidoPaterno.setEditable(false);

        txt_C_ApellidoMaterno.setEditable(false);

        btn_C_Buscar.setText("Buscar");
        btn_C_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_C_BuscarActionPerformed(evt);
            }
        });

        btn_C_Nuevo.setText("Nuevo");
        btn_C_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_C_NuevoActionPerformed(evt);
            }
        });

        btn_C_Guardar.setText("Guardar");
        btn_C_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_C_GuardarActionPerformed(evt);
            }
        });

        btn_C_Cancelar.setText("Cancelar");
        btn_C_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_C_CancelarActionPerformed(evt);
            }
        });

        btn_C_Editar.setText("Editar");
        btn_C_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_C_EditarActionPerformed(evt);
            }
        });

        btn_C_Eliminar.setText("Eliminar");
        btn_C_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_C_EliminarActionPerformed(evt);
            }
        });

        btn_C_Salir.setText("Salir");
        btn_C_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_C_SalirActionPerformed(evt);
            }
        });

        txt_C_IdUsuario.setEditable(false);

        javax.swing.GroupLayout pnlClientesLayout = new javax.swing.GroupLayout(pnlClientes);
        pnlClientes.setLayout(pnlClientesLayout);
        pnlClientesLayout.setHorizontalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlClientesLayout.createSequentialGroup()
                                .addComponent(lbl_C_IdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_C_IdUsuario)
                                .addGap(230, 230, 230))
                            .addGroup(pnlClientesLayout.createSequentialGroup()
                                .addComponent(lbl_C_IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_C_IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlClientesLayout.createSequentialGroup()
                                .addComponent(lbl_C_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_C_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlClientesLayout.createSequentialGroup()
                                .addComponent(lbl_C_ApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_C_ApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlClientesLayout.createSequentialGroup()
                                .addComponent(lbl_C_ApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_C_ApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlClientesLayout.createSequentialGroup()
                                .addComponent(btn_C_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_C_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_C_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_C_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_C_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addComponent(lbl_C_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_C_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btn_C_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(btn_C_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
            .addComponent(jSeparator1)
        );
        pnlClientesLayout.setVerticalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_C_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_C_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_C_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_C_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_C_IdUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txt_C_IdUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_C_IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_C_IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_C_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_C_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_C_ApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_C_ApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_C_ApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_C_ApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_C_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_C_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_C_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_C_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_C_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        tpane.addTab("Clientes", pnlClientes);

        lbl_V_SeleccioneCliente.setText("Cliente");

        lbl_V_IdVehiculo.setText("ID Vehiculo");

        lbl_V_Matricula.setText("Matricula");

        lbl_V_Marca.setText("Marca");

        lbl_V_Modelo.setText("Modelo");

        lbl_V_Id.setText("ID");

        lbl_V_Fecha.setText("Fecha");

        txt_V_IdVehiculo.setEditable(false);

        txt_V_Matricula.setEditable(false);

        txt_V_Marca.setEditable(false);

        txt_V_Modelo.setEditable(false);

        cb_V_SeleccioneCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));

        btn_V_Nuevo.setText("Nuevo");
        btn_V_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_V_NuevoActionPerformed(evt);
            }
        });

        btn_V_Guardar.setText("Guardar");
        btn_V_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_V_GuardarActionPerformed(evt);
            }
        });

        btn_V_Cancelar.setText("Cancelar");
        btn_V_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_V_CancelarActionPerformed(evt);
            }
        });

        btn_V_Editar.setText("Editar");
        btn_V_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_V_EditarActionPerformed(evt);
            }
        });

        btn_V_Eliminar.setText("Eliminar");
        btn_V_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_V_EliminarActionPerformed(evt);
            }
        });

        btn_V_Buscar.setText("Buscar");
        btn_V_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_V_BuscarActionPerformed(evt);
            }
        });

        jdt_V_Fecha.setDateFormatString("dd-MM-yyyy");
        jdt_V_Fecha.setEnabled(false);

        javax.swing.GroupLayout pnlVehiculosLayout = new javax.swing.GroupLayout(pnlVehiculos);
        pnlVehiculos.setLayout(pnlVehiculosLayout);
        pnlVehiculosLayout.setHorizontalGroup(
            pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVehiculosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVehiculosLayout.createSequentialGroup()
                        .addComponent(lbl_V_SeleccioneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_V_SeleccioneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVehiculosLayout.createSequentialGroup()
                        .addComponent(lbl_V_IdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_V_IdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVehiculosLayout.createSequentialGroup()
                        .addComponent(lbl_V_Matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_V_Matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVehiculosLayout.createSequentialGroup()
                        .addComponent(lbl_V_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_V_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVehiculosLayout.createSequentialGroup()
                        .addComponent(lbl_V_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_V_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btn_V_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVehiculosLayout.createSequentialGroup()
                        .addComponent(btn_V_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_V_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_V_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_V_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_V_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVehiculosLayout.createSequentialGroup()
                            .addComponent(lbl_V_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jdt_V_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVehiculosLayout.createSequentialGroup()
                            .addComponent(lbl_V_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_V_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(96, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );
        pnlVehiculosLayout.setVerticalGroup(
            pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVehiculosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_V_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_V_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_V_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_V_SeleccioneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_V_SeleccioneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_V_IdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_V_IdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_V_Matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_V_Matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_V_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_V_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_V_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_V_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_V_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jdt_V_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_V_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_V_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_V_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_V_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_V_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        tpane.addTab("Vehículos", pnlVehiculos);

        lbl_R_Id.setText("ID");

        lbl_R_IdVehiculo.setText("ID Vehiculo");

        lbl_R_IdPieza.setText("ID Pieza");

        lbl_R_Id_Reparacion.setText("ID Reparación");

        lbl_R_Falla.setText("Falla");

        lbl_R_ControlPiezas.setText("Control Piezas");

        lbl_R_FechaEntrada.setText("Fecha Entrada");

        lbl_R_FechaSalida.setText("Fecha Salida");

        cb_R_IdVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));

        cb_R_IdPieza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));

        btn_R_Nuevo.setText("Nuevo");
        btn_R_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_R_NuevoActionPerformed(evt);
            }
        });

        txt_R_IdReparacion.setEditable(false);

        txt_R_Falla.setEditable(false);

        txt_R_ControlPiezas.setEditable(false);

        btn_R_Guardar.setText("Guardar");
        btn_R_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_R_GuardarActionPerformed(evt);
            }
        });

        btn_R_Cancelar.setText("Cancelar");
        btn_R_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_R_CancelarActionPerformed(evt);
            }
        });

        btn_R_Editar.setText("Editar");
        btn_R_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_R_EditarActionPerformed(evt);
            }
        });

        btn_R_Eliminar.setText("Eliminar");
        btn_R_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_R_EliminarActionPerformed(evt);
            }
        });

        btn_R_Buscar.setText("Buscar");
        btn_R_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_R_BuscarActionPerformed(evt);
            }
        });

        btn_R_Salir.setText("Salir");
        btn_R_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_R_SalirActionPerformed(evt);
            }
        });

        jdt_E_Fecha.setDateFormatString("dd-MM-yyyy");
        jdt_E_Fecha.setEnabled(false);

        jdt_S_Fecha.setDateFormatString("dd-MM-yyyy");
        jdt_S_Fecha.setEnabled(false);

        javax.swing.GroupLayout pnlReparacionesLayout = new javax.swing.GroupLayout(pnlReparaciones);
        pnlReparaciones.setLayout(pnlReparacionesLayout);
        pnlReparacionesLayout.setHorizontalGroup(
            pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReparacionesLayout.createSequentialGroup()
                        .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                                .addComponent(lbl_R_IdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cb_R_IdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                                .addComponent(lbl_R_Id_Reparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_R_IdReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                                .addComponent(lbl_R_IdPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cb_R_IdPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                                .addComponent(lbl_R_Falla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_R_Falla, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(326, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlReparacionesLayout.createSequentialGroup()
                        .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_R_ControlPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_R_FechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_R_FechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_R_ControlPiezas)
                                    .addComponent(jdt_E_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jdt_S_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                                .addComponent(lbl_R_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_R_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btn_R_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_R_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31))))
            .addComponent(jSeparator3)
            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btn_R_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_R_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_R_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_R_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_R_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlReparacionesLayout.setVerticalGroup(
            pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReparacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_R_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_R_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_R_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_R_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_R_IdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_R_IdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_R_IdPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_R_IdPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_R_Id_Reparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_R_IdReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_R_Falla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_R_Falla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_R_ControlPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_R_ControlPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_R_FechaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jdt_E_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_R_FechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jdt_S_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_R_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_R_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_R_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_R_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_R_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        txt_R_Id.getAccessibleContext().setAccessibleName("");

        tpane.addTab("Reparaciones", pnlReparaciones);

        lbl_P_Id.setText("ID");

        lbl_P_IdPieza.setText("ID Pieza");

        lbl_P_Descripcion.setText("Descripción");

        lbl_P_Stock.setText("Stock");

        btn_P_Nuevo.setText("Nuevo");
        btn_P_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_P_NuevoActionPerformed(evt);
            }
        });

        txt_P_IdPieza.setEditable(false);

        txt_P_Descripcion.setEditable(false);

        txt_P_Stock.setEditable(false);

        btn_P_Guardar.setText("Guardar");
        btn_P_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_P_GuardarActionPerformed(evt);
            }
        });

        btn_P_Cancelar.setText("Cancelar");
        btn_P_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_P_CancelarActionPerformed(evt);
            }
        });

        btn_P_Editar.setText("Editar");
        btn_P_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_P_EditarActionPerformed(evt);
            }
        });

        btn_P_Eliminar.setText("Eliminar");
        btn_P_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_P_EliminarActionPerformed(evt);
            }
        });

        btn_P_Buscar.setText("Buscar");
        btn_P_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_P_BuscarActionPerformed(evt);
            }
        });

        btn_P_Salir.setText("Salir");
        btn_P_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_P_SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPiezasLayout = new javax.swing.GroupLayout(pnlPiezas);
        pnlPiezas.setLayout(pnlPiezasLayout);
        pnlPiezasLayout.setHorizontalGroup(
            pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPiezasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPiezasLayout.createSequentialGroup()
                        .addComponent(lbl_P_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_P_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btn_P_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(btn_P_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(pnlPiezasLayout.createSequentialGroup()
                        .addGroup(pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPiezasLayout.createSequentialGroup()
                                .addComponent(lbl_P_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_P_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPiezasLayout.createSequentialGroup()
                                .addComponent(lbl_P_Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_P_Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPiezasLayout.createSequentialGroup()
                                .addComponent(lbl_P_IdPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_P_IdPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(326, Short.MAX_VALUE))))
            .addComponent(jSeparator4)
            .addGroup(pnlPiezasLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btn_P_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_P_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_P_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_P_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_P_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlPiezasLayout.setVerticalGroup(
            pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPiezasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_P_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_P_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_P_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_P_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_P_IdPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_P_IdPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_P_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_P_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_P_Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_P_Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_P_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_P_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_P_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_P_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_P_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        tpane.addTab("Piezas", pnlPiezas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtBuscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del usuario");
            txtID.setText("");
            txtNombre.setText("");
            txtTelefono.setText("");
            txtPaterno.setText("");
            txtMaterno.setText("");
            txtUsername.setText("");
            txtPsw.setText("");
            cbPerfil.setEditable(true);
            cbPerfil.setSelectedItem("");
            cbPerfil.setEditable(false);
            txtDireccion.setText("");

            return;
        }

        if (!ValidaNum(txtBuscar.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
            txtBuscar.setText("");
            txtID.setText("");
            txtNombre.setText("");
            txtTelefono.setText("");
            txtPaterno.setText("");
            txtMaterno.setText("");
            txtUsername.setText("");
            cbPerfil.setEditable(true);
            cbPerfil.setSelectedItem("");
            cbPerfil.setEditable(false);
            txtDireccion.setText("");

            return;
        }

        try {
            cto = new contacto();
            cto.setId(Integer.parseInt(txtBuscar.getText()));

            cto = f.BuscarContacto(cto);

            if (cto != null) {
                if (cto.getId() == 0) {
                    btnSalvar.setEnabled(false);
                    btnNuevo.setEnabled(false);
                    btnEditar.setEnabled(false);
                    btnRemover.setEnabled(false);
                    btnCancelar.setEnabled(true);
                } else {
                    btnSalvar.setEnabled(false);
                    btnNuevo.setEnabled(false);
                    btnEditar.setEnabled(true);
                    btnRemover.setEnabled(true);
                    btnCancelar.setEnabled(true);
                }
                Deshabilitar();
                txtPsw.setVisible(false);
                lblPassword.setVisible(false);

                txtBuscar.setText("");
                txtID.setText(String.valueOf(cto.getId()));
                txtNombre.setText(cto.getNombre());
                txtTelefono.setText(cto.getTelefono());
                txtPaterno.setText(cto.getPaterno());
                txtMaterno.setText(cto.getMaterno());
                txtUsername.setText(cto.getUsername());
                cbPerfil.setSelectedItem(cto.getPerfil());
                txtDireccion.setText(cto.getDireccion());

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese ID");
                txtBuscar.setText("");
                txtID.setText("");
                txtNombre.setText("");
                txtTelefono.setText("");
                txtPaterno.setText("");
                txtMaterno.setText("");
                txtUsername.setText("");
                cbPerfil.setSelectedItem("");
                txtDireccion.setText("");

            }

        } catch (FileNotFoundException ex) {

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        try {
            txtID.setText(String.valueOf(f.Id()));
        } catch (FileNotFoundException ex) {
        }

        Habilitar();
        txtPsw.setVisible(true);
        lblPassword.setVisible(true);

        btnSalvar.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
        btnCancelar.setEnabled(true);

        txtNombre.setText("");
        txtTelefono.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtUsername.setText("");
        cbPerfil.setSelectedItem("");
        txtDireccion.setText("");
        txtPsw.setText("");

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            cto = new contacto();
            cto.setUsername(txtUsername.getText());
            String contra;

            if (ban != true && f.BuscarUsuario(cto) != null) {
                JOptionPane.showMessageDialog(null, "Ese Nombre de Usuario ya existe");
                return;
            }

            //Validar que no existan campos vacios
            if (txtNombre.getText().equals("") || txtTelefono.getText().equals("") || txtPaterno.getText().equals("") || txtMaterno.getText().equals("") || txtUsername.getText().equals("") || txtPsw.getText().equals("") || cbPerfil.getSelectedItem().equals("") || txtDireccion.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
                return;
            }

            cto.setId(Integer.parseInt(txtID.getText()));
            cto.setNombre(txtNombre.getText());
            cto.setTelefono(txtTelefono.getText());
            cto.setPaterno(txtPaterno.getText());
            cto.setMaterno(txtMaterno.getText());
            cto.setPerfil(cbPerfil.getSelectedItem().toString());
            cto.setDireccion(txtDireccion.getText());
            

            if (ban != true) {
                cto.setPassword(txtPsw.getText());
                f.Guardar(cto);
                txtID.setText("");
                txtNombre.setText("");
                txtTelefono.setText("");
                txtPaterno.setText("");
                txtMaterno.setText("");
                txtUsername.setText("");
                txtPsw.setText("");
                cbPerfil.setSelectedItem("");
                txtDireccion.setText("");

                JOptionPane.showMessageDialog(null, "Guardado con Éxito");
            } else {
                ban = false;
                try {
                    contra = f.BuscarUsuario(cto).getPassword();
                    System.out.println(contra);
                    cto.setPassword(contra);
                    f.Editar(cto);
                    JOptionPane.showMessageDialog(null, "Editado con Éxito");
                    System.out.println("SI");
                } catch (IOException ex) {

                }
            }
            btnSalvar.setEnabled(false);
            btnNuevo.setEnabled(true);
            btnEditar.setEnabled(false);
            btnRemover.setEnabled(false);
            btnCancelar.setEnabled(false);

            //band=true;
        } catch (FileNotFoundException ex) {

        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        try {
            cto = new contacto();
            cto.setId(Integer.parseInt(txtID.getText()));
            cto = f.BuscarContacto(cto);

            if (cto != null) {
                try {
                    if (cto != null) {
                        f.Eliminar(cto);
                        btnSalvar.setEnabled(false);
                        btnNuevo.setEnabled(true);
                        btnEditar.setEnabled(false);
                        btnRemover.setEnabled(false);
                        btnCancelar.setEnabled(true);

                        txtID.setText("");
                        txtNombre.setText("");
                        txtTelefono.setText("");
                        txtPaterno.setText("");
                        txtMaterno.setText("");
                        txtUsername.setText("");
                        cbPerfil.setSelectedItem("");
                        txtDireccion.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "No existe el registro");
                    }
                } catch (IOException ex) {

                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe el registro");
            }
        } catch (FileNotFoundException ex) {

        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnAutentificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutentificarActionPerformed
        cto = new contacto();
        cto.setUsername(txtUsuario.getText());

        String pw;
        pw = String.valueOf(txtPassword.getPassword());

        try {
            cto = f.BuscarUsuario(cto);
        } catch (FileNotFoundException ex) {

        }
        if (cto != null) {
            if (cto.getPassword().equals(pw)) {

                IdUs = String.valueOf(cto.getId());
                txt_C_IdUsuario.setText(IdUs);

                int maxID = v.getMax();
               
                cb_vehiculos();
                cb_R_vehiculos();
                cb_R_Pieza();

                txtUsuario.setText("");
                txtPassword.setText("");

                if ("Admin".equals(cto.getPerfil())) {
                    tpane.setEnabledAt(0, false);
                    tpane.setEnabledAt(1, true);
                    tpane.setEnabledAt(2, true);
                    tpane.setEnabledAt(3, true);
                    tpane.setEnabledAt(4, true);
                    tpane.setEnabledAt(5, true);

                    tpane.setSelectedIndex(1);

                    btnSalvar.setEnabled(false);
                    btnNuevo.setEnabled(true);
                    btnEditar.setEnabled(false);
                    btnRemover.setEnabled(false);
                    btnCancelar.setEnabled(false);

                    btn_C_Nuevo.setVisible(true);
                    btn_C_Guardar.setVisible(true);
                    btn_C_Cancelar.setVisible(true);
                    btn_C_Editar.setVisible(true);
                    btn_C_Eliminar.setVisible(true);

                    btn_V_Nuevo.setVisible(true);
                    btn_V_Guardar.setVisible(true);
                    btn_V_Cancelar.setVisible(true);
                    btn_V_Editar.setVisible(true);
                    btn_V_Eliminar.setVisible(true);

                    btn_R_Nuevo.setVisible(true);
                    btn_R_Guardar.setVisible(true);
                    btn_R_Cancelar.setVisible(true);
                    btn_R_Editar.setVisible(true);
                    btn_R_Eliminar.setVisible(true);
                }
                if ("Gerente".equals(cto.getPerfil())) {

                    tpane.setEnabledAt(0, false);
                    tpane.setEnabledAt(1, false);
                    tpane.setEnabledAt(2, true);
                    tpane.setEnabledAt(3, false);
                    tpane.setEnabledAt(4, true);
                    tpane.setEnabledAt(5, false);

                    btn_C_Nuevo.setVisible(true);
                    btn_C_Guardar.setVisible(true);
                    btn_C_Cancelar.setVisible(true);
                    btn_C_Editar.setVisible(false);
                    btn_C_Eliminar.setVisible(false);

                    btn_R_Nuevo.setVisible(false);
                    btn_R_Guardar.setVisible(true);
                    btn_R_Cancelar.setVisible(true);
                    btn_R_Editar.setVisible(true);
                    btn_R_Eliminar.setVisible(false);

                    tpane.setSelectedIndex(2);
                }
                if ("Secretaria".equals(cto.getPerfil())) {

                    tpane.setEnabledAt(0, false);
                    tpane.setEnabledAt(1, false);
                    tpane.setEnabledAt(2, true);
                    tpane.setEnabledAt(3, true);
                    tpane.setEnabledAt(4, false);
                    tpane.setEnabledAt(5, false);

                    btn_C_Nuevo.setVisible(true);
                    btn_C_Guardar.setVisible(true);
                    btn_C_Cancelar.setVisible(true);
                    btn_C_Editar.setVisible(false);
                    btn_C_Eliminar.setVisible(false);

                    btn_V_Nuevo.setVisible(true);
                    btn_V_Guardar.setVisible(true);
                    btn_V_Cancelar.setVisible(true);
                    btn_V_Editar.setVisible(false);
                    btn_V_Eliminar.setVisible(false);

                    tpane.setSelectedIndex(2);
                }
                if ("Mecánico".equals(cto.getPerfil())) {

                    tpane.setEnabledAt(0, false);
                    tpane.setEnabledAt(1, false);
                    tpane.setEnabledAt(2, false);
                    tpane.setEnabledAt(3, false);
                    tpane.setEnabledAt(4, true);
                    tpane.setEnabledAt(5, false);

                    btn_R_Nuevo.setVisible(true);
                    btn_R_Guardar.setVisible(true);
                    btn_R_Cancelar.setVisible(true);
                    btn_R_Editar.setVisible(false);
                    btn_R_Eliminar.setVisible(false);

                    tpane.setSelectedIndex(4);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                txtPassword.setText("");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No existe ese Usuario");
            txtPassword.setText("");
        }


    }//GEN-LAST:event_btnAutentificarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Habilitar();
        btnSalvar.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
        btnCancelar.setEnabled(true);
        ban = true;
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        tpane.setSelectedIndex(0);

        tpane.setEnabledAt(0, true);
        tpane.setEnabledAt(1, false);
        tpane.setEnabledAt(2, false);
        tpane.setEnabledAt(3, false);
        tpane.setEnabledAt(4, false);
        tpane.setEnabledAt(5, false);

        txtBuscar.setText("");

        txtID.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtUsername.setText("");
        cbPerfil.setSelectedItem("");
        txtDireccion.setText("");

        Deshabilitar();

        Vehiculos_Deshabilitar();

        txt_C_IdUsuario.setEditable(false);
        txt_C_IdCliente.setEditable(false);
        txt_C_Nombre.setEditable(false);
        txt_C_ApellidoPaterno.setEditable(false);
        txt_C_ApellidoMaterno.setEditable(false);

        cb_V_SeleccioneCliente.removeAllItems();

        int maxId = fc.getMaxId();

        txt_C_Buscar.setText("");
        txt_C_IdUsuario.setText(IdUs);
        txt_C_IdCliente.setText(String.valueOf(maxId));
        txt_C_Nombre.setText("");
        txt_C_ApellidoPaterno.setText("");
        txt_C_ApellidoMaterno.setText("");
        btn_V_Guardar.setEnabled(false);
        btn_V_Nuevo.setEnabled(true);
        btn_V_Editar.setEnabled(false);
        btn_V_Eliminar.setEnabled(false);
        btn_V_Cancelar.setEnabled(false);

        btn_C_Guardar.setEnabled(false);
        btn_C_Nuevo.setEnabled(true);
        btn_C_Editar.setEnabled(false);
        btn_C_Eliminar.setEnabled(false);
        btn_C_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Habilitar();
        btnSalvar.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
        btnCancelar.setEnabled(false);

        txtBuscar.setText("");

        txtID.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtUsername.setText("");
        cbPerfil.setSelectedItem("");
        txtDireccion.setText("");

        ban = false;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btn_C_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_C_SalirActionPerformed
        tpane.setSelectedIndex(0);

        Vehiculos_Deshabilitar();

        txt_C_IdUsuario.setEditable(false);
        txt_C_IdCliente.setEditable(false);
        txt_C_Nombre.setEditable(false);
        txt_C_ApellidoPaterno.setEditable(false);
        txt_C_ApellidoMaterno.setEditable(false);

        cb_V_SeleccioneCliente.removeAllItems();

        int maxId = fc.getMaxId();

        txt_C_Buscar.setText("");
        txt_C_IdUsuario.setText(IdUs);
        txt_C_IdCliente.setText(String.valueOf(maxId));
        txt_C_Nombre.setText("");
        txt_C_ApellidoPaterno.setText("");
        txt_C_ApellidoMaterno.setText("");

        tpane.setEnabledAt(0, true);
        tpane.setEnabledAt(1, false);
        tpane.setEnabledAt(2, false);
        tpane.setEnabledAt(3, false);
        tpane.setEnabledAt(4, false);
        tpane.setEnabledAt(5, false);

        btn_V_Guardar.setEnabled(false);
        btn_V_Nuevo.setEnabled(true);
        btn_V_Editar.setEnabled(false);
        btn_V_Eliminar.setEnabled(false);
        btn_V_Cancelar.setEnabled(false);

        btn_C_Guardar.setEnabled(false);
        btn_C_Nuevo.setEnabled(true);
        btn_C_Editar.setEnabled(false);
        btn_C_Eliminar.setEnabled(false);
        btn_C_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btn_C_SalirActionPerformed

    private void btn_R_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_R_SalirActionPerformed
        tpane.setSelectedIndex(0);

        tpane.setEnabledAt(0, true);
        tpane.setEnabledAt(1, false);
        tpane.setEnabledAt(2, false);
        tpane.setEnabledAt(3, false);
        tpane.setEnabledAt(4, false);
        tpane.setEnabledAt(5, false);
    }//GEN-LAST:event_btn_R_SalirActionPerformed

    private void btn_P_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_P_SalirActionPerformed
        tpane.setSelectedIndex(0);

        tpane.setEnabledAt(0, true);
        tpane.setEnabledAt(1, false);
        tpane.setEnabledAt(2, false);
        tpane.setEnabledAt(3, false);
        tpane.setEnabledAt(4, false);
        tpane.setEnabledAt(5, false);
    }//GEN-LAST:event_btn_P_SalirActionPerformed

    private void btn_R_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_R_NuevoActionPerformed
        Reparaciones_Habilitar();
        cb_R_vehiculos();
        cb_R_Pieza();

        int maxID = rf.getMax();

        btn_R_Guardar.setEnabled(true);
        btn_R_Nuevo.setEnabled(false);
        btn_R_Editar.setEnabled(false);
        btn_R_Eliminar.setEnabled(false);
        btn_R_Cancelar.setEnabled(true);

        cb_R_IdVehiculo.setSelectedItem("");
        cb_R_IdPieza.setSelectedItem("");
        txt_R_IdReparacion.setText(String.valueOf(maxID));
        txt_R_Falla.setText("");
        txt_R_ControlPiezas.setText("");

        jdt_E_Fecha.setDate(null);
        jdt_S_Fecha.setDate(null);

    }//GEN-LAST:event_btn_R_NuevoActionPerformed

    private void btn_R_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_R_GuardarActionPerformed

        if ("Seleccione".equals(cb_R_IdVehiculo.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Eliga el Id del Vehiculo");
            return;
        }

        if ("Seleccione".equals(cb_R_IdPieza.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Eliga el Id de la pieza a usar");
            return;
        }

        if ("".equals(txt_R_Falla.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese la falla");
            return;
        }

        if ("".equals(txt_R_ControlPiezas.getText()) || !ValidaNum(txt_R_ControlPiezas.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese la cantidad de piezas a usar");
            return;
        }

        if (!txt_R_Control()) {
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad posible, o en su defecto, escoja una pieza disponible");
            return;
        }

        if (jdt_E_Fecha.getDate() == null || jdt_S_Fecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Escoja una fecha del calendario");
            return;
        }

        SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
        String fecha_E = fecha.format(jdt_E_Fecha.getDate());
        String fecha_S = fecha.format(jdt_S_Fecha.getDate());

        Date actual = new Date();
        Date fentrada = jdt_E_Fecha.getDate();
        Date fsalida = jdt_S_Fecha.getDate();

        if ((fentrada.before(actual) || fentrada.equals(actual)) && (fsalida.before(actual) || fsalida.equals(actual))) {
            if (fsalida.after(fentrada)) {
                try {
                    rep = new reparaciones();
                    rep.setId_re(Integer.parseInt(txt_R_IdReparacion.getText()));

                    if (ban_reparaciones != true && rf.BuscarReparacion(rep) != null) {
                        JOptionPane.showMessageDialog(null, "Ese Id de reparacion ya existe");
                        return;
                    }

                    int aux = Integer.parseInt(txt_R_ControlPiezas.getText());

                    pi = new piezas();
                    pi.SetPiz(Integer.parseInt(cb_R_IdPieza.getSelectedItem().toString()));
                    pi = pf.BuscarPiezas(pi);
                    aux = (pi.getStock() - aux);
                    pi.SetStock(aux);

                    try {
                        pf.Editar(pi);
                    } catch (IOException ex) {

                    }

                    rep.setId_ve(Integer.parseInt(cb_R_IdVehiculo.getSelectedItem().toString()));
                    rep.setId_pi(Integer.parseInt(cb_R_IdPieza.getSelectedItem().toString()));
                    rep.setFalla(txt_R_Falla.getText());
                    rep.setId_contrl(Integer.parseInt(txt_R_ControlPiezas.getText()));
                    rep.setFecha_e(fecha_E);
                    rep.setFecha_s(fecha_S);

                    if (ban_reparaciones != true) {
                        rf.Guardar(rep);
                        JOptionPane.showMessageDialog(null, "Guardado con Éxito");
                    } else {
                        ban_reparaciones = false;
                        try {
                            rf.Editar(rep);

                            JOptionPane.showMessageDialog(null, "Editado con Éxito");
                            System.out.println("SI");
                        } catch (IOException ex) {

                        }
                    }

                    Reparaciones_Deshabilitar();

                    btn_R_Guardar.setEnabled(false);
                    btn_R_Nuevo.setEnabled(true);
                    btn_R_Editar.setEnabled(false);
                    btn_R_Eliminar.setEnabled(false);
                    btn_R_Cancelar.setEnabled(false);

                    cb_R_vehiculos();
                    cb_R_Pieza();

                } catch (FileNotFoundException ex) {

                }
            } else {
                JOptionPane.showMessageDialog(null, "La Fecha de Entrada tiene que ser anterior a la Fecha de Salida");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Elija una fecha posible");
        }

    }//GEN-LAST:event_btn_R_GuardarActionPerformed

    private void btn_V_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_V_NuevoActionPerformed
        Vehiculos_Habilitar();
        jdt_V_Fecha.setDate(null);
        cb_vehiculos();

        int maxID = v.getMax();

        btn_V_Guardar.setEnabled(true);
        btn_V_Nuevo.setEnabled(false);
        btn_V_Editar.setEnabled(false);
        btn_V_Eliminar.setEnabled(false);
        btn_V_Cancelar.setEnabled(true);

        txt_V_IdVehiculo.setText(String.valueOf(maxID));
        txt_V_Matricula.setText("");
        txt_V_Marca.setText("");
        txt_V_Modelo.setText("");

    }//GEN-LAST:event_btn_V_NuevoActionPerformed

    private void btn_V_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_V_GuardarActionPerformed

        if ("Seleccione".equals(cb_V_SeleccioneCliente.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Eliga el cliente");
            return;
        }

        if ("".equals(txt_V_Matricula.getText()) || "".equals(txt_V_Marca.getText()) || "".equals(txt_V_Modelo.getText())) {
            JOptionPane.showMessageDialog(null, "Rellene los textos faltantes");
            return;
        }

        if (jdt_V_Fecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Escoja una fecha del calendario");
            return;
        }

        try {
            vcs = new Vehiculos();
            vcs.setMatricula(txt_V_Matricula.getText());
            vcs = v.BuscarMatricula(vcs);

            if (vcs != null) {
                JOptionPane.showMessageDialog(null, "Esa matricula ya existe");
                return;
            }
        } catch (FileNotFoundException ex) {

        }

        vcs = new Vehiculos();
        vcs.setMatricula(txt_V_Matricula.getText());
        vcs.setCliente(cb_V_SeleccioneCliente.getSelectedItem().toString());
        vcs.setId_vehiculo(Integer.parseInt(txt_V_IdVehiculo.getText()));
        vcs.setMatricula(txt_V_Matricula.getText());
        vcs.setMarca(txt_V_Marca.getText());
        vcs.setModelo(txt_V_Modelo.getText());

        SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-YYYY");
        String date = dformat.format(jdt_V_Fecha.getDate());
        vcs.setFecha(date);

        System.out.println(vcs.getFecha());

        Date fa = new Date();
        Date s = jdt_V_Fecha.getDate();

        if (s.before(fa) || s.equals(fa)) {
            if (ban_vehiculos == false) {
                try {
                    v.Guardar(vcs);
                    JOptionPane.showMessageDialog(null, "Guardado correctamente");
                } catch (FileNotFoundException ex) {

                }
            } else {
                try {
                    v.Editar_Vehiculo(vcs);
                    ban_vehiculos = false;
                    JOptionPane.showMessageDialog(null, "Editado correctamente");
                } catch (IOException ex) {
                }
            }

            Vehiculos_Deshabilitar();
            cb_vehiculos();
            btn_V_Guardar.setEnabled(false);
            btn_V_Nuevo.setEnabled(true);
            btn_V_Editar.setEnabled(false);
            btn_V_Eliminar.setEnabled(false);
            btn_V_Cancelar.setEnabled(false);

            txt_V_IdVehiculo.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Elija una fecha posible");
        }

    }//GEN-LAST:event_btn_V_GuardarActionPerformed

    private void btn_V_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_V_CancelarActionPerformed
        Vehiculos_Deshabilitar();
        cb_vehiculos();
        btn_V_Guardar.setEnabled(false);
        btn_V_Nuevo.setEnabled(true);
        btn_V_Editar.setEnabled(false);
        btn_V_Eliminar.setEnabled(false);
        btn_V_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btn_V_CancelarActionPerformed

    private void btn_V_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_V_EditarActionPerformed
        Vehiculos_Habilitar();

        btn_V_Guardar.setEnabled(true);
        btn_V_Nuevo.setEnabled(false);
        btn_V_Editar.setEnabled(false);
        btn_V_Eliminar.setEnabled(false);
        btn_V_Cancelar.setEnabled(true);

        ban_vehiculos = true;
    }//GEN-LAST:event_btn_V_EditarActionPerformed

    private void btn_V_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_V_EliminarActionPerformed
        vcs.setCliente(cb_V_SeleccioneCliente.getSelectedItem().toString());
        vcs.setId_vehiculo(Integer.parseInt(txt_V_IdVehiculo.getText()));
        vcs.setMatricula(txt_V_Matricula.getText());
        vcs.setMarca(txt_V_Marca.getText());
        vcs.setModelo(txt_V_Modelo.getText());

        SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-YYYY");
        String date = dformat.format(jdt_V_Fecha.getDate());
        vcs.setFecha(date);

        try {
            v.Eliminar_Vehiculos(vcs);
            JOptionPane.showMessageDialog(null, "Eliminado correctamente");
        } catch (IOException ex) {

        }

        Vehiculos_Deshabilitar();
        cb_vehiculos();

        btn_V_Guardar.setEnabled(false);
        btn_V_Nuevo.setEnabled(true);
        btn_V_Editar.setEnabled(false);
        btn_V_Eliminar.setEnabled(false);
        btn_V_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btn_V_EliminarActionPerformed

    private void btn_V_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_V_BuscarActionPerformed
        if (txt_V_Buscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del vehiculo");
            txt_V_Buscar.setText("");
            cb_V_SeleccioneCliente.setSelectedItem("Seleccione");
            txt_V_IdVehiculo.setText("");
            txt_V_Matricula.setText("");
            txt_V_Marca.setText("");
            txt_V_Modelo.setText("");
            jdt_V_Fecha.setDate(null);
            return;
        }

        if (!ValidaNum(txt_V_Buscar.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
            txt_V_Buscar.setText("");
            cb_V_SeleccioneCliente.setSelectedItem("Seleccione");
            txt_V_IdVehiculo.setText("");
            txt_V_Matricula.setText("");
            txt_V_Marca.setText("");
            txt_V_Modelo.setText("");
            jdt_V_Fecha.setDate(null);
            return;
        }

        Vehiculos_Deshabilitar();
        try {
            vcs = new Vehiculos();
            vcs.setId_vehiculo(Integer.parseInt(txt_V_Buscar.getText()));
            vcs = v.BuscarIdVehiculo(vcs);

            if (vcs != null) {

                cb_V_SeleccioneCliente.setSelectedItem(vcs.getCliente());
                txt_V_IdVehiculo.setText(String.valueOf(vcs.getId_vehiculo()));
                txt_V_Matricula.setText(vcs.getMatricula());
                txt_V_Marca.setText(vcs.getMarca());
                txt_V_Modelo.setText(vcs.getModelo());

                SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
                Date formato = null;
                try {
                    formato = fecha.parse(vcs.getFecha());
                } catch (ParseException ex) {
                }
                jdt_V_Fecha.setDate(formato);

                btn_V_Guardar.setEnabled(false);
                btn_V_Nuevo.setEnabled(false);
                btn_V_Editar.setEnabled(true);
                btn_V_Eliminar.setEnabled(true);
                btn_V_Cancelar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese ID");

                btn_V_Guardar.setEnabled(false);
                btn_V_Nuevo.setEnabled(true);
                btn_V_Editar.setEnabled(false);
                btn_V_Eliminar.setEnabled(false);
                btn_V_Cancelar.setEnabled(true);
            }

        } catch (FileNotFoundException ex) {

        }

        txt_V_Buscar.setText("");
    }//GEN-LAST:event_btn_V_BuscarActionPerformed

    private void btn_C_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_C_GuardarActionPerformed
        //Verificar que el campo txt_C_IdCliente no este vacio

        if (txt_C_IdCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del cliente");
            return;
        }
        //Verificar que el campo txt_C_Nombre no este vacio
        if (txt_C_Nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del cliente");
            return;
        }
        //Verificar que el campo txt_C_ApellidoPaterno no este vacio
        if (txt_C_ApellidoPaterno.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido paterno del cliente");
            return;
        }
        //Verificar que el campo txt_C_ApellidoMaterno no este vacio
        if (txt_C_ApellidoMaterno.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido materno del cliente");
            return;
        }
        //Se crea un objeto de tipo cliente
        c = new cliente();
        //Se le asignan los valores a los atributos del objeto
        c.setIdUsuario(Integer.parseInt(txt_C_IdUsuario.getText()));
        c.setId(Integer.parseInt(txt_C_IdCliente.getText()));
        c.setNombre(txt_C_Nombre.getText());
        c.setApellidoPaterno(txt_C_ApellidoPaterno.getText());
        c.setApellidoMaterno(txt_C_ApellidoMaterno.getText());

        vc = new vehiculo_cliente();
        vc.setIdUsuario(txt_C_IdUsuario.getText());
        vc.setIdCliente(txt_C_IdCliente.getText());
        //Se crea un objeto de tipo archivoCliente
        //Cliente_File ac = new Cliente_File();
        //Se llama al metodo guardarCliente y se le pasa como parametro el objeto cliente
        fc.guardar(c);
        vcf.guardar(vc);
        cb_vehiculos();
        //Se muestra un mensaje de que se guardo correctamente
        JOptionPane.showMessageDialog(null, "Guardado correctamente");
        //Se limpian los campos de texto
        txt_C_IdCliente.setText("");
        txt_C_Nombre.setText("");
        txt_C_ApellidoPaterno.setText("");
        txt_C_ApellidoMaterno.setText("");

        btn_C_Guardar.setEnabled(false);
        btn_C_Nuevo.setEnabled(true);
        btn_C_Editar.setEnabled(false);
        btn_C_Eliminar.setEnabled(false);
        btn_C_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btn_C_GuardarActionPerformed

    private void btn_C_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_C_NuevoActionPerformed
       
        int maxId = fc.getMaxId();

        txt_C_Buscar.setText("");
        txt_C_IdUsuario.setText(IdUs);
        txt_C_IdCliente.setText(String.valueOf(maxId));
        txt_C_Nombre.setText("");
        txt_C_ApellidoPaterno.setText("");
        txt_C_ApellidoMaterno.setText("");

        txt_C_Nombre.setEditable(true);
        txt_C_ApellidoPaterno.setEditable(true);
        txt_C_ApellidoMaterno.setEditable(true);

        btn_C_Guardar.setEnabled(true);
        btn_C_Nuevo.setEnabled(false);
        btn_C_Editar.setEnabled(false);
        btn_C_Eliminar.setEnabled(false);
        btn_C_Cancelar.setEnabled(true);
    }//GEN-LAST:event_btn_C_NuevoActionPerformed

    private void btn_C_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_C_CancelarActionPerformed

        txt_C_Buscar.setText("");
        txt_C_IdUsuario.setText(IdUs);
        txt_C_IdCliente.setText("");
        txt_C_Nombre.setText("");
        txt_C_ApellidoPaterno.setText("");
        txt_C_ApellidoMaterno.setText("");

        btn_C_Guardar.setEnabled(false);
        btn_C_Nuevo.setEnabled(true);
        btn_C_Editar.setEnabled(false);
        btn_C_Eliminar.setEnabled(false);
        btn_C_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btn_C_CancelarActionPerformed

    private void btn_C_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_C_BuscarActionPerformed
        //Verificar que el campo txt_C_Id no este vacio
        if (txt_C_Buscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del cliente");
            txt_C_Buscar.setText("");
            txt_C_IdUsuario.setText(IdUs);
            txt_C_IdCliente.setText("");
            txt_C_Nombre.setText("");
            txt_C_ApellidoPaterno.setText("");
            txt_C_ApellidoMaterno.setText("");

            return;
        }

        if (!ValidaNum(txt_C_Buscar.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
            txt_C_Buscar.setText("");
            txt_C_IdUsuario.setText(IdUs);
            txt_C_IdCliente.setText("");
            txt_C_Nombre.setText("");
            txt_C_ApellidoPaterno.setText("");
            txt_C_ApellidoMaterno.setText("");
            return;
        }

        // Obtener el id 
        int id = Integer.parseInt(txt_C_Buscar.getText());

        // validar que el id si exista
        c = new cliente();
        c = fc.buscar(id);
        if (c != null) {
            txt_C_Buscar.setText("");
            txt_C_IdUsuario.setText(String.valueOf(c.getIdUsuario()));
            // Mostrar los datos del cliente

            txt_C_IdCliente.setText(String.valueOf(c.getId()));
            txt_C_Nombre.setText(c.getNombre());
            txt_C_ApellidoPaterno.setText(c.getApellidoPaterno());
            txt_C_ApellidoMaterno.setText(c.getApellidoMaterno());
        } else {
            JOptionPane.showMessageDialog(null, "No existe el cliente");
            txt_C_Buscar.setText("");
            txt_C_IdUsuario.setText(IdUs);
            txt_C_IdCliente.setText("");
            txt_C_Nombre.setText("");
            txt_C_ApellidoPaterno.setText("");
            txt_C_ApellidoMaterno.setText("");

        }

        btn_C_Guardar.setEnabled(false);
        btn_C_Nuevo.setEnabled(false);
        btn_C_Editar.setEnabled(true);
        btn_C_Eliminar.setEnabled(true);
        btn_C_Cancelar.setEnabled(true);

        txt_C_IdUsuario.setEditable(false);
        txt_C_IdCliente.setEditable(false);
        txt_C_Nombre.setEditable(true);
        txt_C_ApellidoPaterno.setEditable(true);
        txt_C_ApellidoMaterno.setEditable(true);

    }//GEN-LAST:event_btn_C_BuscarActionPerformed

    private void btn_C_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_C_EditarActionPerformed
        //Boton para editar
        //Verificar que el campo txt_C_IdCliente no este vacio

        if (txt_C_IdCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del cliente");
            return;
        }
        //Verificar que el campo txt_C_Nombre no este vacio
        if (txt_C_Nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del cliente");
            return;
        }
        //Verificar que el campo txt_C_ApellidoPaterno no este vacio
        if (txt_C_ApellidoPaterno.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido paterno del cliente");
            return;
        }
        //Verificar que el campo txt_C_ApellidoMaterno no este vacio
        if (txt_C_ApellidoMaterno.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido materno del cliente");
            return;
        }
        //Se crea un objeto de tipo cliente
        c = new cliente();
        //Se le asignan los valores a los atributos del objeto
        c.setId(Integer.parseInt(txt_C_IdCliente.getText()));
        c.setNombre(txt_C_Nombre.getText());
        c.setApellidoPaterno(txt_C_ApellidoPaterno.getText());
        c.setApellidoMaterno(txt_C_ApellidoMaterno.getText());

        vc = new vehiculo_cliente();
        vc.setIdUsuario(txt_C_IdUsuario.getText());
        vc.setIdCliente(txt_C_IdCliente.getText());
        //Se crea un objeto de tipo archivoCliente
        //Cliente_File ac = new Cliente_File();
        //Se llama al metodo guardarCliente y se le pasa como parametro el objeto cliente
        fc.editar(c);
        vcf.editar(vc);
        //Se muestra un mensaje de que se guardo correctamente
        JOptionPane.showMessageDialog(null, "Editado correctamente");
        //Se limpian los campos de texto
        txt_C_IdUsuario.setText(IdUs);
        txt_C_IdCliente.setText("");
        txt_C_Nombre.setText("");
        txt_C_ApellidoPaterno.setText("");
        txt_C_ApellidoMaterno.setText("");

        btn_C_Guardar.setEnabled(false);
        btn_C_Nuevo.setEnabled(true);
        btn_C_Editar.setEnabled(false);
        btn_C_Eliminar.setEnabled(false);
        btn_C_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btn_C_EditarActionPerformed

    private void btn_C_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_C_EliminarActionPerformed
        
        // Obtener el id 
        int id = Integer.parseInt(txt_C_IdCliente.getText());

        // validar que el id si exista
        c = fc.buscar(id);

        // Eliminar el cliente
        fc.eliminar(c);
        JOptionPane.showMessageDialog(null, "Eliminado correctamente");

        vc = new vehiculo_cliente();
        vc.setIdUsuario(txt_C_IdUsuario.getText());
        vc.setIdCliente(txt_C_IdCliente.getText());

        vcf.eliminar(vc);
        cb_vehiculos();

        txt_C_IdUsuario.setText(IdUs);
        txt_C_IdCliente.setText("");
        txt_C_Nombre.setText("");
        txt_C_ApellidoPaterno.setText("");
        txt_C_ApellidoMaterno.setText("");

        btn_C_Guardar.setEnabled(false);
        btn_C_Nuevo.setEnabled(true);
        btn_C_Editar.setEnabled(false);
        btn_C_Eliminar.setEnabled(false);
        btn_C_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btn_C_EliminarActionPerformed

    private void btn_R_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_R_EditarActionPerformed
        Reparaciones_Habilitar();
        btn_R_Guardar.setEnabled(true);
        btn_R_Nuevo.setEnabled(false);
        btn_R_Editar.setEnabled(false);
        btn_R_Eliminar.setEnabled(false);
        btn_R_Cancelar.setEnabled(true);

        ban_reparaciones = true;
    }//GEN-LAST:event_btn_R_EditarActionPerformed

    private void btn_R_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_R_CancelarActionPerformed
        Reparaciones_Deshabilitar();
        cb_R_vehiculos();
        cb_R_Pieza();

        btn_R_Guardar.setEnabled(false);
        btn_R_Nuevo.setEnabled(true);
        btn_R_Editar.setEnabled(false);
        btn_R_Eliminar.setEnabled(false);
        btn_R_Cancelar.setEnabled(false);

        jdt_E_Fecha.setEnabled(false);
        jdt_S_Fecha.setEnabled(false);

        ban_reparaciones = false;

    }//GEN-LAST:event_btn_R_CancelarActionPerformed

    private void btn_R_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_R_EliminarActionPerformed

        rep.setId_ve(Integer.parseInt(cb_R_IdVehiculo.getSelectedItem().toString()));
        rep.setId_pi(Integer.parseInt(cb_R_IdPieza.getSelectedItem().toString()));
        rep.setId_re(Integer.parseInt(txt_R_IdReparacion.getText()));
        rep.setFalla(txt_R_Falla.getText());
        rep.setId_contrl(Integer.parseInt(txt_R_ControlPiezas.getText()));

        SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dformat.format(jdt_E_Fecha.getDate());
        rep.setFecha_e(date);

        date = dformat.format(jdt_S_Fecha.getDate());
        rep.setFecha_s(date);

        try {
            rf.Eliminar_Reparacion(rep);
        } catch (IOException ex) {

        }

        Reparaciones_Habilitar();
        cb_R_vehiculos();
        cb_R_Pieza();

        cb_R_IdVehiculo.setSelectedItem("");
        cb_R_IdPieza.setSelectedItem("");
        txt_R_IdReparacion.setText("");
        txt_R_Falla.setText("");
        txt_R_ControlPiezas.setText("");

        btn_R_Guardar.setEnabled(false);
        btn_R_Nuevo.setEnabled(true);
        btn_R_Editar.setEnabled(false);
        btn_R_Eliminar.setEnabled(false);
        btn_R_Cancelar.setEnabled(false);
    }//GEN-LAST:event_btn_R_EliminarActionPerformed

    private void btn_R_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_R_BuscarActionPerformed
        if (txt_R_Id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID de la reparacion");
            txt_R_Id.setText("");
            cb_R_IdVehiculo.setEditable(true);
            cb_R_IdPieza.setEditable(true);
            cb_R_IdVehiculo.setSelectedItem("Seleccione");
            cb_R_IdPieza.setSelectedItem("Seleccione");
            cb_R_IdPieza.setEditable(false);
            cb_R_IdVehiculo.setEditable(false);
            txt_R_IdReparacion.setText("");
            txt_R_Falla.setText("");
            txt_R_ControlPiezas.setText("");
            jdt_E_Fecha.setDate(null);
            jdt_S_Fecha.setDate(null);

            return;
        }

        if (!ValidaNum(txt_R_Id.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
            txt_R_Id.setText("");
            cb_R_IdVehiculo.setEditable(true);
            cb_R_IdPieza.setEditable(true);
            cb_R_IdVehiculo.setSelectedItem("Seleccione");
            cb_R_IdPieza.setSelectedItem("Seleccione");
            cb_R_IdPieza.setEditable(false);
            cb_R_IdVehiculo.setEditable(false);
            txt_R_IdReparacion.setText("");
            txt_R_Falla.setText("");
            txt_R_ControlPiezas.setText("");
            jdt_E_Fecha.setDate(null);
            jdt_S_Fecha.setDate(null);
            return;
        }

        Reparaciones_Deshabilitar();
        try {
            rep = new reparaciones();
            rep.setId_re(Integer.parseInt(txt_R_Id.getText()));
            rep = rf.BuscarReparacion(rep);

            if (rep != null) {
                cb_R_IdVehiculo.setSelectedItem(String.valueOf(rep.getId_ve()));
                cb_R_IdPieza.setSelectedItem(String.valueOf(rep.getId_pi()));
                txt_R_IdReparacion.setText(String.valueOf(rep.getId_re()));
                txt_R_Falla.setText(rep.getFalla());
                txt_R_ControlPiezas.setText(String.valueOf(rep.getId_contrl()));

                SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
                Date formato = null;
                try {
                    formato = fecha.parse(rep.getFecha_e());
                } catch (ParseException ex) {
                }

                jdt_E_Fecha.setDate(formato);

                try {
                    formato = fecha.parse(rep.getFecha_s());
                } catch (ParseException ex) {
                }

                jdt_S_Fecha.setDate(formato);

                btn_R_Guardar.setEnabled(false);
                btn_R_Nuevo.setEnabled(false);
                btn_R_Editar.setEnabled(true);
                btn_R_Eliminar.setEnabled(true);
                btn_R_Cancelar.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese ID");

                btn_R_Guardar.setEnabled(false);
                btn_R_Nuevo.setEnabled(true);
                btn_R_Editar.setEnabled(false);
                btn_R_Eliminar.setEnabled(false);
                btn_R_Cancelar.setEnabled(false);

                txt_R_Id.setText("");
                cb_R_IdVehiculo.setEditable(true);
                cb_R_IdPieza.setEditable(true);
                cb_R_IdVehiculo.setSelectedItem("Seleccione");
                cb_R_IdPieza.setSelectedItem("Seleccione");
                cb_R_IdPieza.setEditable(false);
                cb_R_IdVehiculo.setEditable(false);
                txt_R_IdReparacion.setText("");
                txt_R_Falla.setText("");
                txt_R_ControlPiezas.setText("");
                jdt_E_Fecha.setDate(null);
                jdt_S_Fecha.setDate(null);

            }

        } catch (FileNotFoundException ex) {

        }

        txt_R_Id.setText("");

    }//GEN-LAST:event_btn_R_BuscarActionPerformed

    private void btn_P_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_P_NuevoActionPerformed
        Piezas_Habilitar();

        int maxID = pf.getMax();

        btn_P_Guardar.setEnabled(true);
        btn_P_Nuevo.setEnabled(false);
        btn_P_Editar.setEnabled(false);
        btn_P_Eliminar.setEnabled(false);
        btn_P_Cancelar.setEnabled(true);

        txt_P_IdPieza.setText(String.valueOf(maxID));
        txt_P_Descripcion.setText("");
        txt_P_Stock.setText("");

    }//GEN-LAST:event_btn_P_NuevoActionPerformed

    private void btn_P_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_P_GuardarActionPerformed

        if ("".equals(txt_P_Descripcion.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese la descripcion de la pieza");
            return;
        }

        if ("".equals(txt_P_Stock.getText())) {
            JOptionPane.showMessageDialog(null, "Indique la cantidad disponible");
            return;
        }

        try {
            pi = new piezas();
            pi.SetPiz(Integer.parseInt(txt_P_IdPieza.getText()));

            if (ban_piezas != true && pf.BuscarPiezas(pi) != null) {
                JOptionPane.showMessageDialog(null, "Ese Id de pieza ya existe");
                return;
            }

            pi.SetPiz(Integer.parseInt(txt_P_IdPieza.getText()));
            pi.SetDescrp(txt_P_Descripcion.getText());
            pi.SetStock(Integer.parseInt(txt_P_Stock.getText()));

            if (ban_piezas != true) {
                pf.Guardar(pi);
                JOptionPane.showMessageDialog(null, "Guardado con Éxito");
            } else {
                ban_piezas = false;
                try {
                    pf.Editar(pi);
                    JOptionPane.showMessageDialog(null, "Editado con Éxito");
                    System.out.println("SI");
                } catch (IOException ex) {

                }
            }

            Piezas_Deshabilitar();

            btn_P_Guardar.setEnabled(false);
            btn_P_Nuevo.setEnabled(true);
            btn_P_Editar.setEnabled(false);
            btn_P_Eliminar.setEnabled(false);
            btn_P_Cancelar.setEnabled(false);

        } catch (FileNotFoundException ex) {

        }

    }//GEN-LAST:event_btn_P_GuardarActionPerformed

    private void btn_P_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_P_CancelarActionPerformed
        Piezas_Deshabilitar();

        btn_P_Guardar.setEnabled(false);
        btn_P_Nuevo.setEnabled(true);
        btn_P_Editar.setEnabled(false);
        btn_P_Eliminar.setEnabled(false);
        btn_P_Cancelar.setEnabled(false);

        ban_piezas = false;

    }//GEN-LAST:event_btn_P_CancelarActionPerformed

    private void btn_P_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_P_EditarActionPerformed
        Piezas_Habilitar();

        btn_P_Guardar.setEnabled(true);
        btn_P_Nuevo.setEnabled(false);
        btn_P_Editar.setEnabled(false);
        btn_P_Eliminar.setEnabled(false);
        btn_P_Cancelar.setEnabled(true);

        ban_piezas = true;
    }//GEN-LAST:event_btn_P_EditarActionPerformed

    private void btn_P_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_P_EliminarActionPerformed

        pi.SetPiz(Integer.parseInt(txt_P_IdPieza.getText()));
        pi.SetDescrp(txt_P_Descripcion.getText());
        pi.SetStock(Integer.parseInt(txt_P_Stock.getText()));

        try {
            pf.Eliminar_Piezas(pi);
        } catch (IOException ex) {

        }

        Piezas_Habilitar();

        txt_P_IdPieza.setText("");
        txt_P_Descripcion.setText("");
        txt_P_Stock.setText("");

        btn_P_Guardar.setEnabled(false);
        btn_P_Nuevo.setEnabled(true);
        btn_P_Editar.setEnabled(false);
        btn_P_Eliminar.setEnabled(false);
        btn_P_Cancelar.setEnabled(false);

    }//GEN-LAST:event_btn_P_EliminarActionPerformed

    private void btn_P_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_P_BuscarActionPerformed
        if (txt_P_Id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID de la pieza");
            txt_P_Id.setText("");
            txt_P_IdPieza.setText("");
            txt_P_Descripcion.setText("");
            txt_P_Stock.setText("");
            return;
        }

        if (!ValidaNum(txt_P_Id.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
            txt_P_Id.setText("");
            txt_P_IdPieza.setText("");
            txt_P_Descripcion.setText("");
            txt_P_Stock.setText("");
            return;
        }

        Piezas_Deshabilitar();
        try {
            pi = new piezas();
            pi.SetPiz(Integer.parseInt(txt_P_Id.getText()));
            pi = pf.BuscarPiezas(pi);

            if (pi != null) {
                txt_P_IdPieza.setText(String.valueOf(pi.getPiz()));
                txt_P_Descripcion.setText(pi.getDescrp());
                txt_P_Stock.setText(String.valueOf(pi.getStock()));

                btn_P_Guardar.setEnabled(false);
                btn_P_Nuevo.setEnabled(false);
                btn_P_Editar.setEnabled(true);
                btn_P_Eliminar.setEnabled(true);
                btn_P_Cancelar.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese ID");

                btn_P_Guardar.setEnabled(false);
                btn_P_Nuevo.setEnabled(true);
                btn_P_Editar.setEnabled(false);
                btn_P_Eliminar.setEnabled(false);
                btn_P_Cancelar.setEnabled(false);

                txt_P_Id.setText("");
                txt_P_IdPieza.setText("");
                txt_P_Descripcion.setText("");
                txt_P_Stock.setText("");
            }

        } catch (FileNotFoundException ex) {

        }

        txt_P_Id.setText("");

    }//GEN-LAST:event_btn_P_BuscarActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new main().setVisible(true);
                } catch (IOException ex) {
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAutentificar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btn_C_Buscar;
    private javax.swing.JButton btn_C_Cancelar;
    private javax.swing.JButton btn_C_Editar;
    private javax.swing.JButton btn_C_Eliminar;
    private javax.swing.JButton btn_C_Guardar;
    private javax.swing.JButton btn_C_Nuevo;
    private javax.swing.JButton btn_C_Salir;
    private javax.swing.JButton btn_P_Buscar;
    private javax.swing.JButton btn_P_Cancelar;
    private javax.swing.JButton btn_P_Editar;
    private javax.swing.JButton btn_P_Eliminar;
    private javax.swing.JButton btn_P_Guardar;
    private javax.swing.JButton btn_P_Nuevo;
    private javax.swing.JButton btn_P_Salir;
    private javax.swing.JButton btn_R_Buscar;
    private javax.swing.JButton btn_R_Cancelar;
    private javax.swing.JButton btn_R_Editar;
    private javax.swing.JButton btn_R_Eliminar;
    private javax.swing.JButton btn_R_Guardar;
    private javax.swing.JButton btn_R_Nuevo;
    private javax.swing.JButton btn_R_Salir;
    private javax.swing.JButton btn_V_Buscar;
    private javax.swing.JButton btn_V_Cancelar;
    private javax.swing.JButton btn_V_Editar;
    private javax.swing.JButton btn_V_Eliminar;
    private javax.swing.JButton btn_V_Guardar;
    private javax.swing.JButton btn_V_Nuevo;
    private javax.swing.JComboBox<String> cbPerfil;
    private javax.swing.JComboBox<String> cb_R_IdPieza;
    private javax.swing.JComboBox<String> cb_R_IdVehiculo;
    private javax.swing.JComboBox<String> cb_V_SeleccioneCliente;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private com.toedter.calendar.JDateChooser jdt_E_Fecha;
    private com.toedter.calendar.JDateChooser jdt_S_Fecha;
    private com.toedter.calendar.JDateChooser jdt_V_Fecha;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPassword1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lbl_C_ApellidoMaterno;
    private javax.swing.JLabel lbl_C_ApellidoPaterno;
    private javax.swing.JLabel lbl_C_Id;
    private javax.swing.JLabel lbl_C_IdCliente;
    private javax.swing.JLabel lbl_C_IdUsuario;
    private javax.swing.JLabel lbl_C_Nombre;
    private javax.swing.JLabel lbl_P_Descripcion;
    private javax.swing.JLabel lbl_P_Id;
    private javax.swing.JLabel lbl_P_IdPieza;
    private javax.swing.JLabel lbl_P_Stock;
    private javax.swing.JLabel lbl_R_ControlPiezas;
    private javax.swing.JLabel lbl_R_Falla;
    private javax.swing.JLabel lbl_R_FechaEntrada;
    private javax.swing.JLabel lbl_R_FechaSalida;
    private javax.swing.JLabel lbl_R_Id;
    private javax.swing.JLabel lbl_R_IdPieza;
    private javax.swing.JLabel lbl_R_IdVehiculo;
    private javax.swing.JLabel lbl_R_Id_Reparacion;
    private javax.swing.JLabel lbl_V_Fecha;
    private javax.swing.JLabel lbl_V_Id;
    private javax.swing.JLabel lbl_V_IdVehiculo;
    private javax.swing.JLabel lbl_V_Marca;
    private javax.swing.JLabel lbl_V_Matricula;
    private javax.swing.JLabel lbl_V_Modelo;
    private javax.swing.JLabel lbl_V_SeleccioneCliente;
    private javax.swing.JPanel pnlClientes;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlPiezas;
    private javax.swing.JPanel pnlReparaciones;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JPanel pnlVehiculos;
    private javax.swing.JTabbedPane tpane;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPaterno;
    private javax.swing.JTextField txtPsw;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txt_C_ApellidoMaterno;
    private javax.swing.JTextField txt_C_ApellidoPaterno;
    private javax.swing.JTextField txt_C_Buscar;
    private javax.swing.JTextField txt_C_IdCliente;
    private javax.swing.JTextField txt_C_IdUsuario;
    private javax.swing.JTextField txt_C_Nombre;
    private javax.swing.JTextField txt_P_Descripcion;
    private javax.swing.JTextField txt_P_Id;
    private javax.swing.JTextField txt_P_IdPieza;
    private javax.swing.JTextField txt_P_Stock;
    private javax.swing.JTextField txt_R_ControlPiezas;
    private javax.swing.JTextField txt_R_Falla;
    private javax.swing.JTextField txt_R_Id;
    private javax.swing.JTextField txt_R_IdReparacion;
    private javax.swing.JTextField txt_V_Buscar;
    private javax.swing.JTextField txt_V_IdVehiculo;
    private javax.swing.JTextField txt_V_Marca;
    private javax.swing.JTextField txt_V_Matricula;
    private javax.swing.JTextField txt_V_Modelo;
    // End of variables declaration//GEN-END:variables
}
