/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ORLANDO
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "ControlDeMensajesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario iniciarSesion(String email, String clave) {
        Usuario u = null;
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.login", Usuario.class);
            q.setParameter("correo", email);
            q.setParameter("password", clave);
            u=q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    
}
