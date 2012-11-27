package com.demo.jmxSpring;

/**
 * @author jinhuawa
 * @version 1.0
 */

public class HusbandLocal {
	private String name;
	private int age;
	private String message;
	
	public String getName() {
		System.out.println("name:" + name);
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		System.out.println("age:" + age);
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getMessage() {
		System.out.println("message:" + message);
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void call(String message){
		System.out.println("husband's call: " + message);
	}
	
	public void look(){
		System.out.println("see the sexy girl!");
	}
	
	public void playDota(){
		System.out.println("paly DOTA!");
	}
	
}
