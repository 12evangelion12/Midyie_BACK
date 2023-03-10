/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 09/03/2023 23:48
 */

package fr.tom.midyie;

import fr.tom.midyie.api.AccountController;
import fr.tom.midyie.api.ItemController;
import fr.tom.midyie.api.MinecraftNameController;
import fr.tom.midyie.api.PropertyController;
import fr.tom.midyie.common.Constants;
import fr.tom.midyie.util.PropertiesManager;
import io.javalin.Javalin;

import java.util.Properties;

public class RestAPIInitializer {

    Javalin restAPI;
    int restAPIPort;

    public RestAPIInitializer() {

        //Initialisation de l'api javalin
        Properties applicationProperties = PropertiesManager.loadProperties(Constants.APPLICATION_PROPERTIES_FILE);
        restAPI = Javalin.create();
        restAPIPort = Integer.parseInt(applicationProperties.getProperty(Constants.APPLICATION_RESTAPI_PORT_PROPERTY));
    }

    /**
     * Initialisation des différentes routes de chaque service
     */
    public void initRoad() {

        AccountController accountController = new AccountController();
        restAPI.get("/accounts", accountController::getAllAccounts);
        restAPI.get("/account/", accountController::getAccountById);
        restAPI.post("/account", accountController::verifyAccount);
        restAPI.put("/account/{accountId}", accountController::updateProperty);
        restAPI.delete("/account/{accountId}", accountController::deleteProperty);

        ItemController itemController = new ItemController();
        restAPI.get("/items", itemController::getAllItems);
        restAPI.get("/itemsByProperty", itemController::getItemsByProperty);
        restAPI.get("/item", itemController::getItemById);
        restAPI.post("/item", itemController::createItem);
        restAPI.put("/item/{itemId}", itemController::updateItem);
        restAPI.delete("/item", itemController::deleteItem);

        PropertyController propertyController = new PropertyController();
        restAPI.get("/property/{propertyId}", propertyController::getPropertyById);
        restAPI.post("/property", propertyController::createAccount);
        restAPI.put("/property/{propertyId}", propertyController::updateAccount);
        restAPI.delete("/property/{propertyId}", propertyController::deleteProperty);

        MinecraftNameController minecraftNameController = new MinecraftNameController();
        restAPI.get("/pseudoAvailable", minecraftNameController::checkIfNameIsAvailable);
    }

    /**
     * Démarrage de l'API REST Javalin
     */
    public void start() {
        restAPI.start(restAPIPort);
    }
}
