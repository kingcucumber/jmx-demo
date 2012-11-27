package com.demo.jmxHello;

/**
 * @author jinhuawa
 * @version 1.0
 */

public class Hello implements HelloMBean {
	private String name;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String printHello(){
		//System.out.println("hello,world!!");
		return "hello.world";
	}
	
	public void printHello(String whoName){
		
		System.out.println("hello," + whoName);
	}
}
