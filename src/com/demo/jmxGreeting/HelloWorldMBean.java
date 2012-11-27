package com.demo.jmxGreeting;
 /**
  * 
  * @author jinhuawa
  *
  */
public interface HelloWorldMBean {

	public void setGreeting(String greeting);
	public String getGreeting();
	public void printGreeting();
}
