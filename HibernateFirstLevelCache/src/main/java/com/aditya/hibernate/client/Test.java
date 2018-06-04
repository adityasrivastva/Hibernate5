package com.aditya.hibernate.client;

public class Test {
	public static void main(String[] args) {
		Hello h= new Hello();
		h.x=10;
		h.s="Ram";
		System.out.println(h);
		
		
		Hai hai= new Hai(20, "Krishna");
		System.out.println(hai);
		
		
		Animal a= new Animal(30, "Arjuna");
		System.out.println(a);
		
		
	}

}
class Hello{
	int x;
	String s;
	
	@Override
	public String toString() {
		return "Hello [x=" + x + ", s=" + s + "]";
	}
}

class Hai{
	int y;
	String str;
	public Hai(int y1, String str1) {
		
		y=y1;
		str= str1;
	}
	@Override
	public String toString() {
		return "Hai [y=" + y + ", str=" + str + "]";
	}
}

class Animal{
	int z;
	String st;
	
	public Animal(int z, String st) {
		this.z=z;
		this.st=st;
	}

	@Override
	public String toString() {
		return "Animal [z=" + z + ", st=" + st + "]";
	}
	
	
}