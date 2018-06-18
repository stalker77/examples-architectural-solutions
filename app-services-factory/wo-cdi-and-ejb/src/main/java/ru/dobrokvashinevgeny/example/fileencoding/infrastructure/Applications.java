/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure;

import ru.dobrokvashinevgeny.example.fileencoding.infrastructure.configuration.*;

/**
 * Класс Applications
 */
public class Applications {
	public static Application createFromConfigFileWithName(String locationConfigFile) throws AppConfigurationException {
		return new Application(AppConfiguration.loadFromConfigFile(locationConfigFile));
	}
}