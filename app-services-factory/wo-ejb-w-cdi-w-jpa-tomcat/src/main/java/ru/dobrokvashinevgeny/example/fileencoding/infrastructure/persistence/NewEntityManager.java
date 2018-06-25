package ru.dobrokvashinevgeny.example.fileencoding.infrastructure.persistence;

import javax.inject.Qualifier;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Класс NewEntityManager
 */
@Qualifier
@Retention(RUNTIME)
@Target({FIELD, TYPE, METHOD, PARAMETER})
public @interface NewEntityManager {
}