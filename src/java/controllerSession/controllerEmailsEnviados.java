/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSession;

import controllerLogin.controllerSession;
import entities.Correo;
import entities.Usuario;
import facades.CorreoFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author ORLANDO
 */
@Named(value = "controllerEmailsEnviados")
@SessionScoped
public class controllerEmailsEnviados implements Serializable {

   
    @Inject 
    private controllerEmail cemail;
    
    
    @Inject
    private controllerSession cs;
    
    @EJB
    private CorreoFacadeLocal cfl;
    private Correo correo;
    private List<Correo> listaCorreo;
    private Usuario corre;
    
    
    @PostConstruct
    public void init(){
        listaCorreo = cfl.findAll();
        correo = new Correo();
    }
    public List<Correo> listarCorreos(){
        System.out.println(" Correos");
        this.listaCorreo = cfl.findAll();
        return listaCorreo;
    }
    public List<Correo> correosenviados(){
        Usuario uS = cs.getUsuario();
        System.out.println("Listando correos propios");
        this.listaCorreo = cfl.listaCorreos(uS);
        return listaCorreo;
    }
    
    
    
    
    public controllerEmailsEnviados() {
    }
    
}
