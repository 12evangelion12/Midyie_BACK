/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 03/03/2023 00:02
 */

package fr.tom.midyie.service;

import fr.tom.midyie.dao.ItemDao;
import fr.tom.midyie.model.Item;

import java.util.List;

public class ItemService {

    private final ItemDao itemDao;

    public ItemService() {
        itemDao = new ItemDao();
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    public List<Item> getItemsByProperty(String property, boolean state) {
        return itemDao.getItemsByProperty(property, state);
    }

    public Item getItemById(int id) {
        return itemDao.getItemById(id);
    }

    public void createItem(Item item) {
        itemDao.createItem(item);
    }

    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    public void deleteItem(int id) {
        itemDao.deleteItem(id);
    }
}
