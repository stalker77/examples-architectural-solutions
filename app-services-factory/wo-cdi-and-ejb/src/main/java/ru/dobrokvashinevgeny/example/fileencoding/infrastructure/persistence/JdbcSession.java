/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import java.sql.*;

/**
 * Класс JdbcSession
 */
public class JdbcSession implements TransactionalSession, Session {
	private final String jdbcUrl;

	private Connection connection;

	public JdbcSession(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	@Override
	public void open() throws SessionException {
		try {
			connection = DriverManager.getConnection(jdbcUrl);
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	@Override
	public void startTransaction() throws SessionException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	@Override
	public void rollbackTransaction() throws SessionException {
		try {
			connection.rollback();
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	@Override
	public void commitTransaction() throws SessionException {
		try {
			connection.commit();
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	@Override
	public void close() throws SessionException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SessionException {
		try {
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	@Override
	public CallableStatement getCallableStatement(String sql) throws SessionException {
		try {
			return connection.prepareCall(sql);
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}
}