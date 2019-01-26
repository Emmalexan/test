package gr.hua.dit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.Role;
import gr.hua.dit.entity.Teacher;
import gr.hua.dit.entity.User;


@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Role> getRoles() {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Role> query = currentSession.createQuery("from Role", Role.class);
		// execute the query and get the results list
		List<Role> roles = query.getResultList();
		// return the results
		return roles;
	}

	@Override
	public void deleteRole(int id) {
		// get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				// find the user
				Role role = currentSession.get(Role.class, id);
				// delete user
				currentSession.delete(role);
	}
	
	@Override
	   public void saveRole(Role role) {
	           // get current hibernate session
	           Session currentSession = sessionFactory.getCurrentSession();
	           // save the textbook
		
			currentSession.save(role);
	           
	   }
	
	
	@Override
	public Role getRole(int id) {
Session currentSession = sessionFactory.getCurrentSession();
		
		//get and return Teacher
		Role role = currentSession.get(Role.class, id);
		return role;
	}
	
	@Override
	   public void updateRole(Role role) {
	           // get current hibernate session
	           Session currentSession = sessionFactory.getCurrentSession();
	           // save the textbookprofile
			
			//System.out.println(textbookprofile);
	          
			System.out.println(role);

			currentSession.update(role);
	           
	   }
}
