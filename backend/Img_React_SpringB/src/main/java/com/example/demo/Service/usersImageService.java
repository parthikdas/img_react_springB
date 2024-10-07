package com.example.demo.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.UsersImage;
import com.example.demo.Repository.UsersImageRepo;

@Service
public class usersImageService {
	
	@Autowired
	UsersImageRepo repo;
	
	// Add user
	public UsersImage addUser(String name, MultipartFile img) throws IOException {
		UsersImage user = new UsersImage();
		user.setUserName(name);
		user.setImg(img.getBytes());
		return repo.save(user);
	}
	
	// Get all users
	public List<UsersImage> getAllUsers() {
		return repo.findAll();
	}
	
	// Get user
	public UsersImage getUser(int id) {
			return repo.findById(id).orElse(null);
	}
	
	// Update user
	public UsersImage updateUser(int id, UsersImage userDetails) {
		UsersImage userFound = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		userFound.setUserName(userDetails.getUserName());
		userFound.setImg(userDetails.getImg());
		return repo.save(userFound);	
	}
	
	// Delete user
	public void deleteUser(int id) {
		repo.deleteById(id);
	}
}
