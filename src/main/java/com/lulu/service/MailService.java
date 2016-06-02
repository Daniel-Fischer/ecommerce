package com.lulu.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	private static String username = "lulucupcakebakery@gmail.com";
	private static String password = "llcupcake";
	public static void SendMail(String to, String msg, String subj){
		Properties props = System.getProperties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session defSession = Session.getInstance(props, 
				new javax.mail.Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(username, password);
					}
		});
		
		try{
			MimeMessage message = new MimeMessage(defSession);
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subj);
			message.setText(msg, "UTF-8", "html");
			
			Transport.send(message);
		}catch(MessagingException mex){
			mex.printStackTrace();
		}
	}
	
	public static String parseEmailTemplate(Map<String, String> parseMap, String fileName) throws IOException{
		
		BufferedReader in = null;
		StringBuffer out = new StringBuffer();
		
		try{
			in = new BufferedReader(new FileReader(fileName));
			
			String l;
			while((l = in.readLine()) != null){
				out = out.append(l);
			}
			
			for(Map.Entry<String, String> e : parseMap.entrySet()){
				l = out.toString();
				System.out.println(e.getKey() + " " + e.getValue());
				l = l.replaceAll(e.getKey(), e.getValue());
				out = new StringBuffer(l);
			}
			System.out.println(out);
			
			return out.toString();
		}finally{
			if(in != null){
				in.close();
			}
		}
	}
}
