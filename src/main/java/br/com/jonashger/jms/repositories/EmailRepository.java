package br.com.jonashger.jms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jonashger.jms.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>, EmailRepositoryCustom {
}
