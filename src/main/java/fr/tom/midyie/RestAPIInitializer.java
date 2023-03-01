/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 22:35
 */

package fr.tom.midyie;

import fr.tom.midyie.database.DBConnector;
import fr.tom.midyie.database.DBCredentials;
import fr.tom.midyie.exceptions.ResourceLoaderException;
import fr.tom.midyie.routes.get.UserController;
import fr.tom.midyie.utils.ResouceLoader;
import io.javalin.Javalin;

import java.io.IOException;
import java.util.Properties;

public class RestAPIInitializer {

    Javalin restAPI;

    public RestAPIInitializer() {

        try {
            ResouceLoader resouceLoader = new ResouceLoader();
            Properties applicationProperties = new Properties();
            applicationProperties.load(resouceLoader.load("application.properties"));
            restAPI = Javalin.create().start(Integer.parseInt(applicationProperties.getProperty("application.restAPI.port")));
        } catch (ResourceLoaderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {

        try {
            DBCredentials dbCredentials = new DBCredentials();
            DBConnector dbConnector = new DBConnector(dbCredentials.getUrl(), dbCredentials.getUsername(), dbCredentials.getPassword());
            dbConnector.connect();


        } catch (Exception e) {
            e.printStackTrace();
        }

        restAPI.get("/users", UserController.getAllUsers);
    }
}
