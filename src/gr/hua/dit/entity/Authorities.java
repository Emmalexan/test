package gr.hua.dit.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities {
	
	  @Id
	  @Column(name = "authority")
	  private String authority;

/*	  @ManyToOne
	  @JoinColumn(name = "username")
	  private User user;
*/
/*	  @OneToOne
	  @JoinColumn(name = "username")
	  private User user;
*/	  
	  
	  
	
/*	  @OneToMany(fetch = FetchType.LAZY, mappedBy="authority",
			   cascade=CascadeType.ALL)
	      private List<User> users;
*/
	  
	  
	  
	  
	  public Authorities() {
			
		}
	  
	public Authorities(String authority, User user) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

/*	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	
	
	
	
	
	@Override
	public String toString() {
		return "Authorities [authority=" + authority + "]";
	}

/*	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	*/

	  
}
