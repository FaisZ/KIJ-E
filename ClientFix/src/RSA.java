import java.math.BigInteger; 
import java.util.Random;
import java.io.*;
import java.nio.charset.Charset;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author F
 */

// Encrypt message
public class RSA {

    private BigInteger p; 
    private BigInteger q; 
    private BigInteger N; 
    private BigInteger phi; 
    private BigInteger e; 
    private BigInteger d; 
    private int bitlength = 1024; 
    private int blocksize = 256; //blocksize in byte 
     
    private Random r; 
     
    public RSA() 
    { 
        r = new Random(); 
        p = BigInteger.probablePrime(bitlength, r); 
        q = BigInteger.probablePrime(bitlength, r); 
        N = p.multiply(q); 
           
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); 
        e = BigInteger.probablePrime(bitlength/2, r); 
         
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) 
        { 
            e.add(BigInteger.ONE); 
        } 

        d = e.modInverse(phi);  
    } 
    
    public RSA(BigInteger e, BigInteger d, BigInteger N) 
    { 
        this.e = e; 
        this.d = d; 
        this.N = N; 
    } 
     
    public static String RSA_Encryption (String AESKey)
{ 
    RSA rsa = new RSA(); 
    DataInputStream in=new DataInputStream(System.in);  

    // encrypt 
    byte[] encrypted = rsa.encrypt(AESKey.getBytes());                   

    // decrypt 
    byte[] decrypted = rsa.decrypt(encrypted);       

    return (bytesToString(decrypted));
} 

    private static String bytesToString(byte[] encrypted) 
    { 
        String test = ""; 
        for (byte b : encrypted) 
        { 
            test += Byte.toString(b); 
        } 
        return test; 
    } 
   
    // Encrypt key
    public byte[] encrypt(byte[] AESKey) 
    {
        return (new BigInteger(AESKey)).modPow(e, N).toByteArray(); 
    } 
       
    // Decrypt key
    public byte[] decrypt(byte[] AESKey) 
    {
        return (new BigInteger(AESKey)).modPow(d, N).toByteArray(); 
    }    
}
