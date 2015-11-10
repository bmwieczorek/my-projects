package com.bawi.spring.boot;

import java.io.IOException;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MyBeanScopeApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(MyBeanScopeApplication.class, args);
	}
	
	@SuppressWarnings("serial")
	@Bean
	public Servlet dispatcherServlet() {
		return new GenericServlet() {
			@Override
			public void service(ServletRequest req, ServletResponse res)
					throws ServletException, IOException {
				res.setContentType("text/plain");
				res.getWriter().append("Hello World");
			}
		};
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyBeanScopeApplication.class);
	}

	
	@RestController
	public static class MyRestController {
		
//		@Autowired
//		SessionHolder sessionHolder;
		
		@Autowired
		RequestHolder requestHolder;
		
		@RequestMapping("/view")
		public String view() {
			//return "Request scope date: " + requestHolder.getDate() + ", session scope date:" + sessionHolder.getDate();
			return "Request scope date: " + requestHolder.getDate();
		}
	}
	
	@Component
	@Scope("session")
	public static class RequestHolder {
		Date date;
		public RequestHolder() {
			this.date = new Date();
			System.out.println(date);
		}
		public Date getDate() {
			return date;
		}
	}
	
//	@Component
//	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
//	public static class SessionHolder {
//		Date date;
//		public SessionHolder() {
//			this.date = new Date();
//		}
//		public Date getDate() {
//			return date;
//		}
//	}
	

}

