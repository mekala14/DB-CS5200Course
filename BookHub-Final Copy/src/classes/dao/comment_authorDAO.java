package classes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import classes.users.*;
public class comment_authorDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookHub");
	EntityManager em = factory.createEntityManager();
	
	public void createComment(comment_author c, author a, User u){
		c.setAuthor(a);
		c.setUsr(u);
		a.getComnt().add(c);
		u.getAuth_comnt().add(c);
		em.getTransaction().begin();
		em.merge(a);
		em.merge(u);
		em.getTransaction().commit();		
	}
	
	public comment_author getCommentByID(int id){
		return em.find(comment_author.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<comment_author> getComments(){
		Query query = em.createQuery("select c from comment_author c");
		return (List<comment_author>) query.getResultList();
	}

	public void updateComment(comment_author c){
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();	
	}
	
	public void deleteComment(int id){
		comment_author c = em.find(comment_author.class, id);
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();		
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		authorDAO ad = new authorDAO();
		author a = new author();
		a = ad.getAuthorByName("Vishnu");
		
		UserDAO ud = new UserDAO();
		User u = new User();
		u = ud.readUserByUname("vishnu");
		
		comment_authorDAO cad = new comment_authorDAO();
		
		comment_author ca = new comment_author(1, "9:29 PM", "I like this book a lot!");
		
		cad.createComment(ca, a, u);
		
		
	}*/
}

