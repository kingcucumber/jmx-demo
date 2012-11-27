package com.demo.jmxGreeting;

import javax.management.*;
import com.sun.jdmk.comm.*;

/**
 * 
 * @author jinhuawa
 * 
 */
public class HelloAgent {

	private MBeanServer mbs = null;

	public HelloAgent() {
		mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
		HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
		HelloWorld hw = new HelloWorld();

		ObjectName helloWorldName = null;
		ObjectName adaptorName = null;

		try {
			helloWorldName = new ObjectName("HelloAgent:name=HelloWorld");
			mbs.registerMBean(hw, helloWorldName);

			adaptorName = new ObjectName(
					"HelloAgent:name=htmladaptor,port=9092");
			adaptor.setPort(9092);
			mbs.registerMBean(adaptor, adaptorName);

			adaptor.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		System.out.println("HelloAgent is starting!");
		HelloAgent exmaple = new HelloAgent();

	}

}
