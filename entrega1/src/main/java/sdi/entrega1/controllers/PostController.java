package sdi.entrega1.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
		String imgSource = post.getPhotoPath();
		if(imgSource != null && imgSource.trim().length() > 0) {
			try {
				Files.copy(Paths.get(imgSource), Paths.get("src/main/resources/static/fotossubidas/" + post.getId()),StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		postsService.createPost(post);
		return "redirect:/post/list";
	}
	
	@RequestMapping(value = "post/add/{url}",  method = RequestMethod.POST)
	public String createPostWithPhoto(@ModelAttribute Post post,Principal principal, @PathVariable String url) {
		User user = usersService.getUserByEmail(principal.getName());
		post.setUser(user);
		if(url != null && url.trim().length() > 0) {
			try {
				Files.copy(Paths.get(url), Paths.get("src/main/resources/static/fotossubidas/" + post.getId()),StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		postsService.createPost(post);
		return "redirect:/post/list";
	}
	
	
	@RequestMapping(value = "post/list")
	public String getPostList(Model model, Principal principal) {
		List<Post> posts=  postsService.getUserPost(principal.getName());
		model.addAttribute("username",principal.getName());
		model.addAttribute("posts", posts );
		return "posts/list";
	}
	
	@RequestMapping(value = "post/list/{id}")
	public String getPostList(Model model, Principal principal, @PathVariable Long id) {
		String email = usersService.getUser(id).getEmail();
		List<Post> posts=  postsService.getUserPost(email);
		model.addAttribute("username",email);
		model.addAttribute("posts", posts );
		return "posts/list";
	}
}
