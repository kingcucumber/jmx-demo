package com.demo.jmxNotification;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * 
 * @author jinhuawa
 *
 */

public class Jack extends NotificationBroadcasterSupport implements JackMBean{
	private int seq = 0 ;
	public void hi(){
		Notification n = new Notification("jack.hi",this,++seq,System.currentTimeMillis(),"jack");
		sendNotification(n);
	}
}
