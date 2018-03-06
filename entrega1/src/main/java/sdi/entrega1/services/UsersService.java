package sdi.entrega1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sdi.entrega1.entities.User;
import sdi.entrega1.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User getUser(Long id) {
		return usersRepository.findOne(id);
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.delete(id);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public Page<User> getAllUsers(Pageable pageable) {
		return usersRepository.findAll(pageable);
	}

}
