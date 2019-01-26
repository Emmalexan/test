package gr.hua.dit.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "name_role")
	private String nameRole;
	
	/*@OneToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private List<User> users;
	*/
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="role",
            cascade= CascadeType.ALL)
    private List<Function> functions;
	
	public Role() {
	
	}

	public Role(String nameRole, int id) {
		super();
		this.id = id;
		this.nameRole = nameRole;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	
	
	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", nameRole=" + nameRole + "]";
	}

	
	
}
