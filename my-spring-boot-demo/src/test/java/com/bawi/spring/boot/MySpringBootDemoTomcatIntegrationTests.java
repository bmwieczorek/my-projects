package com.bawi.spring.boot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.bawi.spring.boot.MySpringBootDemoApplication.Hello;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MySpringBootDemoApplication.class)
@WebIntegrationTest({"server.port:8080"})
public class MySpringBootDemoTomcatIntegrationTests {
	
	@Component
	@EnableWebSecurity
	@Order(0)
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.anyRequest().permitAll();
		}
	}
	
	@Test
	public void shouldTestHello() {
		// given
		RestTemplate restTemplate = new TestRestTemplate();
		
		// when
		ResponseEntity<Hello> response = restTemplate.getForEntity("http://localhost:8080/hello/PL/Bartek", Hello.class);
		
		// then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Hello hello = response.getBody();
		assertEquals(0L, hello.getId());
		assertEquals("Dzien dobry: Bartek", hello.getMessage());
	}

}
