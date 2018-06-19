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

	private String jdbcUrl;

	@Produces @ConfigurationPropertyWithName(propertyName = "jdbcUrl")
	public String getJdbcUrl() {
		return jdbcUrl;
	}

	@PostConstruct
	private void loadFromConfigFile() {
		String locationConfigFile = getLocationConfigFile();
		// load config from file ...
		jdbcUrl = "jdbc:h2:mem:test;MVCC=true";
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