/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 18:51
 */

package fr.tom.midyie.database;

import fr.tom.midyie.common.Constants;
import fr.tom.midyie.exception.DatabaseCredentialsException;
import fr.tom.midyie.exception.ResourceLoaderException;
import fr.tom.midyie.util.ResouceLoader;

import java.io.InputStream;
import java.util.Properties;

/**
 * Classe de gestion des paramètres d'accès à la base de donnée
 */
public class DBCredentials {

    private final String url;
    private final String username;
    private final String password;

    public DBCredentials() throws DatabaseCredentialsException {

        Properties properties = new Properties();
        ResouceLoader resouceLoader = new ResouceLoader();
        try {
            InputStream inputStream = resouceLoader.load(Constants.DATABASE_PROPERTIES_FILE);
            properties.load(inputStream);

        } catch (ResourceLoaderException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new DatabaseCredentialsException("Impossible de charger les identifiants de la base de donnée !", e);
        }


        url = properties.getProperty(Constants.DATABASE_URL_PROPERTY);
        username = properties.getProperty(Constants.DATABASE_USERNAME_PROPERTY);
        password = properties.getProperty(Constants.DATABASE_PASSWORD_PROPERTY);
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
