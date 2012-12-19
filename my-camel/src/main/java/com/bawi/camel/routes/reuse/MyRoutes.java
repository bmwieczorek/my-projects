package com.bawi.camel.routes.reuse;

import org.apache.camel.builder.RouteBuilder;

public class MyRoutes extends RouteBuilder {

	public static final String OPERATION_HEADER = "operation";
	public static final String DIRECT_START = "direct:start";
	private static final String DIRECT_ADD = "direct:add";
	private static final String DIRECT_UNSUPPORTED = "direct:unsupported";

	@Override
	public void configure() throws Exception {

		from(DIRECT_START)
			.log("In start route")
			.choice()
				.when(header(OPERATION_HEADER).isEqualTo("add"))
					.to(DIRECT_ADD)
				.otherwise()
					.to(DIRECT_UNSUPPORTED)
			//.end()
			.to("bean:myBean");
			
		
		from(DIRECT_ADD)
			.to("bean:addOperation");
			
		
		from(DIRECT_UNSUPPORTED)
			.to("bean:unsupportedOperation");
		

	}

}
