/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 20:25
 */

package fr.tom.midyie.database;

import fr.tom.midyie.exception.DatabaseCredentialsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de gestion de la connexion à la base de donnée
 */
public class DBConnector {

    private final DBCredentials dbCredentials;

    private Connection connection;

    public DBConnector() throws DatabaseCredentialsException {
        this.dbCredentials = new DBCredentials();
    }

    public Connection connect() throws SQLException {
        connection = DriverManager.getConnection(dbCredentials.getUrl(), dbCredentials.getUsername(), dbCredentials.getPassword());
        return connection;
    }
}
