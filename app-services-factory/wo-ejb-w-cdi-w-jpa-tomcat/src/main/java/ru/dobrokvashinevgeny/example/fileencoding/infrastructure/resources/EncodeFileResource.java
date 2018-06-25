/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.resources;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.Application;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Класс EncodeFileResource
 */
@Path("/files/encode")
public class EncodeFileResource {
	// Создание экземпляра фабрики сервисов приложения:
	@Inject
	private Application application;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String encodeFile() throws AppServiceProxyException {
		// Создание экземпляра прокси сервиса приложения из фабрики сервисов:
		AppServiceProxy appServiceProxy = application.createAppServiceProxy();
		// Выполнение нужного в данном месте сценария использования:
		appServiceProxy.executeUseCase();

		return "Success!";
	}
}