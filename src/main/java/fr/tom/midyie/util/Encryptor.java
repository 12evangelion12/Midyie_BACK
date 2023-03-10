/*
 * Copyright © 2023 RICHE Tom.
 * All rights reserved
 *
 * @Author RICHE Tom
 * @LastEdit 05/03/2023 23:31
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

    public static String decrypt(String encrypted) throws EncryptorException {
        try {
            // Décodage du texte chiffré en Base64
            byte[] ciphertextBytes = Base64.getDecoder().decode(encrypted);

            // Récupération de la clé de chiffrement et création de l'objet SecretKeySpec
            byte[] keyBytes = PropertiesManager.loadProperties(Constants.DATABASE_PROPERTIES_FILE).getProperty(Constants.DATABASE_PASSWORD_KEY_PROPERTY).getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            // Création de l'objet Cipher et configuration en mode déchiffrement
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            // Déchiffrement du texte chiffré
            byte[] plaintextBytes = cipher.doFinal(ciphertextBytes);

            // Conversion du texte déchiffré en String

            return new String(plaintextBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new EncryptorException("Erreur de déchiffrement", e);
        }
    }
}
