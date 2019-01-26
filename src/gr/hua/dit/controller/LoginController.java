package gr.hua.dit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.TextBookProfile;
import gr.hua.dit.entity.User;
import gr.hua.dit.service.AuthoritiesService;
import gr.hua.dit.service.LoginService;
import gr.hua.dit.service.TeacherService;
import gr.hua.dit.service.UserService;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@Controller
//@RequestMapping("/login")
public class LoginController {

	@Autowired
	public LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authService;
	

	@RequestMapping("/")
	  public String index(Model model, Principal principal) {
		System.out.println("sthn index");
	    model.addAttribute("message", "You are logged in as " + principal.getName());
	    return "index";
	  }
	
	//@GetMapping("/showLoginForm")
	@GetMapping("/login")
	public String showForm(Model model) {
		//User userform = new User();
		//model.addAttribute("userform", userform);
		System.out.println("sthn login apo controller");
		return "login";
	}
	
	/*@GetMapping("/authUser")
	public String showfirstpage() {
		System.out.println("eimai sthn authUser");
	        return "first-page";
	}*/
	
	
	
	@GetMapping("/firstPage")
	public String showfirstpage(Model model, Principal principal) {
		//model.addAttribute("message", "You are logged in as " + principal.getName());
		
	//	List<User> users = userService.getUsers();
		
		User user = userService.getUser(principal.getName());
		//System.out.println(user.getAuthorities().get(1).getAuthority());
		
		System.out.println(user.getAuthoritie().getAuthority());
			//return"teacher-profile";
		
		if(user.getAuthoritie().getAuthority().equals("ROLE_TEACHER")) {	
			model.addAttribute("user", user);
			
			System.out.println(user.getTeacher().getId());
//			return "teacher-profile";		
		}
		else if  (user.getAuthoritie().getAuthority().equals("ROLE_PUBLISHER")) {
			model.addAttribute("user", user);
//			return "publisher-profile";		
		}
		else if(user.getAuthoritie().getAuthority().equals("ROLE_ADMIN")) {	
			model.addAttribute("user", user);
//			return "admin-profile";		
		}
		else if(user.getAuthoritie().getAuthority().equals("ROLE_STUDENT")) {
			model.addAttribute("user", user);
//			return "student-profile";		
		}
		else if(user.getAuthoritie().getAuthority().equals("ROLE_SECRETARY")) {	
			model.addAttribute("user", user);
//			return "secretary-profile";		
		}else {
			System.out.println("This user has not been assigned a role");
			//return "redirect:/user/showLoginForm";  
			return "index";
		}
		
		return "home-page";
		 
	}   
	
	@GetMapping("/profile")
	public String ProfilePage(Model model,  Principal principal) {
		User user = userService.getUser(principal.getName());
		//System.out.println(user.getAuthorities().get(1).getAuthority());
		
		System.out.println(user.getAuthoritie().getAuthority());
			//return"teacher-profile";
		
		if(user.getAuthoritie().getAuthority().equals("ROLE_TEACHER")) {	
			model.addAttribute("user", user);
			
			System.out.println(user.getTeacher().getId());
//			return "teacher-view-profile";		
		}
		else if  (user.getAuthoritie().getAuthority().equals("ROLE_PUBLISHER")) {
			model.addAttribute("user", user);
//			return "publisher-view-profile";		
		}
		else if(user.getAuthoritie().getAuthority().equals("ROLE_ADMIN")) {	
			model.addAttribute("user", user);
//			return "admin-view-profile";		
		}
		else if(user.getAuthoritie().getAuthority().equals("ROLE_STUDENT")) {
			model.addAttribute("user", user);
//			return "student-view-profile";		
		}
		else if(user.getAuthoritie().getAuthority().equals("ROLE_SECRETARY")) {	
			model.addAttribute("user", user);
//			return "secretary-view-profile";		
		}else {
			System.out.println("This user has not been assigned a role");
			//return "redirect:/user/showLoginForm";  
			return "index";
		}
		
		return "profile";
	}

/*	@GetMapping("/home/{id}")
	public String showHome(Model model, @PathVariable("id") int id) {

		User user = userService.getUser(id);
		
			if(user.getRole().getNameRole().equals("teacher")|| user.getRole().getNameRole().equals("Teacher") || user.getRole().getNameRole().equals("TEACHER")) {	
				model.addAttribute("user", user);
				System.out.println(user.getTeacher().getId());
				return "teacher-profile";		
			}
			else if  (user.getRole().getNameRole().equals("publisher") || user.getRole().getNameRole().equals("Publisher") || user.getRole().getNameRole().equals("PUBLISHER")) {
				model.addAttribute("user", user);
				return "publisher-profile";		
			}
			else if(user.getRole().getNameRole().equals("admin") || user.getRole().getNameRole().equals("Admin") || user.getRole().getNameRole().equals("ADMIN")) {	
				model.addAttribute("user", user);
				return "admin-profile";		
			}
			else if(user.getRole().getNameRole().equals("student") || user.getRole().getNameRole().equals("Student") || user.getRole().getNameRole().equals("STUDENT")) {
				model.addAttribute("user", user);
				return "student-profile";		
			}
			else if(user.getRole().getNameRole().equals("secretary") || user.getRole().getNameRole().equals("Secretary") || user.getRole().getNameRole().equals("SECRETARY")) {	
				model.addAttribute("user", user);
				return "secretary-profile";		
			}
			System.out.println("This user has not been assigned a role");
			return "redirect:/user/showLoginForm";
	
	}  */

	@PostMapping("/checkoutPage")
	public String CheckoutPage(@ModelAttribute("userform") User userform, Model model) {

		model.addAttribute("userform", userform);
		return "CheckoutPage";
	}



	@PostMapping("/searchUser")
	public String processForm(@ModelAttribute("userform") User userform, BindingResult result, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
		String username =request.getParameter("userName");
		String password =request.getParameter("userPassword");
		if (result.hasErrors()) {
			System.out.println("has error");
			return "login";
		}

		
		String errorMsg = null;
		

		if (username == null || username.equals("")) {
			errorMsg = "Username can't be null or empty";
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			 PrintWriter out = response.getWriter();
			 
			 out.println("<font color=red>" + errorMsg + "</font>");
			 
           rd.include(request, response); 
			System.out.println("keno onoma");
			
			return "redirect:/login/showLoginForm";
		}
		

		if (password == null || password.equals("")) {
			errorMsg = "Password can't be null or empty";
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			 PrintWriter out = response.getWriter();
			 out.println("<font color=red>" + errorMsg + "</font>");
			 
           rd.include(request, response); 
			System.out.println("keno password");
			
			return "redirect:/login/showLoginForm";
		}
		

		
			System.out.printf("ononma" + username + "pas" + password);
			
			boolean userExists = loginService.checkLogin(username, password);
		
			if (userExists) {


				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				

				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);
				Cookie userName = new Cookie("user", username);
				userName.setMaxAge(30 * 60);
				response.addCookie(userName);
				
				List<User> users = userService.getUsers();
				model.addAttribute("users", users);
				
				System.out.println("!!!!!!!!!!!ta katafere");
				return "LoginSuccess";
			} else {
				System.out.println("then ta katafere");
				errorMsg = "No User with this data was found";
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				 PrintWriter out = response.getWriter();
				 out.println("<font color=red>" + errorMsg + "</font>");
				 
	           rd.include(request, response); 
				result.rejectValue("user", "invaliduser");
		
				
				return "redirect:/login/showLoginForm";
			}
		}

	

}
