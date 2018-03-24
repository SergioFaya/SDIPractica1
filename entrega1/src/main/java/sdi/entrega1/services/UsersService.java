package sdi.entrega1.services;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.User;
import sdi.entrega1.repositories.UsersRepository;

@Service
public class UsersService {

	private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User getUser(Long id) {
		return usersRepository.findOne(id);
	}

	public boolean checkPassword(User user, String password) {
		if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return true;
		}
		logger.warn("Las contraseñas del usuario %s no coinciden", user.getEmail());
		return false;
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
		logger.info("Usuario %s registrado correctamente", user.getEmail());
	}

	public void deleteUser(Long id) {
		usersRepository.delete(id);
		logger.info("El usuario con id %d ha sido borrado", id);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public Page<User> getAllUsers(Pageable pageable) {
		return usersRepository.findAll(pageable);
	}

	public Page<User> getAllUsersBut(Pageable pageable, String email) {
		return usersRepository.searchAllButUser(pageable, email);
	}

	public Page<User> searchUsersByNombreAndEmail(Pageable pageable, String searchText) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%" + searchText + "%";
		users = usersRepository.searchByNombreAndEmail(pageable, searchText);
		return users;
	}

}
