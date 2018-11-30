package br.com.jonashger.jms.jms;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.jonashger.jms.model.EmailConfiguracao;
import br.com.jonashger.jms.repository.EmailConfiguracaoRepository;
import br.com.jonashger.jms.util.CryptoUtil;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@ManagedBean
public class SaveEmailReceiver {

	@Autowired
	private EmailConfiguracaoRepository emailConfiguracaoRepository;

	private int count = 1;

	@JmsListener(destination = "SaveEmailQueue", containerFactory = "myFactory")
	public void receiveMessage(EmailConfiguracao emailConfiguracaoToSave) {
		log.info("<" + count + "> Received <" + emailConfiguracaoToSave.getEmailRemetente() + ">");
		count++;

		try {
			emailConfiguracaoToSave.setEmailSenha(CryptoUtil.encrypt(emailConfiguracaoToSave.getEmailSenha()));
		} catch (Exception e) {
			log.error("Não foi possivel converter string para blowFish");
		}

		EmailConfiguracao emailConfiguracao = emailConfiguracaoRepository
				.findConfig(emailConfiguracaoToSave.getTenant(), emailConfiguracaoToSave.getModulo());

		if (emailConfiguracao == null) {
			emailConfiguracaoRepository.save(emailConfiguracaoToSave);
		} else {
			emailConfiguracaoToSave.setId(emailConfiguracao.getId());
			emailConfiguracaoRepository.save(emailConfiguracaoToSave);

		}

	}

}
