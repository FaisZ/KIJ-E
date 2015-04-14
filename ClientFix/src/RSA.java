/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatRoom;

/**
 *
 * @author Vanjul
 */

import java.math.BigInteger; 
import java.util.Random;
import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
  
public class RSA { 
        
        public static void main (String[] args) throws IOException, NoSuchAlgorithmException
        { 
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            final KeyPair key = keyGen.generateKeyPair();

            String pub = key.getPublic().toString();
            String pri = key.getPrivate().toString();

            System.out.println("public key: "+pub+" - " + pub.length());
            System.out.println("private key: "+pri+" - " + pri.length());

        }    
}
