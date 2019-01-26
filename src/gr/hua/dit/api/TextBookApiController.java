package gr.hua.dit.api;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.dao.CourseDAO;
import gr.hua.dit.dao.TextBookDAO;
import gr.hua.dit.entity.Course;
import gr.hua.dit.entity.Course_textbook;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.TextBook;
import gr.hua.dit.entity.TextBookProfile;
import gr.hua.dit.service.CourseService;
import gr.hua.dit.service.StudentService;
import gr.hua.dit.service.TextBookService;

@RestController
@RequestMapping("/api")
public class TextBookApiController {

	@Autowired
	private TextBookService textbookService;
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private TextBookDAO textbookDAO;
	
	@Autowired
	private SessionFactory sessionFactory;
	

	 @GetMapping("/{id}")
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	public TextBook getTextBook(@PathVariable("id") int id) {
		 
		 

		TextBook textbook = textbookService.getTextBook(id);
		//String title = textbookService.getTextBook(id).getTitle();
		
		System.out.println("textbook :" + textbook);

		//return textbook;
		return textbook;
	}
	 
	 @GetMapping("/textbooks")
     public List<TextBook> listTextBooks() {
		 //List<TextBook> textbooks = courseService.getCourse(2).getTextBooks();
		// List<TextBook> textbooks = courseDAO.getCourse_textbooks_new(2);
             // get customers from dao
		//	System.out.println(textbooks);
		 List<TextBook> textbooks = textbookService.getTextBooks();	
			System.out.println(textbooks);
             return textbooks;
     }
	 
	 @GetMapping("/courses")
     public List<Course> listCourses() {
		 //List<TextBook> textbooks = courseService.getCourse(2).getTextBooks();
		// List<TextBook> textbooks = courseDAO.getCourse_textbooks_new(2);
             // get customers from dao
		//	System.out.println(textbooks);
		 List<Course> courses = courseService.getCourses();	
			System.out.println(courses);
             return courses;
     }
	 
	 @GetMapping("/profileTextbook")
     public List<TextBookProfile> lisstProfile() {
		 //List<TextBook> textbooks = courseService.getCourse(2).getTextBooks();
		// List<TextBook> textbooks = courseDAO.getCourse_textbooks_new(2);
             // get customers from dao
		//	System.out.println(textbooks);
		 List<TextBookProfile> profiles = textbookService.getTextBookProfiles();
		 
			System.out.println(profiles);
             return profiles;
     }
	 
	 
	 
     
	 
	 
	

	@RequestMapping(value="/delete/{id}", method= RequestMethod.DELETE , produces = { "application/json", "application/xml" })
	public ResponseEntity deleteTextBook(@PathVariable("id") int id) {
		textbookService.deleteTextBook(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@PostMapping("/show")
	//@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json", "application/xml" })
	public TextBook createTextBook(@RequestParam("title") String title,
			@RequestParam("writer") String writer, @RequestParam("year") String year, @RequestParam("publishername") String publishername, @RequestParam("isbn") String ISBN,@RequestParam("receivingPoint") String receivingPoint) {
		TextBook textbook = new TextBook(title,writer,year,publishername,ISBN,receivingPoint);
		textbookService.saveTextBook(textbook);
		return textbook;

	}

	@RequestMapping(value = "/jsonadd", method = RequestMethod.POST,  produces = { "application/json", "application/xml" })
	public TextBook createTextBookfromJson(@RequestBody TextBook textbook) {
		textbookService.saveTextBook(textbook);
		return textbook;
	}
	
	 @PostMapping("/addtextbook")
     public TextBook addTextbook(@RequestBody TextBook theTextbook) {
             
             // also just in case the pass an id in JSON ... set id to 0
             // this is force a save of new item ... instead of update
             // sample data (raw-application/json)
//             {
//                     "firstName": "Alekos",
//                     "lastName": "Sakellarios",
//                     "email": "alekos@hua.gr"
//             }
		 
             
		 theTextbook.setId(0);
             theTextbook.setTitle("Informatis");
             textbookService.saveTextBook(theTextbook);
             
             return theTextbook;
     }

	
	 @GetMapping("/courses/{tid}")
     public List<Object> listCourses(@PathVariable("tid") int tid) {
		 
		 System.out.println("ok sto restcontroller");
		 List<Object> newcourses = new ArrayList<Object>();
		 
		 Student student = studentService.getStudent(tid);
		 int semester = student.getSemesterNum();		
		 
		List<Object> courses = courseDAO.getAllCourses(semester);
	
        return courses;
     }
	 
	 @GetMapping("/textbooks/{tid}")
     public List<Object> listTextBooks(@PathVariable("tid") int tid) {
		 //List<TextBook> textbooks = courseService.getCourse(2).getTextBooks();
		 Student student = studentService.getStudent(tid);
		 
		 List<Object> newtextbooks = new ArrayList<Object>();
		 List<Course> courses = courseService.getCourses();
		 for(int i=0; i<courses.size(); i++) {
			 if(courses.get(i).getSemester() == student.getSemesterNum() ) {
				 int cid = courses.get(i).getId();
			 	List<Object> textbooks = courseDAO.getCourse_textbooks_new(cid);
             // get customers from dao
			 	System.out.println(textbooks);
			 	newtextbooks.add(textbooks);
		 	}
		 }
		 
			
             return newtextbooks;
     }
	 
	 @GetMapping("/textbooksProfile/{tid}")
     public List<Object> listTextBooksProfile(@PathVariable("tid") int tid) {
		 //List<TextBook> textbooks = courseService.getCourse(2).getTextBooks();
		 Student student = studentService.getStudent(tid);
		 
		 List<Object> newtextbooks = new ArrayList<Object>();
		 List<TextBookProfile> profiles = textbookService.getTextBookProfiles();
		 for(int i=0; i<profiles.size(); i++) {
			 if(profiles.get(i).getStudent().getId() == tid ) {
				 int profileid = profiles.get(i).getId();
			 	List<Object> bookprofiles = textbookDAO.ProfileTextbooks(profileid);
             // get customers from dao
			 	System.out.println(bookprofiles);
			 	newtextbooks.add(bookprofiles);
		 	}
		 }
		 
			
             return newtextbooks;
     }
}

