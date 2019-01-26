package gr.hua.dit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.UserDAO;
import gr.hua.dit.entity.Secretary;
import gr.hua.dit.entity.TextBook;
import gr.hua.dit.entity.User;
import gr.hua.dit.dao.SecretaryDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SecretaryDAO secretaryDAO;
	
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDAO.saveUser(user);		
	}

	@Override
	@Transactional
	public User getUser(String username) {
		return userDAO.getUser(username);

	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		userDAO.deleteUser(username);
		
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDAO.updateUser(user);
		
	}
	
	//Secretary
	
	@Override
	@Transactional
	public List<Secretary> getSecretaries() {
		return secretaryDAO.getSecretaries();
	}

	@Override
	@Transactional
	public void saveSecretary(Secretary secretary) {
		secretaryDAO.saveSecretary(secretary);		
	}

	@Override
	@Transactional
	public Secretary getSecretary(int id) {
		return secretaryDAO.getSecretary(id);

	}

	@Override
	@Transactional
	public void deleteSecretary(int id) {
		secretaryDAO.deleteSecretary(id);
		
	}

	@Override
	@Transactional
	public void updateSecretary(Secretary secretary) {
		secretaryDAO.updateSecretary(secretary);
		
	}

}
