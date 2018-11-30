package br.com.jonashger.jms.jms;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.annotation.ManagedBean;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import br.com.jonashger.jms.dto.EmailDTO;
import br.com.jonashger.jms.model.Email;
import br.com.jonashger.jms.model.EmailConfiguracao;
import br.com.jonashger.jms.repository.EmailConfiguracaoRepository;
import br.com.jonashger.jms.repository.EmailRepository;
import br.com.jonashger.jms.util.CryptoUtil;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@ManagedBean
public class EmailReceiver {

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private EmailConfiguracaoRepository emailConfiguracaoRepository;

	private int count = 1;

	@JmsListener(destination = "EmailQueue", containerFactory = "myFactory")
	public void receiveMessage(EmailDTO emailDTO) {
		log.info("<" + count + "> Received <" + emailDTO.toString() + ">");
		count++;
		if (!this.enviarEmail(emailDTO)) {

			Email email = new Email();

			email.setAssunto(emailDTO.getAssunto());
			email.setCorpoMensagem(emailDTO.getAssunto().getBytes(StandardCharsets.UTF_8));
			email.setPara(emailDTO.getPara());
			email.setTenant(emailDTO.getTenant());
			email.setModulo(emailDTO.getModulo());

			emailRepository.save(email);
		}

	}

	private Boolean enviarEmail(EmailDTO emailDTO) {

		// TODO VALIDAR PARAMETROS

		EmailConfiguracao emailConfiguracao = emailConfiguracaoRepository.findConfig(emailDTO.getTenant(),
				emailDTO.getModulo());

		if (emailConfiguracao == null) {
			return Boolean.FALSE;
		}

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailConfiguracao.getHost());
		mailSender.setPort(emailConfiguracao.getPorta().intValue());

		mailSender.setUsername(emailConfiguracao.getEmailRemetente());
		try {
			mailSender.setPassword(CryptoUtil.decrypt(emailConfiguracao.getEmailSenha()));
		} catch (Exception e) {
			log.error("Não foi possivel descriptografar Blowfish");
			log.error(e.getCause());
			return Boolean.FALSE;

		}

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(emailDTO.getAssunto());
			helper.setFrom(emailConfiguracao.getEmailRemetente(), emailConfiguracao.getNomeRemetente());
			helper.setTo(emailDTO.getPara());
			helper.setText(emailDTO.getMensagem());
		} catch (MessagingException | UnsupportedEncodingException e) {
			log.error("Não foi possivel criar Mime");
			log.error(e.getCause());
		}

		mailSender.send(message);

		return Boolean.TRUE;
	}
}
