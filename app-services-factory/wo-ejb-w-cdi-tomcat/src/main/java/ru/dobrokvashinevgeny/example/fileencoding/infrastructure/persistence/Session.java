package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import java.sql.*;

/**
 * Класс Session
 */
public interface Session {
	PreparedStatement prepareStatement(String sql) throws SessionException;

	CallableStatement getCallableStatement(String sql) throws SessionException;
}