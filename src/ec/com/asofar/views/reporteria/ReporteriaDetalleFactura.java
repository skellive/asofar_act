/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.reporteria;

import ec.com.asofar.dao.InDetalleMovimientoJpaController;
import ec.com.asofar.dao.InKardexJpaController;
import ec.com.asofar.dao.InMotivosJpaController;
import ec.com.asofar.dao.InMovimientosJpaController;
import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.dao.InTipoMovimientoJpaController;
import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dao.SeLocalidadClienteJpaController;
import ec.com.asofar.dao.SeTipoIdentificacionJpaController;
import ec.com.asofar.dao.VeFacturaDetalleJpaController;
import ec.com.asofar.dao.VeFacturaJpaController;
import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.daoext.MovimientosDaoExt;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.daoext.ReporteComprasDTO;
import ec.com.asofar.daoext.ReporteDetalleComprasDTO;
import ec.com.asofar.daoext.ReporteDetalleFacturaDTO;
import ec.com.asofar.daoext.ReporteFacturaDTO;
import ec.com.asofar.daoext.ReporteProveedorDTO;
import ec.com.asofar.daoext.ReporteriaExt;
import ec.com.asofar.daoext.SeClientesExt;
import ec.com.asofar.dto.InDetalleMovimiento;
import ec.com.asofar.dto.InDetalleMovimientoPK;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InKardexPK;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeTipoIdentificacion;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.dto.VeFacturaPK;
import ec.com.asofar.util.ClaseReporte;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author ineval
 */
