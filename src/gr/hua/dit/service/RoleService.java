package gr.hua.dit.service;

import java.util.List;

import gr.hua.dit.entity.Role;

public interface RoleService {
	public List<Role> getRoles();

	Role getRole(int id);

	void deleteRole(int id);

	void saveRole(Role role);

	void updateRole(Role role);
}
