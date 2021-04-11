package com.in28minutes.rest.webservices.user;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		// add link to get all users //"all-users", SERVER_PATH + "/users
		// retrieveAllUsers
		// Spring-boot-hateoas enables us to easily add links to other resources using
		// methods

		// create resource of user
		EntityModel<User> resource = EntityModel.of(user);

		// create link to /users using retrieveAllUsers method
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		// add link by key "all-users"
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	/**
	 * <!-- to get the response in xml format, default is json --> <dependency>
	 * <groupId>com.fasterxml.jackson.dataformat</groupId>
	 * <artifactId>jackson-dataformat-xml</artifactId> </dependency> after adding
	 * dependency if you do not specify accept-header in request response would be
	 * json but if you specify accept-header as application/xml then response would
	 * be xml after adding this dependency now service will support both Json or XML
	 * depend upon accept-header
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);

		// get currentRequest URI append id by replacing it from variable id to
		// savedUser.getId and return URI
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		// returning status code 201 created as well as location of the created resource
		// with id in the header of the response
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}

	}
}
