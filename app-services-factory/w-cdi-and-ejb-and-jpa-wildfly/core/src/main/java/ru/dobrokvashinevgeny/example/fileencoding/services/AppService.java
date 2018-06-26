/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.services;

/**
 * Класс AppService
 */
public class AppService {
	private final SomeDao someDao;

	public AppService(ServiceRegistry serviceRegistry) {
		this.someDao = serviceRegistry.someDao();
	}

	public void executeUseCase() throws AppServiceException {
		try {
			someDao.doSomething();
		} catch (SomeDaoException e) {
			throw new AppServiceException(e);
		}
	}
}