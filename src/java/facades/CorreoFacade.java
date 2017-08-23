/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Correo;
import entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ORLANDO
 */
@Stateless
public class CorreoFacade extends AbstractFacade<Correo> implements CorreoFacadeLocal {

    @PersistenceContext(unitName = "ControlDeMensajesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorreoFacade() {
        super(Correo.class);
    }
    
    @Override
    public List<Correo> listaCorreos(Usuario codUsuario) {
        List<Correo> listaCorreos = null;
        try {            
            TypedQuery<Correo> q = getEntityManager().createNamedQuery("Correo.listaporRol", Correo.class);
            q.setParameter("codUsuario", codUsuario);
            listaCorreos = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCorreos;
    }

    
    
    
}
