/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import ru.dobrokvashinevgeny.example.fileencoding.services.*;

import javax.persistence.*;
import java.util.Random;

/**
 * Класс JpaSomeDao
 */
public class JpaSomeDao implements SomeDao {
	@PersistenceContext(unitName = "ru.dobrokvashinevgeny.example.jpa")
	private EntityManager entityManager;

	@Override
	public void doSomething() throws SomeDaoException {	//<- можно заметить что здесь мы уже не заботимся об
														// транзакциях и соединениях, а выполняем нужную работу
		final JpaSomething jpaSomething = new JpaSomething();
		int id = new Random().nextInt();
		jpaSomething.setId(id);
		jpaSomething.setName("Test" + id);

		entityManager.persist(jpaSomething);
		entityManager.flush();
	}
}