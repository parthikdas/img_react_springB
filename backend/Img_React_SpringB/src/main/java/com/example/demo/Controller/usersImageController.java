package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.UsersImage;
import com.example.demo.Service.usersImageService;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class usersImageController {
	@Autowired
	usersImageService service;
	
	// show all users
	@GetMapping
	public ResponseEntity<List<UsersImage>> showAllUsers() {
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	
	// show user
	@GetMapping("/{id}")
	public ResponseEntity<UsersImage> showUser(@PathVariable int id) {
	    UsersImage userFound = service.getUser(id);
	    HttpStatus status = (userFound == null) ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
	    return new ResponseEntity<>(userFound, status);
	}
	
	// add user
	@PostMapping
    public ResponseEntity<?> addUser(@RequestPart("userName") String userName, @RequestPart("img") MultipartFile img) throws IOException {
		if (img.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is missing");
	    }
		UsersImage newUser = service.addUser(userName, img);
        HttpStatus status = (newUser  == null) ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(newUser, status);
    }
	
	// update user
	@PutMapping("/{id}")
	public ResponseEntity<UsersImage> updateUser(@PathVariable int id, @RequestBody UsersImage user) {
		return ResponseEntity.ok(service.updateUser(id, user));
	}
	
	// delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		service.deleteUser(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
