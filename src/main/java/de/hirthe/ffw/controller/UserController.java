package de.hirthe.ffw.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hirthe.ffw.model.User;
import de.hirthe.ffw.service.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	public UserController() {

	}

	@GetMapping
	// get all Users - Return 200
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/createUserList")
	public ResponseEntity<Integer> createAListOfUsers(@RequestBody List<User> list) {
		Integer numberOfUsers = 0;
		for (User user : list) {
			if (checkIfUserExists(user) == null) {
				userRepository.save(user);
				numberOfUsers++;
			}
		}
		return new ResponseEntity<>(numberOfUsers, HttpStatus.CREATED);
	}

	@PostMapping("/createUser")
	// create a new User - Return 201
	public ResponseEntity<User> createUser(@RequestBody User user) {
		// check if user existed
		User userFound = checkIfUserExists(user);
		if (userFound != null)
			return new ResponseEntity<>(userFound, HttpStatus.FOUND);

		User savedUser = userRepository.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	private User checkIfUserExists(User user) {
		List<User> test = userRepository.findByLastname(user.getLastname());
		for (User userFirstname : test) {
			if (user.getFirstname().equals(userFirstname.getFirstname())) {
				return userFirstname;
			}
		}
		return null;

	}

	@DeleteMapping("/{id}")
	// delete a User
	public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	// Update an existing user
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable UUID id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();
		user.setId(id);
		userRepository.save(user);
		return ResponseEntity.noContent().build();
	}

}
