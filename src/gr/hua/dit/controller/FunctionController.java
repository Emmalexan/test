package gr.hua.dit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.entity.Course;
import gr.hua.dit.entity.Function;
import gr.hua.dit.entity.Role;
import gr.hua.dit.entity.User;
import gr.hua.dit.service.CourseService;
import gr.hua.dit.service.FunctionService;
import gr.hua.dit.service.RoleService;

@Controller
@RequestMapping("/function")
public class FunctionController {
	
	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private RoleService roleService;
	
	
	@GetMapping("/listfunction")
	public String listFounction(Model model) {
		
		// get courses from the service
		List<Function> functions = functionService.getFunctions();
		
		// add the courses to the model
		model.addAttribute("functions",functions);
		
		// add page title
		model.addAttribute("pageTitle", "List of Functions");
		return "list-functions";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteFunction(@PathVariable("id") int id, Model model) {
		functionService.deleteFunction(id);
		System.out.println("bhke sth delete function");
		return "redirect:/function/listfunction";  
	}
	
	@PostMapping("{functid}/saveFunction/{functid}/{roleid}")
	public String saveFunction(@ModelAttribute("function") Function function, @PathVariable("functid") int functid, @PathVariable("roleid") int roleid){
		
		System.out.println(function.getId());
		System.out.println(functid);
		System.out.println(roleid);
		
//			Role role = roleService.getRole(roleid);
			function.setId(functid);
//			function.setRole(role);
			functionService.updateFunction(function);
		
		System.out.println("bhke sth save function");
		
		
		return "redirect:/function/listfunction";
	}
	
	@PostMapping("/saveFunction/{functid}/{roleid}")
	public String saveNewFunction(@ModelAttribute("function") Function function, @PathVariable("functid") int functid, @PathVariable("roleid") int roleid){
		
		functionService.saveFunction(function);
		
		System.out.println("bhke sth save function");
		
		return "redirect:/function/listfunction";
	}
	
	@GetMapping("/{functid}/{roleid}")
	public String getFunction(Model model, @PathVariable("functid") int functid,@PathVariable("roleid") int roleid) {
		// get the user
		Function function = functionService.getFunction(functid);
		
		System.out.println(functid);
		System.out.println(roleid);
		
		model.addAttribute("function", function);
		model.addAttribute("functid", functid);
		model.addAttribute("roleid", roleid);
		
		return "function-form";
	}
	
	@GetMapping("/showFunctForm")
	public String showFunctForm(Model model) {
		// create model attribute to get form data
		Function function = new Function();
		int functid = function.getId();
		int roleid = 0;
		
		System.out.println(functid);
		System.out.println(roleid);
		
		model.addAttribute("function", function);
		model.addAttribute("functid", functid);
		model.addAttribute("roleid", roleid);
		
		// add page title
		model.addAttribute("pageTitle", "Add Function");
		return "function-form";
	}  
	
	@GetMapping("/role/{functid}")
	public String chooseRoleForFunction(Model model, @PathVariable("functid") int functid) {
		// get the user
		//Function function = functionService.getFunction(functid);
		
		List<Role> roles = roleService.getRoles();
		Role role = new Role();
		model.addAttribute("role", role);
		model.addAttribute("roles", roles);
		model.addAttribute("functid", functid);
		
		return "function-role-form";
		//return "select-roleUser";
	}

	
	@GetMapping("/saveRoleFunction/{functid}")
	public String saveRoleForFunction(@RequestParam int nameRole, Model model,@ModelAttribute("role") Role role, @PathVariable("functid") int functid){
		System.out.println("bhke sth save function");
		//Function function = new Function();
		Function function = functionService.getFunction(functid);
		System.out.println(function.getFunctionName() + function.getId());

		System.out.println(nameRole +"to nameRole");
		Role tempRole = new Role();
		tempRole.setId(nameRole);
		
		System.out.println(tempRole.getId());
		System.out.println(tempRole.getNameRole());
		
		function.setRole(tempRole);
		functionService.updateFunction(function);
		
		return "redirect:/function/listfunction";
	}
	
	
	
}
