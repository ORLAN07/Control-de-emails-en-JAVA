/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerLogin;

import entities.Usuario;
import facades.UsuarioFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author ORLANDO
 */
@Named(value = "controllerSession")
@SessionScoped
public class controllerSession extends controllerApp {

    /**
     * Creates a new instance of controllerSession
     */
    public controllerSession() {
    }
    
    
    @EJB
    private UsuarioFacadeLocal ufl;
    private String email;
    private String clave;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        
    }

    public UsuarioFacadeLocal getUfl() {
        return ufl;
    }

    public void setUfl(UsuarioFacadeLocal ufl) {
        this.ufl = ufl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        System.out.println("estamos iniciando sesion");
        FacesContext fc = FacesContext.getCurrentInstance();
        
        if (email != null && clave != null && !email.equals("") && !clave.equals("")) {
            usuario = ufl.iniciarSesion(email, clave); 
            System.out.println(ufl.findAll());
            if (usuario != null) {
                System.out.println("se inicio sesion");
                return "index?faces-redirect=true";
            }else{
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lo sentimos su email y contraseña no son validos", "revise sus datos e intente de nuevo" );
                fc.addMessage(null, m);
            }
            
        }else{
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Todos los datos son obligatorios", "ingrese sus datos e intente de nuevo");
            fc.addMessage(null, m);
        }
        return "";
    }
    
    
    public void cerrarSesion(){
        try {
            System.out.println("estamos cerrando la sesion");
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            System.out.println(ec.getContext());
            ec.invalidateSession();
            this.email="";
            this.clave="";
            this.usuario=null;
            ec.redirect(hostName() +"index.xhtml");
            System.out.println(ec.getRequestContextPath()+"/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(controllerSession.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
    
    public boolean inicioSesion(){
        return (usuario != null);
    }
    
    public void validarSesion(){
        if (!inicioSesion()) {
            cerrarSesion();
        }
    }
  
}
