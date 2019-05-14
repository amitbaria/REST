package com.example.LadApp.userServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LadApp.model.UserModel;
import com.example.LadApp.repository.UserRegistrationRepository;
import com.example.LadApp.userServiceMethods.UserService;

@Service
public class UserServiceImp  implements UserService
{

	    @Autowired
    	private  UserRegistrationRepository userRegistrationRepository; 
	  
	
	@Override
	public List<UserModel> UserDetails() {
		
		//userRegistrationRepository.findById(3);
		
		return   userRegistrationRepository.findAll();
		
		
	}


	@Override
	public Optional<UserModel> userDetailsById(int userid) {  
		
		 
		//  we cannot user getOne(useid) here, because it just give the reference of UserModel Object as proxy, not actual 
		// data,directly from database until the properties of this entity accessed.
		
		 // Therefore, This method directly hit the database to get the entity
		Optional<UserModel> d =  userRegistrationRepository.findById(userid);
		return d;
	}


	@Override
	public UserModel addUserDetail(UserModel model) {
		
		UserModel  u=	userRegistrationRepository.save(model);
		
		 return u;
		
		
	}
	
	
	


	
	
	

}
