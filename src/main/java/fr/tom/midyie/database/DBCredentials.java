/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 21:23
 */

package fr.tom.midyie.database;

import fr.tom.midyie.exceptions.DatabaseCredentialsException;
import fr.tom.midyie.exceptions.ResourceLoaderException;
import fr.tom.midyie.utils.ResouceLoader;

import java.io.InputStream;
import java.util.Properties;

public class DBCredentials {

    private final String url;
    private final String username;
    private final String password;

    public DBCredentials() throws DatabaseCredentialsException {

        Properties properties = new Properties();
        ResouceLoader resouceLoader = new ResouceLoader();
        try {
            InputStream inputStream = resouceLoader.load("database.properties");
            properties.load(inputStream);

        } catch (ResourceLoaderException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new DatabaseCredentialsException("Impossible de charger les identifiants de la base de donnée !", e);
        }


        url = properties.getProperty("database.url");
        username = properties.getProperty("database.username");
        password = properties.getProperty("database.password");
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
