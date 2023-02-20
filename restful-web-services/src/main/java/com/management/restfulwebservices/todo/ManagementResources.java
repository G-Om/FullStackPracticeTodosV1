package com.management.restfulwebservices.todo;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ManagementResources {
	
	@Autowired
	private TodoHardcodedService todoService;
	
	@GetMapping(path="/users/{username}/todos")
	public List<Todo> getAllTodos(@ PathVariable String username){
		return todoService.findAll();
	}
	
	@GetMapping(path="/users/{username}/todos/{id}")
	public Todo getAllTodos(@ PathVariable long id){
		return todoService.findById(id);
	}
//	PUT: /users/{username}/todos/{todo_id}
	@PutMapping(path="/users/{username}/todos/{todo_id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable("username") String username, 
			@PathVariable("todo_id") long id, @RequestBody Todo todo){
		Todo todoUpdated = todoService.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping( path="/users/{username}/todos")
	public ResponseEntity<Void>  createTodo(
			@PathVariable String username, @RequestBody Todo todo){
		
		Todo createdTodo = todoService.save(todo);
		// Always return location of new todo
		// get current source uri and append {id}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
																	.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();   		
		return  ResponseEntity.created(uri).build();
	}
	
//	DELETE: /users/{username}/todos/{todo_id}
	@DeleteMapping(path = "/users/{username}/todos/{todo_id}")	
//	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteTodo(
			@PathVariable("username") String username, @PathVariable("todo_id") long id){
		Todo todo = todoService.deleteById(id);
		if(todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build(); 
	}
}
