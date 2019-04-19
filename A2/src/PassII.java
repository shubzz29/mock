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

public class PassII {

	static Vector<symTab> SYMTAB= new Vector<symTab>();
	static Vector<symTab> LITTAB= new Vector<symTab>();
	static Vector<Integer> POOLTAB= new Vector<Integer>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PassII obj = new PassII();
		BufferedReader br = new BufferedReader(new FileReader("SourceCode.txt"));
		String st=br.readLine();
		if(!st.matches("[(AD,01)].*"))
		{
			System.out.println("Missing Start Statement\n");
			return;
		}
		int lc = Integer.parseInt(st.substring(st.indexOf("C")+2, st.lastIndexOf(")")));
		System.out.println(lc);
		String mcline;
		String part1="",part2="",part3="";
		int index=0,addr=0;
		while((st=br.readLine())!=null)
		{
			mcline=String.valueOf(lc);
			part1 = st.substring(0,st.indexOf(")")+1);
			part2 = "";
			part3 = "";
			st=st.replace(part1,"");
			if(!st.equals(""))
			{
				part3=st.substring(st.lastIndexOf("("), st.length());
				part2=st.replace(part3,"");
			}
//			System.out.println(part1+"\t"+part2+"\t"+part3);
			if(!part3.equals(""))
			{
				String s=part3.substring(1,2);
				index = Integer.parseInt(part3.substring(part3.indexOf(",")+1, part3.lastIndexOf(")")));
				switch(s)
				{
					case "L":
						symTab lobj = SYMTAB.elementAt(index-1);
						addr = lobj.getAdd();
						break;
					case "S":
						symTab sobj = SYMTAB.elementAt(index-1);
						addr = sobj.getAdd();
						break;
					case "C":
						addr=index;
						break;
				}
			}
			String type = part1.substring(1,3);
			String code = part1.substring(4, part1.length()-1);
			switch(type)
			{
				case "DL":
					mcline += " 00 00 "+addr;
					lc++;
					break;
				case "AD":
					String[] tb=part3.split("\\+");
					if(code.equals("03"))
					{
						if(tb.length==2)
							lc=addr+Integer.parseInt(tb[1]);
						else
							lc=addr;
					}
					break;
				case "IS":
					mcline += " "+code;
					if(!part2.equals(""))
						mcline += " "+part2.substring(1, part2.length()-1);
					else
						mcline=" 00";
					mcline+=" "+addr;
					lc++;
					break;
			}
			System.out.println(mcline);
		}



		System.exit(0);
	}

	public PassII() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("SYMTAB.txt"));
		String st;
		symTab stobj;
		while((st=br.readLine())!=null)
		{
			String[] tokens = st.split("\\s");
			stobj = new symTab(tokens[0],Integer.parseInt(tokens[1]));
			SYMTAB.add(stobj);
		}

		 br = new BufferedReader(new FileReader("LITTAB.txt"));
		while((st=br.readLine())!=null)
		{
			String[] tokens = st.split("\\s");
			stobj = new symTab(tokens[0],Integer.parseInt(tokens[1]));
			LITTAB.add(stobj);
		}

		 br = new BufferedReader(new FileReader("POOLTAB.txt"));
		while((st=br.readLine())!=null)
		{
			POOLTAB.add(Integer.parseInt(st));
		}
	}

}
