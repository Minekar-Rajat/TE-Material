/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

// A Java program for a Client 
import java.net.*; 
import java.io.*; 
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
  
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
        Combine c;
        String nm,msg;
        Employee response;
        Scanner sc = new Scanner(System.in);
        try{
            do{
                System.out.println("\n\n\t1.Login \n\t2.SignUp \n\t3.Employee Manager \n\t0.Exit");
                System.out.print("\n\tEnter Your Choice   :   ");
                ch = sc.nextInt();
                out.write(ch);
                switch(ch)
                {
                    case 1 :
                        //Login
                        System.out.print("\n\n\tEnter Name  :   ");
                        nm = sc.nextLine();
                        nm = sc.nextLine();
                        out.writeUTF(nm);
                        response = (Employee)inObj.readObject();
                        
                        if(response != null)
                        {
                            System.out.println("Login Successful !");
                            
                            do{
                                System.out.println("\n\n\t1.Profile \n\t2.Help \n\t3.Projects \n\t0.LogOut");
                                System.out.print("Enter Your Choice   :   ");
                                ch2 = sc.nextInt();
                                out.write(ch2);
                                switch(ch2)
                                {
                                    case 1 :
                                        System.out.println(response.giveEmp());
                                        break;
                                    case 2 :
                                        String line = "";
                                        System.out.println("\n\t--------------------------");
                                         while(!line.equals("Over"))
                                        {
                                                try
                                                { 
                                                    line = in.readUTF(); 
                                                    System.out.println("Server  :   " + line);
                                                    if(!line.equals("Over"))
                                                    {
                                                        System.out.print("Client  :   ");
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
                                        int prg;
                                        out.writeUTF(response.getName());
                                        do{
                                          c =(Combine) inObj.readObject();
                                          if(c != null)
                                          {
                                            prg = c.getProgress();
                                            if(nm.equals((c.getName())))
                                                c.showProject(prg);
                                          }
                                          }while(c != null);
                                        break;
                                    default :
                                        if(ch2 != 0)
                                        {
                                            System.out.println("Invalid Choice !");
                                        }
                                             
                                }
                                
                            }while(ch2!=0);
                        }
                        else
                        {
                            System.out.println("Login Failed !");
                        }
                        break;
                    case 2 :
                        //SignUp
                        e = new Employee();
                        e.getEmp();
                        outObj.writeObject(e);
                        System.out.println("SignUp Successful !");
                        break;
                        
                    case 3 :
                        System.out.println("\n\t------ Employee Manager Section -------");
                        System.out.print("\n\tEnter UserName  :   ");
                        nm = sc.nextLine();
                        nm = sc.nextLine();
                        if(nm.equals("admin") || nm.equals("Admin") || nm.equals("ADMIN"))
                        {   
                            do{
                                System.out.println("\n\n\t1.Create Project \n\t2.Remove Employee \n\t3.Delete Project\n\t0.Exit");
                                System.out.print("\n\tEnter your choice   :   ");
                                ch2 = sc.nextInt();
                                out.writeInt(ch2);
                                switch(ch2)
                                {
                                    case 1 :
                                        Project p = new Project();
                                        p.getProject();
                                        outObj.writeObject(p);
                                        System.out.print("\n\tEnter Employee Name :   ");
                                        msg = sc.nextLine();
                                        msg = sc.nextLine();
                                        out.writeUTF(msg);
                                        msg = in.readUTF();
                                        System.out.println("\n\t" + msg);
                                        break;
                                    case 2 :
                                        System.out.print("\n\tEnter Employee Name :   ");
                                        msg = sc.nextLine();
                                        msg = sc.nextLine();
                                        out.writeUTF(msg);
                                        msg = in.readUTF();
                                        System.out.println("\n\t" + msg);
                                        break;
                                    case 3 :
                                        int prg;
                                        System.out.print("\n\tEnter Employee Name :   ");
                                        msg = sc.nextLine();
                                        msg = sc.nextLine();
                                        out.writeUTF(msg);
                                        msg = in.readUTF();
                                        System.out.println("\n\t" + msg);
                                        break;
                                    default :
                                        if(ch2 !=0)
                                        {
                                            System.out.println("\n\tInvalid Choice");
                                        }
                                }
                                
                            }while(ch2!=0);
                        }
                        else{
                            out.writeInt(5);
                        }
                        break;
                    default:
                        if(ch!=0)
                        {
                            System.out.println("Invalid Input");
                        }
                        break;
                }
                
            }while(ch != 0);
        }
        catch(IOException i) 
        { 
            System.out.println(i); 
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        



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