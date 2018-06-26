/*
 * Copyright (c) 2018 Evgeny Dobrokvashin, All Rights Reserved.
 */

package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.services;

import ru.dobrokvashinevgeny.example.fileencoding.services.*;

import javax.inject.Inject;

/**
 * Класс CdiServiceRegistry
 */
public class CdiServiceRegistry implements ServiceRegistry {
	@Inject
	private SomeDao someDao;

	@Override
	public SomeDao someDao() {
		return someDao;
	}
}