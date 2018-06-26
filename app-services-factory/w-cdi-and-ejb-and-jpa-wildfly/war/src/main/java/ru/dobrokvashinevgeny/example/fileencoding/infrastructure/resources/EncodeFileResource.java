/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.resources;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Класс EncodeFileResource
 */
@Path("/files/encode")
public class EncodeFileResource {
	// Получение экземпляра сервиса приложения:
	@EJB
	private AppServiceLocal appServiceBean;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String encodeFile() throws AppServiceBeanException {
		// Выполнение нужного в данном месте сценария использования:
		appServiceBean.executeUseCase();

		return "Success!";
	}
}