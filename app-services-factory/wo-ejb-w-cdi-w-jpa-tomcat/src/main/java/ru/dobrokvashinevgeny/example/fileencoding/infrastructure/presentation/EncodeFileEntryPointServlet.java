/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.presentation;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.Application;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.*;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Класс EncodeFileEntryPointServlet
 */
@WebServlet("/services/encodefile")
public class EncodeFileEntryPointServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger("EncodeFileEntryPointServlet");
	private static final String RESPONSE_CONTENT_TYPE = "text/plain";
	private static final String RESPONSE_ENCODING = "UTF-8";

	// Создание экземпляра фабрики сервисов приложения:
	@Inject
	private Application application;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException {
		// Создание экземпляра прокси сервиса приложения из фабрики сервисов:
		AppServiceProxy appServiceProxy = application.createAppServiceProxy();
		// Выполнение нужного в данном месте сценария использования:
		try {
			appServiceProxy.executeUseCase();

			putSuccessfulResultTo(response);
		} catch (AppServiceProxyException | IOException e) {
			LOG.severe(e.toString());
			throw new ServletException(e);
		}
	}

	private void putSuccessfulResultTo(HttpServletResponse response) throws IOException {
		response.setContentType(RESPONSE_CONTENT_TYPE);
		response.setCharacterEncoding(RESPONSE_ENCODING);
		response.getWriter().write("Success!");
	}
}