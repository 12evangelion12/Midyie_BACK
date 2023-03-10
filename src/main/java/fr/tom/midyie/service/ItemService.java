/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 10/03/2023 01:55
 */

package fr.tom.midyie.service;

import fr.tom.midyie.dao.ItemDao;
import fr.tom.midyie.model.Item;

import java.util.List;

public class ItemService {

    private final ItemDao itemDao;
    private final PropertyService propertyService;

    public ItemService() {
        itemDao = new ItemDao();
        propertyService = new PropertyService();
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    public List<Item> getItemsByProperty(String property, boolean state) {

        if (!propertyService.verifyPropertyExist(property)) {
            return null;
        }
        return itemDao.getItemsByProperty(property, state);
    }

    public Item getItemById(int id) {
        return itemDao.getItemById(id);
    }

    public boolean createItem(Item item) {
        return itemDao.createItem(item);
    }

    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    public void deleteItem(int id) {
        itemDao.deleteItem(id);
    }
}
