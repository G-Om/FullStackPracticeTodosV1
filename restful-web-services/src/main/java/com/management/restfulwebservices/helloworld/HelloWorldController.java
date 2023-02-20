package com.management.restfulwebservices.helloworld;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller	
@RestController
@CrossOrigin("http://localhost:4200")
public class HelloWorldController {
	
	//GET
	// URI- /helloworld
	//method helloworld

	@GetMapping(path="/helloworld")
	public String helloworld() {
		return "Hello World";
	}
	
	@GetMapping(path="/helloworld-bean")
	public HelloWorldBean helloworldbean() {
		return new HelloWorldBean("Hello World");
	}
	@GetMapping(path="/helloworld/{name}")
	public HelloWorldBean helloworldbean(@PathVariable String name) {
//		throw new RuntimeException("Sorry! Something went wrong.");
		return new HelloWorldBean(String.format("Hello %s", name));
	}	

}
