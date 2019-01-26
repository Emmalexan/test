package gr.hua.dit.controller;

import java.security.Principal;
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

import gr.hua.dit.entity.Course;
import gr.hua.dit.entity.Publisher;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.Teacher;
import gr.hua.dit.entity.TextBook;
import gr.hua.dit.entity.TextBookProfile;
import gr.hua.dit.entity.User;
import gr.hua.dit.service.PublisherService;
import gr.hua.dit.service.StudentService;
import gr.hua.dit.service.TextBookService;
import gr.hua.dit.service.UserService;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
	
	@Autowired
	private PublisherService publisherService;	
	@Autowired
	private TextBookService textbookService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@Autowired
	private HttpServletRequest request;
	
	/*
	@GetMapping("/publisherTextbooks")
	public String listpublisherTextbooks(Model model) {
		
		// get customers from the service
		List<TextBook> textbooks = textbookService.getTextBooks();
		
		// add the customers to the model
		model.addAttribute("textbooks",textbooks);
		
		// add page title
		model.addAttribute("pageTitle", "Publisher's Textbooks");
		return "publisher_textbooks";
	}  */
	
	
	@GetMapping("/mytextbooks/{id}")
	public String listPublisherTextbooks(Model model , @PathVariable("id") int id) {
					HttpSession currentSession = request.getSession();
					currentSession.setAttribute("publisher_id",id);
					
					Publisher publisher = publisherService.getPublisher(id);
					
					List<TextBook> textbooks = textbookService.getTextBooks();
					
					model.addAttribute("pub",publisher.getId());
					
					// add the publisher and textbooks to the model
					model.addAttribute("publisher",publisher);
					model.addAttribute("textbooks",textbooks);
					
					// add page title
					model.addAttribute("pageTitle", "Publishers' Textbooks");
					return "publisher-textbooks";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTextBook(@PathVariable("id") int id, Model model) {
		
		System.out.println("mphke sth delete");
		HttpSession currentSession = request.getSession();
		int t = (int) currentSession.getAttribute("publisher_id");
		textbookService.deleteTextBook(id);
		System.out.println("bhke sth delete textbook");
		return "redirect:/publisher/mytextbooks/"+t;  
	}
	
/*	@GetMapping("/listprofile")
	public String listTextBookProfile(Model model) {
		
		// get textbookprofiles from the service
		List<TextBookProfile> textbookprofiles = textbookService.getTextBookProfiles();
		
		// add the textbookprofiles to the model
		model.addAttribute("textbookprofiles",textbookprofiles);
		
		// add page title
		model.addAttribute("pageTitle", "List of Textbook's Profile");
		return "listprofile";
	}
	*/

	
	@GetMapping("/students")
public String listStudents(Model model) {
		
		// get students from the service
		List<Student> students = studentService.getStudents();
		
		// add the students to the model
		model.addAttribute("students",students);
		
		
		// add page title
		model.addAttribute("pageTitle", "List of Students");
		return "list-students";
	}
	
	@GetMapping("/studentProfile/{stid}")
	public String listStudentsTextBooks(Model model, @PathVariable("stid") int stid) {
		
		Student student = studentService.getStudent(stid);
		
		List<TextBookProfile> textbookprofiles = textbookService.getTextBookProfiles();
		
		model.addAttribute("stid", stid);
		model.addAttribute("student", student);
		model.addAttribute("textbookprofiles", textbookprofiles);
		model.addAttribute("pageTitle", "Profile Student");
		
		return "receipt-textbook";
		//return "profile-student";
	}
/*	
	@GetMapping("/studentTextbooks&{stid}")
	public String listTextbookProfileStudent(Model model,@PathVariable("stid") int stid) {
		// get the student
		Student student = studentService.getStudent(stid);
		
		List<TextBookProfile> textbookprofiles = textbookService.getTextBookProfiles();
		
		model.addAttribute("stid", stid);
		model.addAttribute("student", student);
		model.addAttribute("textbookprofiles", textbookprofiles);
		
		return "receipt-textbook";
	}
	*/
	
	
	@GetMapping("/deliveryPoint/{tid}")
	public String getDeliveryForm(Model model, @PathVariable("tid") int tid) {
		
		TextBook textbook = textbookService.getTextBook(tid);
		
		//List<TextBookProfile> textbookprofiles = textbookService.getTextBookProfiles();
		
		
		model.addAttribute("textbook", textbook);
		//model.addAttribute("textbookprofiles", textbookprofiles);
		
		return "add-delivery-point";
	}

	@GetMapping("/confirmation/{stid}/{textprofid}")
	public String getConfirmationForm(Model model, @PathVariable("textprofid") int textprofid) {
		// get the textbookprofile
		
	/*	TextBookProfile textbookprofile = textbookService.getTextBookProfile(textprofid);
		
		model.addAttribute("textbookprofile", textbookprofile);
		
		return "confirmation-delivery-textbook";   */
		
		TextBookProfile textbookprofile = textbookService.getTextBookProfile(textprofid);
		if(textbookprofile.getConfirmationReceived()== false) {
			textbookprofile.setConfirmationReceived(true);
			System.out.println(textbookprofile.getConfirmationReceived());
			textbookService.updateTextBookProfile(textbookprofile);
		}
		
		return "redirect:/publisher/studentProfile/{stid}";
	}   
	
	/*@PostMapping("/saveConfirmationChnages/")
	public String saveConfirmationChanges(@ModelAttribute("textbookprofile") TextBookProfile textbookprofile){
		// save the customer using the service
		System.out.println("bhke sth save textbookprofile");
		
		textbookService.saveTextBookProfile(textbookprofile);
		return "redirect:/publisher/receipt-textbook";  
	} */
	
	
	@PostMapping("/saveTextBook/{tid}")
	public String saveTextBookProfile(@ModelAttribute("textbook") TextBook textbook, @PathVariable("tid") int tid){
		// save the textbookprofile using the service
		System.out.println("bhke sth save textbook");
		System.out.println(textbookService.getTextBook(tid).getTitle());
		System.out.println(textbookService.getTextBook(tid).getId());
		//System.out.println(textbookService.getTextBook(tid).getReceivingPoint());
		//System.out.println(textbook.getId());
		
		System.out.println(textbook.getReceivingPoint());
		
		
		TextBook newTextbook = textbookService.getTextBook(tid);
		newTextbook.setReceivingPoint(textbook.getReceivingPoint());
		
		System.out.println(newTextbook.getId());
		System.out.println(newTextbook.getReceivingPoint());
		
		
		textbookService.updateTextBook(newTextbook);
		
		HttpSession currentSession = request.getSession();
		int t = (int) currentSession.getAttribute("publisher_id");
	/*	
		//textbook.setReceivingPoint(receivingPoint);
		System.out.println();
		
		
		//textbook.setReceivingPoint(receivingPoint);
		System.out.println(textbook.getReceivingPoint());
		
		String rp = textbook.getReceivingPoint();
		TextBook tempTextBook = textbookService.getTextBook(tid);
		
		tempTextBook.setReceivingPoint(rp);
		
		textbookService.updateTextBook(tempTextBook);
		
		int pid = tempTextBook.getPublisher().getId();
		System.out.println(tempTextBook.getPublisher().getPublisherName());
		*/
		
		return "redirect:/publisher/mytextbooks/"+t;
	}
	

	
	@GetMapping("/showDeliveryPointForm")
	public String showDeliveryPointForm(Model model) {
		// create model attribute to get form data
		
		
		TextBookProfile textbookprofile = new TextBookProfile();
		model.addAttribute("textbookprofile", textbookprofile);
		
		// add page title
		model.addAttribute("pageTitle", "Add Delivery Point");
		return "add-delivery-point";
	}
	
	@GetMapping("/{id}")
	public String showAddForm(Model model, @PathVariable("id") int id) {
		// create model attribute to get form data
		Publisher publisher= publisherService.getPublisher(id);
		TextBook textbook = new TextBook();
		//textbook.setPublisher(publisher);
		model.addAttribute("pub",publisher.getId());
		model.addAttribute("idTextbook", 0);
		model.addAttribute("textbook", textbook);
		
		// add page title
		model.addAttribute("pageTitle", "Add Textbook");
		return "textbook-form";
	}  
	
	@GetMapping("/{pub}/viewtextbook/{id}")
	public String getTextBook(Model model, @PathVariable("id") int id,@PathVariable("pub") int pub) {
		// get the textbook
		
		TextBook textbook = textbookService.getTextBook(id);
		Publisher publisher= publisherService.getPublisher(pub);
		//System.out.println(textbook);
		model.addAttribute("pub",publisher.getId());
		model.addAttribute("textbook", textbook);
		model.addAttribute("idTextbook", textbook.getId());
		return "textbook-form";
	}
	
	@PostMapping("/{pub}/update/{id}")
	public String updatesTextBook(@PathVariable("id") int id,@ModelAttribute("textbook") TextBook textbook,@PathVariable("pub") int pub) {
		if(id== 0) {
			
			System.out.println("bhke sth save textbook");
			Publisher publisher = publisherService.getPublisher(pub);
			textbook.setPublisher(publisher);
			textbookService.saveTextBook(textbook);
			return "redirect:/publisher/mytextbooks/{pub}";
		}
		System.out.println(id);
		textbook.setId(id);
		Publisher publisher = publisherService.getPublisher(pub);
		textbook.setPublisher(publisher);
		textbookService.updateTextBook(textbook);
		return "redirect:/publisher/mytextbooks/{pub}";  
	} 
	
	@PostMapping("/{pub}/viewtextbook/{pub}/update/{id}")
	public String updateTextBook(@PathVariable("id") int id,@ModelAttribute("textbook") TextBook textbook,@PathVariable("pub") int pub) {
		if(id== 0) {
			
			System.out.println("bhke sth save textbook");
			Publisher publisher = publisherService.getPublisher(pub);
			textbook.setPublisher(publisher);
			textbookService.saveTextBook(textbook);
			return "redirect:/publisher/mytextbooks/{pub}";
		}
		System.out.println(id);
		textbook.setId(id);
		Publisher publisher = publisherService.getPublisher(pub);
		textbook.setPublisher(publisher);
		textbookService.updateTextBook(textbook);
		return "redirect:/publisher/mytextbooks/{pub}";  
	} 
	
	
}
