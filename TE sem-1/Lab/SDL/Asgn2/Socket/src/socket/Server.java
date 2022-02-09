/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

import java.net.*; 
import java.io.*; 
import java.util.*;

  
public class Server 
{ 
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream  input   = null; 
    private ObjectInputStream inObj = null;
    private DataOutputStream out     = null;
    private ObjectOutputStream outObj = null;
    private DataInputStream in       = null; 
  	ArrayList<String> al = new ArrayList<String>();
        ArrayList ale = new ArrayList();
        ArrayList prj = new ArrayList();
    // constructor with port 
    public Server(int port) 
    { 

        // starts server and waits for a connection 
        try
        { 
            
            
            
            server = new ServerSocket(port); 
            System.out.println(" -- Server started"); 
  
            System.out.println(" -- Waiting for a client ..."); 
            
  
            socket = server.accept(); 
            System.out.println(" -- Client accepted"); 
  
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
            Combine c;
            boolean flag ;
            try{
                do{
                    ch = in.read();

                    switch(ch)
                    {
                        case 1 : 
                            flag = false;
                            nm = in.readUTF();
                            for(int i=0;i<ale.size();i++)
                            {
                                e = (Employee)ale.get(i);
                                //System.out.println("@   " + e.giveEmp());
                                if(nm.equals(e.name))
                                {
                                    flag= true;
                                    outObj.writeObject(e);
                                    break;
                                }
                            }
                            if(flag)
                            {
                                System.out.println(" -- Client :  " + nm + " , Just Logged In");
                                
                                do{
                                    ch2 = in.read();

                                    switch(ch2)
                                    {
                                      
                                        case 2 :
                                            System.out.println("----------------------");
                                            String line = ""; 
  
                                            // reads message from client until "Over" is sent 
                                            try
                                                { 
                                                        line = "Welcome to Admin service "; 
                                                        System.out.println("Server  :   " + line);
                                                        out.writeUTF(line);
                                                }
                                                  catch(IOException i) 
                                                { 
                                                    System.out.println(i); 
                                                } 
                                            while(!line.equals("Over"))
                                            {
                                                        try
                                                        { 
                                                            line = in.readUTF(); 
                                                            System.out.println("Client  :   " +line); 
                                                            al.add(line);
                                                            if(!line.equals("Over"))
                                                            {
                                                                System.out.print("Server    :   ");
                                                                line = input.readLine(); 
                                                                out.writeUTF(line);
                                                            }
                                                            
                                                        } 
                                                        catch(IOException i) 
                                                        { 
                                                            System.out.println(i); 
                                                        } 
                                            }
                                            break;
                                            
                                        case 3 :
                                            nm2 = in.readUTF();
                                            for(int i = 0;i<prj.size();i++)
                                            {
                                                c = (Combine)prj.get(i);
                                                outObj.writeObject(c);
                                            }
                                            outObj.writeObject(null);
                                            break;
                                        default :
                                            if(ch2!= 0 && ch2 !=1)
                                            {
                                                System.out.println("Invaild Input from client");
                                            }
                                    }
                                }while(ch2 !=0);
                            }
                            else
                            {
                                e = null;
                                outObj.writeObject(e);
                                System.out.println(" -- Login failure for " + nm);
                            }     
                            
                            break;
                        case 2 :
                            e = (Employee)inObj.readObject();
                            ale.add(e);
                            System.out.println(" -- Client  :  " +e.getName() + " Signed Up Successfully ");
                            System.out.println(" -- Client Details : \n");
                            System.out.println(e.giveEmp());
                            break;
                            
                        case 3 :
                            
                            do{
                                ch2 = in.readInt();
                                
                                switch(ch2)
                                {
                                    case 1:
                                        
                                        Project p;
                                        p = (Project)inObj.readObject();
                                        nm = in.readUTF();
                                        c = new Combine(p,nm);
                                        prj.add(c);
                                        out.writeUTF("Project Created Successfully !");
                                        
                                        
                                        break;
                                    case 2:
                                        flag = false;
                                        nm = in.readUTF();
                                        for(int i=0;i<ale.size();i++)
                                        {
                                            e = (Employee)ale.get(i);
                                            if(nm.equals(e.getName()))
                                            {
                                                ale.remove(i);
                                                flag =true;
                                                out.writeUTF("Employee Removed !");
                                                break;
                                            }
                                        }
                                        if(!flag)
                                        {
                                            out.writeUTF("Employee Not Removed");
                                        }
                                        break;
                                    case 3:
                                        flag = false;
                                        nm = in.readUTF();
                                        
                                        for(int i=0;i<prj.size();i++)
                                        {
                                            c = (Combine)prj.get(i);
                                            
                                            if(nm.equals(c.getName()))
                                            {
                                                flag =true;
                                                prj.remove(i);
                                                out.writeUTF("Project Deleted !");
                                                break;
                                            }
                                        }
                                        if(!flag)
                                        {
                                            out.writeUTF("Project Not Deleted !!");
                                        }
                                        break;
                                        
                                    case 5 :
                                        ch2=0;
                                        break;
                                }
                                
                            }while(ch2!=0);
                            
                            
                            break;
                            
                        default:
                            if(ch != 0)
                            {
                                System.out.println(" -- Invalid Input from Client !");
                            }
                    }

                }while(ch != 0);
            }
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
            catch(ClassNotFoundException cnf)
            {
                System.out.println(cnf);
            }
            
            
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
            input.close();
            out.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Server server = new Server(8000); 
    } 
} 