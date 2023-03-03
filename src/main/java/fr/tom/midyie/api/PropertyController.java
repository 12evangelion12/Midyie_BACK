/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 21:47
 */

package fr.tom.midyie.api;

import fr.tom.midyie.model.Property;
import fr.tom.midyie.service.PropertyService;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

/**
 * Classe controller des propriétés d'item contenant les différentes requêtes de l'API pour ce service
 */
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController() {
        propertyService = new PropertyService();
    }

    // Handler pour récupérer une propriété par id
    public void getPropertyById(Context ctx) {
        String propertyId = ctx.pathParam("id");
        Property property = propertyService.getPropertyById(Integer.parseInt(propertyId));
        if (property == null) {
            throw new NotFoundResponse("Propriété introuvable");
        }
        ctx.json(property);
    }

    // Handler pour créer une propriété 
    public void createAccount(Context ctx) {
        Property property = ctx.bodyAsClass(Property.class);
        propertyService.createProperty(property);
        ctx.status(201); // Created
    }

    // Handler pour mettre à jour une propriété
    public void updateAccount(Context ctx) {
        Property property = ctx.bodyAsClass(Property.class);
        propertyService.updateProperty(property);
        ctx.status(204); // No Content
    }

    // Handler pour supprimer une propriété
    public void deleteProperty(Context ctx) {
        String propertyId = ctx.pathParam("id");
        propertyService.deleteProperty(Integer.parseInt(propertyId));
        ctx.status(204); // No Content
    }
}
