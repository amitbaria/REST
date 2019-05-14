package com.example.LadApp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GeneratorType;

import com.example.LadApp.DateDeserialize.CustomeDateDeserialize;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="LadUserInformation")
public class UserModel {
	@Id
	@Column(name="USERID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int  userid;
	
	
	@Column(name="USERNAME")
	@NotNull
	private String userName;
	
	@Column(name="USEREMAIL")
	@NotNull
	private String userEmail;
	
	@Column(name="USERPASSWORD")
	@NotNull
	private String userPassword;
	
	@Column(name="SECURITYQUESTION")
	@NotNull
	private String securityQuestion;
	
	@Column(name="USERPHONENO")
	@NotNull
	private String userPhoneno;
	
	@Column(name="SECURITYANSWER")
	@NotNull
	private String securityAnswer;
	
	@NotNull
	@Column(name="ISADMIN")
	private String isAdmin;
	
	@NotNull
	@Column(name="ENABLED")
	private int    enabled;
	
	 @NotNull
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name="CREATIONDATE")
	 
	 
	 // same date Format is required from JSON String...
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 private Date  datetime;
	
	
	public Date getDatetime() {
		
		
		
		return datetime;
	}



	public void setDatetime(Date datetime) {
		
		 this.datetime=datetime;
		
//		try
//		{
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // your format
//	    datetime = format.parse(datetime.toString());
//		}catch(Exception e)
//		{
//			
//	     System.out.println("Date Exception.........................."+this.datetime);		
//		}
	}



	public UserModel()
	{}
	
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	
	
	public String getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	public int getEnabled() {
		return enabled;
	}


	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	
	
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	
	
	
	public String getUserPhoneno() {
		return userPhoneno;
	}
	
	public void setUserPhoneno(String userPhoneno) {
		this.userPhoneno = userPhoneno;
	}

	
	
	public UserModel(String userName,String userEmail,String userPassword,String securityQuestion,
			String securityAnswer,String userPhoneno,String isAdmin, int    enabled ,Date datetime)
	{
		this.userName=userName;
		this.userEmail=userEmail;
		this.userPassword=userPassword;
		this.securityQuestion=securityQuestion;
		this.userPhoneno=userPhoneno;
		this.securityAnswer=securityAnswer;
		this.isAdmin=isAdmin;
		this.enabled=enabled;
		this.datetime=datetime;
	}
	

}
