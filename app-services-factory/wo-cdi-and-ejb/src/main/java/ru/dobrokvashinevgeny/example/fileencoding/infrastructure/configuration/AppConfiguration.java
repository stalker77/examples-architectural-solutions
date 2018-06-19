/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.configuration;

/**
 * Класс AppConfiguration
 */
public class AppConfiguration {
	private String jdbcUrl = "jdbc:h2:mem:test;MVCC=true";

	public static AppConfiguration loadFromConfigFile(String locationConfigFile) throws AppConfigurationException {
		//...
		return new AppConfiguration();
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}
}