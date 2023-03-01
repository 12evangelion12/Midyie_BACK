/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 22:30
 */

package fr.tom.midyie;

import fr.tom.midyie.exceptions.JDBCDriverException;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws JDBCDriverException {
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            new RestAPIInitializer().start();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new JDBCDriverException("Erreur lors de l'instanciation du driver JDBC !", e);
        }
    }
}