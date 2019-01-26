package gr.hua.dit.service;

import java.util.List;

import gr.hua.dit.entity.Secretary;
import gr.hua.dit.entity.User;

public interface UserService {
	public List<User> getUsers();

	public void saveUser(User user);
	
	public User getUser(String username);

	public void deleteUser(String username);

	public void updateUser(User user);

	public void saveSecretary(Secretary secretary);

	public List<Secretary> getSecretaries();

	public Secretary getSecretary(int id);

	public void deleteSecretary(int id);

	public void updateSecretary(Secretary secretary);
}
