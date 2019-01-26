package gr.hua.dit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.StudentDAO;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.Teacher;
import gr.hua.dit.entity.TextBook;
import gr.hua.dit.entity.TextBookProfile;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDAO studentDAO;


@Override
@Transactional
	public List<Student> getStudents() {
	// TODO Auto-generated method stub
	return studentDAO.getStudents();
	}


@Override
@Transactional
public Student getStudent(int id) {
	// TODO Auto-generated method stub
	return studentDAO.getStudent(id);
}

@Override
@Transactional
public void saveStudent(Student student) {
	
	studentDAO.saveStudent(student);
}

@Override
@Transactional
public void deleteStudent(int id) {
	// TODO Auto-generated method stub
	studentDAO.deleteStudent(id);
}

@Override
@Transactional
public void updateStudent(Student student) {
	studentDAO.updateStudent(student);
	
}

@Override
@Transactional
public List<TextBookProfile> viewStudentTextBooks(int sid){
	return studentDAO.viewStudentTextBooks(sid);
}

@Override
@Transactional
public void delete_textbookprofile(int tid, int sid) {
	studentDAO.delete_textbookprofile(tid, sid);				
}

@Override
@Transactional
public void addProfileBook(TextBookProfile textbookprofile, int sid) {
	
		// TODO Auto-generated method stub
		studentDAO.addProfileBook(textbookprofile,sid);
	
}
}
