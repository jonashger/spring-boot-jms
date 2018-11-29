package br.com.jonashger.jms.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class EmailRepositoryCustomImpl implements EmailRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
}
