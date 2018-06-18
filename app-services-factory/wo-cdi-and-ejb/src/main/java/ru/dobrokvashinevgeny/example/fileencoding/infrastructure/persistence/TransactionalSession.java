package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

/**
 * Класс TransactionalSession
 */
public interface TransactionalSession {
	void open() throws SessionException;

	void startTransaction() throws SessionException;

	void rollbackTransaction() throws SessionException;

	void commitTransaction() throws SessionException;

	void close() throws SessionException;
}