package com.demo.jmxModelMBean;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.modelmbean.RequiredModelMBean;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * 
 * @author jinhuawa
 *
 */
public class HelloAgent {
	public static void main(String []args) throws Exception{
		MBeanServer server = MBeanServerFactory.createMBeanServer();
		ObjectName helloName = new ObjectName("baby:name=HelloWorld");
		//Hello hello = new Hello();
		
		RequiredModelMBean hello = ModelMBeanUtils.createModlerMBean();
		server.registerMBean(hello, helloName);
		
		ObjectName adapterName = new ObjectName("HelloAgent:name=htmlAdapter,port=8082");
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		adapter.start();
		System.out.println("start............");
		
	}
}
