/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4;



import java.net.*; 
import java.io.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

  
public class Server 
{ 
    //initialize socket and input stream 
    
  	static ArrayList<String> al = new ArrayList<String>();
        ArrayList ale = new ArrayList();
        ArrayList prj = new ArrayList();
    // constructor with port 
    
  
    public static void main(String args[]) 
    { 
        
        Socket          socket   = null; 
        ServerSocket    server   = null; 
        
        int port = 8000;
        // starts server and waits for a connection 
        try
        { 
            
            
            
            server = new ServerSocket(port); 
            System.out.println(" -- Server started"); 
  
            System.out.println(" -- Waiting for a client ..."); 
            
            int i=0;
            while(true)
            {
                i++;
                socket = server.accept(); 
                System.out.println(" Client " + i + " accepted");
                MyThread newTh = new MyThread(socket,port);
            }
             
            
            
             
        }     
        catch (IOException ex) 
        {
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void runServer(Socket socket,int port)
    {
            try{
                DataInputStream  input   = null;
                ObjectInputStream inObj = null;
                DataOutputStream out     = null;
                ObjectOutputStream outObj = null;
                DataInputStream in       = null;
                //JDBC Connection
                
                String path="jdbc:mysql://localhost:3306/employee";
                
                
                // takes input from the client socket
                in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                
                inObj = new ObjectInputStream(socket.getInputStream());
                
                input  = new DataInputStream(System.in);
                
                // sends output to the socket
                out    = new DataOutputStream(socket.getOutputStream());
                
                outObj = new ObjectOutputStream(socket.getOutputStream());
                
                
                
                int ch,ch2;
                Employee e;
                //Project p;
                Scanner sc = new Scanner(System.in);
                String nm,nm2;
                boolean flag ;
                
                
                
                System.out.println("Closing connection");
                
                // close connection
                socket.close();
                in.close();
                input.close();
                out.close();
            }
            catch(IOException ex) 
            { 
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null,ex);
            }
        } 
         
    }


