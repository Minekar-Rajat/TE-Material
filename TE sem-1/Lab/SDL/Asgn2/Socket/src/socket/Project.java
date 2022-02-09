/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class Project implements Serializable{
    String prjTaskName;
    String prjTaskDetail;
    int d,m,y;
    int progress;
    
    public Project()
    {
        prjTaskName = "";
        prjTaskDetail = "";
        d=1;m=1;y=2020;
        progress = 0;
        
    }
    
    public Project(Project pr)
    {
        prjTaskName = pr.prjTaskName;
        prjTaskDetail = pr.prjTaskDetail;
        d = pr.d;
        m = pr.m;
        y = pr.y;
        progress = pr.progress;
    }
    
    public Project(Project pr , int p)
    {
        prjTaskName = pr.prjTaskName;
        prjTaskDetail = pr.prjTaskDetail;
        d = pr.d;
        m = pr.m;
        y = pr.y;
        progress = p;
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
    
    public void showPrj(int prg)
    {
        System.out.println("\n\tProject Task Name   :   " + prjTaskName);
        System.out.println("\n\tProject Task Detail :   " + prjTaskDetail);
        System.out.println("\n\tProject Deadline    :   " + d + "/" +m + "/" +y);
    }
    
    public void getProject()
    {
        Scanner sc  = new Scanner(System.in);
        
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
