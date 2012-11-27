package com.demo.jmxHello;

/**
 * @version 1.0
 * @author jinhuawa
 *
 */
public interface HelloMBean {
	
	public String getName();
	public void setName(String name);
	public String printHello();
	public void printHello(String whoName);
	
}
