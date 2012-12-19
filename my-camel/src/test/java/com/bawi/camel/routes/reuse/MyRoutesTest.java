package com.bawi.camel.routes.reuse;

import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyRoutesTest extends CamelSpringTestSupport {

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("routes/reuse/camel-context.xml");
	}
	
	@Test
	public void test() {
		template.sendBodyAndHeader(MyRoutes.DIRECT_START, "Mike", MyRoutes.OPERATION_HEADER, "add");
	}


}
