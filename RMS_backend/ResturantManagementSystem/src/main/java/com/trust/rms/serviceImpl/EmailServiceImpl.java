package com.trust.rms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trust.rms.utils.EmailUtils;

@Service
public class EmailServiceImpl {
	
	@Autowired
	private EmailUtils emailUtils;
	
	public static void sendMailToUsers(String status, String context, List<String> emailList) {
		if(status ==  "ACTIVE") {
			
		}
	}

}
