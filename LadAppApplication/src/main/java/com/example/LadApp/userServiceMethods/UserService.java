package com.example.LadApp.userServiceMethods;

import java.util.List;
import java.util.Optional;

import com.example.LadApp.model.UserModel;

public interface UserService {
	
	public  List<UserModel> UserDetails();
	public Optional<UserModel> userDetailsById(int userid);
	
	public UserModel addUserDetail(UserModel model);

	
	

}
