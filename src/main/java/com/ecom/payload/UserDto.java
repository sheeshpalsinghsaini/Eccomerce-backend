package com.ecom.payload;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*
 * whatever data we need to send that we will put here.
 */

public class UserDto {

	private int userId;
	@NotEmpty	//these annotation used for validation of input data.
	@Size(min = 4,max = 20, message = "name must be min of 4 char and max of 20 char")
	private String name;
	@Email(message ="enter a valid email")
	private String email;
	@NotEmpty
	@Size(min=4,message = "Password must be min 4 digits!!")
	private String password; //we dont' want sent it, so we remove it
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String address;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$",message = "invalid user name")
	private String about;
	private String gender;
	private Date createAt;
	@NotBlank
	private String phone;
	private boolean Active;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isActive() {
		return Active;
	}
	public void setActive(boolean active) {
		Active = active;
	}
	
}
