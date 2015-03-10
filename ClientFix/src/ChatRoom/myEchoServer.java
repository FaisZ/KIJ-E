
package ChatRoom;


import java.io.*;
import java.net.*;
import java.util.*;
public class myEchoServer implements Runnable{
    
    private  ServerSocket servSock;
    private int PORT;
    public UI_Server formInduk;
    public UI_Client formKlien;
    public Handle[] clients;
    public Socket klien;
   // public ServerSocket server = null;
    public Thread thread = null;
    public int clientCount = 0;
   // public ServerFrame ui;
    
    
    
    public myEchoServer(){
        
    }
    
    private void addThread(Socket socket){
         
         if (clientCount < clients.length){  
            //formInduk.writelog("[server] a new Client has been accepted with IP Address : " + socket.getInetAddress().getHostAddress() + "\n");
	    clients[clientCount] = new Handle(formInduk, socket);
	    try{  
	      	//clients[clientCount].open(); 
	        //clients[clientCount].start();  // running client thread
                clientCount++; 
	        //formInduk.writelog("test\n");
	    }
	    catch(Exception e){  
	      	formInduk.writelog(" Error opening thread: " + e.getMessage() + "\n"); 
	    } 
	}
         
     } 
    
    public void run() {
       //this.formInduk.writelog("[server] EchoServer Thread has been started\n");
       
        while (thread != null){  
            try{  
	         /*if (this.clientCount ==0 ){
                   this.formInduk.writelog("[server] Waiting for a new client ...\n");
                 }
                 else
                 {
                     this.formInduk.writelog("[server] Waiting for other clients ...\n");    
                 }*/  
                 
                 Socket in = servSock.accept();
                 formInduk.writelog("[server] a new Client has been accepted with IP Address : " + in.getInetAddress().getHostAddress() + "\n");
                 //DataOutputStream output = new DataOutputStream(in.getOutputStream());
                 //DataInputStream input = new DataInputStream(in.getInputStream());
                 
	         addThread(in); 
                 String listuser = "";
                for(int i = 0;i<clientCount;i++)
                {
                    listuser+="\n"+clients[i].nama;
                }
                this.formInduk.sendToClient(listuser);
	    }
	    catch(Exception ioe){ 
                this.formInduk.writelog("\nServer accept error: \n");
                
	    }
        }
        
        
    }

    
    
    public myEchoServer( UI_Server formUtama,  int Port){
        
        this.formInduk = formUtama;
        clients = new Handle[50];
        
        try {
            this.servSock = new ServerSocket(Port);
            this.formInduk.writelog("[server] Building Server  .. \n");    
            this.mulaiThread();
        }
        catch (Exception e) {
            
        }
        
    }
    
     public void mulaiThread(){  
    	if (thread == null){  
            thread = new Thread(this); 
	    thread.start();
	}
    }
    
   
   
   
    public static void main(String[] args)
   {
    //    myEchoServer myServer = new myEchoServer();
     //   myServer.startingServer();
              
       // myServer.
      
   }
   
}  