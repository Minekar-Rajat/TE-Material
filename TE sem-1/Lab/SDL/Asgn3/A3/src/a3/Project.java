/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Project implements Serializable{
    String prjTaskName;
    String emp;
    String prjTaskDetail;
    int d,m,y;
    int progress;
    
    public Project()
    {
        prjTaskName = "";
        prjTaskDetail = "";
        d=1;m=1;y=2020;
        progress = 0;
        emp="";
        
    }
    public Project(String prj_name ,String prj_detail,int dd,int mm,int yy,int prg ,String emp_nm)
    {
        prjTaskName = prj_name;
        prjTaskDetail = prj_detail;
        d=dd;m=mm;y=yy;
        progress = prg;
        emp = emp_nm;
    }
    
    public Project(Project pr)
    {
        prjTaskName = pr.prjTaskName;
        prjTaskDetail = pr.prjTaskDetail;
        d = pr.d;
        m = pr.m;
        y = pr.y;
        progress = pr.progress;
        emp = pr.emp;
    }
    
    public Project(Project pr , int p)
    {
        prjTaskName = pr.prjTaskName;
        prjTaskDetail = pr.prjTaskDetail;
        d = pr.d;
        m = pr.m;
        y = pr.y;
        progress = p;
        emp = pr.emp;
    }
    
    public int getProg()
    {
        return progress;
    }
    
    public void setProg(int p)
    {
        progress = p;
    }
    
    public String prjName()
    {
        return prjTaskName;
    }
    public String prjDetail()
    {
        return prjTaskDetail;
    }
    public int getDay()
    {
        return d;
    }
    public int getMonth()
    {
        return m;
    }
    public int getYear()
    {
        return y;
    }
    public String getEmp()
    {
        return emp;
    }
    
    public void showPrj()
    {
        System.out.println("\n\tProject Task Name   :   " + prjTaskName);
        System.out.println("\n\tProject Task Detail :   " + prjTaskDetail);
        System.out.println("\n\tProject Deadline    :   " + d + "/" +m + "/" +y);
    }
    
    public void getProject()
    {
        Scanner sc  = new Scanner(System.in);
        
        System.out.print("\n\n\tEnter Employee Name   :   ");
        emp = sc.nextLine();
        
        System.out.print("\n\tEnter Task Name  :   ");
        prjTaskName = sc.nextLine();
        System.out.print("\n\tEnter Project Task Details   :   ");
        prjTaskDetail = sc.nextLine();
        
        System.out.println("\n\n\tEnter Deadline    :   ");
        System.out.print("\n\t\tDay :   ");
        d = sc.nextInt();
        System.out.print("\n\t\tMonth :   ");
        m = sc.nextInt();
        System.out.print("\n\t\tYear  :   ");
        y = sc.nextInt();
        
        
    }
    
}

