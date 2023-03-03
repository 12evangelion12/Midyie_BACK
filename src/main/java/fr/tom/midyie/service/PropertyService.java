/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 21:48
 */

package fr.tom.midyie.service;

import fr.tom.midyie.dao.PropertyDao;
import fr.tom.midyie.model.Property;

public class PropertyService {

    private final PropertyDao propertyDao;

    public PropertyService() {
        propertyDao = new PropertyDao();
    }

    public Property getPropertyById(int id) {
        return propertyDao.getPropertyById(id);
    }

    public void createProperty(Property property) {
        propertyDao.addProperty(property);
    }

    public void updateProperty(Property property) {
        propertyDao.updateProperty(property);
    }

    public void deleteProperty(int id) {
        propertyDao.deleteProperty(id);
    }

    public boolean verifyPropertyExist(String property) { return propertyDao.verifyPropertyExist(property); }
}
