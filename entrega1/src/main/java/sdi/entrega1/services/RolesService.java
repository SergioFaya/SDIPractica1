package sdi.entrega1.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
	
	String[] roles = { "ROL_USUARIO" , "ROL_ADMIN" };

	public String[] getRoles() {
		return roles;
	}

}
