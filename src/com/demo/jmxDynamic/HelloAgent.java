package com.demo.jmxDynamic;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * 
 * @author jinhuawa
 *
 */

public class HelloAgent {
	public static void main(String[] args) throws Exception{
		MBeanServer server = MBeanServerFactory.createMBeanServer();
		ObjectName hellooName = new ObjectName("wow:name= helloDynamic");
		HelloDynamic hello = new HelloDynamic();
		server.registerMBean(hello, hellooName);
		
		ObjectName adapterName = new ObjectName("HelloAgent:name = htmlAdapter,port = 8082");
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		adapter.start();
		System.out.println("start..........");
	}
}
