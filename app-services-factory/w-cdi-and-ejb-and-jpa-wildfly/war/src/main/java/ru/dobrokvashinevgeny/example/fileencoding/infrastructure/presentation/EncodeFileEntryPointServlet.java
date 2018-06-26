/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.presentation;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.*;

import javax.ejb.EJB;
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

	// Получение экземпляра сервиса приложения:
	@EJB
	private AppServiceLocal appServiceBean;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException {
		// Выполнение нужного в данном месте сценария использования:
		try {
			appServiceBean.executeUseCase();

			putSuccessfulResultTo(response);
		} catch (AppServiceBeanException | IOException e) {
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