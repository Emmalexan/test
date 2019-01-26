package gr.hua.dit.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.Role;
import gr.hua.dit.entity.TextBook;
import gr.hua.dit.entity.User;

import gr.hua.dit.service.AuthoritiesService;
import gr.hua.dit.service.FunctionService;
import gr.hua.dit.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private FunctionService functionService;
	
	@GetMapping("/listrole")
	public String listRole(Model model) {
		
		// get users from the service
		List<Role> roles = roleService.getRoles();
		
		// add the users to the model
		model.addAttribute("roles",roles);
		
		// add page title
		model.addAttribute("pageTitle", "List of Roles");
		return "list-role";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRole(@PathVariable("id") int id, Model model) {
		
		Role role = roleService.getRole(id);
		//functionService.updateFunctions(id, role);
		roleService.deleteRole(id);
		
		
		
//		String rolenm = roleService.getRole(id).getNameRole();
//		authoritiesService.deleteAuthoritie(rolenm);
	
		System.out.println("bhke sth delete role");
		return "redirect:/role/listrole";  
	}
	
	@PostMapping("/saveRole/{id}")
	public String saveRole(Model model,@ModelAttribute("role") Role role,@PathVariable("id") int id){
		
		//int roleid = role.getId();
		List<Role> roles = roleService.getRoles();
		for(Role r : roles) {
			if(r.getNameRole().equals(role.getNameRole())) {
				model.addAttribute("message", "This Role already exist " + role.getNameRole());
				return "error";
			}
		}
		
		if(id == 0) {
			System.out.println("bhke sth save role");
			System.out.println(role.getNameRole());
			
			Role temprole = new Role();
			String namer = role.getNameRole();
			temprole.setNameRole(namer);
			
			roleService.saveRole(role);
		}else if(id != 0) {
			
			System.out.println("bhke sth update role");
			
			Role temprole = roleService.getRole(id);
			String namer = role.getNameRole();
			temprole.setNameRole(namer);
			
			roleService.updateRole(temprole);
		}
		
		
		return "redirect:/role/listrole";
	}
	
	@GetMapping("/{id}")
	public String getRole(Model model, @PathVariable("id") int id) {
		// get the user
		//int newRole = 0;
		Role role = roleService.getRole(id);
		
		model.addAttribute("role", role);
		model.addAttribute("id", id);
		System.out.println(id);
		
		return "add-role-form";
	}   
	
	@GetMapping("/showRoleForm")
	public String showRoleForm(Model model) {
		// create model attribute to get form data
		Role role = new Role();
		int id = role.getId();
		
		System.out.println(id);
		
		model.addAttribute("role", role);
		model.addAttribute("id", id);
		// add page title
		model.addAttribute("pageTitle", "Add Role");
		return "add-role-form";
	}  

}
