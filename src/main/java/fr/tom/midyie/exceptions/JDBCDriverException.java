/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 22:29
 */

package fr.tom.midyie.exceptions;

public class JDBCDriverException extends Exception {
    public JDBCDriverException(String message, Throwable cause) {
        super(message, cause);
    }
}
