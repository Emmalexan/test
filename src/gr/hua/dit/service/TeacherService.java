package gr.hua.dit.service;

import java.util.List;

import gr.hua.dit.entity.Teacher;

public interface TeacherService {
	public List<Teacher> getTeachers();

	public Teacher getTeacher(int id);

	public void saveTeacher(Teacher teacher);

	public void deleteTeacher(int id);

	public void updateTeacher(Teacher teacher);

}
