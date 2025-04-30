package com.trust.rms.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendSimplMessage(String sendTo, String subject, String context, List<String> list) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("julia.thetkhinewin@gmail.com");
		message.setTo(sendTo);
		message.setSubject(subject);
		message.setText(context);
		if(list != null && list.size() > 0)
			message.setCc(getCcArray(list));
		emailSender.send(message);
	}
	
	private String[] getCcArray(List<String> list) {
		return list.toArray(new String[0]);
	}

}
