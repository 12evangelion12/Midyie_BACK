/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 22:34
 */

package fr.tom.midyie.routes.get;

import io.javalin.http.Handler;

public class UserController {

    public static Handler getAllUsers = context -> {

        context.json("test");
    };

}
