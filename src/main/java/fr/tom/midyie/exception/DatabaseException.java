/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 18:51
 */

package fr.tom.midyie.exception;

public class DatabaseException extends Exception {

    public DatabaseException(String message, Throwable exception) {
        super(message, exception);
    }
}
