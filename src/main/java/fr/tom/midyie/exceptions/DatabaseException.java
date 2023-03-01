/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 21:06
 */

package fr.tom.midyie.exceptions;

public class DatabaseException extends Exception {

    public DatabaseException(String message, Throwable exception) {
        super(message, exception);
    }
}
