/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.presentation;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.*;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.AppServiceProxy;

/**
 * Класс MainEntryPoint
 */
public class MainEntryPoint {
	private static final String LOCATION_APP_CONFIG_FILE = "location.app.config.file";

	public static void main(String[] args) throws Exception { //<- точка входа
		// Создание экземпляра фабрики сервисов приложения из ее конфигурации:
		Application application = Applications.createFromConfigFileWithName(getConfigFileLocation());
		// Создание экземпляра прокси сервиса приложения из фабрики сервисов:
		AppServiceProxy appServiceProxy = application.createAppServiceProxy();
		// Выполнение нужного в данном месте сценария использования:
		appServiceProxy.executeUseCase();
	}

	private static String getConfigFileLocation() throws GeneralApplicationException {
		final String configFileLocation = System.getProperty(LOCATION_APP_CONFIG_FILE);
		if (configFileLocation == null ||
			"".equals(configFileLocation.trim())) {
			throw new GeneralApplicationException("Parameter " + LOCATION_APP_CONFIG_FILE + " - not found!");
		}

		return configFileLocation;
	}
}