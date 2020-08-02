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
@Table(name = "prestaciones_auto_tarifarios", catalog = "bd_farmacia_desa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestacionesAutoTarifarios.findAll", query = "SELECT p FROM PrestacionesAutoTarifarios p")
    , @NamedQuery(name = "PrestacionesAutoTarifarios.findByIdPrestaciones", query = "SELECT p FROM PrestacionesAutoTarifarios p WHERE p.idPrestaciones = :idPrestaciones")
    , @NamedQuery(name = "PrestacionesAutoTarifarios.findByProductosComprados", query = "SELECT p FROM PrestacionesAutoTarifarios p WHERE p.productosComprados = :productosComprados")
    , @NamedQuery(name = "PrestacionesAutoTarifarios.findByPrestacion", query = "SELECT p FROM PrestacionesAutoTarifarios p WHERE p.prestacion = :prestacion")
    , @NamedQuery(name = "PrestacionesAutoTarifarios.findByFacturable", query = "SELECT p FROM PrestacionesAutoTarifarios p WHERE p.facturable = :facturable")
    , @NamedQuery(name = "PrestacionesAutoTarifarios.findByIva", query = "SELECT p FROM PrestacionesAutoTarifarios p WHERE p.iva = :iva")
    , @NamedQuery(name = "PrestacionesAutoTarifarios.findByDescuento", query = "SELECT p FROM PrestacionesAutoTarifarios p WHERE p.descuento = :descuento")})
public class PrestacionesAutoTarifarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prestaciones")
    private Integer idPrestaciones;
    @Basic(optional = false)
    private String productosComprados;
    @Basic(optional = false)
    private String prestacion;
    @Basic(optional = false)
    private String facturable;
    @Basic(optional = false)
    private double iva;
    @Basic(optional = false)
    private double descuento;

    public PrestacionesAutoTarifarios() {
    }

    public PrestacionesAutoTarifarios(Integer idPrestaciones) {
        this.idPrestaciones = idPrestaciones;
    }

    public PrestacionesAutoTarifarios(Integer idPrestaciones, String productosComprados, String prestacion, String facturable, double iva, double descuento) {
        this.idPrestaciones = idPrestaciones;
        this.productosComprados = productosComprados;
        this.prestacion = prestacion;
        this.facturable = facturable;
        this.iva = iva;
        this.descuento = descuento;
    }

    public Integer getIdPrestaciones() {
        return idPrestaciones;
    }

    public void setIdPrestaciones(Integer idPrestaciones) {
        this.idPrestaciones = idPrestaciones;
    }

    public String getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(String productosComprados) {
        this.productosComprados = productosComprados;
    }

    public String getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(String prestacion) {
        this.prestacion = prestacion;
    }

    public String getFacturable() {
        return facturable;
    }

    public void setFacturable(String facturable) {
        this.facturable = facturable;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
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
        hash += (idPrestaciones != null ? idPrestaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestacionesAutoTarifarios)) {
            return false;
        }
        PrestacionesAutoTarifarios other = (PrestacionesAutoTarifarios) object;
        if ((this.idPrestaciones == null && other.idPrestaciones != null) || (this.idPrestaciones != null && !this.idPrestaciones.equals(other.idPrestaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrestacionesAutoTarifarios[ idPrestaciones=" + idPrestaciones + " ]";
    }
    
}