public class ReporteriaDetalleFactura extends javax.swing.JDialog {

    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int x, y;
    List<SeClientes> Cliente;
    SeClientesJpaController Cc = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());

    BigDecimal VGiva = null, VGtotal = null, VGdescuento = null;
    ReporteFacturaDTO objeto = null;
    ReporteriaExt rep = new ReporteriaExt();
    List<ReporteDetalleFacturaDTO> listaDetalle = null;
    SeClientesJpaController client_Controler = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeTipoIdentificacionJpaController tipo_doc_Control = new SeTipoIdentificacionJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    Date d = new Date();

    InKardex objetoKardex = new InKardex();
    InTipoDocumento documento = new InTipoDocumento();

    /**
     * Creates new form Reporte_DetalleCompra
     */
    public ReporteriaDetalleFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ReporteriaDetalleFactura(java.awt.Frame parent, boolean modal,
            ReporteFacturaDTO obj, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        objeto = obj;
        usu = us;
        emp = em;
        suc = su;
        formularioProveedor();
        llenar_detalles();
        totaless();
        verificarEstadoDevuelto();

    }

    public void verificarEstadoDevuelto() {
        if (objeto.getEstado().equals("D")) {
            btnAnular.setEnabled(false);
        }
    }

    public void formularioProveedor() {
        System.out.println("id " + objeto.getId_cliente());
        if (objeto.getId_cliente() == 1) {
            consFinal();
            txt_forma_pago.setText(objeto.getForma_pago());
            System.out.println("consu ");
        } else {
            System.out.println("normak");
            SeClientes cliente = new SeClientes();
            SeTipoIdentificacion tip_ident = new SeTipoIdentificacion();
            SeLocalidadCliente local_client = new SeLocalidadCliente();
            cliente = client_Controler.findSeClientes(Long.valueOf(objeto.getId_cliente().toString()));
            tip_ident = tipo_doc_Control.findSeTipoIdentificacion(cliente.getIdTipoIndentificacion().getIdTipoIdentificacion());

            txt_forma_pago.setText(objeto.getForma_pago());
            System.out.println("null " + objeto.getForma_pago());
            txtCodigoCliente.setText(cliente.getIdClientes().toString());
            txtNombre.setText(cliente.getPrimerNombre() + " " + cliente.getSegundoNombre());
            txtapellidos.setText(cliente.getPrimerApellido() + " " + cliente.getSegundoApellido());
            txtTelefono.setText(rep.buscarCelular(cliente.getIdClientes()));
            txttipodoc.setText(tip_ident.getNombreIdentificacion());
            txtn_doc.setText(cliente.getNumeroIdentificacion());
            txtDireccion.setText(rep.buscarLocalidad(cliente.getIdClientes()));
            txtcorreo.setText(rep.buscarCorreo(cliente.getIdClientes()));
            System.out.println("**");
            txt_N_VENTA.setText(String.format("%06d", objeto.getId_factura()));
//            txt_N_VENTA.setText(objeto.getId_factura().toString());
            txtFechaCreacion.setText(objeto.getFecha_facturacion().toString());
            txtCaja.setText(objeto.getNombre_caja());
            txSucursal.setText(objeto.getNombre_comercial_suc());
        }
    }

    public void consFinal() {
        Cliente = Cc.findSeClientesEntities();
        for (int i = 0; i < Cliente.size(); i++) {

            if (Cliente.get(i).getIdClientes() == 1) {
//                System.out.println("clie " + Cliente.get(i).getPrimerNombre());
                txtCaja.setText(objeto.getNombre_caja());
                txSucursal.setText(suc.getNombreComercial());
                txtFechaCreacion.setText(objeto.getFecha_facturacion().toString());
                txt_N_VENTA.setText(String.format("%06d", objeto.getId_factura()));
                txtNombre.setText(Cliente.get(i).getPrimerNombre());
                txtapellidos.setText(Cliente.get(i).getPrimerApellido());
                txtn_doc.setText("9999999999999");
                txtCodigoCliente.setText(Cliente.get(i).getIdClientes().toString());
                txttipodoc.setText("********************************");
                txtcorreo.setText("********************************");
                txtDireccion.setText("********************************");
                txtTelefono.setText("********************************");
            }
        }
    }

    public void llenar_detalles() {
        listaDetalle = rep.listadoDetallesFactura(objeto);
        Tablas.listarReporteDetalleFactura(listaDetalle, tbaListaComprasB);
    }

    public void totaless() {
        Double total_iva = 0.00;
        Double total_descuento = 0.00;
        Double total_total = 0.00;

        // Double total = 0.00;
        for (int i = 0; i < listaDetalle.size(); i++) {
            for (int j = 0; j < tbaListaComprasB.getRowCount(); j++) {
                if (tbaListaComprasB.getValueAt(j, 0).toString().equals(listaDetalle.get(i).getId_factura_detalle().toString())) {
                    // System.out.println(tbaReporteCompra.getValueAt(j, 0).toString() + " " + (itemList.get(i).getId_orden_compra().toString()));
                    total_iva = total_iva + listaDetalle.get(i).getValor_iva();
                    txtIva.setText(rep.formatoNumero(total_iva.toString()));
                }
            }
        }
        for (int i = 0; i < listaDetalle.size(); i++) {
            for (int j = 0; j < tbaListaComprasB.getRowCount(); j++) {
                if (tbaListaComprasB.getValueAt(j, 0).toString().equals(listaDetalle.get(i).getId_factura_detalle().toString())) {
                    // System.out.println(tbaReporteCompra.getValueAt(j, 0).toString() + " " + (itemList.get(i).getId_orden_compra().toString()));
                    total_descuento = total_descuento + listaDetalle.get(i).getValor_descuento();
                    txtDescuento.setText(rep.formatoNumero(total_descuento.toString()));
                }
            }
        }
        for (int i = 0; i < listaDetalle.size(); i++) {
            for (int j = 0; j < tbaListaComprasB.getRowCount(); j++) {
                if (tbaListaComprasB.getValueAt(j, 0).toString().equals(listaDetalle.get(i).getId_factura_detalle().toString())) {
                    // System.out.println(tbaReporteCompra.getValueAt(j, 0).toString() + " " + (itemList.get(i).getId_orden_compra().toString()));
                    total_total = total_total + listaDetalle.get(i).getValor_total();
                    txtTotal.setText(rep.formatoNumero(total_total.toString()));
                }
            }
        }
    }
//    public ReporteriaDetalleCompras(java.awt.Frame parent, boolean modal, JoinListarNotaPedidosCabecera Obj) {
//        super(parent, modal);
//        setUndecorated(true);
//        initComponents();
//        setLocationRelativeTo(null);
//        llenarProveedor();
//        String id_cab = txt_Numero.getText().toString();
//        lista3 = crud.listarDetalleNotaPedido(1, id_cab);
//        Tablas.cargarJoinRegistroDetalleCompras(tbaListaComprasB, lista3);
//        Total();
//        TotalIVA();
//        TotalDescuento();
//    }

