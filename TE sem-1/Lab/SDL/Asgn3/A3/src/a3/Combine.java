/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.io.Serializable;

public class Combine implements Serializable{
    String name;
    int progress;
    Project p;
    public Combine(Project pr,String nm){
        p = new Project(pr);
        name = nm;
    }
    
    public void setProgress(int pr){
        progress = pr;
    }
    public int getProgress()
    {
        return progress;
    }
    
    
    public void updateProg(int progress)
    {
        p.setProg(progress);
    }
    
    public void showProject(int prg)
    {
        System.out.println("\n\n\tEmployee  :   " + name);
        System.out.println("\t--------------------------------");
        if(p == null)
        {
            System.out.println("\n\tNo Projects ");
        }
        else
        {
           // p.showPrj(prg);
        }
    }
    
    
    public int getPrg()
    {
        return p.getProg();
    }
    
    public String getName()
    {
        return name;
    }
}

