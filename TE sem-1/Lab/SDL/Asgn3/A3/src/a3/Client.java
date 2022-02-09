/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;


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
                        f = in.readBoolean();
                        
                        if(f)
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
                                        try{
                                            response = (Employee)inObj.readObject();
                                            System.out.println(response.giveEmp());
                                        }
                                        catch(Exception ex)
                                        {
                                            System.out.println(ex);
                                        }
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
                                        try
                                        {
                                           Project p = (Project) inObj.readObject();
                                           if(p!=null)
                                                p.showPrj();
                                           else
                                                System.out.println("No Projects Available !");
                                        }
                                        catch(Exception exp)
                                        {
                                            System.out.println(exp);
                                        }
                                        
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
                                        //create project
                                        Project prj=new Project();
                                        prj.getProject();
                                        outObj.writeObject(prj);
                                        System.out.println("\n\n\tProject Created Successfully!!");
                                        break;
                                    case 2 :
                                        //remove employee
                                        System.out.print("\n\n\tEnter Employee Name   :   ");
                                        nm = sc.nextLine();
                                        nm = sc.nextLine();
                                        out.writeUTF(nm);
                                        System.out.println(in.readUTF());
                                        break;
                                    case 3 :
                                        //remove project
                                        System.out.print("\n\n\tEnter Employee Name   :   ");
                                        nm = sc.nextLine();
                                        nm = sc.nextLine();
                                        out.writeUTF(nm);
                                        System.out.println(in.readUTF());
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
