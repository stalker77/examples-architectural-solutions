/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.presentation;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.Application;
import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services.AppServiceProxy;

import javax.enterprise.inject.se.*;

/**
 * Класс MainEntryPoint
 */
public class MainEntryPoint {
	public static void main(String[] args) throws Exception { //<- точка входа
		// Создание экземпляра фабрики сервисов приложения из ее конфигурации:
		SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
		try (SeContainer container = containerInitializer.initialize()) {
			Application application = container.select(Application.class).get();
			// Создание экземпляра прокси сервиса приложения из фабрики сервисов:
			AppServiceProxy appServiceProxy = application.createAppServiceProxy();
			// Выполнение нужного в данном месте сценария использования:
			appServiceProxy.executeUseCase();
		}
	}
}