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
import java.util.Scanner;
import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
  
public class RSA { 
        
    private static String input;
    private static byte[] cipher;
    private static String plain;
    private static PublicKey pubKey;
    private static PrivateKey priKey;
    
    public RSA(String in) throws NoSuchAlgorithmException
    {
            this.input = in;
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            final KeyPair key = keyGen.generateKeyPair();
            
            pubKey = key.getPublic();
            priKey = key.getPrivate();

            /*System.out.println("public key: "+pubKey.toString()+" - " + pubKey.toString().length());
            System.out.println("private key: "+priKey.toString()+" - " + priKey.toString().length());
            
            System.out.println("Inputan: "+input);*/
            
            cipher = encrypt(input, pubKey);
            //System.out.println("Setelah enkripsi (cipher): " +cipher);
            
            plain = decrypt(cipher, priKey);
            //System.out.println("Setelah dekripsi (plain): " +plain);
    }
    
        public static void main (String[] args)
        { 
        }    
        
        public static byte[] encrypt(String text, PublicKey pub) {
        byte[] Result = null;
        try 
        {
          final Cipher cipher = Cipher.getInstance("RSA");
          
          cipher.init(Cipher.ENCRYPT_MODE, pub);
          Result = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return Result;
      }
        
        public static String decrypt(byte[] text, PrivateKey pri) {
        byte[] decrypted = null;
        String Result = null;
        try 
        {          
          final Cipher cipher = Cipher.getInstance("RSA");

          cipher.init(Cipher.DECRYPT_MODE, pri);
          decrypted = cipher.doFinal(text);
          Result = new String(decrypted);

        } catch (Exception ex) {
          ex.printStackTrace();
        }

        return Result;
  }
        
        public static byte[] getCipher()
        {
            return RSA.cipher;
        }
        
        public static String getPlain()
        {
            return RSA.plain;
        }
        
        public static PublicKey getPubKey()
        {
            return RSA.pubKey;
        }
        
        public static PrivateKey getPriKey()
        {
            return RSA.priKey;
        }
}
