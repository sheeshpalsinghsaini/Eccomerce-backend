package com.ecom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecom.exception.*;
import com.ecom.models.User;
import com.ecom.payload.UserDto;
import com.ecom.repositries.UserRepository;
import com.ecom.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	//create user
	@Override
	public UserDto create(UserDto userDto) {
		//DTO to Entity
		User user = this.toEntity(userDto);
		User createUser = this.userRepository.save(user);
		//Entity to DTO
		return this.toDto(createUser);
	}

	@Override
	public UserDto update(UserDto userDto, int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found :"+userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setAddress(userDto.getAddress());
		user.setActive(userDto.isActive());
		user.setGender(userDto.getGender());
		user.setCreateAt(userDto.getCreateAt());
		user.setPhone(userDto.getPhone());
		User updatedUser = this.userRepository.save(user);
		return this.toDto(updatedUser);
	}

	@Override
	public void delete(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found :"+userId));
		this.userRepository.delete(user);
	}

	@Override
	public List<UserDto> getAll() {
		List<User> allUser = this.userRepository.findAll();
		//need to convert all user to dto
		List<UserDto> allDtos = allUser.stream().map(user->this.toDto(user)).collect(Collectors.toList());
		return allDtos;
	}

	@Override
	public UserDto getByUserId(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found :"+userId));
		return this.toDto(user);
	}

	@Override
	public UserDto getByEmail(String email) {
		User user = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User with email not found"));
		return this.toDto(user);
	}
	
	public UserDto toDto(User user) {
		UserDto dto = new UserDto();
		dto.setUserId(user.getUserId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setAbout(user.getAbout());
		dto.setAddress(user.getAddress());
		dto.setActive(user.isActive());
		dto.setGender(user.getGender());
		dto.setCreateAt(user.getCreateAt());
		dto.setPhone(user.getPhone());
		return dto;
	}
	
	public User toEntity(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setAddress(userDto.getAddress());
		user.setActive(userDto.isActive());
		user.setGender(userDto.getGender());
		user.setCreateAt(userDto.getCreateAt());
		user.setPhone(userDto.getPhone());
		return user;
		
	}


	
	
	
}
