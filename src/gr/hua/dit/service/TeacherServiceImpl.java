package gr.hua.dit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.TeacherDAO;
import gr.hua.dit.entity.Publisher;
import gr.hua.dit.entity.Teacher;
import gr.hua.dit.entity.TextBook;
@Service
public class TeacherServiceImpl implements TeacherService {

	// inject the TeacherDAO
			@Autowired
			private TeacherDAO teacherDAO;
		
		
		@Override
		@Transactional
		public List<Teacher> getTeachers() {
			// TODO Auto-generated method stub
			return teacherDAO.getTeachers();
		}
		
		@Override
		@Transactional
		public Teacher getTeacher(int id) {
			// TODO Auto-generated method stub
			return teacherDAO.getTeacher(id);
		}
		
		@Override
		@Transactional
		public void saveTeacher(Teacher teacher) {
			
			teacherDAO.saveTeacher(teacher);
		}
		
		@Override
		@Transactional
		public void deleteTeacher(int id) {
			// TODO Auto-generated method stub
			teacherDAO.deleteTeacher(id);
		}
		
		@Override
		@Transactional
		public void updateTeacher(Teacher teacher) {
			teacherDAO.updateTeacher(teacher);
			
		}

}
