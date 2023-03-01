/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 20:39
 */

package fr.tom.midyie.exceptions;

public class DecryptorException extends Exception {

    public DecryptorException(String message, Throwable exception) {
        super(message, exception);
    }
}
