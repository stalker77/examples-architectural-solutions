package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services;

import javax.ejb.Local;

/**
 * Класс AppServiceLocal
 */
@Local
public interface AppServiceLocal {
	void executeUseCase() throws AppServiceBeanException;
}