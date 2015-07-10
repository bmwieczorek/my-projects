package com.bawi.spring.boot;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.bawi.spring.boot.MySpringBootDemoApplication.Hello;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MySpringBootDemoApplication.class)
@WebAppConfiguration
public class MySpringBootDemoApplicationMockRestTests {
	
	@Test
	public void shouldTestHello() throws Exception {
		// given
		RestTemplate restTemplate = new TestRestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer
			.expect(requestTo("/hello/PL/Bartek"))
			.andExpect(method(HttpMethod.GET))
			.andRespond(withSuccess("{ \"id\" : \"0\", \"message\" : \"Dzien dobry: Bartek\"}", MediaType.APPLICATION_JSON));

		// when
		 Hello hello = restTemplate.getForObject("/hello/{languageCode}/{name}", Hello.class, "PL", "Bartek");
		
		// then
		mockServer.verify();
		assertEquals(0L, hello.getId());
		assertEquals("Dzien dobry: Bartek", hello.getMessage());
	}

}
