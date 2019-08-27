package edu.genesislima.coffeequiz.bean.stateless;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import edu.genesislima.coffeequiz.model.Jogador;

@Stateless(name = "DesafioBean")
@Local
public class DesafioBean {
	
	@Resource(mappedName = "java:/DesafioMessageTopicConnectionFactory")
	private TopicConnectionFactory desafioMessageTopicCF;
	@Resource(mappedName = "java:/DesafioMessageTopic")
	private Topic desafioTopic;
	
	
	public String enviarDesafio(int scoreFinal, Jogador desafiante, String emailAdversario) {		
		String content = "Olá! "+desafiante.getNome()+" gostaria de te desafiar a bater meu score"+desafiante.getMaiorScore()+"."
				+ "acesse http://cooffeequiz.play e derrote seu desafiante.";
		System.out.println("Before status TopicCF connection");
		Connection connection;
		try {
			connection = desafioMessageTopicCF.createConnection();
			connection.start();
			System.out.println("Created connection");			
			System.out.println("started connection");
			System.out.println("Starting Topic Session");
			Session topicSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = topicSession.
			createProducer(desafioTopic);
			System.out.println("created producer");
			MapMessage message = topicSession.createMapMessage();
			message.setStringProperty("emailFrom", desafiante.getNome());
			message.setStringProperty("emailTo", emailAdversario);
			message.setStringProperty("subject", "Você foi desafiado no jogo Coffee Quiz!");
			message.setStringProperty("content", content);
			System.out.println("before send");
			publisher.send(message);
			System.out.println("after send");
		} catch (JMSException e1) {
			
			e1.printStackTrace();
		}
		
		return "Desafio criado e enviado para o repósitório de mensagem desafioTopic";
	} 
}
