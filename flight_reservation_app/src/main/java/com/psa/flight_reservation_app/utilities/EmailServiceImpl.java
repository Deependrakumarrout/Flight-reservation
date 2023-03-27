package com.psa.flight_reservation_app.utilities;

import java.io.File;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSender sender;
	
	@Override
	public void sendItineray(String toAddress, String filePath) {
		MimeMessage message = sender.createMimeMessage();
		
		try {
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject("Flight PDF attached");
			messageHelper.setText("kindly take your flight confirmation copy sending in your register email.");
			messageHelper.addAttachment("Itinerary", new File(filePath));
			sender.send(message);
		}catch(MessagingException e) {
			System.out.println(e);
		}
		
		
	}
	
}
//emailService.sendingEmail(request.getEmail(),"WEB TESTING","Hello its good to you see may i help you...");