/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerLogin;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author ORLANDO
 */
@Named(value = "controllerApp")
@ConversationScoped
public class controllerApp implements Serializable {

    /**
     * Creates a new instance of controllerApp
     */
    public controllerApp() {
    }
    
    @Inject
    private Conversation conversacion;
    
    
    public void iniciarConversacion(){
        if (conversacion.isTransient()) {
            conversacion.begin();
        }
    }
    
    public void finalizarConversacion(){ 
        if (conversacion.isTransient()) {
            conversacion.end();
        }
    }
    
    public String cancelar(){
        finalizarConversacion();
        return "";
    }

    public Conversation getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversation conversacion) {
        this.conversacion = conversacion;
    }
    
    public String hostName(){
        String servername = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName(); 
        String name ="http://";
        if ("localhost".equals(servername)) {
            name += servername +":8080/ControlMensajeria/";
        }else{
            name += servername;
        }
        return name;   
    }
    
    public void redireccionar(String url) throws IOException{
        url = this.hostName() +url+".xhtml";
        if (!"".equals(url)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }
}
