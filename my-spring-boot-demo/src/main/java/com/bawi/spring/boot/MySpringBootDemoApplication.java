package com.bawi.spring.boot;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MySpringBootDemoApplication {

	@Component
	@ConfigurationProperties("app")
	public static class AppProperties {
		private String defaultMessage;
		private Map<String, String> languageCodeToMessage;
		
		public String getDefaultMessage() {
			return defaultMessage;
		}
		public void setDefaultMessage(String defaultMessage) {
			this.defaultMessage = defaultMessage;
		}
		public Map<String, String> getLanguageCodeToMessage() {
			return languageCodeToMessage;
		}
		public void setLanguageCodeToMessage(Map<String, String> languageCodeToMessage) {
			this.languageCodeToMessage = languageCodeToMessage;
		}
	}
	
	public static class Hello {
		private long id;
		private String message;
		public Hello() {
			// default constructor needed by json
		}
		public Hello(long id, String message) {
			this.id = id;
			this.message = message;
		}
		public long getId() {
			return id;
		}
		public String getMessage() {
			return message;
		}
		public void setId(long id) {
			this.id = id;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	@RestController
	public static class HelloController {
		private final AtomicLong id = new AtomicLong();

		@Autowired
		AppProperties appProperties; 
		
		// http://localhost:8888/hello?name=Bob
		@RequestMapping("/hello")
		public String hello(@RequestParam String name) {
			return appProperties.getDefaultMessage() + ": " + name;
		}
		
		// URI template pattern
		// http://localhost:8888/hello/US/Bob
		@RequestMapping("/hello/{languageCode}/{name}") 
		public Hello multiLanguageHello(@PathVariable String languageCode, @PathVariable String name) {
			Map<String, String> languageCodeToMessage = appProperties.getLanguageCodeToMessage();
			if (languageCodeToMessage.containsKey(languageCode)) {
				return new Hello(id.getAndIncrement(), languageCodeToMessage.get(languageCode) + ": " + name);
			}
			return new Hello(id.getAndIncrement(), appProperties.getDefaultMessage() + ": " + name);
		}

	}
	
	@Controller
	public static class GreetingController {
		
		@Autowired
		AppProperties appProperties; 

		// http://localhost:8888/greeting.html?name=Bob
		@RequestMapping("/greeting.html")
		public String greeting(@RequestParam String name, Model model) {
			model.addAttribute("message", appProperties.getDefaultMessage() + " " +  name);
			return "greeting"; // loads templates/greeting.html
		}
	}
	
	@Bean
	CommandLineRunner commandLineRunner() {
		
		return arg0 -> {
			System.out.println("Hello"); // on Spring Boot startup
			
		};
	}

	@Profile("local")
	@Component
	@EnableWebSecurity
	public static class LocalWebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.antMatchers("/hello/**").authenticated()
					.anyRequest().permitAll()
					.and()
				.formLogin();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.inMemoryAuthentication()
					.withUser("test").password("test").roles("USER");
		}
	}
	
	@Profile("default")
	@Component
	@EnableWebSecurity
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        	.authorizeRequests()
	            	.antMatchers("/hello/**").authenticated()
	            	.anyRequest().permitAll()
	            	.and()
	            .httpBasic()
	            .realmName("Ddap login required");
	     }
		
		@Override
		protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
			authManagerBuilder.authenticationProvider(ldapAuthenticationProvider());
		}
		
		@Bean
	    public LdapContextSource ldapContextSource() {
	        DefaultSpringSecurityContextSource ldapContextSource = new DefaultSpringSecurityContextSource("ldap://global.ad.sabre.com:389");
	        ldapContextSource.setUserDn("CN=TKTPORTAL,OU=Functional Accounts,OU=PROD,DC=Global,DC=ad,DC=sabre,DC=com");
	        ldapContextSource.setPassword("Thursday20");
	        return ldapContextSource;
	    }

	    @Bean
	    public FilterBasedLdapUserSearch filterBasedLdapUserSearch() {
	        FilterBasedLdapUserSearch filterBasedLdapUserSearch = 
	            new FilterBasedLdapUserSearch("OU=PROD,DC=Global,DC=ad,DC=sabre,DC=com","(sAMAccountName={0})", ldapContextSource());
	        filterBasedLdapUserSearch.setSearchSubtree(true);
	        return filterBasedLdapUserSearch;
	    }

	    @Bean
	    public LdapAuthenticator ldapAuthenticator() {
	        BindAuthenticator ldapAuthenticator = new BindAuthenticator(ldapContextSource());
	        ldapAuthenticator.setUserSearch(filterBasedLdapUserSearch());
	        return ldapAuthenticator;
	    }

	    @Bean
	    public AuthenticationProvider ldapAuthenticationProvider() {
	        return new LdapAuthenticationProvider(ldapAuthenticator());
	    }
	}
	
	public static void main(String[] args) {
        SpringApplication.run(MySpringBootDemoApplication.class, args);
    }
    
}
