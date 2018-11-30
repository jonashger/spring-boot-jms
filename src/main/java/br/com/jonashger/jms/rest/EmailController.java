package br.com.jonashger.jms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonashger.jms.dto.EmailDTO;
import br.com.jonashger.jms.model.EmailConfiguracao;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/transaction")
@Log4j2
public class EmailController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@PostMapping("/sendEmail")
	public void send(@RequestBody EmailDTO emailDTO) {
		log.info("Sending a transaction.");

		jmsTemplate.convertAndSend("EmailQueue", emailDTO);
	}

	@PostMapping("/saveNewEmail")
	public void send(@RequestBody EmailConfiguracao emailConfiguracao) {
		log.info("Saving a new config from Email");

		jmsTemplate.convertAndSend("SaveEmailQueue", emailConfiguracao);
	}
}
