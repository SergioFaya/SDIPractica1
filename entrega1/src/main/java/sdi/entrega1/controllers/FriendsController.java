package sdi.entrega1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sdi.entrega1.entities.Friend;
import sdi.entrega1.services.friends.FriendsService;

@Controller
public class FriendsController {

	@Autowired
	private FriendsService service;
	

	@RequestMapping("/friends")
	public String getListFriends(Model model, Pageable pageable) {
		Page<Friend> friends = service.getAllFriends(pageable);
		model.addAttribute("friendList", friends.getContent());
		return "friend_request/list";
	}
	
}
