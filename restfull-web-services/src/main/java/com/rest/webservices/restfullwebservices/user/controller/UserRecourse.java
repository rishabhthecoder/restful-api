package com.rest.webservices.restfullwebservices.user.controller;

// we need to do static import for the methodOn() function
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
//WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfullwebservices.user.User;
import com.rest.webservices.restfullwebservices.user.dao.UserDaoServices;
import com.rest.webservices.restfullwebservices.user.exceptions.UserNotFoundException;

@RestController
public class UserRecourse {
	@Autowired
	private UserDaoServices service;

	// Retrive all user;
	// Get /user
	@GetMapping("/user")
	public List<User> retriveAllData() {
		return service.findAll();

	}

	// Retrive user by id
	// Get /user/{id}

	@GetMapping("/user/{id}")
	public EntityModel<User> retriveById(@PathVariable int id) {
		User user = service.findById(id);
		if (user == null) {
			throw new UserNotFoundException("id -" + id);
		}
		// defining entity model of retrieveAllData
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).retriveAllData());
		model.add(linkToUser.withRel("All-User"));
		return model;
	}

	// Create a user Post/user
	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		// for building the new url for the newly added user
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

//		return savedUser;
	}

	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable int id) {
		User u = service.deleteById(id);
		if (u == null) {
			throw new UserNotFoundException("id- " + id);
		}
		return u.toString() + " deleted";
	}
}
