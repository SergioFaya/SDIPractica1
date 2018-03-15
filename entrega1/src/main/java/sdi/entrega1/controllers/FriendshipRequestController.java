package sdi.entrega1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sdi.entrega1.entities.FriendShipRequest;
import sdi.entrega1.services.friends.request.FriendshipRequestService;

@Controller
public class FriendshipRequestController {

	@Autowired
	private FriendshipRequestService service;
	
	@RequestMapping("/friends/requests")
	public String getRequests(Model model, Pageable pageable) {
		Page<FriendShipRequest> requests = service.getAllFriendshipRequests(pageable);
		model.addAttribute("fRequestList",requests.getContent());
		return "friend_request/list";
	}
	
	
	
	
}
