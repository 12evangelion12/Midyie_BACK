/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 18:51
 */

package fr.tom.midyie.util;

import fr.tom.midyie.Main;
import fr.tom.midyie.exception.ResourceLoaderException;

import java.io.InputStream;

public class ResouceLoader {

    public InputStream load(String relativeResourcePath) throws ResourceLoaderException {

        return Main.class.getClassLoader().getResourceAsStream(relativeResourcePath);
    }
}
