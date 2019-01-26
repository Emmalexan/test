package gr.hua.dit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.Function;
import gr.hua.dit.entity.Role;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.Teacher;
import gr.hua.dit.entity.User;
import gr.hua.dit.service.AuthoritiesService;
import gr.hua.dit.service.RoleService;
import gr.hua.dit.service.UserService;



@Controller
@RequestMapping("/authorities")
public class AuthoritiesController {
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private HttpServletRequest request;
	
/*	@GetMapping("/listrole")
	public String listRole(Model model) {
		
		// get users from the service
		List<Authorities> roles = authoritiesService.getAuthorities();
		
		// add the users to the model
		model.addAttribute("roles",roles);
		
		// add page title
		model.addAttribute("pageTitle", "List of Roles");
		return "list-role";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRole(@PathVariable("id") int id, Model model) {
		authoritiesService.deleteAuthoritie(id);;
		System.out.println("bhke sth delete role");
		return "redirect:/authorities/listrole";  
	}
	
	*/
	
	
	
/*	
	@PostMapping("/saveRole/{username}")
	public String saveRole(@RequestParam int nameRole,@ModelAttribute("role") Role role,@PathVariable("username") String username){

		System.out.println("HIIII");
		System.out.println(nameRole);
		Role therole = roleService.getRole(nameRole);
		String thename = therole.getNameRole();
		System.out.println(thename);
		
		Authorities tempauth = new Authorities();
		tempauth.setAuthority(thename);
		
		User user = userService.getUser(username);
		tempauth.setUser(user);
		
		authoritiesService.saveAuthoritie(tempauth);
		System.out.println("EKANE SAVE");
		
		
		HttpSession currentSession = request.getSession();
		currentSession.setAttribute("username",username);
		String t =(String) currentSession.getAttribute("username");
		
//		return "redirect:/user/listuser";
		return "redirect:/user/ShowDetailsForm/"+t;
	}*/
	
/*	@GetMapping("/{id}")
	public String getRole(Model model, @PathVariable("id") int id) {
		// get the user
		Authorities role = authoritiesService.getAuthoritie(id);
		
		model.addAttribute("role", role);
		
		return "role-form";
	}
	*/
	@GetMapping("/showRoleForm/{username}")
	public String showRoleForm(Model model,@PathVariable("username") String username) {
		// create model attribute to get form data
		
	//	Authorities role = new Authorities();
		List<Role> roles = roleService.getRoles();
		Role role = new Role();
		
		
		model.addAttribute("role", role);
		model.addAttribute("username", username);
		model.addAttribute("roles", roles);
		
		// add page title
		model.addAttribute("pageTitle", "Add Role to User");
		return "role-form";
	}  

}
