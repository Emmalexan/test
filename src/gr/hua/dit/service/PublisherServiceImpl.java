package gr.hua.dit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.PublisherDAO;
import gr.hua.dit.entity.Publisher;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.Teacher;
@Service
public class PublisherServiceImpl implements PublisherService {

	// inject the PublisherDAO
				@Autowired
				private PublisherDAO publisherDAO;
			
			
			@Override
			@Transactional
			public List<Publisher> getPublishers() {
				// TODO Auto-generated method stub
				return publisherDAO.getPublishers();
			}
			
			@Override
			@Transactional
			public Publisher getPublisher(int id) {
				// TODO Auto-generated method stub
				return publisherDAO.getPublisher(id);
			}
			
			@Override
			@Transactional
			public void savePublisher(Publisher publisher) {
				
				publisherDAO.savePublisher(publisher);
			}
			
			@Override
			@Transactional
			public void deletePublisher(int id) {
				// TODO Auto-generated method stub
				publisherDAO.deletePublisher(id);
			}
			@Override
			@Transactional
			public void updatePublisher(Publisher publisher) {
				publisherDAO.updatePublisher(publisher);
				
			}

}
