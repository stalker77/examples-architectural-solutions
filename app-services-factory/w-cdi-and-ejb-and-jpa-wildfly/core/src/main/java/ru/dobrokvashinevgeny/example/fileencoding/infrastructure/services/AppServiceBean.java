/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services;

import org.slf4j.*;
import ru.dobrokvashinevgeny.example.fileencoding.services.*;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Класс AppServiceBean
 */
@Stateless
public class AppServiceBean implements AppServiceLocal {
	private final static Logger LOG = LoggerFactory.getLogger(AppServiceBean.class);

	private final AppService appService;

	public AppServiceBean() {
		appService = null;
	}

	@Inject
	public AppServiceBean(ServiceRegistry serviceRegistry) {
		appService = new AppService(serviceRegistry);
	}

	@Override
	public void executeUseCase() throws AppServiceBeanException {
		try {
			appService.executeUseCase();
		} catch (AppServiceException e) {
			throw new AppServiceBeanException(e);
		}
	}
}