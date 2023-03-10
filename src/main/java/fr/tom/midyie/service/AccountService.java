/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 05/03/2023 23:45
 */

package fr.tom.midyie.service;

import fr.tom.midyie.Main;
import fr.tom.midyie.dao.AccountDao;
import fr.tom.midyie.exception.EncryptorException;
import fr.tom.midyie.model.Account;
import fr.tom.midyie.util.Encryptor;
import org.json.JSONObject;

import java.util.List;

public class AccountService {

    private final AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }

    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    public Account getAccountById(int id) {
        return accountDao.getAccountById(id);
    }

    public void createAccount(Account account) {
        try {
            account.setPassword(Encryptor.encrypt(account.getPassword()));
            accountDao.addAccount(account);
        } catch (EncryptorException e) {
            Main.getLogger(getClass()).error("Erreur lors de la création  d'un compte", e);
        }
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(int id) {
        accountDao.deleteAccount(id);
    }

    public JSONObject authenticate(String accountIdentifiant, String accountPassword) {
        try {
            return accountDao.authenticate(accountIdentifiant, Encryptor.encrypt(accountPassword));
        } catch (EncryptorException e) {
            throw new RuntimeException(e);
        }
    }
}
