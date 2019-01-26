package gr.hua.dit.service;

import java.util.List;

import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.TextBookProfile;

public interface StudentService {
	public List<Student> getStudents();
	
	public Student getStudent(int id);

	void saveStudent(Student student);

	void deleteStudent(int id);

	List<TextBookProfile> viewStudentTextBooks(int sid);

	void delete_textbookprofile(int tid, int sid);

	void addProfileBook(TextBookProfile textbookprofile, int sid);

	void updateStudent(Student student);
}
