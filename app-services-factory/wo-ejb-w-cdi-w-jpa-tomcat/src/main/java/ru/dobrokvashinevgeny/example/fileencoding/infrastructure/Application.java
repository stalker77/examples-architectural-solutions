/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure;

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
	@Inject
	private JpaSession session;

	public AppServiceProxy createAppServiceProxy() {
		SomeDao someDao = new JpaSomeDao(session);
		AppService appService = new AppService(someDao);
		return new AppServiceProxy(session, appService);
	}
}