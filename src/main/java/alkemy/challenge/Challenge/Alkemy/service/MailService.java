package alkemy.challenge.Challenge.Alkemy.service;


import java.io.IOException;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import alkemy.challenge.Challenge.Alkemy.dto.UserDTO;


@Service
public class MailService {
	@Value("alkemy.preaceleracion.sendgrid.apikey")
	private String apiKey;
	
	@Value("alkemy.preaceleracion.sendgrid.senderemail")
	private String senderEMail;
	
	public  void sendWelcomeEmail(UserDTO user) {
	    Email from = new Email(senderEMail);
	    String subject = "Registro nuevo usuario";
	    Email to = new Email(user.getEmail());
	    String contentText = "Bienvenido" + user.getName() + " " + user.getLastName()
	    	+ ". Su registro se realizo con exito. Su username es: " + user.getUserName();
	    Content content = new Content("text/plain", contentText);
	    Mail mail = new Mail(from, subject, to, content);
	    SendGrid sg = new SendGrid(apiKey);
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
}
