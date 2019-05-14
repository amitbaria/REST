package com.example.LadApp.controller;

import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LadApp.customizedUserMethodService.customizedUserModelService;
import com.example.LadApp.model.UserModel;
import com.example.LadApp.userServiceImp.UserServiceImp;
import com.example.LadApp.utils.Mail;



@RestController
@ComponentScan({"com.example.LadApp.*"})
public class RestAPIUserConntroller
{
	@Autowired
	private  UserServiceImp  userServiceImp;
	@Autowired
	private customizedUserModelService  customizeduserModelService;
	 
	
	@GetMapping(value="/userDetails")
	public ResponseEntity<Object>  getUserDetails()
	{
		
	List<UserModel>	data=userServiceImp.UserDetails();	
	if(data.size()>0)
	{
		return new ResponseEntity<>(data,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<>("Recoreds Unavailable",HttpStatus.OK);
		
	}
		
		
	}
	
	
	@GetMapping(value="/getById/{userid}")
	public ResponseEntity<Object>  getUserDetailsById(@PathVariable("userid") int userid)
	{
		
		Optional<UserModel>  data=userServiceImp.userDetailsById(userid);
	
		if(data.isPresent())
	   {
		
			UserModel data1= data.get();
		return new ResponseEntity<>(data1,HttpStatus.OK);
	    }
	else
	{
		return new ResponseEntity<>("Recoreds Unavailable",HttpStatus.OK);
		
	}
	
	
		
	}
	
	@RequestMapping(value="/newUserRegistration",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>  savsaveUserDetailseUserRecord(@RequestBody UserModel  usermodel )
	{
		    try
		    {
		    	// data save into database
		     UserModel u=userServiceImp.addUserDetail(usermodel);
				if(u!=null)
				{
					
					// create a new URL
				    String createURL="http://localhost:8080/EnableUserCredentialsById?userid="+u.getUserid();	
					// email send to Registered Email id..
				
				
				
					Mail m=new Mail(u.getUserName(),u.getUserEmail(),createURL);
					if(m.mailTrigger())
					{
						
					
						return new ResponseEntity<>("Recored Saved",HttpStatus.OK);
						
					}
					
					else
					{
						return new ResponseEntity<>("Mail Problem",HttpStatus.OK);
						
					}
				    
				}
				else
				{
					return new ResponseEntity<>("not saved",HttpStatus.OK);	
				}
		
		    }catch(Exception e)
		    {
		    	
		    	return new ResponseEntity<>("Something problem",HttpStatus.OK);	
		    	
		    	
		    }
		
	}
	
	
	
	@RequestMapping(value="/EnableUserCredentialsById")
	public String  updateUserDetailsById(@RequestParam("userid") int userid,Model model)
	{
		
		Optional<UserModel>  data=userServiceImp.userDetailsById(userid);
		if(data.isPresent())
	   {
			
	      // get User Detail
	      UserModel data1= data.get();
	      data1.setEnabled(1);
	      
	      // update Recored
	      UserModel u=userServiceImp.addUserDetail(data1);
	      if(u!=null)
	      {
	    	  
	    	    model.addAttribute("RegistereduserName",u.getUserName());
				model.addAttribute("RegisteredMessage","You have been Successfully Registered, You can Login From LAD Mobile App...");
				return "  You are Now Active User...........,  Go to Login Application";
 
	    	  
	      }
	      else
	      {
	    	    model.addAttribute("RegistereduserName","Sorry");
				model.addAttribute("RegisteredMessage","  Sorry for Inconvenience,  Your Credentials are not Activated....");
				return "  Sorry........";
	      }
	      
	    }
	else
	{
		
		model.addAttribute("RegistereduserName","Invalid User");
		model.addAttribute("RegisteredMessage","  Sorry for Inconvenience,  Your Credentials are not Activated....");
		
		return "  Sorry......";
		
	}
	
	}	
	
	
	@PostMapping(value="/loginUser", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>  userLogin(@RequestBody  UserModel userinfo)
	{
	      try
	      {
	 List<UserModel>  usermodel	=customizeduserModelService.verifyLogin(userinfo.getUserid(),userinfo.getUserPassword());
	 
	      if(usermodel.size()>0)
	      {
	    	  System.out.println("successfull..................................");
	    	  return new ResponseEntity<>((UserModel)usermodel.get(0),HttpStatus.OK);	  
	    	  
	      }
	      else
	      {
	    	  
	    	  System.out.println("Unsuccessfull..................................");
	    	  return new ResponseEntity<>("Not verified",HttpStatus.OK);
	    	  
	      }
		
	      }catch(Exception e)
	      {
	    	  System.out.println("Unsuccessfull..................................");
	    	  return new ResponseEntity<>("Not verified",HttpStatus.OK);
	      }
		
			
		
	}
	
	
	
	
	@RequestMapping("/test")	
	public String testPage()
	{
		
		return "SuccessReg";
		
	}
	
	
	
	

}
