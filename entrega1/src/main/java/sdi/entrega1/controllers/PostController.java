package sdi.entrega1.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sdi.entrega1.entities.Post;
import sdi.entrega1.entities.User;
import sdi.entrega1.services.UsersService;
import sdi.entrega1.services.posts.PostsService;

@Controller
public class PostController {
	
	@Autowired
	private PostsService postsService;
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "post/add")
	public String getPostForm(Model model) {
		return "posts/add";
	}
	
	@RequestMapping(value = "post/add",  method = RequestMethod.POST)
	public String createPost(@ModelAttribute Post post,Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		post.setUser(user);
		postsService.createPost(post);
		return "redirect:/post/list";
	}
	
	@RequestMapping(value = "post/list")
	public String getPostList(Model model, Principal principal) {
		List<Post> posts=  postsService.getUserPost(principal.getName());
		model.addAttribute("posts", posts );
		return "posts/list";
	}
	
	@RequestMapping(value = "post/list/{id}")
	public String getPostList(Model model, Principal principal, @PathVariable Long id) {
		List<Post> posts=  postsService.getUserPost(id);
		model.addAttribute("posts", posts );
		return "posts/list";
	}
}
