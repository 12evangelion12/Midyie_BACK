/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 03/03/2023 00:36
 */

package fr.tom.midyie.dao;

import fr.tom.midyie.Main;
import fr.tom.midyie.database.DBConnector;
import fr.tom.midyie.exception.DatabaseCredentialsException;
import fr.tom.midyie.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String MINECRAFT_ID_FIELD = "minecraft_id";
    private static final String PROPERTIES_FIELD = "properties";
    private static final String SELECT_ALL_QUERY = "SELECT id,name,minecraft_id,properties FROM item";
    private static final String SELECT_BY_ID_QUERY = "SELECT id,name,minecraft_id,properties FROM item WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO item (id, name, minecraft_id, properties) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE item SET name=?, minecraft_id=?, properties=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM item WHERE id=?";

    private final PropertyDao propertyDao;

    public ItemDao() {
        propertyDao = new PropertyDao();
    }

    public List<Item> getAllItems() {

        List<Item> items = new ArrayList<>();

        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt(ID_FIELD));
                item.setName(rs.getString(NAME_FIELD));
                item.setMinecraftId(rs.getString(MINECRAFT_ID_FIELD));
                item.setProperty(propertyDao.getPropertyById(rs.getInt(PROPERTIES_FIELD)));
                items.add(item);
            }
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de récupérer la liste des items de la base de donnée", e);
        }

        return items;
    }

    public List<Item> getItemsByProperty(String property, boolean state) {

        List<Item> items = new ArrayList<>();
        String sqlRequest = "SELECT item.id,name,minecraft_id,properties FROM item JOIN properties p on item.properties = p.id WHERE  " + property + "=?";

        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(sqlRequest)) {

            statement.setBoolean(1, state);

            System.out.println(statement);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt(ID_FIELD));
                    item.setName(rs.getString(NAME_FIELD));
                    item.setMinecraftId(rs.getString(MINECRAFT_ID_FIELD));
                    item.setProperty(propertyDao.getPropertyById(rs.getInt(PROPERTIES_FIELD)));
                    items.add(item);
                }
            }

        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de récupérer la liste des items comprenant les propriétés \"" + property + "\" !", e);
        }

        return items;
    }

    public Item getItemById(int id) {

        Item item = null;

        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    item = new Item();
                    item.setId(rs.getInt(ID_FIELD));
                    item.setName(rs.getString(NAME_FIELD));
                    item.setMinecraftId(rs.getString(MINECRAFT_ID_FIELD));
                    item.setProperty(propertyDao.getPropertyById(rs.getInt(PROPERTIES_FIELD)));
                }
            }
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de récupérer l'item avec l'id \"" + id + "\" de la base de donnée", e);
        }

        return item;
    }

    public void createItem(Item item) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, item.getName());
            statement.setString(2, item.getMinecraftId());
            statement.setInt(3, item.getProperty().getId());
            propertyDao.addProperty(item.getProperty());
            statement.executeUpdate();
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible d'ajouter le compte " + item + " à la base de donnée", e);
        }
    }

    public void updateItem(Item item) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, item.getName());
            statement.setString(2, item.getMinecraftId());
            statement.setInt(3, item.getProperty().getId());
            propertyDao.updateProperty(item.getProperty());
            statement.setInt(4, item.getId());
            statement.executeUpdate();
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de mettre à jour l'item " + item + " de la base de donnée", e);
        }
    }

    public void deleteItem(int id) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            propertyDao.deleteProperty(getItemById(id).getProperty().getId());
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de supprimer l'item avec l'id \"" + id + "\" de la base de donnée", e);
        }
    }
}
