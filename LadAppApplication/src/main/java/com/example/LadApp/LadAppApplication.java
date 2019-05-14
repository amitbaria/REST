package com.example.LadApp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.LadApp.repository.UserRegistrationRepository;

import com.example.LadApp.model.UserModel;

@SpringBootApplication

public class LadAppApplication   implements   CommandLineRunner{

	@Autowired
	private UserRegistrationRepository userRegistrationRepository;
	
	public static void main(String[] args) {
		
	
		SpringApplication.run(LadAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserModel user=new UserModel("Ajay","sachin@yahoo.com","1234","abc","Answer","123456789","no",0, new Date());
	    userRegistrationRepository.save(user);	
	
	System.out.println("Server is Started Now.......");
	}

	
}
