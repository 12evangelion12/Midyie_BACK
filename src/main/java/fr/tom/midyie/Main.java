/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 00:20
 */

package fr.tom.midyie;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        try (Javalin app = Javalin.create().start(7777)) {

            app.get("/", context -> context.result("Hello World"));
        }
    }
}