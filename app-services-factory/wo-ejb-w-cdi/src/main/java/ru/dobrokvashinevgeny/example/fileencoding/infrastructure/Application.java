/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.configuration.ConfigurationPropertyWithName;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence.*;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.AppServiceProxy;
import ru.dobrokvashinevgeny.example.fileencoding.services.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Класс Application
 */
@ApplicationScoped
public class Application {
	@Inject @ConfigurationPropertyWithName(propertyName = "jdbcUrl")
	private String jdbcUrl;

	public AppServiceProxy createAppServiceProxy() {
		JdbcSession session = new JdbcSession(jdbcUrl);
		SomeDao someDao = new JdbcSomeDao(session);
		AppService appService = new AppService(someDao);
		return new AppServiceProxy(session, appService);
	}
}