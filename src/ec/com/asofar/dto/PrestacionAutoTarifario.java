/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "prestacion_auto_tarifario", catalog = "bd_farmacia_desa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestacionAutoTarifario.findAll", query = "SELECT p FROM PrestacionAutoTarifario p")
    , @NamedQuery(name = "PrestacionAutoTarifario.findByIdTipoPrestaciones", query = "SELECT p FROM PrestacionAutoTarifario p WHERE p.idTipoPrestaciones = :idTipoPrestaciones")
    , @NamedQuery(name = "PrestacionAutoTarifario.findByTipoPrestaciones", query = "SELECT p FROM PrestacionAutoTarifario p WHERE p.tipoPrestaciones = :tipoPrestaciones")
    , @NamedQuery(name = "PrestacionAutoTarifario.findByProductoComprado", query = "SELECT p FROM PrestacionAutoTarifario p WHERE p.productoComprado = :productoComprado")
    , @NamedQuery(name = "PrestacionAutoTarifario.findByIva", query = "SELECT p FROM PrestacionAutoTarifario p WHERE p.iva = :iva")
    , @NamedQuery(name = "PrestacionAutoTarifario.findByFacturable", query = "SELECT p FROM PrestacionAutoTarifario p WHERE p.facturable = :facturable")
    , @NamedQuery(name = "PrestacionAutoTarifario.findByDescuento", query = "SELECT p FROM PrestacionAutoTarifario p WHERE p.descuento = :descuento")})
public class PrestacionAutoTarifario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idTipoPrestaciones;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String tipoPrestaciones;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String productoComprado;
    @Basic(optional = false)
    @Column(nullable = false)
    private double iva;
    @Basic(optional = false)
    @Column(nullable = false)
    private double facturable;
    @Basic(optional = false)
    @Column(nullable = false)
    private double descuento;

    public PrestacionAutoTarifario() {
    }

    public PrestacionAutoTarifario(Integer idTipoPrestaciones) {
        this.idTipoPrestaciones = idTipoPrestaciones;
    }

    public PrestacionAutoTarifario(Integer idTipoPrestaciones, String tipoPrestaciones, String productoComprado, double iva, double facturable, double descuento) {
        this.idTipoPrestaciones = idTipoPrestaciones;
        this.tipoPrestaciones = tipoPrestaciones;
        this.productoComprado = productoComprado;
        this.iva = iva;
        this.facturable = facturable;
        this.descuento = descuento;
    }

    public Integer getIdTipoPrestaciones() {
        return idTipoPrestaciones;
    }

    public void setIdTipoPrestaciones(Integer idTipoPrestaciones) {
        this.idTipoPrestaciones = idTipoPrestaciones;
    }

    public String getTipoPrestaciones() {
        return tipoPrestaciones;
    }

    public void setTipoPrestaciones(String tipoPrestaciones) {
        this.tipoPrestaciones = tipoPrestaciones;
    }

    public String getProductoComprado() {
        return productoComprado;
    }

    public void setProductoComprado(String productoComprado) {
        this.productoComprado = productoComprado;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getFacturable() {
        return facturable;
    }

    public void setFacturable(double facturable) {
        this.facturable = facturable;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPrestaciones != null ? idTipoPrestaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestacionAutoTarifario)) {
            return false;
        }
        PrestacionAutoTarifario other = (PrestacionAutoTarifario) object;
        if ((this.idTipoPrestaciones == null && other.idTipoPrestaciones != null) || (this.idTipoPrestaciones != null && !this.idTipoPrestaciones.equals(other.idTipoPrestaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrestacionAutoTarifario[ idTipoPrestaciones=" + idTipoPrestaciones + " ]";
    }
    
}
