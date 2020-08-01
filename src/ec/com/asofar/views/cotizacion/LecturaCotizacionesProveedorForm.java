/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.cotizacion;

import ec.com.asofar.dao.CoCotizacionesPorProveedorJpaController;
import ec.com.asofar.daoext.Cotizacion_cabExt;
import ec.com.asofar.daoext.DetalleCotizacionExt;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.dto.CoDetItemsCotizacion;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin1
 */
public class LecturaCotizacionesProveedorForm extends javax.swing.JDialog {

    /**
     * Creates new form cotizacionesProveedorForm
     */
    CoCotizacionesPorProveedor obj = new CoCotizacionesPorProveedor();
    CoDetItemsCotizacion objt = new CoDetItemsCotizacion();
    List<CoDetalleCotizacionPorProveedor> listar;
    List<CoCotizacionesPorProveedor> lista;
    CoCotizacionesPorProveedorJpaController ptm = new CoCotizacionesPorProveedorJpaController(EntityManagerUtil.ObtenerEntityManager());
    CoCotizacionesPorProveedorJpaController env = new CoCotizacionesPorProveedorJpaController(EntityManagerUtil.ObtenerEntityManager());
    String valor = "";
    Cotizacion_cabExt guardar=new Cotizacion_cabExt();
    DetalleCotizacionExt guarda = new DetalleCotizacionExt();

    public LecturaCotizacionesProveedorForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lista = ptm.findCoCotizacionesPorProveedorEntities();
//        Tablas.tablaCotizacionPorProveedor(jtCabecera, lista);
        Tablas.TablaCotizacionPorProveedorDos(jtCabecera, lista);

    }

    public LecturaCotizacionesProveedorForm() {
//        super(parent, modal);
        initComponents();

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
        jScrollPane2 = new javax.swing.JScrollPane();
        jtDetalle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCabecera = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jtDetalle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jtDetalle.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jtDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtDetalle.setRowHeight(22);
        jScrollPane2.setViewportView(jtDetalle);

        jLabel1.setBackground(java.awt.Color.red);
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("COTIZACION");
        jLabel1.setOpaque(true);

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/aceptar_Mesa de trabajo 1.jpg"))); // NOI18N
        jButton1.setText("ACEPTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(254, 254, 254));
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(1, 1, 1));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jtCabecera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtCabecera.setRowHeight(25);
        jtCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtCabeceraMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCabeceraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtCabecera);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 470, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
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

    private void jtCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCabeceraMousePressed
        if (evt.getClickCount() == 1) {
            String valor = jtCabecera.getValueAt(jtCabecera.getSelectedRow(), 0).toString();
//            CoCotizacionesPorProveedor coti = ObtenerDTO.ObtenerCoCotizacionesPorProveedor(valor);
//            Tablas.TablaDetallePorProveerdo(coti.getCoDetalleCotizacionPorProveedorList(), jtDetalle);
        }

    }//GEN-LAST:event_jtCabeceraMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    String estado = "";
    
    private void jtCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCabeceraMouseClicked
//public void pruebaCabecera() {
//        try {
////            if (evt.getClickCount() >= 1) {
//            CoCotizacionesPorProveedor cotpro = new CoCotizacionesPorProveedor();
//            cotpro=obj;
//            int t = jtCabecera.getRowCount();
////            String dato = "";
//            boolean dato = false;
//            for (int i = 0; i < t; i++) {
//                dato = (boolean)jtCabecera.getValueAt(i, 7);
//                //System.out.println("estado: " + est);
//                CoCotizacionesPorProveedor tdoc=lista.get(i);
//                if (dato) {
//                    tdoc.setEstado("A");
//                } else {
//                    tdoc.setEstado("I");
//                }
//            }
//            try {
//                guardar.guardarEstadoCotizacion(lista);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            Logger.getLogger(cotizacionesProveedorForm.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
    }//GEN-LAST:event_jtCabeceraMouseClicked

    public void prueba() {
        try {
            int t = jtDetalle.getRowCount();
            boolean dato = false;
            for (int i = 0; i < t; i++) {
                dato = (boolean)jtDetalle.getValueAt(i, 5);
                CoDetalleCotizacionPorProveedor tdoc=listar.get(i);
                if (dato) {
                    tdoc.setEstado("A");
                } else {
                    tdoc.setEstado("I");
                }
            }
            for (int i = 0; i < t; i++) {
                dato = (boolean)jtCabecera.getValueAt(i, 7);
                
            }
            try {
                guarda.guardarEstadoCotizacionDetalle(listar);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Logger.getLogger(LecturaCotizacionesProveedorForm.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        prueba();
    }//GEN-LAST:event_jButton1ActionPerformed

//    public void pruebaDetalleFuncional() {
//        try {
//            int t = jtDetalle.getRowCount();
//            boolean dato = false;
//            for (int i = 0; i < t; i++) {
//                dato = (boolean)jtDetalle.getValueAt(i, 5);
//                CoDetalleCotizacionPorProveedor tdoc=listar.get(i);
//                if (dato) {
//                    tdoc.setEstado("A");
//                } else {
//                    tdoc.setEstado("I");
//                }
//            }
//            
//            try {
//                guarda.guardarEstadoCotizacionDetalle(listar);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            Logger.getLogger(cotizacionesProveedorForm.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
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
            java.util.logging.Logger.getLogger(LecturaCotizacionesProveedorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturaCotizacionesProveedorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturaCotizacionesProveedorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturaCotizacionesProveedorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LecturaCotizacionesProveedorForm dialog = new LecturaCotizacionesProveedorForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtCabecera;
    private javax.swing.JTable jtDetalle;
    // End of variables declaration//GEN-END:variables
}
