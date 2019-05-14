package com.example.LadApp.customizedUserMethodService;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LadApp.customizedUserMethodInterface.CustomizedUserMethods;
import com.example.LadApp.model.UserModel;

@Service
public class customizedUserModelService implements CustomizedUserMethods {

	@Autowired  
	private EntityManager entityManager;
	
	@Override
	public List <UserModel> verifyLogin(int userid,String password) {
       Query query =		entityManager.createQuery("from UserModel c where  c.userid=:userid and c.userPassword=:password and c.enabled=:enable");
       
         query.setParameter("userid", userid);
         query.setParameter("password", password);
         query.setParameter("enable",1);
         
         
          List<UserModel>  userinfo =query.getResultList();
           return userinfo;
       
		
        
	
		     
		
	}

	

}
