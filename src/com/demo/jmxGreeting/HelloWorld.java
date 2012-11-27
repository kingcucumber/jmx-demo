package com.demo.jmxGreeting;

/**
 * 
 * @author jinhuawa
 *
 */
public class HelloWorld implements HelloWorldMBean{

	private String greeting = null;

	public HelloWorld(){
		this.greeting = "Hello World! I'm a standard MBean!";
	}
	public HelloWorld(String greeting){
		this.greeting = greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	
	public String getGreeting() {
		return greeting;
	}


	public void printGreeting() {

		System.out.println(this.greeting);
		
	}
	
}
