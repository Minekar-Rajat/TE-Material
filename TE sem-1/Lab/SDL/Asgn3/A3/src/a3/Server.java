/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;



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
                                //login validation
                                
                                try
                                {
                                    
                                    Connection con =  DriverManager.getConnection(path,"root","root");
                                    Statement st = con.createStatement();
                                    String Query = "select * from employee.employee where name = '" + nm + "'";
                                    ResultSet rs = st.executeQuery(Query);
                                    flag = rs.next();
                                    
                                    
                                }
                                catch(SQLException ex)
                                {
                                    //Failure to signUp
                                    System.out.println("\n\n\tSQL Exception Error !");
                                }
                                out.writeBoolean(flag);
                                if(flag)
                                {
                                    System.out.println(" -- Client :  " + nm + " , Just Logged In");
                                    
                                    do{
                                        ch2 = in.read();
                                        
                                        switch(ch2)
                                        {
                                            
                                            case 1 :
                                                try
                                                {
                                                    
                                                    int i=0;
                                                    Connection con2 =  DriverManager.getConnection(path,"root","root");
                                                    Statement st2 = con2.createStatement();
                                                    String Query2 = "select * from employee.employee where name = '" + nm + "'";
                                                    ResultSet rs2 = st2.executeQuery(Query2);
                                                    rs2.next();
                                                    Employee emp = new Employee(rs2.getString("name") , rs2.getInt("exp") , rs2.getString("desig") , rs2.getString("email"),null);
                                                    outObj.writeObject(emp);
                                                    
                                                    
                                                }
                                                catch (Exception ex)
                                                {
                                                    System.out.println(ex);
                                                }
                                                
                                                
                                                
                                                break;
                                                
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
                                                try
                                                {

                                                    Connection con =  DriverManager.getConnection(path,"root","root");
                                                    Statement st = con.createStatement();
                                                    String Query ="SELECT * FROM employee WHERE name= '" + nm +"'";
                                                    ResultSet rs2 = st.executeQuery(Query);
                                                    if(rs2.next())
                                                    {
                                                        String prj_nm;
                                                        prj_nm = rs2.getString("prj_nm");
                                                        Query ="SELECT * FROM project WHERE prj_name= '" + prj_nm +"'";
                                                        rs2 = st.executeQuery(Query); 
                                                        if(rs2.next())
                                                        {
                                                            Project p = new Project(rs2.getString("prj_name") , rs2.getString("prj_detail") , rs2.getInt("d"), rs2.getInt("m"), rs2.getInt("y"), rs2.getInt("progress") ,nm);
                                                            outObj.writeObject(p); 
                                                        }
                                                        else
                                                        {
                                                            outObj.writeObject(null);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        outObj.writeObject(null);
                                                    }
                                                    
                                                }
                                                catch(SQLException ex)
                                                {
                                                    //Failure to signUp
                                                    System.out.println("Error at Show Project :   " + "\n\n\t" + ex);
                                                    break;
                                                }
                                                break;
                                            default :
                                                if(ch2!= 0 )
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
                                //Employee signup throgh dbms
                                e = (Employee)inObj.readObject();
                                try
                                {
                                    
                                    Connection con =  DriverManager.getConnection(path,"root","root");
                                    Statement st = con.createStatement();
                                    String Query ="INSERT INTO employee(name ,exp , desig,email,salary) VALUES ('" + e.getName() + "'," + e.getExp() + ",'" + e.getDesig() + "','" + e.getEmail() + "'," + e.getSal() + ")";
                                    st.executeUpdate(Query);
                                    
                                }
                                catch(SQLException ex)
                                {
                                    //Failure to signUp
                                    System.out.println("SignUp Error for User :   " + e.getName() + "\n\n\t" + ex);
                                    break;
                                }
                                
                                System.out.println(" -- Client  :  " + e.getName() + " Signed Up Successfully ");
                                System.out.println(" -- Client Details : \n " + e.giveEmp() );
                                
                                break;
                                
                            case 3 :
                                
                                do{
                                    ch2 = in.readInt();
                                    
                                    switch(ch2)
                                    {
                                        case 1:
                                            //create project
                                            Project prj;
                                            prj = (Project)inObj.readObject();
                                            try
                                            {
                                                Connection con =  DriverManager.getConnection(path,"root","root");
                                                Statement st = con.createStatement();
                                                String Query ="INSERT INTO project(prj_name ,prj_detail , progress ,d,m,y) VALUES ('" + prj.prjName() + "' , '" + prj.prjDetail() +"' , "+ prj.getProg() +" , "+ prj.getDay() +" , "+ prj.getMonth() +" , "+ prj.getYear() + " )";
                                                st.executeUpdate(Query);
                                                
                                                Query = "UPDATE employee SET prj_nm = '"+ prj.prjName() + "' WHERE name = '" + prj.getEmp() + "'" ;
                                                st.executeUpdate(Query);
                                            }
                                            catch(SQLException ex)
                                            {
                                                //Failure to Create project
                                                System.out.println("Error at Project Creation :   "  + "\n\n\t" + ex);
                                                break;
                                            }
                                            break;
                                        case 2:
                                            //remove emp
                                            String nm3;
                                            nm3 = in.readUTF();
                                            try
                                            {
                                                Connection con =  DriverManager.getConnection(path,"root","root");
                                                Statement st = con.createStatement();
                                                String Query ="SELECT * FROM employee WHERE name = '" + nm3 + "'";
                                                ResultSet rs2 = st.executeQuery(Query);
                                                if(rs2.next())
                                                {
                                                    String prj_nm = rs2.getString("prj_nm");
                                                    if(prj_nm != null)
                                                    {
                                                        Query ="DELETE FROM employee WHERE name = '" + nm3 + "'";
                                                        st.executeUpdate(Query);
                                                        Query ="DELETE FROM project WHERE prj_name = '" + prj_nm + "'";
                                                        st.executeUpdate(Query);
                                                        out.writeUTF("\n\n\tEmployee\t :\t"+nm3+" Removed!");
                                                    }
                                                    else
                                                    {
                                                        Query ="DELETE FROM employee WHERE name = '" + nm3 + "'";
                                                        st.executeUpdate(Query);
                                                        out.writeUTF("\n\n\tEmployee\t :\t"+nm3+" Removed!");
                                                    }
                                                }
                                                else
                                                {
                                                    out.writeUTF("\n\n\tEmployee\t :\t"+nm3+" Doesn't Exist !");
                                                }
                                                
                                            }
                                            catch(SQLException ex)
                                            {
                                                //Failure to Create project
                                                System.out.println("Error at Employee Removal :   "  + "\n\n\t" + ex);
                                                break;
                                            }
                                            break;
                                        case 3:
                                            //project removed
                                            
                                            nm3 = in.readUTF();
                                            try
                                            {
                                                Connection con =  DriverManager.getConnection(path,"root","root");
                                                Statement st = con.createStatement();
                                                String Query ="SELECT * FROM employee WHERE name = '" + nm3 + "'";
                                                ResultSet rs2 = st.executeQuery(Query);
                                                if(rs2.next())
                                                {
                                                    String prj_nm = rs2.getString("prj_nm");
                                                    if(prj_nm != null)
                                                    {
                                                        Query ="UPDATE employee SET prj_nm = " + null +" WHERE name = '" + nm3 + "'";
                                                        st.executeUpdate(Query);
                                                        Query ="DELETE FROM project WHERE prj_name = '" + prj_nm + "'";
                                                        st.executeUpdate(Query);
                                                        out.writeUTF("\n\n\tProject \t : "+prj_nm+" Removed!");
                                                    }
                                                    else
                                                    {
                                                        out.writeUTF("\n\n\tAlready No Projects for \t :\t"+nm3+" !!");
                                                    }
                                                }
                                                else
                                                {
                                                    out.writeUTF("\n\n\tEmployee\t :\t"+nm3+" Doesn't Exist !");
                                                }
                                                
                                            }
                                            catch(SQLException ex)
                                            {
                                                //Failure to Create project
                                                System.out.println("Error at Employee Removal :   "  + "\n\n\t" + ex);
                                                break;
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
            catch(IOException ex) 
            { 
                  Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null,ex);
            }
        } 
         
    }

