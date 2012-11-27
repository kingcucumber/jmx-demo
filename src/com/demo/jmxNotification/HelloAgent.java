package com.demo.jmxNotification;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.demo.jmxHello.Hello;
import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * 
 * @author jinhuawa
 *
 */
public class HelloAgent {
	
	public static void main(String[] args) throws Exception{
		MBeanServer server = MBeanServerFactory.createMBeanServer();
		
		ObjectName helloName = new ObjectName("HelloAgent:name = Hello World");
		Hello hello = new Hello();
		server.registerMBean(hello,helloName);
		
		ObjectName adapterName = new ObjectName("HelloAgent:name= htmlAdapter,port=8082");
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		
		Jack jack = new Jack();
		
		server.registerMBean(jack,new ObjectName("HelloAgent:name=jack"));
		jack.addNotificationListener(new HelloListener(), null, hello);
		adapter.start();
		System.out.println("start........");
		
		
	}
}
