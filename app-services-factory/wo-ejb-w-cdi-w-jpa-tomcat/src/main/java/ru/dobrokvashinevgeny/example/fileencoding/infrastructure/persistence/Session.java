package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import javax.persistence.Query;

/**
 * Класс Session
 */
public interface Session {
	Query cerateQuery(String queryText);

	void persistEntity(Object entity);

	void flush();
}