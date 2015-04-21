package classes.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import classes.users.*;

public class commentDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookHub");
	EntityManager em = factory.createEntityManager();
	
	public void createComment(comment c, book b, User r)
	{
		c.setBks(b);
		c.setUsers(r);
		b.getComnt().add(c);
		r.getComnt().add(c);
		em.getTransaction().begin();
		em.merge(b);
		em.merge(r);
		em.getTransaction().commit();	
	}
	
	public comment getCommentByID(int id){
		return em.find(comment.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<comment> getComments(){
		Query query = em.createQuery("select c from comment c");
		return (List<comment>) query.getResultList();
	}
	
	public void updateComment(comment c){
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();	
	}
	
	public void deleteComment(int id){
		comment c = em.find(comment.class, id);
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}

