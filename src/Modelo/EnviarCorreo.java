/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;




import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author ASUS
 */
public class EnviarCorreo {
    
    
    public static void escribiendoDatos(String correo, String codigo )
    {
        String txtEnConfig="gmail.account=KFCCompany01@gmail.com\n" +
"gmail.password=@123456789asd\n" +
"emaildestinations="+correo+"\n" +
"codigo="+codigo+"\n"; 
        String nombreArchivo = "G:\\UNFV\\P.APLICADA II\\FINAL_PROJECT\\KFC-Company\\src\\main\\resources\\config.properties";
       
        FileWriter flwriter  = null;
        
        try {
            flwriter = new FileWriter(nombreArchivo,false);
            BufferedWriter bfWriter = new BufferedWriter(flwriter);
           bfWriter.write(txtEnConfig);
           
           bfWriter.close();
        }  catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (flwriter != null) {
                    try {
                        flwriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
    }
        
    }
    
    public static boolean enviando(String nombre )
    {
        String mensaje = "ESTIMADO  "+ nombre+" LE SALUDA LA EMPRESA KFC, EL MOTIVO DEL SIGUIENTE MENSAJE ES PARA QUE PUEDA"
                + "TENER ACCESO OTRA VEZ A SU CUENTA  DE LA EMPRESA, PARA ESTO SE NECESITA QUE CONFIRME EL CÓDIGO DE RECUPERACIÓN "
                + "EN LA PLATAFORMA, EL CODIGO ES EL SIGUIENTE: \n"+
                " CODIGO DE RECUPERACION: \n";
        
       Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		String resourceName = "config.properties";
                
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties config = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
		    config.load(resourceStream);
		} catch (IOException ex) {
                    
        }
 
		final String gmailAccount = config.getProperty("gmail.account");
		final String gmailPassword = config.getProperty("gmail.password");
		final String[] emailDestinations = config.getProperty("emaildestinations").split(";");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(gmailAccount,gmailPassword);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(gmailAccount));
			
			for (String emailDestination : emailDestinations) {
				message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestination));
			}
 
			message.setSubject("RECUPERACION DE CONTRASEÑA");
 
			BodyPart messageBodyPart = new MimeBodyPart();
                                                                                
			messageBodyPart.setText(mensaje+ config.getProperty("codigo"));
			
 
			Transport.send(message);
                                                    
			System.out.println("Correo enviado");
                                                                               return true;
		} catch (MessagingException e) {
                                                                            System.out.println(e);
                                                                           return false;
			   }
    }                                        

    /**
     * @param args the command line arguments
     */
   
    }
   
 
  