//    public void TotalIVA() {
//        BigDecimal Total1Iva = new BigDecimal("0.0000");
//
//        for (int i = 0; i < tbaListaComprasB.getRowCount(); i++) {
//            BigDecimal Iva1 = lista3.get(i).getIva();
//            Total1Iva = Total1Iva.add(Iva1);
////            totalIva = redondearDecimales(totalIva, 2);
//        }
//        VGiva = BigDecimal.valueOf(Double.parseDouble(removeScientificNotation(Total1Iva.setScale(7, BigDecimal.ROUND_HALF_UP).toString())));
//        txtIva.setText(removeScientificNotation(Total1Iva.setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
//        txtIva.setText(Formato_Numeros.formatoNumero(Total1Iva.toString()));
//    }
//
//    public void TotalDescuento() {
//        BigDecimal TotalDescuento = new BigDecimal("0.0000");
//        for (int i = 0; i < tbaListaComprasB.getRowCount(); i++) {
//            BigDecimal descuento = lista3.get(i).getDescuento();
//            TotalDescuento = TotalDescuento.add(descuento);
////            TotalDescuento = redondearDecimales(TotalDescuento, 2);
//        }
//        VGdescuento = BigDecimal.valueOf(Double.parseDouble(removeScientificNotation(TotalDescuento.setScale(7, BigDecimal.ROUND_HALF_UP).toString())));
//        txtDescuento.setText(removeScientificNotation(TotalDescuento.setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
//        txtDescuento.setText(Formato_Numeros.formatoNumero(TotalDescuento.toString()));
//    }
//
//    public void Total() {
//        BigDecimal Total_ = new BigDecimal("0.0000");
//        for (int i = 0; i < tbaListaComprasB.getRowCount(); i++) {
//            BigDecimal total = lista3.get(i).getTotal();
//            Total_ = Total_.add(total);
//
//        }
//        VGtotal = BigDecimal.valueOf(Double.parseDouble(removeScientificNotation(Total_.setScale(7, BigDecimal.ROUND_HALF_UP).toString())));
////        txtTotal.setText(Total_.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//        txtTotal.setText(Formato_Numeros.formatoNumero(Total_.toString()));
//    }
//
//    public ListarJoinProveedor buscarObjeto(String cedula, ArrayList<ListarJoinProveedor> lis) {
//        ListarJoinProveedor pro = new ListarJoinProveedor();
//        pro = null;
//        //int ced = Integer.valueOf(cedula);
//        for (int i = 0; i < lis.size(); i++) {
//            if (cedula.equals(lis.get(i).getCedula_ruc())) {
//                pro = lis.get(i);
//            }
//        }
//        return pro;
//    }
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
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttipodoc = new javax.swing.JTextField();
        txtn_doc = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txSucursal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_forma_pago = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_N_VENTA = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnSalir2 = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbaListaComprasB = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnAnular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DATOS DEL CLIENTE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("N° DOC.");

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel13.setText("CODIGO:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("DIRECCION:");

        txtcorreo.setEditable(false);
        txtcorreo.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setText("CORREO:");

        txtapellidos.setEditable(false);
        txtapellidos.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapellidosKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel14.setText("APELLIDOS:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setText("DOCUMENTO:");

        txttipodoc.setEditable(false);
        txttipodoc.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txttipodoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttipodocActionPerformed(evt);
            }
        });

        txtn_doc.setEditable(false);
        txtn_doc.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        txtDireccion.setEditable(false);
        txtDireccion.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("TELEFONO:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("NOMBRES:");

        txtCodigoCliente.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtCodigoCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtFechaCreacion.setEditable(false);
        txtFechaCreacion.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel9.setText("FECHA :");

        txSucursal.setEditable(false);
        txSucursal.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel10.setText("SUCURSAL:");

        txtCaja.setEditable(false);
        txtCaja.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel11.setText("N° CAJA:");

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel16.setText("FORMA PAGO :");

        txt_forma_pago.setEditable(false);
        txt_forma_pago.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("#");

        txt_N_VENTA.setEditable(false);
        txt_N_VENTA.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel14)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addGap(114, 114, 114)
                        .addComponent(txt_N_VENTA, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel11)
                                    .addGap(28, 28, 28))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel10)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtn_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_forma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_N_VENTA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(txt_forma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(txtn_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("$");

        jLabel21.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel21.setText("TOTAL:");

        txtIva.setEditable(false);
        txtIva.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel18.setText("DESCUENTO:");

        txtDescuento.setEditable(false);
        txtDescuento.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel17.setText("IVA:");

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("$");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("$");

        btnSalir2.setBackground(new java.awt.Color(254, 254, 254));
        btnSalir2.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnSalir2.setForeground(new java.awt.Color(1, 1, 1));
        btnSalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        btnSalir2.setText("SALIR");
        btnSalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir2ActionPerformed(evt);
            }
        });

        btnReporte.setBackground(new java.awt.Color(254, 254, 254));
        btnReporte.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnReporte.setForeground(new java.awt.Color(1, 1, 1));
        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/imprimir_mesa.png"))); // NOI18N
        btnReporte.setText("IMPRIMIR");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tbaListaComprasB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "      CODIGO", "           PRODUCTO", "                 MARCA", "                  TIPO", "             MEDIDA", "       CANTIDAD"
            }
        ));
        tbaListaComprasB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbaListaComprasBMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbaListaComprasB);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel7.setBackground(java.awt.Color.red);
        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DETALLE VENTAS");
        jLabel7.setOpaque(true);
        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel7MouseDragged(evt);
            }
        });
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        btnAnular.setBackground(new java.awt.Color(254, 254, 254));
        btnAnular.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnAnular.setForeground(new java.awt.Color(1, 1, 1));
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Cancelar_Mesa de trabajo 1.jpg"))); // NOI18N
        btnAnular.setText("ANULAR");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(212, 212, 212))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18))
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtapellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidosKeyReleased

    }//GEN-LAST:event_txtapellidosKeyReleased

    private void txttipodocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipodocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipodocActionPerformed

    private void tbaListaComprasBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbaListaComprasBMousePressed

    }//GEN-LAST:event_tbaListaComprasBMousePressed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btnSalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalir2ActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        ArrayList tablac = new ArrayList();
        for (int i = 0; i < tbaListaComprasB.getRowCount(); i++) {
            ClaseReporte tabla1 = new ClaseReporte(txtCodigoCliente.getText(),
                    txttipodoc.getText(),
                    txt_N_VENTA.getText(),
                    txtNombre.getText(),
                    txtn_doc.getText(),
                    txtFechaCreacion.getText(),
                    txtapellidos.getText(),
                    txtDireccion.getText(),
                    txSucursal.getText(),
                    txtTelefono.getText(),
                    txtcorreo.getText(),
                    txtCaja.getText(),
                    tbaListaComprasB.getValueAt(i, 0).toString(),
                    tbaListaComprasB.getValueAt(i, 1).toString(),
                    tbaListaComprasB.getValueAt(i, 2).toString(),
                    tbaListaComprasB.getValueAt(i, 3).toString(),
                    tbaListaComprasB.getValueAt(i, 4).toString(),
                    tbaListaComprasB.getValueAt(i, 5).toString(),
                    tbaListaComprasB.getValueAt(i, 6).toString(),
                    tbaListaComprasB.getValueAt(i, 7).toString(),
                    txtDescuento.getText(),
                    txtIva.getText(),
                    txtTotal.getText());
            tablac.add(tabla1);
        }
        try {

            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/ReporteriaDetalleFactura.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(tablac));
            JDialog frame = new JDialog(this);
            net.sf.jasperreports.view.JRViewer viewer = new net.sf.jasperreports.view.JRViewer(jprint);
            frame.add(viewer);
            frame.setSize(new Dimension(ancho / 2, alto / 2));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            viewer.setFitWidthZoomRatio();
        } catch (JRException ex) {
            Logger.getLogger(ReporteriaDetalleCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void jLabel7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel7MouseDragged

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel7MousePressed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed

        int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de Anular la factura?", "", JOptionPane.YES_NO_OPTION);

        InKardexJpaController kardexController = new InKardexJpaController(EntityManagerUtil.ObtenerEntityManager());
        InKardexExt kardexExt = new InKardexExt(EntityManagerUtil.ObtenerEntityManager());

        if (r == JOptionPane.YES_OPTION) {

            //////////// agregar al kardex
            try {

                for (int i = 0; i < listaDetalle.size(); i++) {

                    objetoKardex = kardexExt.obtenerUltimoProductoKardex(listaDetalle.get(i).getId_producto());

                    InKardex kardex = new InKardex();

                    kardex.setInKardexPK(new InKardexPK());

                    kardex.getInKardexPK().setIdBodega(1);

                    kardex.getInKardexPK().setIdProducto(listaDetalle.get(i).getId_producto());

                    InTipoDocumentoJpaController docuCont = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
                    List<InTipoDocumento> listaD = docuCont.findInTipoDocumentoEntities();
                    for (int j = 0; j < listaD.size(); j++) {
                        if (listaD.get(j).getNombreDocumento().equals("DEVOLUCION DE VENTAS")) {
                            documento = listaD.get(j);
                        }
                    }

                    kardex.setInTipoDocumento(documento);

                    kardex.setSeSucursal(suc);

                    kardex.setCantidad(BigInteger.valueOf(listaDetalle.get(i).getCantidad()));

                    kardex.setNumeroDocumento(BigInteger.valueOf(listaDetalle.get(i).getId_factura()));
                    kardex.setFechaSistema(d);
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY");
                    kardex.setAnioDocumento(formatoFecha.format(d));
                    kardex.setUsuarioCreacion(usu.getUsuario());
                    kardex.setFechaCreacion(d);

                    kardex.setSaldoAnterior(objetoKardex.getSaldoActual());

                    kardex.setSaldoActual(objetoKardex.getSaldoActual().add(kardex.getCantidad()));

                    kardex.setCostoAnterior(objetoKardex.getCostoActual());

                    kardex.setCostoActual(kardex.getCostoAnterior().add(
                            ((objetoKardex.getCostoPromedio().multiply(BigDecimal.valueOf(listaDetalle.get(i).getCantidad().intValue()))))));

                    kardex.setCostoPromedio(kardex.getCostoActual().divide(BigDecimal.valueOf(kardex.getSaldoActual().intValue()), 5, RoundingMode.HALF_EVEN));

                    kardexController.create(kardex);

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

            /////////// AGREGAR AL MOVIMIENTO
            InMovimientosJpaController cabMovController = new InMovimientosJpaController(EntityManagerUtil.ObtenerEntityManager());
            InDetalleMovimientoJpaController detMovController = new InDetalleMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
            InMovimientos cabMovimiento = new InMovimientos();
            InDetalleMovimiento detMovimiento = new InDetalleMovimiento();
            InMovimientos pkMovimiento = new InMovimientos();
            MovimientosDaoExt obtenerIdMovimiento = new MovimientosDaoExt(EntityManagerUtil.ObtenerEntityManager());

            try {

                InTipoMovimiento movimiento = new InTipoMovimiento();
                InTipoMovimientoJpaController moviCont = new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
                List<InTipoMovimiento> listaM = moviCont.findInTipoMovimientoEntities();
                for (int j = 0; j < listaM.size(); j++) {
                    if (listaM.get(j).getNombreMovimiento().equals("DEVOLUCION DE VENTAS")) {
                        movimiento = listaM.get(j);
                    }
                }

                InMotivos motivo = new InMotivos();
                InMotivosJpaController motiCont = new InMotivosJpaController(EntityManagerUtil.ObtenerEntityManager());
                List<InMotivos> listaT = motiCont.findInMotivosEntities();
                for (int j = 0; j < listaT.size(); j++) {
                    if (listaT.get(j).getNombre().equals("DEVOLUCION DE VENTAS")) {
                        motivo = listaT.get(j);
                    }
                }

                ////////////setear para la pk cab movimientos
                cabMovimiento.setSeSucursal(suc);
                cabMovimiento.setInTipoDocumento(documento);
                cabMovimiento.setInTipoMovimiento(movimiento);
                cabMovimiento.setInMotivos(motivo);
                cabMovimiento.setObservacion("DEVOLUCION DE VENTAS");

                ///////////setear cab movimientos
                cabMovimiento.setFechaSistema(d);
                cabMovimiento.setAnioDocumento(d);

                cabMovimiento.setIdFactura(BigInteger.valueOf(listaDetalle.get(0).getId_factura()));
                cabMovimiento.setEstado("D");

                cabMovimiento.setUsuarioCreacion(usu.getUsuario());
                cabMovimiento.setFechaCreacion(d);

                pkMovimiento = obtenerIdMovimiento.guardarPedido(cabMovimiento);
                System.out.println(" IDcabedcera movimiento" + pkMovimiento);

                for (int i = 0; i < listaDetalle.size(); i++) {

                    objetoKardex = kardexExt.obtenerUltimoProductoKardex(listaDetalle.get(i).getId_producto());

                    ////////////setear para la pk detalle con cab movimiento
                    detMovimiento.setInMovimientos(pkMovimiento);
                    ///////////setear detalle movimientos
                    detMovimiento.setInDetalleMovimientoPK(new InDetalleMovimientoPK()); // inicializar pk
                    detMovimiento.getInDetalleMovimientoPK().setLineaDetalle(listaDetalle.get(i).getLinea_detalle());
                    detMovimiento.getInDetalleMovimientoPK().setIdProducto(listaDetalle.get(i).getId_producto());

                    detMovimiento.setDescripcion(listaDetalle.get(i).getDescripcion());
                    detMovimiento.setCantidad(BigInteger.valueOf(listaDetalle.get(i).getCantidad()));
                    detMovimiento.setPrecioUnitario(objetoKardex.getCostoPromedio());
                    detMovimiento.setEstado("A");

                    detMovimiento.setUsuarioCreacion(usu.getUsuario());
                    detMovimiento.setFechaCreacion(d);

                    detMovController.create(detMovimiento);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            /////////// modificar la  factura
            try {

                VeFacturaJpaController facCont = new VeFacturaJpaController(EntityManagerUtil.ObtenerEntityManager());
                VeFacturaDetalleJpaController facDetCont = new VeFacturaDetalleJpaController(EntityManagerUtil.ObtenerEntityManager());

                VeFactura facturaPk = new VeFactura();
                VeFactura facturaObj = new VeFactura();

                facturaPk.setVeFacturaPK(new VeFacturaPK());
                facturaPk.getVeFacturaPK().setIdEmpresa(objeto.getId_empresa());
                facturaPk.getVeFacturaPK().setIdSucursal(objeto.getId_sucursal());
                facturaPk.getVeFacturaPK().setIdFactura(objeto.getId_factura());

                facturaObj = facCont.findVeFactura(facturaPk.getVeFacturaPK());

                List<VeFacturaDetalle> listDet = facturaObj.getVeFacturaDetalleList();

                facturaObj.setEstado("D");
                facturaObj.setUsuarioActualizacion(usu.getUsuario());
                facturaObj.setFechaActualizacion(d);

                facCont.edit(facturaObj);

                for (int i = 0; i < listDet.size(); i++) {
                    VeFacturaDetalle detalleObj = new VeFacturaDetalle();

                    detalleObj = listDet.get(i);

                    detalleObj.setEstado("D");
                    detalleObj.setUsuarioActualizacion(usu.getUsuario());
                    detalleObj.setFechaActualizacion(d);

                    facDetCont.edit(detalleObj);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");
            setVisible(false);
        }
    }//GEN-LAST:event_btnAnularActionPerformed

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
            java.util.logging.Logger.getLogger(ReporteriaDetalleFactura.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteriaDetalleFactura.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteriaDetalleFactura.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteriaDetalleFactura.class
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReporteriaDetalleFactura dialog = new ReporteriaDetalleFactura(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnSalir2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    public static javax.swing.JLabel jLabel17;
    public static javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel21;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbaListaComprasB;
    private javax.swing.JTextField txSucursal;
    private javax.swing.JTextField txtCaja;
    public static javax.swing.JLabel txtCodigoCliente;
    public static javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFechaCreacion;
    public static javax.swing.JTextField txtIva;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtTelefono;
    public static javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txt_N_VENTA;
    private javax.swing.JTextField txt_forma_pago;
    public static javax.swing.JTextField txtapellidos;
    public static javax.swing.JTextField txtcorreo;
    public static javax.swing.JTextField txtn_doc;
    public static javax.swing.JTextField txttipodoc;
    // End of variables declaration//GEN-END:variables
}
