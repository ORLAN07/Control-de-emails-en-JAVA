/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Correo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
