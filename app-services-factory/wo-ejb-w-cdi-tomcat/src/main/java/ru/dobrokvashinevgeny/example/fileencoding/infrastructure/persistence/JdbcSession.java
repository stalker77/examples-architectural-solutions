/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Класс JdbcSession
 */
public class JdbcSession implements TransactionalSession, Session {
	private static final String TOMCAT_ENV_CONTEXT = "java:comp/env";
	private final DataSource dataSource;

	private Connection connection;

	public JdbcSession(String dataSourceName) throws SessionException {
		dataSource = getDataSourceForName(dataSourceName);
	}

	private DataSource getDataSourceForName(String dataSourceName) throws SessionException {
		try {
			Context context = new InitialContext();
			Context tomcatEnvContext = (Context) context.lookup(TOMCAT_ENV_CONTEXT);
			return (DataSource) tomcatEnvContext.lookup(dataSourceName);
		} catch (NamingException e) {
			throw new SessionException(e);
		}
	}

	@Override
	public void open() throws SessionException {
		try {
			connection = dataSource.getConnection();
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