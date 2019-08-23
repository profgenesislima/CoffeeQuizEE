package edu.genesislima.coffeequiz.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationName",
				propertyValue = "DesafioMessageTopic"),
		@ActivationConfigProperty(propertyName = "destinationType",
		        propertyValue = "javax.jms.Topic")
},mappedName = "DesafioMessageTopic")
public class MDBDesafio	implements javax.jms.MessageListener {

	@Resource(name="mail/wineappMail" )
	private javax.mail.Session ms;
	
	@Override
	public void onMessage(Message message) {
		
		try {
			if (message instanceof MapMessage) {
			MapMessage orderMessage = (MapMessage)message;
			String from = orderMessage.getStringProperty("from");
			String to = orderMessage.getStringProperty("to");
			String subject = orderMessage.getStringProperty("subject");
			String content = orderMessage.getStringProperty("content");
			javax.mail.Message msg = new MimeMessage(ms);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = {new InternetAddress(to)};
			msg.setRecipients(javax.mail.Message.RecipientType.TO,address);
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date());
			msg.setContent(content, "text/html");
			System.out.println("MDB: Enviando Mensagem...");
			Transport.send(msg);
			System.out.println("MDB: Mensagem Enviada!");
			}
			else {
			System.out.println("Mensagem Inv�lida.");
			}
			} catch (Exception ex) {
			ex.printStackTrace();
			}
			}

}
