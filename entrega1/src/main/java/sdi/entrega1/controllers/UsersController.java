﻿package sdi.entrega1.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sdi.entrega1.entities.User;
import sdi.entrega1.services.RolesService;
import sdi.entrega1.services.SecurityService;
import sdi.entrega1.services.UsersService;
import sdi.entrega1.validators.SignupFormValidator;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignupFormValidator signUpFormValidator;

	@Autowired
	private RolesService rolesService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Pageable pagebale) {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(@Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/users/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByNombreAndEmail(pageable, searchText);
		} else {
			users = usersService.getAllUsersBut(pageable, principal.getName());
		}
		model.addAttribute("userList", users.getContent());
		model.addAttribute("page", users);
		return "users/list";
	}

	@RequestMapping(value = "/users/details/{id}")
	public String getDetails(Model model, @PathVariable Long id) {
		model.addAttribute("user", usersService.getUser(id));
		return "users/details";
	}

	@RequestMapping(value = "/admin/adminLogin", method = RequestMethod.GET)
	public String loginAdmin(Model model) {
		model.addAttribute("admin", new User());
		return "/admin/adminLogin";
	}

	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String getAdminList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByNombreAndEmail(pageable, searchText);
		} else {
			users = usersService.getAllUsersBut(pageable, principal.getName());
		}
		model.addAttribute("userList", users.getContent());
		model.addAttribute("page", users);
		return "users/list";
	}

	@RequestMapping(value = "/admin/adminLogin", method = RequestMethod.POST)
	public String adminLogin(Model model, @RequestParam String email, @RequestParam String password,
			Pageable pageable) {
		User user = usersService.getUserByEmail(email);

		if (user != null && usersService.checkPassword(user, password) && user.getRole().equals("ROLE_ADMIN")) {
			securityService.autoLogin(email, password);
			return "redirect:/admin/list";
		} else {
			model.addAttribute("error", true);
			return "redirect:adminLogin?error";
		}
	}

	@RequestMapping(value = "/admin/list/eliminar/{id}")
	public String eliminarUsuario(Model model, @PathVariable Long id, Pageable pageable) {
		Page<User> usersList = usersService.getAllUsers(pageable);
		model.addAttribute("usersList", usersList);
		usersService.deleteUser(id);
		return "redirect:/admin/list";
	}

}
