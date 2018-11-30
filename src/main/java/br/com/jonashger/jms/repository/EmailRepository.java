package br.com.jonashger.jms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonashger.jms.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long>, EmailRepositoryCustom {
}
