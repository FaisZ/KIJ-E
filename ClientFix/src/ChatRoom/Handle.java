package ChatRoom;

import java.net.*;
import java.io.*;
import java.util.*;

public class Handle extends Thread
{
    public String nama;
    private Socket client = null;
    //public DataInputStream input;
    //public DataOutputStream output;
    FilterInputStream input;
    FilterOutputStream output;
    private UI_Server serverUI = null;
    public UI_Client clientUI = null;
    private int Flag = 0;
    //private myEchoServer ser;
    String message;
    String[] user;
    public Handle(UI_Server data, Socket client){
        this.client = client;
        this.serverUI = data;
        //this.ser = ser;
        try{
            input = new DataInputStream(client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());
            //this.setName("Client "+numNama);
            //String message = input.readUTF();
            this.nama=message;
            this.setDaemon(true);
            this.start();
        }
        catch(java.io.IOException e)
        {
        	
        }
    }
    
    public Handle(FilterInputStream input, UI_Client data){
        this.input = input;
        this.clientUI = data;
    }
    
    public String baca(FilterInputStream input)
    {
    	try
    	{
    		String res = "";
	    	//byte[] b = new byte[20];
    		int readed;
	    	while ((readed = input.read()) > -1)
	    	{
	    		//char c = (char)b[0];
	    		char c = (char)readed;
	    		if (c=='\r') break;
	    		res = res + c;
	    	}
	    	return res;
    	}
    	catch(Exception e)
    	{
    		System.out.println("Masalah pada Thread client");
    		return null;
    	}
    }
    
    public void putus()
    {
        this.clientUI.cabut();
    }
    
    public void sendMessage(String message)throws IOException{
        //output.writeUTF(message);
    }
    
    public void pm(String Message)throws IOException
   	{     
        clientUI.writelog(Message);
    }
    
    public int Disconnect(){
        Flag = -1;
        return Flag;
    }
    
    
    public void run()
    {
		if(serverUI!=null)
		{
		    while(true)
		    {
		        try
		        {
		        	//message = input.readUTF();
		        	if(message.equals("++**Exit++**"))
		        	{
		        		message = this.nama + " Telah Meninggalkan Room\n";
		        		serverUI.writelog("\n"+this.nama+" has been Disconnect\n");
		        		//System.exit(1);
		        		//client.close();
		        	}
			        else
			        {
			            //serverUI.writelog(this.nama+" has sent message\n");
			            serverUI.writelog(message);
			        }
		        	//massage = this.getName()+" "+message+"\n";
		        
			        if(message.contains("@"))
			        {
			            String[] sp = message.split("@");
			            serverUI.writelog(sp[sp.length-1]);
			            serverUI.sendToAClient(sp[0], sp[sp.length-1]);
			        }
			        else
			            serverUI.sendToClient(message);
			            
			        //output.writeUTF(message);
			        //data_1.writelog(message);  
		        }
		        catch(IOException e)
		        {
		        	System.out.println(e.getMessage());
		        }
		    }
		}
		 
	    if(clientUI!=null)
	    { 
	        while(true)
	        {   
	            //try
	            //{
	                //message = input.readUTF();
	                message = baca(input);
	            	//ser.sendToClient(message);
	            //}
		        //catch(IOException e)
		        //{
		        //    System.out.println("Masalah pada Thread client");
		        //}
		    
		        if (message.equalsIgnoreCase("QUIT"))
		        {
		            clientUI.cabut();
		        }
		        else
		        {
		            if(message.substring(0, 1).equals("\n"))
		            {
		                user = message.split("\n");
		                this.clientUI.tujuanpesan.removeAllItems();
		                for (String user1 : user) 
		                {
		                    if(!user1.equalsIgnoreCase(this.nama))
		                        this.clientUI.tujuanpesan.addItem(user1);
		                }
		            }
		            else
		            {
		                //clientUI.writelog(message);
		            	String[] subpesan = message.split(":");
		            	String[] subMessage = clientUI.aes.AesStart(subpesan[1], clientUI.key, "dekripsi").split("0");
		            	clientUI.writelog(subpesan[0]+" : "+subMessage[0]);
		            }
		        }
	        }
	    }
    }
}
