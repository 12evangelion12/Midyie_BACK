/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 20:21
 */

package fr.tom.midyie;

import fr.tom.midyie.common.Constants;
import fr.tom.midyie.exception.JDBCDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws JDBCDriverException {
        try {
            Class.forName(Constants.JDBC_DRIVER_CLASS_NAME).getDeclaredConstructor().newInstance();
            RestAPIInitializer restAPIInitializer = new RestAPIInitializer();
            restAPIInitializer.initRoad();
            restAPIInitializer.start();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new JDBCDriverException("Erreur lors de l'instanciation du driver JDBC !", e);
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}