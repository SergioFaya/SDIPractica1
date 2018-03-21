package sdi.entrega1.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Autowired
	private FriendshipRequestService requestService;

	@Autowired
	private UsersService userService;

	@RequestMapping("/friends/requests/list")
	public String getRequests(Model model, Principal principal, Pageable pageable) {
		Page<FriendShipRequest> requests = requestService.getMyFriendshipRequests(pageable, principal);
		model.addAttribute("fRequestList", requests.getContent());
		model.addAttribute("page", requests);
		return "friends/requests/list";
	}

	@RequestMapping("/friends/send/request/{id}")
	public String addRequest(Model model, Principal principal, @PathVariable Long id) {
		User authenticated, friend;
		authenticated = userService.getUserByEmail(principal.getName());
		friend = userService.getUser(id);
		boolean added = requestService.addRequest(authenticated, friend);
		if (added) {
			return "/friends/requests/success";
		}
		return "/friends/requests/error";
	}

	@RequestMapping("/friends/accept/request/{id}")
	public String aceptRequest(Model model, Principal principal, @PathVariable Long id) {
		User authenticated, friend;
		authenticated = userService.getUserByEmail(principal.getName());
		friend = userService.getUser(id);
		requestService.acceptRequest(authenticated, friend);
		return "redirect:/friends/requests/list";
	}

	// Es un controlador de User porque los devuelve, aunque al trabajar con request
	// podría ser
	// a su vez un controlador de FriendShipRequest
	@RequestMapping("/friends/list")
	public String getFriends(Model model, Principal principal, Pageable pageable) {
		Page<FriendShipRequest> requests = requestService.getMyFriends(pageable, principal);
		model.addAttribute("friends", requests.getContent());
		model.addAttribute("page", requests);
		return "friends/list";

	
	}
	
	

}
