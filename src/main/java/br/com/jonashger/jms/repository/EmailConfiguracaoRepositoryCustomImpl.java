package br.com.jonashger.jms.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class EmailConfiguracaoRepositoryCustomImpl implements EmailConfiguracaoRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

}
