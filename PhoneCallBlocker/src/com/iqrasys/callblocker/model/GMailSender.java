package com.iqrasys.callblocker.model;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.AccessController;
import java.security.Provider;
import java.security.Security;
import java.util.Properties;

public class GMailSender extends Authenticator {
	private String mailhost = "mail.communify.se";
	private String user;
	private String password;
	private Session session;

	static {
		Security.addProvider(new JSSEProvider());
	}

	public GMailSender(String user, String password) {
		this.user = user;
		this.password = password;

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", mailhost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "2525");
		props.put("mail.smtp.socketFactory.port", "465");
		/*
		 * props.put("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory");
		 */
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		session = Session.getDefaultInstance(props, this);
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}

	public synchronized void sendMail(String subject, String body,
			String sender, String recipients) throws AddressException,
			MessagingException {

		MimeMessage message = new MimeMessage(session);

		DataHandler handler = new DataHandler(body, "text/plain");
		message.setSender(new InternetAddress(sender));
		message.setSubject(subject);
		message.setDataHandler(handler);

		if (recipients.indexOf(',') > 0)
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipients));
		else
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					recipients));

		Transport.send(message);
	}

}

class JSSEProvider extends Provider {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JSSEProvider() {
		super("HarmonyJSSE", 1.0, "Harmony JSSE Provider");
		AccessController
				.doPrivileged(new java.security.PrivilegedAction<Void>() {
					@Override
					public Void run() {
						put("SSLContext.TLS",
								"org.apache.harmony.xnet.provider.jsse.SSLContextImpl");
						put("Alg.Alias.SSLContext.TLSv1", "TLS");
						put("KeyManagerFactory.X509",
								"org.apache.harmony.xnet.provider.jsse.KeyManagerFactoryImpl");
						put("TrustManagerFactory.X509",
								"org.apache.harmony.xnet.provider.jsse.TrustManagerFactoryImpl");
						return null;
					}
				});
	}

}
