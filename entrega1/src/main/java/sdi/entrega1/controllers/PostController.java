package sdi.entrega1.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sdi.entrega1.entities.Post;

@Controller
public class PostController {
	
	
	@RequestMapping(value = "post/add")
	public String getPostForm(Model model) {
		return "posts/add";
	}
	
	@RequestMapping(value = "post/add",  method = RequestMethod.POST)
	public String createPost(@ModelAttribute Post post,Principal principal) {
		return "redirect:posts/list";
	}
	
	
	@RequestMapping(value = "post/list")
	public String getPostList(Model model) {
		return "posts/list";
	}
}
