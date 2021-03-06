package asm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class pass2 {

	public static void main(String[] args) throws IOException {
		String line;
		String[][] mc_code= new String[10][3] ;
		int count=0;
		 BufferedReader b1 = new BufferedReader(new FileReader("IC.txt"));
		 BufferedReader b2 = new BufferedReader(new FileReader("symtab.txt"));
		 BufferedReader b3 = new BufferedReader(new FileReader("littab.txt"));
		 HashMap <Integer,String>symaddr=new HashMap <Integer,String>();
		 HashMap <Integer,String>litaddr =new HashMap <Integer,String>();
		 
		 
		 
	  	System.out.println();
	  	System.out.println("\n\t ======= SYMBOL TABLE ========");
	    System.out.println("\t-----------------------------------");
	    System.out.println("\tSymbol|"+"\t"+"Address");
	    System.out.println("\t-----------------------------------");
		while((line = b2.readLine()) != null)
	    {
			 String split_words[] = line.split("\t");
			 count++;
			 System.out.println("\t" + line);
			 symaddr.put(count, split_words[1]);
	    }
		System.out.println("\n");	
		 
		System.out.println("\n\t ======= Literal TABLE =======");
	    System.out.println("\t---------------------------------");
	    System.out.println("\tLiteral|"+"Address");
	    System.out.println("\t---------------------------------");
		count=0;
		while((line = b3.readLine()) != null)
	    {
			 String split_words[] = line.split("\t");
			 System.out.println("\t "+line);
			 count++;
			 litaddr.put(count, split_words[1]);
	    }
		 
		System.out.println("\n");	
		 
		System.out.println("\n\t ======= OPCODE TABLE ======= \n");
	    System.out.println("\t-----------------------------------------");
	    System.out.println("\tMnemonic|"+"Info");
	    System.out.println("\t----------------------------------------");
	    count=0;
		while((line = b1.readLine()) != null)
		{
			String split_words[] = line.split("\t");
			System.out.println("\t" + line);
			if(split_words[1].contains("IS"))
			{
				mc_code[count][0]=split_words[1].substring(4,5);
				mc_code[count][1]=split_words[2];
				if(split_words[3].contains("C"))
				{
					int lit_index=Integer.parseInt(split_words[3].substring(3,4));
					mc_code[count][2]=litaddr.get(lit_index);
				}
				else if(split_words[3].contains("S"))
				{
					int sym_index=Integer.parseInt(split_words[3].substring(3,4));
					mc_code[count][2]=symaddr.get(sym_index);
				}
				count++;
			} 
			else if(split_words[1].contains("DL,1"))
			{
				mc_code[count][0]=  "00" ;
				mc_code[count][1]= "0" ;
				mc_code[count][2]= "00"+ split_words[2].substring(3,4);
				count++;
			}
			 
		}
		System.out.println("\n");	
		System.out.println("\n\t============== MACHINE CODE TABLE ==============");
	    System.out.println("\t------------------------------------------------");
		System.out.println("\tIS op-code\t"+"|Register\t"+"|Symbol address");
		System.out.println("\t------------------------------------------------");
		for(int i=0;i<count;i++)
		{
			System.out.println("\t"+mc_code[i][0]+"\t\t"+mc_code[i][1]+"\t\t"+mc_code[i][2]);
		}
		
		File mcode = new File("MC.txt");
		FileWriter wr = new FileWriter("MC.txt");
		
			
			for(int i=0;i<count;i++)
			{
				wr.write(mc_code[i][0]+"\t"+mc_code[i][1]+"\t"+mc_code[i][2]+"\n");
			}
			wr.close();
	    
		
	}

}
