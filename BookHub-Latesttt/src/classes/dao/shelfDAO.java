package classes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import classes.users.*;

public class shelfDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookHub");
	EntityManager em = factory.createEntityManager();
	
	public void createShelf(shelf s, User r, book b){
		s.setUsrs(r);
		s.setBoks(b);
		r.getShlf().add(s);
		b.getShlf().add(s);
		
		em.getTransaction().begin();
		em.merge(r);
		em.merge(b);
		em.getTransaction().commit();
	}
	
	public shelf getShelfByID(int id){
		return em.find(shelf.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<shelf> getComments(){
		Query query = em.createQuery("select s from shelf s");
		return (List<shelf>) query.getResultList();
	}

	public void updateShelf(shelf s){
		em.getTransaction().begin();
		em.merge(s);
		em.getTransaction().commit();	
	}
	
	public void deleteShelf(int id){
		shelf s = em.find(shelf.class, id);
		em.getTransaction().begin();
		em.remove(s);
		em.getTransaction().commit();		
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/
}



