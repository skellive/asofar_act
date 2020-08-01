/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.clientes;

import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dao.SeContactosClientesJpaController;
import ec.com.asofar.dao.SeLocalidadClienteJpaController;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeContactosClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeTipoIdentificacion;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.ClaseReporte;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author admin1
 */
public class consulta_cliente_inactivos extends javax.swing.JDialog {
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    /**
     * Creates new form consulta_cliente_inactivos
     */
    int x, y;
    String valor = "";
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    SeClientes cli;
    SeTipoIdentificacion tident;
    List<SeClientes> Cliente;
    SeClientesJpaController Cc = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeLocalidadCliente> LocalidadCliente;
    SeLocalidadClienteJpaController Lc = new SeLocalidadClienteJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeClientes> lista = Cc.findSeClientesEntities();
    SeClientes Client = new SeClientes();
    List<SeContactosClientes> ContactoCliente;
    SeContactosClientesJpaController Ccl = new SeContactosClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeLocalidadCliente> lista1 = Lc.findSeLocalidadClienteEntities();
    SeLocalidadCliente LocaliClient = new SeLocalidadCliente();

    public consulta_cliente_inactivos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public consulta_cliente_inactivos(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        MostrarClientes();
        usu = us;
        emp = em;
        suc = su;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tba_clientes = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tba_localidad = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tba_contacto = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtbusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tba_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_clientesMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tba_clientes);

