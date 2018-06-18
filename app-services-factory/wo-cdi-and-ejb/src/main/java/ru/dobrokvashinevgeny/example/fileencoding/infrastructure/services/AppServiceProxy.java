/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence.*;
import ru.dobrokvashinevgeny.example.fileencoding.services.*;

/**
 * Класс AppServiceProxy
 */
public class AppServiceProxy {
	private final TransactionalSession session; // сессия для управления транзакциями

	private final AppService appService;	     // сам сервис приложения

	public AppServiceProxy(TransactionalSession session, AppService appService) {
		this.session = session;
		this.appService = appService;
	}

	public void executeUseCase() throws AppServiceProxyException {
		try {
			session.open();                    // сессия внутренне получает соединение
			session.startTransaction();        // начинаем транзакцию для сервиса приложения

			try {
				// выполняем сервис приложения в контексте созданной транзакции:
				appService.executeUseCase();
			} catch (AppServiceException e) {
				session.rollbackTransaction();  // если были ошибки то откатываем транзакцию
			} finally {
				session.commitTransaction();   // подтверждаем транзакцию
				session.close();                // освобождаем соединение
			}
		} catch (SessionException e) {
			throw new AppServiceProxyException(e);
		}
	}
}