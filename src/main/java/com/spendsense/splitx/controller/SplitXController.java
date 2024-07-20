package com.spendsense.splitx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.service.GroupService;
import com.spendsense.splitx.service.UserService;

@RestController
public class SplitXController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/home/{id}")
	public List<Group> getGroupsByUser(@PathVariable long id) {
		return groupService.getGroupsByUser(id);
	}
	
	@PostMapping("/home/{id}/creategroup")
	public Group createGroup(@RequestBody Group group, @PathVariable long id) throws Exception {
		try {
			User user = userService.getUserById(id);
			System.out.println("API hit: "+ group.getGroupCode());
			group.setGroupOwner(user);
			return groupService.createGroup(group);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
}
