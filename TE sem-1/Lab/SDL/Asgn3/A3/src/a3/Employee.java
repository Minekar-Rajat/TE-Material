/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;
import java.io.Serializable;
import java.util.Scanner;

public class Employee implements Serializable {
    String name;
    int exp;
    int id;
    String desig;
    String email;
    float sal;
    static int st_id = 0;
    String prj;
    
    public Employee(){
        name="";
        exp=0;
        desig="";
        email="";
        sal = exp<=5 ? 30000 : 50000;
        id=st_id;
        st_id++;
        prj = "";
    }
    
    public Employee(String nm , int ex , String des ,String mail,  String p){
       name = nm;
       exp=ex;
       desig = des;
       email = mail;
       sal = exp<=5 ? 30000 : 50000;
       id = st_id;
       st_id++;
       prj = p;
    }
    
    public Employee(Employee e)
    {
        name = e.name;
        exp = e.exp;
        desig = e.desig;
        email = e.email;
        sal = exp<=5 ? 30000 : 50000;
        prj = e.prj;
        id = st_id;
        st_id++;
    }
    
    
    
    public String givePrj()
    {
        return prj;
    }
    
    public void setPrj(String p)
    {
        prj = p;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDesig()
    {
        return desig;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public int getExp()
    {
        return exp;
    }
    
    public float getSal()
    {
        return sal;
    }
    
    public String giveEmp()
    {
        String msg;
        msg = "------------------------------"  + 
              "\n\tName         :   " + name + 
              "\n\tExpierence   :   " + exp + 
              "\n\tDesignation  :   " + desig +
              "\n\tContact      :   " + email +
              "\n\tSalary       :   Rs. " + sal   +
              "\n\n------------------------------";
        return msg;
    }
    
    
    
    public void getEmp()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\n\tEnter Name          :   ");
        name = sc.nextLine();
        System.out.print("\n\tEnter Experience    :   ");
        exp = sc.nextInt();
        System.out.print("\n\tEnter Designation   :   ");
        desig = sc.nextLine();
        desig = sc.nextLine();
        System.out.print("\n\tEnter Email         :   ");
        email = sc.nextLine();
        sal = exp<=5 ? 30000 : 50000;
        
    }
    
    public void showEmp()
    {
        System.out.println("----------------------------");
        System.out.println("\tName          :   " + name);
        System.out.println("\tExpierence    :   " + exp + " Years");
        System.out.println("\tDesignation   :   " + desig);
        System.out.println("\tContact       :   " + email);
    }
    
    public static void main(String[] args) {
        
    }
}

