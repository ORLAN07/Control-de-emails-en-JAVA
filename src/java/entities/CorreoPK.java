/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ORLANDO
 */
@Embeddable
public class CorreoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idCorreo")
    private int idCorreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuarios_idUsuarios")
    private int usuariosidUsuarios;

    public CorreoPK() {
    }

    public CorreoPK(int idCorreo, int usuariosidUsuarios) {
        this.idCorreo = idCorreo;
        this.usuariosidUsuarios = usuariosidUsuarios;
    }

    public int getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(int idCorreo) {
        this.idCorreo = idCorreo;
    }

    public int getUsuariosidUsuarios() {
        return usuariosidUsuarios;
    }

    public void setUsuariosidUsuarios(int usuariosidUsuarios) {
        this.usuariosidUsuarios = usuariosidUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCorreo;
        hash += (int) usuariosidUsuarios;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorreoPK)) {
            return false;
        }
        CorreoPK other = (CorreoPK) object;
        if (this.idCorreo != other.idCorreo) {
            return false;
        }
        if (this.usuariosidUsuarios != other.usuariosidUsuarios) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CorreoPK[ idCorreo=" + idCorreo + ", usuariosidUsuarios=" + usuariosidUsuarios + " ]";
    }
    
}
