package helper;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	final static String PASSWORD = "mb1003BM";
	final static String GREETING = "HuntingNet";
	final static String COMPANYNAME = "CampuSite";
	final static String TOADDR = "306941426@qq.com";
	static Properties props = new Properties();
	static {
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
	}

	public EmailSender() {

	}

	public void send(String subject, String content) {
		Session session = Session.getInstance(props);
		MimeMessage msg = new MimeMessage(session);
		try {
			Address campusite = new InternetAddress("campusite@outlook.com",
					COMPANYNAME);
			Address toAddress = new InternetAddress(TOADDR, GREETING);
			msg.setFrom(campusite);
			msg.setRecipient(Message.RecipientType.TO, toAddress);
			msg.setSubject(subject);
			Transport.send(msg, "campusite@outlook.com", PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
