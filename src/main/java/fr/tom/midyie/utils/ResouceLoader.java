/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 01/03/2023 21:28
 */

package fr.tom.midyie.utils;

import fr.tom.midyie.Main;
import fr.tom.midyie.exceptions.ResourceLoaderException;

import java.io.InputStream;

public class ResouceLoader {

    public InputStream load(String relativeResourcePath) throws ResourceLoaderException {

        return Main.class.getClassLoader().getResourceAsStream(relativeResourcePath);
    }
}
