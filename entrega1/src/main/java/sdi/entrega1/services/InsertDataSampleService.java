package sdi.entrega1.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.Post;
import sdi.entrega1.entities.User;
import sdi.entrega1.services.friends.request.FriendshipRequestService;
import sdi.entrega1.services.posts.PostsService;

@Service
public class InsertDataSampleService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private FriendshipRequestService fs;

	@Autowired
	private PostsService ps;

	@PostConstruct
	public void init() {
		User user1 = new User("pedro@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("lucas@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("maria@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("marta@gmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("pelayo@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		User user6 = new User("edward@gmail.com", "Edward", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[1]);
		User user7 = new User("manuel@gmail.com", "Manuel", "García");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		User user8 = new User("sergio@gmail.com", "Sergio", "Faya");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[0]);
		User user9 = new User("paco@gmail.com", "Francisco", "Domínguez");
		user9.setPassword("123456");
		user9.setRole(rolesService.getRoles()[0]);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);

		// Peticiones de prueba
		fs.addRequest(user1, user2);
		fs.addRequest(user2, user3);
		// Peticion bidireccional
		fs.addRequest(user7, user8);
		fs.addRequest(user8, user7);
		// amistad
		fs.acceptRequest(user3, user2);
		// publicaciones de prueba
		ps.createPost(new Post("Publicacion1", "Publicacion de Pedro", user1));
		ps.createPost(new Post("Publicacion2", "Publicacion de Francisco", user8));
		ps.createPost(new Post("Publicacion3", "Publicacion de Maria", user1));

	}

}