        jButton2.setBackground(new java.awt.Color(254, 254, 254));
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(1, 1, 1));
        jButton2.setText("ACTIVAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "LOCALIDAD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tba_localidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_localidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_localidadMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tba_localidadMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_localidadMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tba_localidad);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "CONTACTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tba_contacto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_contacto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_contactoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_contactoMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tba_contacto);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel3.setBackground(java.awt.Color.red);
        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CLIENTES INACTIVOS");
        jLabel3.setOpaque(true);
        jLabel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel3MouseDragged(evt);
            }
        });
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("BUSCAR:");

        txtbusqueda.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusquedaActionPerformed(evt);
            }
        });
        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyTyped(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnimprimir.setBackground(new java.awt.Color(254, 254, 254));
        btnimprimir.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnimprimir.setForeground(new java.awt.Color(1, 1, 1));
        btnimprimir.setText("IMPRIMIR");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void MostrarClientes() {
        try {

            Cliente = Cc.findSeClientesEntities();
            Tablas.TablaClientesInactivos(Cliente, tba_clientes);
        } catch (Exception e) {
        }
    }
    private void tba_clientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_clientesMousePressed

    }//GEN-LAST:event_tba_clientesMousePressed
    public SeClientes devuelveObjeto(Long id, List<SeClientes> listabod) {
        SeClientes doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getIdClientes(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }
    private void tba_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_clientesMouseClicked
        int id = 0;
        if (evt.getClickCount() == 1) {
            id = tba_clientes.getSelectedRow();
            Client = devuelveObjeto(Long.valueOf(tba_clientes.getValueAt(id, 0).toString()), lista);

            if (Client != null) {
                LocalidadCliente = Lc.findSeLocalidadClienteEntities();
                Tablas.TablaLocalidadCliente(LocalidadCliente, tba_localidad, Client);

                //                Tablas.TablaContactoCliente(ContactoCliente, tba_contacto, LocaliClient);
                //                tba_contacto.clearSelection();
            }
        }
    }//GEN-LAST:event_tba_clientesMouseClicked

    private void tba_localidadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_localidadMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_localidadMousePressed
 public SeLocalidadCliente devuelveObjeto2(Long id, List<SeLocalidadCliente> listabod) {
        SeLocalidadCliente doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getIdLocalidadCliente(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }
    private void tba_localidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_localidadMouseClicked
        int id = 0;
        if (evt.getClickCount() == 1) {
            id = tba_localidad.getSelectedRow();
            LocaliClient = devuelveObjeto2(Long.valueOf(tba_localidad.getValueAt(id, 0).toString()), lista1);

            if (LocaliClient != null) {
                ContactoCliente = Ccl.findSeContactosClientesEntities();
                Tablas.TablaContactoCliente(ContactoCliente, tba_contacto, LocaliClient);
            }
        }
    }//GEN-LAST:event_tba_localidadMouseClicked

    private void tba_localidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_localidadMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_localidadMouseEntered

    private void tba_contactoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_contactoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_contactoMousePressed

    private void tba_contactoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_contactoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_contactoMouseClicked

    private void jLabel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel3MouseDragged

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel3MousePressed

    private void txtbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusquedaActionPerformed

    private void txtbusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String text = ("" + c).toUpperCase();
            c = text.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtbusquedaKeyTyped

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
        valor = txtbusqueda.getText();
        Tablas.filtro(valor, tba_clientes);
    }//GEN-LAST:event_txtbusquedaKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int id = 0;
        if (tba_clientes.getSelectedRow() >= 0) {
            id = tba_clientes.getSelectedRow();
            Client = devuelveObjeto(Long.valueOf(tba_clientes.getValueAt(id, 0).toString()), lista);

            if (Client != null) {
                int OP = JOptionPane.showConfirmDialog(null, "¿Desea Activar este Cliente?", "", JOptionPane.YES_NO_OPTION);
                if (OP == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < Cliente.size(); i++) {
                     if (Client.getIdClientes().equals(Cliente.get(i).getIdClientes())) {
                         System.out.println("************");
                        Client.setEstado("A");
                        try {
                            Cc.edit(Client);
                            JOptionPane.showMessageDialog(null, " CLIENTE ABILITADO");
                            Tablas.VaciarTabla(tba_contacto);
                            Tablas.VaciarTabla(tba_localidad);
                        } catch (Exception ex) {
                            Logger.getLogger(contacto_agregar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                       
                    }
                } else {
                }
                MostrarClientes();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN CLIENTE PARA ABILITAR");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        ArrayList lista = new ArrayList();   
        if(tba_clientes.getSelectedRowCount()==1){
            if(tba_localidad.getSelectedRowCount()!=0){
                for(int i=0;i<tba_localidad.getSelectedRowCount();i++){
                   ClaseReporte creporte = new ClaseReporte(
                   tba_clientes.getValueAt(tba_clientes.getSelectedRow(),0).toString(),
                   tba_clientes.getValueAt(tba_clientes.getSelectedRow(),1).toString(),
                   tba_clientes.getValueAt(tba_clientes.getSelectedRow(),2).toString(),
                   tba_clientes.getValueAt(tba_clientes.getSelectedRow(),3).toString(),                   
                   tba_localidad.getValueAt(tba_localidad.getSelectedRow(),0).toString(),
                   tba_localidad.getValueAt(tba_localidad.getSelectedRow(),1).toString(),
                   tba_localidad.getValueAt(tba_localidad.getSelectedRow(),2).toString(),
                   tba_localidad.getValueAt(tba_localidad.getSelectedRow(),3).toString(),                   
                   tba_contacto.getValueAt(i,0).toString(),
                   tba_contacto.getValueAt(i,1).toString(),
                   String.valueOf(tba_contacto.getValueAt(i,2)),
                   tba_contacto.getValueAt(i,3).toString(),
                   tba_contacto.getValueAt(i,4).toString());
                   lista.add(creporte);
                    }
                   try {
                    JasperReport reporte = (JasperReport)JRLoader.loadObject(System.getProperty("user.dir")+"/Reportes/consulta_clienteCOMPLETO.jasper");
                    JasperPrint jprint = JasperFillManager.fillReport(reporte,null,new JRBeanCollectionDataSource(lista));
                    JRViewer jviewer = new JRViewer(jprint);
                    JDialog ventana = new JDialog();
                    ventana.add(jviewer);
                    ventana.setVisible(true);
                    ventana.setSize(new Dimension(ancho/2,alto/2));
                    ventana.setLocationRelativeTo(null);
                    jviewer.setFitWidthZoomRatio();
                    
                } catch (JRException ex) {
                    Logger.getLogger(consulta_cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else{
                JOptionPane.showMessageDialog(null,"Por favor seleccione una Localidad");
            }                                        
        }else{
                for(int i=0;i<tba_clientes.getRowCount();i++){
                    ClaseReporte creporte = new ClaseReporte(
                    tba_clientes.getValueAt(i,0).toString(),
                    tba_clientes.getValueAt(i,1).toString(),
                    tba_clientes.getValueAt(i,2).toString(),
                    (String)tba_clientes.getValueAt(i,3));
                    lista.add(creporte);
                }
                try {
                    JasperReport reporte = (JasperReport)JRLoader.loadObject(System.getProperty("user.dir")+"/Reportes/consulta_clienteLISTA.jasper");
                    JasperPrint jprint = JasperFillManager.fillReport(reporte,null,new JRBeanCollectionDataSource(lista));
                    JRViewer jviewer = new JRViewer(jprint);
                    JDialog ventana = new JDialog();
                    ventana.add(jviewer);
                    ventana.setVisible(true);
                    ventana.setSize(new Dimension(ancho/2,alto/2));
                    ventana.setLocationRelativeTo(null);
                    jviewer.setFitWidthZoomRatio();
                    
                }catch (JRException ex) {
                    Logger.getLogger(consulta_cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
        }        
    }//GEN-LAST:event_btnimprimirActionPerformed

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
            java.util.logging.Logger.getLogger(consulta_cliente_inactivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente_inactivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente_inactivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente_inactivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                consulta_cliente_inactivos dialog = new consulta_cliente_inactivos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tba_clientes;
    private javax.swing.JTable tba_contacto;
    private javax.swing.JTable tba_localidad;
    private javax.swing.JTextField txtbusqueda;
    // End of variables declaration//GEN-END:variables
}