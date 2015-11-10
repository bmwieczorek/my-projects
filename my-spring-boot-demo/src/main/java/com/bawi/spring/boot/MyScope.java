package com.bawi.spring.boot;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class MyScope {
	
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MyScope.class);
		RequestHolder bean = ctx.getBean(RequestHolder.class);
		Thread.sleep(1000);
		System.err.println("pause");
		RequestHolder bean2 = ctx.getBean(RequestHolder.class);

	}

	@Component
	@Scope("singleton")
	public static class RequestHolder {
		Date date;
		public RequestHolder() {
			this.date = new Date();
			System.err.println(date + "*************************************************");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public Date getDate() {
			return date;
		}
	}
}
