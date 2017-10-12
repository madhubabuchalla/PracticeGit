package test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author MADHUBABU
 * 16-Aug-2017
 */

public class EmailNotification {
	
	 public static String host_mailserver = "10.176.130.10";   // "outlook.office365.com" for Outlook 
	 public static final String username = "madhubabu.challa@non.schneider-electric.com";
	 public static final String password = "Password@1";
	 int port = 587;
	
	 public static final String toEmail = "madhubabu.challa@non.schneider-electric.com";
	 public static String subject = "Test Automation - Email Notification";
	 public static String textBody = "This is to Test Email Notification";
	
	
	
	public static void main(String args[]) throws EmailException, UnsupportedEncodingException{
	//	sendMailOutlook();
	//	sendMailHtmlMail();
	//	sendMailwithAttachment(toEmail, subject, textBody);
	}

	
	public static void sendMailHtmlMail() throws EmailException{
		
	
	HtmlEmail email = new HtmlEmail();
	

	email.setHostName(host_mailserver);
	email.setAuthentication(username, password);
	//email.setSmtpPort(port);
	email.setFrom(username);
	email.addTo(toEmail);
	email.setSubject(subject);
	email.setTextMsg(textBody);
	email.setHtmlMsg(textBody);

	email.setDebug(true);

	email.send();
	}
	
	
	
	public static void sendMailOutlook(String body){
		
   
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	     // props.put("mail.smtp.host", "outlook.office365.com"); 
	        props.put("mail.smtp.host", host_mailserver);
	      //  props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	           
	          });
	        
	        
	        session.setDebug(true);
	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
	            message.setSubject(subject);
	            message.setText(body);

	            // Send message
	            
	            Transport.send(message);
	           
	            System.out.println("Done, Email Sent successfully....");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	
	
	
	
	public static void sendMailwithAttachment(String body) throws UnsupportedEncodingException{
		
		    Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	     // props.put("mail.smtp.host", "outlook.office365.com"); 
	        props.put("mail.smtp.host", "10.176.130.10");
	      //  props.put("mail.smtp.port", "587");
		
		 Session session = Session.getInstance(props,
		          new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username, password);
		            }
		           
		          });
		        
		
			try{
				
				
		         MimeMessage msg = new MimeMessage(session);
		         msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			     msg.addHeader("format", "flowed");
			     msg.addHeader("Content-Transfer-Encoding", "8bit");
			      
			     msg.setFrom(new InternetAddress(username));

			    // msg.setReplyTo(InternetAddress.parse(username, false));

			     msg.setSubject(subject, "UTF-8");
			     msg.setSentDate(new Date());
			     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			      
		         // Create the message body part
		         BodyPart messageBodyPart = new MimeBodyPart();

		         // Fill the message
		         messageBodyPart.setText(body);         
		         // Create a multipart message for attachment
		         Multipart multipart = new MimeMultipart();
		         // Set text message part
		         multipart.addBodyPart(messageBodyPart);
		         // Second part is attachment
		         messageBodyPart = new MimeBodyPart();
		         String filename = "C:\\Users\\SESA439753\\Desktop\\Softwares for Schneider Automation\\clearcachecmd.txt";
		         DataSource source = new FileDataSource(filename);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(filename);
		         multipart.addBodyPart(messageBodyPart);

		         // Send the complete message parts
		         msg.setContent(multipart);
		         // Send message
		         Transport.send(msg);
		         System.out.println("EMail Sent Successfully with an attachment!!");
		      }catch (MessagingException e) {
		         e.printStackTrace();
		      }

		
		
		
	}
	
	
	}
	
	

