/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.prestacion.tarifario;

import ec.com.asofar.views.compras.*;
import ec.com.asofar.dao.CoDetalleOrdenCompraJpaController;
import ec.com.asofar.dao.CoOrdenComprasJpaController;
import ec.com.asofar.dao.CoProveedoresJpaController;
import ec.com.asofar.dao.InDetalleMovimientoJpaController;
import ec.com.asofar.dao.InKardexJpaController;
import ec.com.asofar.dao.InMotivosJpaController;
import ec.com.asofar.dao.InMovimientosJpaController;
import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.dao.InTipoMovimientoJpaController;
import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InDetalleMovimiento;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InKardexPK;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.Calendario;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author admin1
 */
public class presta extends javax.swing.JDialog {
    
    int x, y;
    SeUsuarios seUsuario;
    SeEmpresa seEmpresa;
    SeSucursal seSucursal;
    
    Boolean selector;
    Boolean[] check;
    
    String[] fechaCaducidad;
    String[] nLote;
    
    Date fecha = null;
    
    Date d = new Date();
    
    CoProveedoresJpaController proveedorController = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoDocumentoJpaController documentoController = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoMovimientoJpaController movimientoController = new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
    InMotivosJpaController motivoController = new InMotivosJpaController(EntityManagerUtil.ObtenerEntityManager());
    InKardexExt kardexExt = new InKardexExt(EntityManagerUtil.ObtenerEntityManager());
    CoOrdenComprasJpaController cabCompraController = new CoOrdenComprasJpaController(EntityManagerUtil.ObtenerEntityManager());
    CoDetalleOrdenCompraJpaController detCompraController = new CoDetalleOrdenCompraJpaController(EntityManagerUtil.ObtenerEntityManager());
    
    InMovimientosJpaController cabMovController = new InMovimientosJpaController(EntityManagerUtil.ObtenerEntityManager());
    InDetalleMovimientoJpaController detMovController = new InDetalleMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
    
    CoOrdenCompras cabCompra;
    CoDetalleOrdenCompra detCompra;
    InMovimientos cabMovimiento;
    
    List<InDetalleMovimiento> listadet = new ArrayList<InDetalleMovimiento>();
    List<CoDetalleOrdenCompra> listadetCompra = new ArrayList<CoDetalleOrdenCompra>();
    
    public presta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        CargarFormulario();
        cargarDetalleCompra();
        
