/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSession;

import entities.Usuario;
import facades.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author ORLANDO
 */
@Named(value = "controllerUsuario")
@ConversationScoped
public class controllerUsuario implements Serializable {

    /**
     * Creates a new instance of controllerUsuario
     */
    public controllerUsuario() {
    }
    @Inject
    private controllerEmail email;
    
    
    @EJB
    private UsuarioFacadeLocal ufl;
    private Usuario usuario;
    private List<Usuario> listausuario;
    
    
    
    @PostConstruct
    public void init(){
        listausuario = ufl.findAll();
        usuario = new Usuario();
    }

    public UsuarioFacadeLocal getUfl() {
        return ufl;
    }

    public void setUfl(UsuarioFacadeLocal ufl) {
        this.ufl = ufl;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListausuario() {
        return listausuario;
    }

    public void setListausuario(List<Usuario> listausuario) {
        this.listausuario = listausuario;
    }
    
    
    public List<Usuario> listaUsuarios(){
        System.out.println("estamos listando los usuarios");
        this.listausuario = ufl.findAll();
        return listausuario;
    }
    
    public String crearEmpleado(){
        if (usuario != null) {
            System.out.println("estamos entrando");
            usuario.setPassword(usuario.getNombre().substring(0, 2)+usuario.getApellido().substring(0, 2)+ usuario.getTelefono());
            usuario.setCargo("Empleado");
            email.setEmailDestinatario(usuario.getCorreo());
            email.emailCreacion(usuario);
            ufl.create(usuario);
            return "index.xhtml";
        }
        return null;   
    }
}
