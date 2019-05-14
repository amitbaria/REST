package com.example.LadApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.LadApp.model.UserModel;

@Service
public interface UserRegistrationRepository extends JpaRepository <UserModel,Integer>{

}
