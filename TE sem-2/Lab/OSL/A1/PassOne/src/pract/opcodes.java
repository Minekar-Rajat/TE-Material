package pract;

import java.util.HashMap;
import java.util.Map;

public class opcodes {
	Map data=new HashMap();
	Map mnemonic=new HashMap();
	Map directive=new HashMap();
	Map condition=new HashMap();
	Map register=new HashMap();
	
	public opcodes()
	{
		register.put("AREG", "31");
		register.put("BREG", "32");
		register.put("CREG", "33");
		register.put("DREG", "34");
		
		condition.put("LT", "21");
		condition.put("GT", "22");
		condition.put("LTE", "23");
		condition.put("GTE", "24");
		condition.put("EQU", "25");
		
		directive.put("START", "1");
		directive.put("END","2");
		directive.put("ORIGIN","3");
		directive.put("LTORG","4");
		
		
		mnemonic.put("ADD", "1");
		mnemonic.put("SUB", "2");
		mnemonic.put("MUL", "3");
		mnemonic.put("DIV", "4");
		mnemonic.put("MOVER", "5");
		mnemonic.put("MOVEM", "6");
		mnemonic.put("READ", "7");
		
		data.put("DS", "1");
		data.put("DC", "2");

		
		
	}

}
