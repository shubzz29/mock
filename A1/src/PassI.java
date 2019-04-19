import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class symTab{
	String symname;
	int symadd;
	symTab(String name,int add)
	{
		this.symname = name;
		this.symadd = add;
	}
	public boolean equals(Object o)
	{
		symTab s = (symTab)o;
		if(this.symname.equals(s.symname))
			return true;
		return false;
	}
	public int getAdd()
	{
		return this.symadd;
	}
	public String getName()
	{
		return this.symname;
	}
}


public class PassI {

	static HashMap<String,String> IC = new HashMap<String,String>();
	public static void main(String[] args) throws IOException
	{
		PassI obj = new PassI();

//make Vector of required tables

		Vector<symTab> SYMTAB = new Vector<symTab>();
		Vector<symTab> LTTAB = new Vector<symTab>();
		Vector<Integer> POOLTAB = new Vector<Integer>();

//include path of input file

 		File file = new File("D:\\PICT\\SEM VI\\SPOSL\\Assignment_A1\\src\\SourceCode.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		int lc = 0,flag = 1,pool_index = 0;
		while((st = br.readLine())!=null)
		{
           String[] tokens = st.split("\\s");
           String icline="";
           for(int i=0;i<tokens.length;i++)
           {
        	   if(IC.get(tokens[i])!=null)
        	   {
        		   icline += IC.get(tokens[i]);
        		   switch(tokens[i])
        		   {
        		     case "DS":
        		    	 lc += Integer.parseInt(tokens[i+1])-1;
        		    	 icline += "(C,"+Integer.parseInt(tokens[i+1])+")\n";
        		    	 i++;
        		    	 break;
        		     case "DC":
        		    	 icline += "(C,"+Integer.parseInt(tokens[i+1])+")\n";
        		    	 i++;
        		    	 break;
        		     case "EQU":
        		        {
	        		    	 icline ="";
	        		    	 //symTab stobj = new symTab(tokens[tokens.length-1],0);
	        		    	 symTab stobj;
	        		    	 String s = tokens[tokens.length-1];
	        		    	 int c=0;
	        		    	 if(s.contains("+"))
	        		    	 {
	        		    		 String[] origarg = s.split("\\+");
	        		    		 c += Integer.parseInt(origarg[origarg.length-1]);
	        		    		 stobj = new symTab(origarg[0],0);
	        		    	 }
	        		    	 else if(s.contains("-"))
	        		    	 {
	        		    		 String[] origarg = s.split("\\-");
	        		    		 c -= Integer.parseInt(origarg[origarg.length-1]);
	        		    		 stobj = new symTab(origarg[0],0);
	        		    	 }
	        		    	 else
	        		    	 {
	        		    		 stobj = new symTab(tokens[tokens.length-1],0);
	        		    	 }
	        		    	 int k = SYMTAB.indexOf(stobj);
	        		    	 stobj = SYMTAB.elementAt(k);
	        		    	 symTab stobj1 = new symTab(tokens[0],0);
	        		    	 int k1 = SYMTAB.indexOf(stobj1);
	        		    	 stobj1 = SYMTAB.elementAt(k1);
	        		    	 stobj1.symadd = stobj.symadd + c;
	        		    	 SYMTAB.setElementAt(stobj1, k1);
	        		    	 i++;
	           		    	 break;
        		        }
        		     case "ORIGIN":
        		     	{
	        		    	 symTab stobj;
	        		    	 String s = tokens[tokens.length-1];
	        		    	 int c=0;
	        		    	 if(s.contains("+"))
	        		    	 {
	        		    		 String[] origarg = s.split("\\+");
	        		    		 c += Integer.parseInt(origarg[origarg.length-1]);
	        		    		 stobj = new symTab(origarg[0],0);
	        		    	 }
	        		    	 else if(s.contains("-"))
	        		    	 {
	        		    		 String[] origarg = s.split("\\-");
	        		    		 c -= Integer.parseInt(origarg[origarg.length-1]);
	        		    		 stobj = new symTab(origarg[0],0);
	        		    	 }
	        		    	 else
	        		    	 {
	        		    		 stobj = new symTab(tokens[tokens.length-1],0);
	        		    	 }
	        		         int k = SYMTAB.indexOf(stobj);
	        		    	 stobj = SYMTAB.elementAt(k);
	        		    	 lc = stobj.getAdd()+c-1;
	        		    	 break;
        		     	}
        		     case "LTORG":
        		    	 icline = "";
        		    	 POOLTAB.add(pool_index);
        		    	 for(symTab s: LTTAB)
        		    	 {
        		    		 if(s.symadd==0)
        		    		 {
	        		    		 icline+="(DL,01)(C,";
	        		    		 int k = SYMTAB.indexOf(s);
	        		    		 s.symadd = lc;
		        		    	 icline += s.symname+")\n";
	        		    		 lc++;
        		    		 }
        		    	 }
        		    	 pool_index = LTTAB.size();
        		    	 lc--;
        		    	 break;
        		     case "END":
        		    	 POOLTAB.add(pool_index);
        		     {
        		    	 for(symTab s: LTTAB)
        		    	 {
        		    		 if(s.symadd==0)
        		    		 {
	        		    		 icline+="\n(DL,01)(C,";
	        		    		 int k = SYMTAB.indexOf(s);
	        		    		 s.symadd = lc;
		        		    	 icline += s.symname+")\n";
	        		    		 lc++;
        		    		 }
        		    	 }
        		    	 lc--;
        		     }
        		    	 break;
        		   }
        	   }
        	   else
        	   {
        		   //constant
        		   try{
        		     lc = Integer.parseInt(tokens[i])-1;
        		   }
        		   catch(Exception e)
        		   {
        			   flag = 0;
        		   }
        		   if(flag == 1)
        			   icline += "(C,"+(lc+1)+")\n";
        		   else if(tokens[i].matches("='.*'"))
        		   {
        			   symTab stobj = new symTab(tokens[i].substring(2,tokens[i].length()-1),0);
        			   LTTAB.add(stobj);
        			   int k= LTTAB.indexOf(stobj);
        			   icline += "(L,0"+(k+1)+")\n";
        		   }
        		   else
        		   {
        			  if(i==0)
        			  {
        				//Label
        				  symTab stobj = new symTab(tokens[i],lc);
        				  if(!SYMTAB.contains(stobj))
        				    SYMTAB.add(stobj);
        				  else
        				  {
        					 int k =  SYMTAB.indexOf(stobj);
        					 SYMTAB.setElementAt(stobj, k);
        				  }
        			  }
        			  else if(i==tokens.length-1)
        			  {
        				//Symbol
        				  symTab stobj = new symTab(tokens[i],0);
        				  if(!SYMTAB.contains(stobj))
        				  {
        					  SYMTAB.add(stobj);
        					  int k =  SYMTAB.indexOf(stobj);
        					  icline += "(S,0"+(k+1)+")\n";
        				  }
        				  else
        				  {
        					  int k =  SYMTAB.indexOf(stobj);
        					  icline += "(S,0"+(k+1)+")\n";
        				  }
        			  }
        		   }
        	   }
           }
           lc++;
           System.out.print(icline /*+ "\t" + lc*/);

		}
		System.out.println("\nSYMBOL TABLE: \n");
		for(symTab s:SYMTAB)
		{
			System.out.println(s.symname + "\t" + s.symadd);
		}

		System.out.println("\nLITERAL TABLE: \n");
		for(symTab s:LTTAB)
		{
			System.out.println(s.symname + "\t" + s.symadd);
		}

		System.out.println("\nPOOL TABLE: \n");
		for(int x:POOLTAB)
		{
			System.out.println(x);
		}
	}

	public PassI(){
		IS_init();
		AD_init();
		CC_init();
		DL_init();
		REG_init();
	}

	public void IS_init()
	{
		IC.put("STOP","(IS,00)");
		IC.put("ADD","(IS,01)");
		IC.put("SUB","(IS,02)");
		IC.put("MULT","(IS,03)");
		IC.put("MOVER","(IS,04)");
		IC.put("MOVEM","(IS,05)");
		IC.put("COMP","(IS,06)");
		IC.put("BC","(IS,07)");
		IC.put("DIV","(IS,08)");
		IC.put("READ","(IS,09)");
		IC.put("PRINT","(IS,10)");
	}

	public void AD_init()
	{
		IC.put("START","(AD,01)");
		IC.put("END","(AD,02)");
		IC.put("ORIGIN","(AD,03)");
		IC.put("EQU","(AD,04)");
		IC.put("LTORG","(AD,05)");
	}

	public void CC_init()
	{
		IC.put("LT","(1)");
		IC.put("LE","(2)");
		IC.put("EQ","(3)");
		IC.put("GT","(4)");
		IC.put("GE","(5)");
		IC.put("ANY","(6)");
	}

	public void DL_init()
	{
		IC.put("DC","(DL,01)");
		IC.put("DS","(DL,02)");
	}

	public void REG_init()
	{
		IC.put("AREG","(1)");
		IC.put("BREG","(2)");
		IC.put("CREG","(3)");
		IC.put("DREG","(4)");
	}

}
