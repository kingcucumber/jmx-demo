package com.demo.jmxHello;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * @version 1.0
 * @author jinhuawa
 *
 */
public class HelloAgent {
	
	public static void main(String[] args) throws Exception{
		MBeanServer server = MBeanServerFactory.createMBeanServer();
		
		ObjectName helloName = new ObjectName("greet:name= HelloWorld");
		server.registerMBean(new Hello(), helloName);
		
		ObjectName adapterName = new ObjectName("HelloAgent:name = htmladapter,port=9092");
		
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		adapter.setPort(9092);
		server.registerMBean(adapter, adapterName);
		
		adapter.start();
		System.out.println("Start ............");
		
	}
}
