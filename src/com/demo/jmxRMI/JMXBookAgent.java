package com.demo.jmxRMI;

import javax.management.*;
import javax.management.remote.rmi.RMIConnectorServer;

import com.sun.jdmk.comm.*;

public class JMXBookAgent {

	private MBeanServer server = null;

	public JMXBookAgent() {
		System.out.println("\n\t CREATE MBeanserver .");
		server = MBeanServerFactory.createMBeanServer("JMXBookAgent");

		startHTMLAdaptor();
		startRMIAdaptor();
	}

	public void startRMIAdaptor() {
	//	RMIConnectorServer connector = new RMIConnectorServer(null, null, server);
		
		
	}

	public void startHTMLAdaptor() {
		HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
		try {
			ObjectName adaptorName = new ObjectName(
					"JMXBookAgent:name = html,port=8082");
			adaptor.setPort(8082);
			server.registerMBean(adaptor, adaptorName);
			adaptor.start();
		} catch (Exception e) {
//			ExceptionUtil.printException(e);
			System.out.println("Error Starting HTML Adaptor for Agent");
		}
	}
}
