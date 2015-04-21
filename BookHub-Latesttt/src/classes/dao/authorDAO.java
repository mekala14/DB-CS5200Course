package classes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import classes.users.*;

public class authorDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookHub");
	EntityManager em = factory.createEntityManager();
	
	public author createAuthor(author a) {
		
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		return a;
	}
	
	public author getauthortByID(int id){
		return em.find(author.class, id);
	}
	
	public List<author> getAuthor() {
		Query query = em.createQuery("select a from author a");
		@SuppressWarnings("unchecked")
		List<author> a = query.getResultList();
		return a;
	}
	
	public author getAuthorByName(String name){
		Query query = em.createQuery("select a from author a where a.name = :id ");
		query.setParameter("id", name);
		try{
		author a = (author) query.getSingleResult();
		return a;
		}
		catch(Exception e){
			return null;
		}
				
	}
	
	@SuppressWarnings("unchecked")
	public author getAuthorByGoodreadsId(int id){
		Query query = em.createQuery("select a from author a where a.goodreads_id = :id ");
		query.setParameter("id", id);
		try{
		List <author> a = query.getResultList();
		return a.get(0);	
		}
		catch(Exception e)
		{
			return null;
		}
	}
		
	public void removeAuthorById(int id){
		author a = em.find(author.class, id);
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
	}
	
	public void updateAuthor(int id, author a){
		em.getTransaction().begin();
		a.setId(id);
		em.merge(a);
		em.getTransaction().commit();
	}
	

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub

		authorDAO ad = new authorDAO();
		//author a = new author(1,"Vishnu","Hyderabad", "Male", 3, 5, 33, "xyz");	
		author a = ad.getAuthorByGoodreadsId(61105);
		System.out.println(a.getName());
	}*/

	}



