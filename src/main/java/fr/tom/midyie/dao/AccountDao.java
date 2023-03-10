/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 10/03/2023 02:25
 */

package fr.tom.midyie.dao;

import fr.tom.midyie.Main;
import fr.tom.midyie.database.DBConnector;
import fr.tom.midyie.exception.DatabaseCredentialsException;
import fr.tom.midyie.exception.EncryptorException;
import fr.tom.midyie.model.Account;
import fr.tom.midyie.util.Encryptor;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe contenant toutes les différentes requêtes vers la base de donnée pour le service "Account"
 */
public class AccountDao {

    private static final String ID_FIELD = "id";
    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private static final String PRIVILEGES_FIELD = "privileges";
    private static final String SELECT_ALL_QUERY = "SELECT id,username,password,privileges FROM account";
    private static final String SELECT_BY_ID_QUERY = "SELECT id,username,password,privileges FROM account WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO account (username, password, privileges) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE account SET username=?, password=?, privileges=? WHERE id=?";

    private static final String AUTHENTICATE_QUERY = "SELECT id,privileges FROM account WHERE username= ? AND password = ?";
    private static final String DELETE_QUERY = "DELETE FROM account WHERE id=?";

    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();

        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Account account = new Account(rs.getInt(ID_FIELD), rs.getString(USERNAME_FIELD), rs.getString(PASSWORD_FIELD), rs.getString(PRIVILEGES_FIELD));
                accounts.add(account);
            }
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de récupérer la liste des comptes de la base de donnée", e);
        }

        return accounts;
    }

    public Account getAccountById(int id) {
        Account account = null;

        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    account = new Account(rs.getInt(ID_FIELD), rs.getString(USERNAME_FIELD), rs.getString(PASSWORD_FIELD), rs.getString(PRIVILEGES_FIELD));
                }
            }
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de récupérer le compte avec l'id \"" + id + "\" de la base de donnée", e);
        }

        return account;
    }

    public void addAccount(Account account) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getPrivileges());
            statement.executeUpdate();
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible d'ajouter le compte " + account + " à la base de donnée", e);
        }
    }

    public void updateAccount(Account account) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getPrivileges());
            statement.setInt(4, account.getId());
            statement.executeUpdate();
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de mettre à jour le compte " + account + " de la base de donnée", e);
        }
    }

    public void deleteAccount(int id) {
        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | DatabaseCredentialsException e) {
            Main.getLogger(getClass()).error("Impossible de supprimer le compte avec l'id \"" + id + "\" de la base de donnée", e);
        }
    }

    public JSONObject authenticate(String accountIdentifiant, String accountPassword) {


        try {
            System.out.println(Encryptor.decrypt("DQi7hDiBEtzzR1uD5Iq49g=="));
        } catch (EncryptorException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject();


        try (Connection conn = new DBConnector().connect();
             PreparedStatement statement = conn.prepareStatement(AUTHENTICATE_QUERY)) {

            statement.setString(1, accountIdentifiant);
            statement.setString(2, accountPassword);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    jsonObject.put("authenticated", true);
                    jsonObject.put("account_type", rs.getString("privileges"));
                }
            }
        } catch (SQLException | DatabaseCredentialsException e) {
            jsonObject.put("authenticated", false);
            Main.getLogger(getClass()).error("L'authentification à échouer les idenfifiants n'existe pas en base", e);
        }

        return jsonObject;

    }
}
