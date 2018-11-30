package br.com.jonashger.jms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jonashger.jms.model.EmailConfiguracao;

public interface EmailConfiguracaoRepository
		extends JpaRepository<EmailConfiguracao, Long>, EmailConfiguracaoRepositoryCustom {

	@Query("SELECT p FROM EmailConfiguracao p WHERE LOWER(p.tenant) = LOWER(:tenant) and p.modulo = :modulo")
	public EmailConfiguracao findConfig(@Param("tenant") String tenant, @Param("modulo") Long modulo);

}
