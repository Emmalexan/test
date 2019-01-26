package gr.hua.dit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.RoleDAO;
import gr.hua.dit.entity.Role;
import gr.hua.dit.entity.User;

@Service
public class RoleServiceImpl implements RoleService {

	// inject the RoleDAO
	@Autowired
	private RoleDAO roleDAO;

	@Override
	@Transactional
	public void deleteRole(int id) {
		roleDAO.deleteRole(id);
		
	}
	
	@Override
	@Transactional
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}

	@Override
	@Transactional
	public Role getRole(int id) {
		return roleDAO.getRole(id);
	}

	@Override
	@Transactional
	public void saveRole(Role role) {
		roleDAO.saveRole(role);		
	}
	
	@Override
	@Transactional
	public void updateRole(Role role) {
		roleDAO.updateRole(role);
		
	}
}
