package macro_pass_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class pass1 {
	static HashMap<String,String>ala=new HashMap<String,String>();
	static String name="";
	public static void main(String[] args) throws IOException {
		
	String[][] mnt= new String[10][3] ;
	String[][] mdt= new String[10][2] ;
	
	int mntptr=0,mdtptr=0;
	boolean proceesmacrodef=false,processmacronm=false;  
	 BufferedReader b1 = new BufferedReader(new FileReader("input.txt"));
	 BufferedWriter bmnt=new BufferedWriter(new FileWriter("mnt.txt"));
	 BufferedWriter bmdt=new BufferedWriter(new FileWriter("mdt.txt"));
	 BufferedWriter bala=new BufferedWriter(new FileWriter("ala.txt"));
	 BufferedWriter bout=new BufferedWriter(new FileWriter("output_pass1.txt"));
	String line;
	
	System.out.println("\n\t======== Output ========\n");
	 while((line=b1.readLine())!=null)
	 {
		
		 String split_words[] = line.split("\s");
		 if(split_words[0].equalsIgnoreCase("MACRO"))
		 {
			 ala.clear();
			 proceesmacrodef=true;
			 processmacronm=true;
		 }
		 else if(proceesmacrodef==true)
		 {
			 if(split_words[0].equalsIgnoreCase("MEND"))
			 {
				 proceesmacrodef=false;
				 name="";
				mdt[mdtptr][0]= String.valueOf(mdtptr);
				mdt[mdtptr][1]=split_words[0];
				mdtptr++;
			 }
			 if(processmacronm==true)
			 {
				 mnt[mntptr][0]=String.valueOf(mntptr);
				 mnt[mntptr][1]=split_words[0];
				 mnt[mntptr][2]=String.valueOf(mdtptr);
				 mntptr++;
				 processmacronm=false;
				 mdt[mdtptr][0]=String.valueOf(mdtptr);
				 mdt[mdtptr][1]=split_words[0]+" "+split_words[1];
				 mdtptr++;
				 name=split_words[0];
				 process_the_argument(split_words[1],bala);
			 }
			 else if(proceesmacrodef==true&&processmacronm==false)
			 {
				 String indexedArglist=process_the_arguments(split_words[1]);
				 mdt[mdtptr][0]=String.valueOf(mdtptr);
				 mdt[mdtptr][1]=split_words[0]+indexedArglist;
				 mdtptr++;
			 }
		 }
		 else 
		 {
			System.out.println("\t" +line);
			bout.write(line+"\n");
		 }
		 
	 }
	 bout.close();
	 System.out.println("\n\n\t ======== MNT Table ========\n");
	 for(int i=0;i<mntptr;i++)
	 {
		 System.out.println("\t" + mnt[i][0]+" "+mnt[i][1]+" "+mnt[i][2]);
		 bmnt.write(mnt[i][0]+" "+mnt[i][1]+" "+mnt[i][2]+"\n");
	 }
	 bmnt.close();
	 System.out.println("\n\n\t ======== MDT Table ========\n");
	 for(int i=0;i<mdtptr;i++)
	 {
		 System.out.println("\t" + mdt[i][0]+" "+mdt[i][1]);
		 bmdt.write(mdt[i][0]+" "+mdt[i][1]+"\n");
	 }
	 System.out.println();
	 bmdt.close();
     bala.close();
	}

	private static String process_the_arguments(String string) {
		// TODO Auto-generated method stub
		String[]arg=string.split(",");
		
		
		for(int i=0;i<arg.length;i++)
		{
			String []prg=arg[i].split("=");
			String arg_index=ala.get(prg[0]);
		    string=string.replaceAll(arg[i], arg_index);
			
		}
		return string;
	}

	private static void process_the_argument(String string,BufferedWriter bala) throws IOException {
		String[]arg=string.split(",");
		ala.clear();
		bala.write(name+":\n");
		for(int i=0;i<arg.length;i++)
		{
		
			String[]k=arg[i].split("=");
			ala.put(k[0], "#"+(i+1));
			bala.write(k[0]+"\t"+"#"+(i+1)+"\n");
		}
		bala.write("\n");
	}
}
