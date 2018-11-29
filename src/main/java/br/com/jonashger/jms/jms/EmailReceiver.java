package br.com.jonashger.jms.jms;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.jonashger.jms.dto.EmailDTO;
import br.com.jonashger.jms.model.Email;
import br.com.jonashger.jms.repositories.EmailRepository;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class EmailReceiver {

	@Autowired
	private EmailRepository emailRepository;

	private int count = 1;

	@JmsListener(destination = "EmailQueue", containerFactory = "myFactory")
	public void receiveMessage(EmailDTO emailDTO) {
		log.info("<" + count + "> Received <" + emailDTO.toString() + ">");
		count++;
		// TODO ENVIO DE EMAILS

		Email email = new Email();

		email.setAssunto(emailDTO.getAssunto());
		email.setCorpoMensagem(emailDTO.getAssunto().getBytes(StandardCharsets.UTF_8));
		email.setPara(emailDTO.getPara());
		email.setTenant(emailDTO.getTenant());
		email.setModulo(emailDTO.getModulo());

		emailRepository.save(email);
	}
}
