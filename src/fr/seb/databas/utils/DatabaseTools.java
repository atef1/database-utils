package fr.seb.databas.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author formation
 */
public class DatabaseTools {

    public static String sha1Encode(String text) {

        String encoded = "";
        try {

            MessageDigest crypto = MessageDigest.getInstance("SHA-1");
            crypto.reset();
            crypto.update(text.getBytes("utf-8"));
            encoded = bytetoHex(crypto.digest());

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encoded;
    }

    /**
     * conversion d'un tableau de caractére en chaine héxadécimale
     *
     * @param byteArray
     * @return
     */
    private static String bytetoHex(byte[] byteArray) {
        String result = "";

        Formatter f = new Formatter();

        for (byte b : byteArray) {
            f.format("%02x", b);
        }
        result = f.toString();
        return result;
    }
}