        Timer tiempo = new Timer(100, new presta.horas());
        tiempo.start();
        
    }
    
    public presta(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su, CoOrdenCompras objeto) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        Timer tiempo = new Timer(100, new presta.horas());
        tiempo.start();
        seUsuario = us;
        seEmpresa = em;
        seSucursal = su;
        cabCompra = objeto;
        
        CargarFormulario();
        cargarDetalleCompra();
        
        System.out.println(" " + seUsuario);
        
    }
    
    class horas implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            java.util.Date sistHora = new java.util.Date();
            String pmAm = "HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(pmAm);
            Calendar hoy = Calendar.getInstance();
            
        }
    }
    
    public static String FechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatoFecha.format(fecha);
    }
    
   
    public void CargarDocumento(InMovimientos obj) {
        List<InDetalleMovimiento> detMov = detMovController.findInDetalleMovimientoEntities();
        for (int i = 0; i < detMov.size(); i++) {
            
            if (detMov.get(i).getDescripcion() != null) {
                    TxtDocumento.setText(detMov.get(i).getDescripcion());
            }
            
        }
    }
    
    public void CargarMovimiento(InMovimientos obj) {
//        List<InTipoMovimiento> listcaja = movimientoController.findInTipoMovimientoEntities();
//        for (int i = 0; i < listcaja.size(); i++) {
//            
//            if (listcaja.get(i).getIdTipoMovimiento() != null) {
//                if (listcaja.get(i).getIdTipoMovimiento() == obj.getInTipoMovimiento().getIdTipoMovimiento()) {
//                    TxtMovimiento.setText(listcaja.get(i).getNombreMovimiento());
//                    
//                }
//            }
//            
//        }
    }
    
    public void CargarMotivos(InMovimientos obj) {
//        List<InMotivos> listcaja = motivoController.findInMotivosEntities();
//        for (int i = 0; i < listcaja.size(); i++) {
//            
//            if (listcaja.get(i).getIdMotivo() != null) {
//                if (listcaja.get(i).getIdMotivo() == obj.getInMotivos().getIdMotivo()) {
//                    TxtMotivo.setText(listcaja.get(i).getNombre());
//                    
//                }
//            }
//            
//        }
    }
    
    public void CargarFormulario() {
        List<InDetalleMovimiento> listCab = new ArrayList<InDetalleMovimiento>();
        
        listCab = detMovController.findInDetalleMovimientoEntities();
        
        for (int i = 0; i < listCab.size(); i++) {
            
            BigInteger value = listCab.get(i).getIdOrdenCompra();
            
            if (value != null) {
                
                if ((listCab.get(i).getIdOrdenCompra()).intValue() == cabCompra.getCoOrdenComprasPK().getIdOrdenCompra()) {
                    cabMovimiento = listCab.get(i);
                }
            }
        }
        List<InDetalleMovimiento> listDet = new ArrayList<InDetalleMovimiento>();
        listDet = detMovController.findInDetalleMovimientoEntities();
        
        check = new Boolean[listDet.size()];  // inicializar el Boolean segun la lista
        fechaCaducidad = new String[listDet.size()];
        nLote = new String[listDet.size()];
        
        for (int i = 0; i < listDet.size(); i++) {
            
            check[i] = false; // setear valor falso

            fechaCaducidad[i] = "--SELECCIONE--";
            nLote[i] = "";
            
            if (listDet.get(i).getInDetalleMovimientoPK().getIdMovimientos() == (cabMovimiento.getInMovimientosPK().getIdMovimientos()) && listDet.get(i).getEstado().equals("A")) {
                listadet.add(listDet.get(i));
                
            }
        }
        
        CargarDocumento(cabMovimiento);
        CargarMovimiento(cabMovimiento);
        CargarMotivos(cabMovimiento);
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        
        Tablas.listarDetalleRecepcion(listadet, jTable1, nLote, fechaCaducidad);
        
    }
    
    public void cargarDetalleCompra() {
        List<CoDetalleOrdenCompra> lista = new ArrayList<CoDetalleOrdenCompra>();
        lista = detCompraController.findCoDetalleOrdenCompraEntities();
        for (int i = 0; i < lista.size(); i++) {
            
            if (lista.get(i).getCoOrdenCompras().getCoOrdenComprasPK().getIdOrdenCompra() == cabCompra.getCoOrdenComprasPK().getIdOrdenCompra()) {
                
                listadetCompra.add(lista.get(i));
                
            }
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BtnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        BtnAprovar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(java.awt.Color.red);
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRESTACION");
        jLabel1.setOpaque(true);
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        BtnCancelar.setBackground(new java.awt.Color(254, 254, 254));
        BtnCancelar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        BtnCancelar.setForeground(new java.awt.Color(1, 1, 1));
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Cancelar_Mesa de trabajo 1.jpg"))); // NOI18N
        BtnCancelar.setText("CANCELAR");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        BtnAprovar1.setBackground(new java.awt.Color(254, 254, 254));
        BtnAprovar1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        BtnAprovar1.setForeground(new java.awt.Color(1, 1, 1));
        BtnAprovar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/aceptar_Mesa de trabajo 1.jpg"))); // NOI18N
        BtnAprovar1.setText("GUARDAR PRESTACION");
        BtnAprovar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAprovar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnAprovar1)
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAprovar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        int r = JOptionPane.showConfirmDialog(null, "¿Desea Regresar?", "", JOptionPane.YES_NO_OPTION);
        
        if (r == JOptionPane.YES_OPTION) {
            setVisible(false);
            
        } else {
            
        }
    }//GEN-LAST:event_BtnCancelarActionPerformed
    

    private void BtnAprovar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAprovar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAprovar1ActionPerformed
    
    public void cargarBodega() {
        try {
            
            for (int i = 0; i < listadet.size(); i++) {
                String valor = (String) jTable1.getModel().getValueAt(i, 7);
                
                InBodega bodega = ObtenerDTO.ObtenerInBodega(valor);
                
                listadet.get(i).setIdBodegaDestino(BigInteger.valueOf(bodega.getInBodegaPK().getIdBodega()));
                
            }
            
        } catch (Exception e) {
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
            java.util.logging.Logger.getLogger(presta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(presta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(presta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(presta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                presta dialog = new presta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BtnAprovar1;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
