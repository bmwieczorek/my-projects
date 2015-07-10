1. File/New/Spring Starter Project
- web
- actuator
- security (comment out for now)
- thymeleaf (comment out for now)
update maven
 
2. Start Demo
- show port 8080 started in logs
- actuator available endpoints:
	http://localhost:8080/health
	http://localhost:8080/beans
	http://localhost:8080/mappings
	http://localhost:8080/metrics
	http://localhost:8080/trace
	http://localhost:8080/configprops
	http://localhost:8080/env 

3. Add @Bean CommandLineRunner commandLineRunner() return new CommandLineRunner to say hello in the logs 

4. @RestController HelloController class, @RequestMapping "/hello" method return String Hi! : http://localhost:8080/hello

5. pom.xml add spring-boot-configuration-processor, maven update, show options, change server.port to 8888
restart app and send hello request to http://localhost:8888/hello

6. Externalize message, create @Component @ConfigurationProperties("app.hello") HelloProperties class, String defaultMessage with getter and setter
use application.property editor to add app.hello.default-message="Hello" 
restart up

7. Add @Autowired HelloProperties in HelloController and reference defaultMessage in hello method
restart
show  http://localhost:8888/hello returns hello not hi

8. Add @RequestParam String name to hello method and run http://localhost:8888/hello?name=Bob

9. Create @Controller GreetingController class with @RequestMapping("/greeting.html") and String greeting(@RequestParam String name, Model model) return "greeting",
add model.addAttribute("message", ...) 
add templates/greeting.html, add spring-boot-starter-thymeleaf
restart and http://localhost:8888/greeting.html?name=Bob

10. Add @RequestMapping("/hello/{languageCode}/{name}") to HelloController with greet(@PathVariable String langugage, @PathVariable String name) 
returning new Hello with AtomicLong id.getAndIncrement() and String message
add languageCodeToMessage String to String Map and update application.properties:
app.language-code-to-message.US=Good morning
app.language-code-to-message.PL=Dzien dobry
restart 
http://localhost:8888/hello/PL/Bartek
http://localhost:8888/hello/US/John
http://localhost:8888/hello/DE/Joergen

 11. Add spring-boot-starter-security to pom.xml, show login with user 'user' and password from logs
 http://localhost:8888/greeting.html?name=Bob
 http://localhost:8888/hello?name=Bob 
 http://localhost:8888/hello/US/Bob
  
 12. Add in memory auth: create @Component @EnableWebSecurity LocalWebSecurityConfig class extending WebSecurityConfigurerAdapter overriding 
 configure(HttpSecurity http) 
 to require login for "/hello/**" with Spring loginForm() and permitting others
 and configure(AuthenticationManagerBuilder auth) for inMemoryAuth
 
 http://localhost:8888/greeting.html?name=Bob
 http://localhost:8888/hello?name=Bob 
 http://localhost:8888/hello/US/Bob
 
 
 13. Add profile @Profile("local") to LocalWebSecurityConfig and 
 application.properties spring.profiles.active=local
 run 
 http://localhost:8888/greeting.html?name=Bob
 http://localhost:8888/hello?name=Bob 
 http://localhost:8888/hello/US/Bob
 
 14. Add ldap auth: create @Component @EnableWebSecurity WebSecurityConfig extending WebSecurityConfigurerAdapter overriding
 configure(HttpSecurity http) 
 to require login for "/hello/**" with
 	            .httpBasic()
	            .realmName("Ddap login required");
and permitting others

and 
configure(AuthenticationManagerBuilder authManagerBuilder)
to add ldapAuthProvider
add 
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-ldap</artifactId>
to pom            	              
 
15. Add @Profile("default") to WebSecurityConfig
 
update application.properties spring.profiles.active=default

http://localhost:8888/greeting.html?name=Bob
http://localhost:8888/hello?name=Bob 
http://localhost:8888/hello/US/Bob
 
 16. Yaml editor support, rename application.properties to _application.properties, and _application.yml to application.yml