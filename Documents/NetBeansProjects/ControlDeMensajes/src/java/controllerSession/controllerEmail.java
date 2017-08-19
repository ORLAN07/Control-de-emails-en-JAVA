/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSession;


import entities.Usuario;
import facades.CorreoFacadeLocal;
import facades.UsuarioFacadeLocal;
import java.io.File;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ORLANDO
 */
@Named(value = "controllerEmail")
@SessionScoped
public class controllerEmail implements Serializable {

    private String emailRemitente;
    private String passwordRemitente;
    private String emailDestinatario;
    private String asunto;
    private String texto;
    private Session session;
    private MimeMessage mimeMessage;
    
    
    public controllerEmail() {
    }

    public controllerEmail(String emailRemitente, String passwordRemitente, String emailDestinatario) {
        this.emailRemitente = emailRemitente;
        this.passwordRemitente = passwordRemitente;
        this.emailDestinatario = emailDestinatario;
    }

    public String getEmailRemitente() {
        return emailRemitente;
    }

    public void setEmailRemitente(String emailRemitente) {
        this.emailRemitente = emailRemitente;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    
   private void init() {
        try {
            Properties propiedades = new Properties();

            propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "25");//587-25
            propiedades.setProperty("mail.smtp.user", this.emailRemitente);
            propiedades.setProperty("mail.smtp.auth", "true");

            session = Session.getDefaultInstance(propiedades);
            mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(emailRemitente));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));
        } catch (MessagingException ex) {
            Logger.getLogger(controllerEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
   public boolean enviarSimple(String asunto, String contenido) {
        try {
            if (asunto != null && contenido != null) {

                init();

                Multipart contenidoMensaje = new MimeMultipart();
                BodyPart text = new MimeBodyPart();
                text.setText(contenido);
                contenidoMensaje.addBodyPart(text);

                mimeMessage.setSubject(asunto);
                mimeMessage.setContent(contenidoMensaje);

                Transport transport = session.getTransport("smtp");
                transport.connect(emailRemitente, passwordRemitente);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                transport.close();
                emailDestinatario = "";
                return true;
            } else {
                System.out.println("Falso");
                return false;
            }

        } catch (MessagingException ex) {
            Logger.getLogger(controllerEmail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public void emailCreacion(Usuario us) {
        System.out.println("Ingresamos a enviar correo" + emailDestinatario);
        try {
            if (emailDestinatario != null && !emailDestinatario.equals("")) {                                    
                    System.out.println("email es: " + emailDestinatario);
                    System.out.println("contraseña es: " + us.getPassword());
                    controllerEmail email = new controllerEmail("neurapiaj3s@gmail.com", "neurapia12345", emailDestinatario);
                    asunto="Nueva contraseña controlMensajeria";
                    texto="Tu nueva contraseña es: " + us.getPassword();
                    email.enviarSimple(asunto, texto);
                

            } else {
                System.out.println("Email es nulo o vacio");
            }
        } catch (Exception e) {
        }
    }
    
    
}
