/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import ru.dobrokvashinevgeny.example.fileencoding.services.*;

import java.sql.*;

/**
 * Класс JdbcSomeDao
 */
public class JdbcSomeDao implements SomeDao {
	private static final String SQL = "update tbl_some set some_field = ? where another_field = ?";
	private final Session session;

	public JdbcSomeDao(Session session) {
		this.session = session;
	}

	@Override
	public void doSomething() throws SomeDaoException {	//<- можно заметить что здесь мы уже не заботимся об
														// транзакциях и соединениях, а выполняем нужную работу
		try (PreparedStatement preparedStatement = session.prepareStatement(SQL)) {
			preparedStatement.setInt(1, 1234567);
			preparedStatement.setInt(2, 7654321);
			preparedStatement.executeUpdate();
		} catch (SQLException | SessionException e) {
			throw new SomeDaoException(e);
		}
	}
}