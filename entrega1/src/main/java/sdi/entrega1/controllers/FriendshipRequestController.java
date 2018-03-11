package sdi.entrega1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FriendshipRequestController {

	@RequestMapping("/contacts/requests/{user_id}")
	public String getList(@PathVariable Long user_id) {
		
		return "friend_request/list";
	}
}
