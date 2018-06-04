package com.aditya.hibernate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ForEachMethod {
	/*
	 * public static void main(String[] args) { List<String> list = new
	 * ArrayList<>();
	 * 
	 * list.add("Hello"); list.add("Hai"); list.add("Hey");
	 * 
	 * }
	 */

	/*public static void main(String[] args) {
		int x;
		if (args.length > 0) {
			x = 10;
		}
		System.out.println(x);// C.E:variable x might not have been initialized
	}
	*/
	
	public static void main(String[] args) {
		String s= "hello";
		
		char ch=Character.toUpperCase(s.charAt(0));
		s=s.replace(s.charAt(0), Character.toUpperCase(s.charAt(0)));
		System.out.println(s);
		
		/*adihya manju ramu jai
		
		AMRJAI*/
		
		String str= "adihya manju ramu jai";
		
		String strnew;
		int l= 0;
		StringBuffer sb= new StringBuffer();
		
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)==' ' && str.charAt(i+1)!=' ') {
				
				sb.append(Character.toUpperCase(str.substring(l, i).charAt(0)));
				l=i+1;
			}else {
				
			}
			
		}
		System.out.println(sb);
	}
}
