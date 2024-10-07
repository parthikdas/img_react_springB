package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.UsersImage;

@Repository
public interface UsersImageRepo extends JpaRepository<UsersImage, Integer> {

}
