/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 09/03/2023 23:57
 */

package fr.tom.midyie.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.tom.midyie.service.MinecraftNameService;
import io.javalin.http.Context;

public class MinecraftNameController {

    MinecraftNameService minecraftNameService;

    public MinecraftNameController() {
        minecraftNameService = new MinecraftNameService();
    }

    public void checkIfNameIsAvailable(Context context) {
        String pseudo = context.queryParam("pseudo");
        boolean pseudoAvailable = minecraftNameService.checkIfNameIsAvailable(pseudo);

        ObjectNode jsonNode = new ObjectMapper().createObjectNode();
        jsonNode.put("available", pseudoAvailable);
        context.json(jsonNode);
    }
}
