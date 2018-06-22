/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.configuration.ConfigurationPropertyWithName;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence.*;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.*;
import ru.dobrokvashinevgeny.example.fileencoding.services.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Класс Application
 */
@ApplicationScoped
public class Application {
	@Inject
	@ConfigurationPropertyWithName(propertyName = "dataSourceName")
	private String dataSourceName;

	public AppServiceProxy createAppServiceProxy() throws AppServiceProxyException {
		JdbcSession session;
		try {
			session = new JdbcSession(dataSourceName);
		} catch (SessionException e) {
			throw new AppServiceProxyException(e);
		}

		SomeDao someDao = new JdbcSomeDao(session);
		AppService appService = new AppService(someDao);
		return new AppServiceProxy(session, appService);
	}
}