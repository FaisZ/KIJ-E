/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaClient {

    private String hostname;
    private int port;
    Socket sockcli;
    
    public JavaClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }
    
    public void connect(){
        System.out.println("Connecting to "+hostname+":"+port);
        try {
            sockcli = new Socket(hostname,port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connected!");
    }
    
    public void rdResponse(){
        //read from server here
    }
    
    public static void main(String[] args) {
     
        JavaClient sockclient = new JavaClient("localhost",8080);
        sockclient.connect();
        sockclient.rdResponse();
    }
}