package gr.hua.dit.service;

import java.util.List;

import gr.hua.dit.entity.Publisher;

public interface PublisherService {
	public List<Publisher> getPublishers();

	public Publisher getPublisher(int id);

	public void savePublisher(Publisher publisher);

	public void deletePublisher(int id);

	public void updatePublisher(Publisher publisher);

}
