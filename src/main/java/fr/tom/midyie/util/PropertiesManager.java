/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 03/03/2023 00:44
 */

package fr.tom.midyie.util;

import fr.tom.midyie.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    /**
     * Chargement des propriétés de configuration de l'application (depuis un
     * éventuel fichier de configuration).
     */
    public static Properties loadProperties(String configurationFilePath) {
        Properties properties = new Properties();

        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(configurationFilePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            Main.getLogger(PropertiesManager.class).error("Erreur lors du chargement du fichier de configuration \"" + configurationFilePath + "\" !", e);
        }

        return properties;
    }
}