/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 22:03
 */

package fr.tom.midyie.api;

import fr.tom.midyie.model.Account;
import fr.tom.midyie.service.AccountService;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;

/**
 * Classe controller des comptes contenant les différentes requêtes de l'API pour ce service
 */
public class AccountController {
    private final AccountService accountService;

    public AccountController() {
        accountService = new AccountService();
    }

    // Handler pour récupérer tous les comptes
    public void getAllAccounts(Context context) {
        List<Account> accounts = accountService.getAllAccounts();
        context.json(accounts);
    }

    // Handler pour récupérer un compte par ID
    public void getAccountById(Context context) {
        String accountId = context.pathParam("id");
        Account account = accountService.getAccountById(Integer.parseInt(accountId));
        if (account == null) {
            throw new NotFoundResponse("Compte introuvable");
        }
        context.json(account);
    }

    // Handler pour créer un nouveau compte
    public void createProperty(Context context) {
        Account account = context.bodyAsClass(Account.class);
        accountService.createAccount(account);
        context.status(201); // Created
    }

    // Handler pour mettre à jour un compte existant
    public void updateProperty(Context context) {
        Account account = context.bodyAsClass(Account.class);
        accountService.updateAccount(account);
        context.status(204); // No Content
    }

    // Handler pour supprimer un compte existant
    public void deleteProperty(Context context) {
        String accountId = context.pathParam("id");
        accountService.deleteAccount(Integer.parseInt(accountId));
        context.status(204); // No Content
    }
}
