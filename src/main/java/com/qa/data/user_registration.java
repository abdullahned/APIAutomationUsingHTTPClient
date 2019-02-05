package com.qa.data;

public class user_registration {
	
	String username;
	String email;
	String first_name;
	String last_name;
	String gender;
	String phone;
	String value;
	String device_id;
	String device_type;
	String is_subscribed;
	String api_version;
	
	public user_registration(String username, String email, String first_name, String last_name,
			String gender, String phone, String value, String device_id, 
			String device_type, String is_subscribed, String api_version )
	{
		this.username = username;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.phone = phone;
		this.value = value;
		this.device_id = device_id;
		this.device_type = device_type;
		this.is_subscribed = is_subscribed;
		this.api_version = api_version;
		
	}
	
	public String getUsername() {
		return username;
		
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getvalue() {
		return value;
	}
	public void setvalue(String value) {
		this.value = value;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getIs_subscribed() {
		return is_subscribed;
	}
	public void setIs_subscribed(String is_subscribed) {
		this.is_subscribed = is_subscribed;
	}
	public String getApi_version() {
		return api_version;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}
	
	
	

}
