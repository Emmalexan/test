package gr.hua.dit.dao;

import java.util.List;

import gr.hua.dit.entity.Role;

public interface RoleDAO {
	
	public List<Role> getRoles();
	
	public Role getRole(int id);

	public void deleteRole(int id);

	public  void saveRole(Role role);

	void updateRole(Role role);
}
