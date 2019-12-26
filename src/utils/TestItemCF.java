package utils;

import java.util.ArrayList;

import org.junit.Test;

public class TestItemCF {
	public ArrayList <String> u1;
	public ArrayList <String> u2;
	public ArrayList <String> u3;
	
	@Test
	public void init() {
		u1=new ArrayList<String>();
		u2=new ArrayList<String>();
		u3=new ArrayList<String>();
		
		u1.add("a");
		u1.add("b");
		u1.add("c");
		
		u2.add("b");
		u2.add("c");
		u2.add("d");
		
		u3.add("c");
		u3.add("d");
		
//		for(String s:u1) {
//			System.out.println(s);
//		}
	}
	
	
	
}
