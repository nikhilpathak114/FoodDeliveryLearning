package com.learning.service;

import com.learning.dto.Role;
import com.learning.exception.IdNotFoundException;

public interface RoleServ{

	public String addRole(Role role);
	public String deleteRole(int roleId) throws IdNotFoundException;
	
}
