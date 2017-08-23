/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ORLANDO
 */
@Entity
@Table(name = "correo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Correo.findAll", query = "SELECT c FROM Correo c")
    , @NamedQuery(name = "Correo.findByIdCorreo", query = "SELECT c FROM Correo c WHERE c.correoPK.idCorreo = :idCorreo")
    , @NamedQuery(name = "Correo.findByRemitente", query = "SELECT c FROM Correo c WHERE c.remitente = :remitente")
    , @NamedQuery(name = "Correo.findByDestinatario", query = "SELECT c FROM Correo c WHERE c.destinatario = :destinatario")
    , @NamedQuery(name = "Correo.findByAsunto", query = "SELECT c FROM Correo c WHERE c.asunto = :asunto")
    , @NamedQuery(name = "Correo.findByUsuariosidUsuarios", query = "SELECT c FROM Correo c WHERE c.correoPK.usuariosidUsuarios = :usuariosidUsuarios")})
public class Correo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CorreoPK correoPK;
    @Size(max = 45)
    @Column(name = "remitente")
    private String remitente;
    @Size(max = 45)
    @Column(name = "destinatario")
    private String destinatario;
    @Size(max = 45)
    @Column(name = "asunto")
    private String asunto;
    @JoinColumn(name = "usuarios_idUsuarios", referencedColumnName = "idUsuarios", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Correo() {
    }

    public Correo(CorreoPK correoPK) {
        this.correoPK = correoPK;
    }

    public Correo(int idCorreo, int usuariosidUsuarios) {
        this.correoPK = new CorreoPK(idCorreo, usuariosidUsuarios);
    }

    public CorreoPK getCorreoPK() {
        return correoPK;
    }

    public void setCorreoPK(CorreoPK correoPK) {
        this.correoPK = correoPK;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correoPK != null ? correoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correo)) {
            return false;
        }
        Correo other = (Correo) object;
        if ((this.correoPK == null && other.correoPK != null) || (this.correoPK != null && !this.correoPK.equals(other.correoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Correo[ correoPK=" + correoPK + " ]";
    }
    
}
