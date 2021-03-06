/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.producto;

import ec.com.asofar.dao.PrEmpaqueJpaController;
import ec.com.asofar.daoext.ValidarDTO;
import ec.com.asofar.dto.PrEmpaque;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author admin1
 */
public class ActualizarEmpaque extends javax.swing.JDialog {

    /**
     * Creates new form ActualizarEmpaque
     */
    PrEmpaque listEmpaq;
    PrEmpaqueJpaController empcontrol = new PrEmpaqueJpaController(EntityManagerUtil.ObtenerEntityManager());
//    PrEmpaque emp=new PrEmpaque();
    List<PrEmpaque> listEmp = empcontrol.findPrEmpaqueEntities();
    PrEmpaque emp = new PrEmpaque();
    Date d = new Date();
    SeUsuarios usuario;
    SeEmpresa empresa;
    SeSucursal sucursal;

    public ActualizarEmpaque(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su, PrEmpaque emp) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        usuario = us;
        empresa = em;
        sucursal = su;
        listEmpaq = emp;
        boton();
    }

    public ActualizarEmpaque(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        boton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setText("NOMBRE:");

        jButton2.setBackground(new java.awt.Color(254, 254, 254));
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(1, 1, 1));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Cancelar_Mesa de trabajo 1.jpg"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(java.awt.Color.red);
        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("EMPAQUE");
        jLabel2.setOpaque(true);
        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel2MouseDragged(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        boton.setBackground(new java.awt.Color(254, 254, 254));
        boton.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        boton.setForeground(new java.awt.Color(1, 1, 1));
        boton.setText("AAAAAAAAAA");
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/GUARDAR_Mesa de trabajo 1.png"))); // NOI18N
        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged
        //        Point point = MouseInfo.getPointerInfo().getLocation();
        //        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel2MouseDragged

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        //        x = evt.getX();
        //        y = evt.getY();
    }//GEN-LAST:event_jLabel2MousePressed

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
        String bot = boton.getText();
        System.out.println("botonn: " + bot);
        if ("ACTIVAR".equals(bot)) {
            activar();
            setVisible(false);
        } else {
            desactivar();
            setVisible(false);
        }
    }//GEN-LAST:event_botonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actualizar();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void boton() {
        String est = listEmpaq.getEstado();
        if ("A".equals(est)) {
            boton.setText("DESACTIVAR");
        }
        if ("I".equals(est)) {
            boton.setText("ACTIVAR");
        }
        
        txtNombre.setText(listEmpaq.getNombreEmpaque());
    }

    public void actualizar() {
        try {
            boolean valor = ValidarDTO.ValidarEmpaques(txtNombre.getText());
            if (valor == true) {
                JOptionPane.showMessageDialog(this, "el empaque ya existe!");
            }
            else if(txtNombre.getText().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Escriba un nombre!");
            }
            else {

            listEmpaq.setFechaActualizacion(d);
            listEmpaq.setUsuarioActualizacion(usuario.getUsuario());
            empcontrol.edit(listEmpaq);
            JOptionPane.showMessageDialog(null, "Nuevo empaque actualizado ");
            setVisible(false);
            }

        } catch (Exception e) {
            System.out.println("Error al guardar prod " + e.getMessage());
            System.err.print(e);
        }
    }

    public void desactivar() {
        try {
            listEmpaq.setEstado("I");
            listEmpaq.setFechaActualizacion(d);
            empcontrol.edit(listEmpaq);
            JOptionPane.showMessageDialog(null, "Empaque actualizado ");
//            }

        } catch (Exception e) {
            System.out.println("Error al guardar prod " + e.getMessage());
            System.err.print(e);
        }
    }
    public void activar() {
        try {
            listEmpaq.setEstado("A");
            listEmpaq.setFechaActualizacion(d);
            empcontrol.edit(listEmpaq);
            JOptionPane.showMessageDialog(null, "Empaque actualizado ");
//            }

        } catch (Exception e) {
            System.out.println("Error al guardar prod " + e.getMessage());
            System.err.print(e);
        }
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
            java.util.logging.Logger.getLogger(ActualizarEmpaque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarEmpaque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarEmpaque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarEmpaque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ActualizarEmpaque dialog = new ActualizarEmpaque(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton boton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
