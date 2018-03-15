package sdi.entrega1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sdi.entrega1.entities.FriendShipRequest;
import sdi.entrega1.entities.User;
import sdi.entrega1.services.UsersService;
import sdi.entrega1.services.friends.request.FriendshipRequestService;

@Controller
public class FriendshipRequestController {
	
	public String alert(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append("<div class=\"alert alert-success\">");
		builder.append(message);
		builder.append("</div>");	
		return builder.toString();
	}
	
	@Autowired
	private FriendshipRequestService requestService;
	
	@Autowired
	private UsersService userService;
	
	@RequestMapping("/friends/requests")
	public String getRequests(Model model, Pageable pageable) {
		Page<FriendShipRequest> requests = requestService.getAllFriendshipRequests(pageable);
		model.addAttribute("fRequestList",requests.getContent());
		return "friend_request/list";
	}
	
	@RequestMapping("/friends/send/request/{id}")
	public String addRequest(Model model,@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		requestService.addRequest(new FriendShipRequest((User)authentication.getDetails(), userService.getUser(id)));
		
		model.addAttribute("alert",alert("Agregado correctamente") );
		return "friend_request/list: alert";
	}
	
	
}
