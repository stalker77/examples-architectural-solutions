/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.configuration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Класс AppConfigurationPropertyProducer
 */
@ApplicationScoped
public class AppConfigurationPropertyProducer {
	private static final String LOCATION_APP_CONFIG_FILE = "location.app.config.file";

	private String dataSourceName;

	@Produces @ConfigurationPropertyWithName(propertyName = "dataSourceName")
	public String getDataSourceName() {
		return dataSourceName;
	}

	@PostConstruct
	private void loadFromConfigFile() {
		String locationConfigFile = getLocationConfigFile();
		// load config from file ...
		dataSourceName = "java:/datasources/H2DS";
	}

	private String getLocationConfigFile() throws AppConfigurationException {
		final String locationConfigFile = System.getProperty(LOCATION_APP_CONFIG_FILE);
		if (locationConfigFile == null ||
			"".equals(locationConfigFile.trim())) {
			throw new AppConfigurationException("Parameter " + LOCATION_APP_CONFIG_FILE + " - not found!");
		}

		return locationConfigFile;
	}
}