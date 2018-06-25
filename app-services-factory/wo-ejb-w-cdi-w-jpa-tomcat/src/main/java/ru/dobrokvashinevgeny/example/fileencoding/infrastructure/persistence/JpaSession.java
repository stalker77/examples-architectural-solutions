/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.*;

/**
 * Класс JpaSession
 */
@RequestScoped
public class JpaSession implements TransactionalSession, Session {
	@Inject @NewEntityManager
	private EntityManager entityManager;

	private EntityTransaction currentTransaction;

	@Override
	public void open() throws SessionException {
		currentTransaction = entityManager.getTransaction();
	}

	@Override
	public void startTransaction() throws SessionException {
		currentTransaction.begin();
	}

	@Override
	public void rollbackTransaction() throws SessionException {
		currentTransaction.rollback();
	}

	@Override
	public void commitTransaction() throws SessionException {
		currentTransaction.commit();
	}

	@Override
	public void close() {
		entityManager.close();
	}

	@Override
	public Query cerateQuery(String queryText) {
		return entityManager.createQuery(queryText);
	}

	@Override
	public void persistEntity(Object entity) {
		entityManager.persist(entity);
	}

	@Override
	public void flush() {
		entityManager.flush();
	}
}