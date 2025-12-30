package com.spendsense.splitx.service;

import com.spendsense.splitx.util.DummyEmailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(long id) {
		return userRepository.findById(id).get();
	}
	
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	public List<User> createDummyUsers(List<User> users, User addedBy) {
		for(User user : users) {
			createDummyUser(user, addedBy);
		}
		return users;
	}

	private void createDummyUser(User user, User addedBy) {
		String dummyEmail = DummyEmailGenerator.generateDummyEmail(user);
		while(true) {
			User existingUser = findUserByEmail(dummyEmail);
			if (existingUser == null) {
				break;
			}
			dummyEmail = DummyEmailGenerator.generateDummyEmail(user);
		}
		user.setEmail(dummyEmail);
		user.setDummyUser(true);
		userRepository.save(user);
	}
}
