package com.demo.jmxDynamic;

import java.lang.reflect.Constructor;
import java.util.Iterator;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ReflectionException;

/**
 * 
 * @author jinhuawa
 * 
 */

public class HelloDynamic implements DynamicMBean {
	// 这是属性名称
	private String name;
	private MBeanInfo mBeanInfo = null;
	private String className;
	private String description;
	private MBeanAttributeInfo[] attributes;
	private MBeanConstructorInfo[] constructors;
	private MBeanOperationInfo[] operations;
	MBeanNotificationInfo[] mBeanNotificationInfoArray;

	public void init() {
		className = this.getClass().getName();
		description = "Simple implementation of a dynamic MBean.";
		attributes = new MBeanAttributeInfo[1];
		constructors = new MBeanConstructorInfo[1];
		operations = new MBeanOperationInfo[1];

		mBeanNotificationInfoArray = new MBeanNotificationInfo[0];
	}

	public HelloDynamic() {
		init();
		buildDynamicMBean();
	}

	public void buildDynamicMBean() {
		// 设定构造函数
		Constructor[] thisConstructors = this.getClass().getConstructors();
		constructors[0] = new MBeanConstructorInfo(
				"HelloDynamic():constructs a HelloDynamic object",
				thisConstructors[0]);
		// 设定一个属性
		attributes[0] = new MBeanAttributeInfo("Name", "java.lang.String",
				"Name: name string.", true, true, false);
		MBeanParameterInfo[] params = null; // 无参数的函数
		// 设定一个操作方法，print
		operations[0] = new MBeanOperationInfo("print",
				"print(): print the name", params, "void",
				MBeanOperationInfo.INFO);
		mBeanInfo = new MBeanInfo(className, description, attributes,
				constructors, operations, mBeanNotificationInfoArray);

	}

	// 动态增加一个print方法
	private void dynamicAddOperation() {
		init();
		operations = new MBeanOperationInfo[2]; // 设定数组为两个
		buildDynamicMBean();
		operations[1] = new MBeanOperationInfo("print1",
				"print1():print the name", null, "void",
				MBeanOperationInfo.INFO);
		mBeanInfo = new MBeanInfo(className, description, attributes,
				constructors, operations, mBeanNotificationInfoArray);

	}

	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException,
			ReflectionException {
		if (attribute != null)
			return null;
		if (attribute.equals("name"))
			return name;
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {
		if (attribute == null) {
			return;
		}
		String Name = attribute.getName();
		Object value = attribute.getValue();

		try {
			if (Name.equals("name")) {
				// if null value,try and see if the setter returns any exception
				if (value == null) {
					name = null;
					// if non null value,make sure it is assignable to the
					// attribute
				} else if ((Class.forName("java.lang.String"))
						.isAssignableFrom(value.getClass())) {
					name = (String) value;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		if (attributes == null) {
			return null;
		}
		AttributeList resultList = new AttributeList();
		if (attributes.length == 0) {
			return resultList;
		}
		for (int i = 0; i < attributes.length; i++) {
			try {
				Object value = getAttribute(attributes[i]);
				resultList.add(new Attribute(attributes[i], value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		if (attributes == null)
			return null;
		AttributeList resultList = new AttributeList();
		// if attributeNames is empty,nothing more to do
		if (attributes.isEmpty())
			return resultList;
		// for each attribute, try to set it and add to the result list if
		// successfull
		for (Iterator i = attributes.iterator(); i.hasNext();) {
			Attribute attr = (Attribute) i.next();
			try {
				setAttribute(attr);
				String name = attr.getName();
				Object value = getAttribute(name);
				resultList.add(new Attribute(name, value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		// check for a recognized operation name and call the corresponding
		// operation
		if (actionName.equals("print")) {
			System.out.println("Hello," + name + ", this is HelloDynamic!");
			dynamicAddOperation();
			return null;
		} else if (actionName.equals("print1")) {
			System.out.println("这是动态增加的一个方法！");
			return null;
		}
		throw new ReflectionException(new NoSuchMethodException(actionName),
				"Can not find the operation" + actionName + "in" + className);
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		return mBeanInfo;
	}

}
