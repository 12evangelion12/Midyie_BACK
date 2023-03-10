/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 10/03/2023 00:21
 */

package fr.tom.midyie.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MinecraftNameService {
    public boolean checkIfNameIsAvailable(String pseudo) {


        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.mojang.com/users/profiles/minecraft/" + pseudo)
                    .build();

            Response response = client.newCall(request).execute();

            String result = response.body().string();

            System.out.println(result);
            JSONObject jsonObject = new JSONObject(result);
            System.out.println(jsonObject);

            try {
                jsonObject.getString("errorMessage");
                return true;
            } catch (JSONException e) {
                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
