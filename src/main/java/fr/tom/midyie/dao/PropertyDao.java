/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 22:22
 */

package fr.tom.midyie.dao;

import fr.tom.midyie.Main;
import fr.tom.midyie.database.DBConnector;
import fr.tom.midyie.exception.DatabaseCredentialsException;
import fr.tom.midyie.model.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe contenant toutes les différentes requêtes vers la base de donnée pour le service "Property"
 */
public class PropertyDao {

    private static final String ID_FIELD = "id";
    private static final String BREAKABLE_FIELD = "isBreakable";
    private static final String FLAMMABLE_FIELD = "isFlammable";
    private static final String STACKABLE_FIELD = "isStackable";
    private static final String SELECT_BY_ID_QUERY = "SELECT id,isBreakable,isFlammable,isStackable FROM properties WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO properties (isBreakable, isFlammable, isStackable) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE properties SET isBreakable=?, isFlammable=?, isStackable=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM properties WHERE id=?";
    private static final String SELECT_ALL_ROW_QUERY = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'properties' AND COLUMN_NAME <> 'id'";

    public Property getPropertyById(int id) {
        Property property = null;

        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    property = new Property();
                    property.setId(rs.getInt(ID_FIELD));
                    property.setIsBreakable(rs.getBoolean(BREAKABLE_FIELD));
                    property.setIsFlammable(rs.getBoolean(FLAMMABLE_FIELD));
                    property.setIsStackable(rs.getBoolean(STACKABLE_FIELD));
                }
            }
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de récupérer le compte avec l'id \"" + id + "\" de la base de donnée", e);
        }

        return property;
    }

    public void addProperty(Property property) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(INSERT_QUERY)) {

            statement.setBoolean(1, property.getIsBreakable());
            statement.setBoolean(2, property.getIsFlammable());
            statement.setBoolean(3, property.getIsStackable());
            statement.executeUpdate();
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible d'ajouter la propriété d'item " + property + " à la base de donnée", e);
        }
    }

    public void updateProperty(Property property) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY)) {

            statement.setBoolean(1, property.getIsBreakable());
            statement.setBoolean(2, property.getIsFlammable());
            statement.setBoolean(3, property.getIsStackable());
            statement.setInt(4, property.getId());
            statement.executeUpdate();
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de mettre à jour la propriété d'item " + property + " de la base de donnée", e);
        }
    }

    public void deleteProperty(int id) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de supprimer la propriété d'item avec l'id \"" + id + "\" de la base de donnée", e);
        }
    }

    public boolean verifyPropertyExist(String property) {

        boolean propertyExist = false;

        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_ROW_QUERY)) {

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    if (rs.getString("COLUMN_NAME").equalsIgnoreCase(property)) {
                        propertyExist = true;
                    }
                }
            }
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de vérifier si la propriété \""+property+"\" existe dans la base de donnée !", e);
        }

        return propertyExist;
    }
}
