/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import org.slf4j.*;

import javax.annotation.*;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.*;

/**
 * Класс JpaEntityManagerProducer
 */
@ApplicationScoped
public class JpaEntityManagerProducer {
	private final static Logger LOG = LoggerFactory.getLogger(JpaEntityManagerProducer.class);
	private static final String PERSISTENCE_UNIT_NAME = "ru.dobrokvashinevgeny.example.jpa";
	private EntityManagerFactory entityManagerFactory;

	@Produces @NewEntityManager
	public EntityManager getNewEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	@PostConstruct
	private void createEntityManagerFactory() {
		LOG.debug("createEntityManagerFactory");
		Persistence.generateSchema(PERSISTENCE_UNIT_NAME, null);
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@PreDestroy
	private void closeEntityManagerFactory() {
		entityManagerFactory.close();
	}
}