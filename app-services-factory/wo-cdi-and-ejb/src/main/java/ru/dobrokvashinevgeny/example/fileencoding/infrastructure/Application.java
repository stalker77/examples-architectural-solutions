/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.configuration.AppConfiguration;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence.*;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.AppServiceProxy;
import ru.dobrokvashinevgeny.example.fileencoding.services.*;

/**
 * Класс Application
 */
public class Application {
	private final AppConfiguration appConfiguration;

	public Application(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}

	public AppServiceProxy createAppServiceProxy() {
		JdbcSession session = new JdbcSession(appConfiguration.getJdbcUrl());
		SomeDao someDao = new JdbcSomeDao(session);
		AppService appService = new AppService(someDao);
		return new AppServiceProxy(session, appService);
	}
}