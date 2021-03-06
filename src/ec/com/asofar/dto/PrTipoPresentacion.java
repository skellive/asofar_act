/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "pr_tipo_presentacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrTipoPresentacion.findAll", query = "SELECT p FROM PrTipoPresentacion p")
    , @NamedQuery(name = "PrTipoPresentacion.findByIdTipoPresentacion", query = "SELECT p FROM PrTipoPresentacion p WHERE p.idTipoPresentacion = :idTipoPresentacion")
    , @NamedQuery(name = "PrTipoPresentacion.findByNombre", query = "SELECT p FROM PrTipoPresentacion p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PrTipoPresentacion.findByEstado", query = "SELECT p FROM PrTipoPresentacion p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrTipoPresentacion.findByUsuarioCreacion", query = "SELECT p FROM PrTipoPresentacion p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrTipoPresentacion.findByFechaCreacion", query = "SELECT p FROM PrTipoPresentacion p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrTipoPresentacion.findByUsuarioActualizacion", query = "SELECT p FROM PrTipoPresentacion p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrTipoPresentacion.findByFechaActualizacion", query = "SELECT p FROM PrTipoPresentacion p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrTipoPresentacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_presentacion")
    private Long idTipoPresentacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private String estado;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prTipoPresentacion")
    private List<PrMedidas> prMedidasList;

    public PrTipoPresentacion() {
    }

    public PrTipoPresentacion(Long idTipoPresentacion) {
        this.idTipoPresentacion = idTipoPresentacion;
    }

    public Long getIdTipoPresentacion() {
        return idTipoPresentacion;
    }

    public void setIdTipoPresentacion(Long idTipoPresentacion) {
        this.idTipoPresentacion = idTipoPresentacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @XmlTransient
    public List<PrMedidas> getPrMedidasList() {
        return prMedidasList;
    }

    public void setPrMedidasList(List<PrMedidas> prMedidasList) {
        this.prMedidasList = prMedidasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPresentacion != null ? idTipoPresentacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrTipoPresentacion)) {
            return false;
        }
        PrTipoPresentacion other = (PrTipoPresentacion) object;
        if ((this.idTipoPresentacion == null && other.idTipoPresentacion != null) || (this.idTipoPresentacion != null && !this.idTipoPresentacion.equals(other.idTipoPresentacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrTipoPresentacion[ idTipoPresentacion=" + idTipoPresentacion + " ]";
    }
    
}
