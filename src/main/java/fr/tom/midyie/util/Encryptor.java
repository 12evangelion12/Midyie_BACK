/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 02/03/2023 21:09
 */

package fr.tom.midyie.util;

import fr.tom.midyie.common.Constants;
import fr.tom.midyie.exception.EncryptorException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryptor {

    private static final String ALGORITHM = "AES";

    public static String encrypt(String plainText) throws EncryptorException {

        try {
            byte[] key = PropertiesManager.loadProperties(Constants.DATABASE_PROPERTIES_FILE).getProperty(Constants.DATABASE_PASSWORD_KEY_PROPERTY).getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(encryptedBytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new EncryptorException("Erreur lors de l'encryptement d'une donnée !", e);
        }
    }
}
