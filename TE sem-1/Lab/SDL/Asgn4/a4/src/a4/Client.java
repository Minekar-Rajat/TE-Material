/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4;


// A Java program for a Client 
import java.net.*; 
import java.io.*; 
import java.util.*;
  
class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private BufferedReader  input   = null; 
    private DataOutputStream out     = null; 
    private DataInputStream in       = null; 
    private ObjectOutputStream outObj = null;
    private ObjectInputStream inObj = null;
    
  
    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new BufferedReader(new InputStreamReader(System.in)); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
            
            outObj = new ObjectOutputStream(socket.getOutputStream());
            
            in = new DataInputStream( 
                    new BufferedInputStream(socket.getInputStream()));
            inObj = new ObjectInputStream(socket.getInputStream());
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
  
        int ch,ch2;
        Employee e;
        boolean f;
        String nm,msg;
        Employee response;
        Scanner sc = new Scanner(System.in);
        String args[] = {""};
        ask.main(args);
        
        



        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close();
            in.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
   public static void main(String args[])
   {
	System.out.println("Client");
	Client c = new Client("localhost" , 8000);
        
        
        
        
   }
} 

