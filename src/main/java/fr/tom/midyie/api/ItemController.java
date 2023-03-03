/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 03/03/2023 00:42
 */

package fr.tom.midyie.api;

import fr.tom.midyie.model.Item;
import fr.tom.midyie.service.ItemService;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class ItemController {

    private final ItemService itemService;

    public ItemController() {
        itemService = new ItemService();
    }

    public void getAllItems(Context context) {
        List<Item> items = itemService.getAllItems();
        context.json(items);
    }

    public void getItemsByProperty(Context context) {
        String property = context.queryParam("property");
        boolean state = Boolean.parseBoolean(context.queryParam("state"));
        List<Item> items = itemService.getItemsByProperty(property, state);

        if (items == null) {
            context.json("La propriété \""+property+"\" n'existe pas !").status(404);
            return;
        }

        context.json(items);
    }

    public void getItemById(Context context) {
        String itemId = context.queryParam("id");
        Item item = itemService.getItemById(Integer.parseInt(itemId));
        if (item == null) {
            throw new NotFoundResponse("Item introuvable");
        }
        context.json(item);
    }

    public void createItem(Context context) {
        Item item = context.bodyAsClass(Item.class);
        itemService.createItem(item);
        context.status(201); // Created
    }

    public void updateItem(Context context) {
        Item item = context.bodyAsClass(Item.class);
        itemService.updateItem(item);
        context.status(204); // No Content
    }

    public void deleteItem(Context context) {
        String itemId = context.queryParam("id");
        itemService.deleteItem(Integer.parseInt(itemId));
        context.status(204); // No Content
    }
}
