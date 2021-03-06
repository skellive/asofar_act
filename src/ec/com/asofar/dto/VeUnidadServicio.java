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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ve_unidad_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VeUnidadServicio.findAll", query = "SELECT v FROM VeUnidadServicio v")
    , @NamedQuery(name = "VeUnidadServicio.findByIdUnidadServicio", query = "SELECT v FROM VeUnidadServicio v WHERE v.idUnidadServicio = :idUnidadServicio")
    , @NamedQuery(name = "VeUnidadServicio.findByNombreUnidadServicio", query = "SELECT v FROM VeUnidadServicio v WHERE v.nombreUnidadServicio = :nombreUnidadServicio")
    , @NamedQuery(name = "VeUnidadServicio.findByEstado", query = "SELECT v FROM VeUnidadServicio v WHERE v.estado = :estado")
    , @NamedQuery(name = "VeUnidadServicio.findByUsuarioCreacion", query = "SELECT v FROM VeUnidadServicio v WHERE v.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "VeUnidadServicio.findByFechaCreacion", query = "SELECT v FROM VeUnidadServicio v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "VeUnidadServicio.findByUsuarioActualizacion", query = "SELECT v FROM VeUnidadServicio v WHERE v.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "VeUnidadServicio.findByFechaActualizacion", query = "SELECT v FROM VeUnidadServicio v WHERE v.fechaActualizacion = :fechaActualizacion")})
public class VeUnidadServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidad_servicio")
    private Long idUnidadServicio;
    @Column(name = "nombre_unidad_servicio")
    private String nombreUnidadServicio;
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
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private SeEmpresa idEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veUnidadServicio")
    private List<InPrestacionesPorServicios> inPrestacionesPorServiciosList;

    public VeUnidadServicio() {
    }

    public VeUnidadServicio(Long idUnidadServicio) {
        this.idUnidadServicio = idUnidadServicio;
    }

    public Long getIdUnidadServicio() {
        return idUnidadServicio;
    }

    public void setIdUnidadServicio(Long idUnidadServicio) {
        this.idUnidadServicio = idUnidadServicio;
    }

    public String getNombreUnidadServicio() {
        return nombreUnidadServicio;
    }

    public void setNombreUnidadServicio(String nombreUnidadServicio) {
        this.nombreUnidadServicio = nombreUnidadServicio;
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

    public SeEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(SeEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @XmlTransient
    public List<InPrestacionesPorServicios> getInPrestacionesPorServiciosList() {
        return inPrestacionesPorServiciosList;
    }

    public void setInPrestacionesPorServiciosList(List<InPrestacionesPorServicios> inPrestacionesPorServiciosList) {
        this.inPrestacionesPorServiciosList = inPrestacionesPorServiciosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadServicio != null ? idUnidadServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeUnidadServicio)) {
            return false;
        }
        VeUnidadServicio other = (VeUnidadServicio) object;
        if ((this.idUnidadServicio == null && other.idUnidadServicio != null) || (this.idUnidadServicio != null && !this.idUnidadServicio.equals(other.idUnidadServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeUnidadServicio[ idUnidadServicio=" + idUnidadServicio + " ]";
    }
    
}
