package classes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import classes.users.*;


public class bookDAO {
	//entityManager.getEntityManagerFactory().getCache().evictAll()
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookHub");
	EntityManager em = factory.createEntityManager();
	
	
	public void createBook(author a, book b){		
		b.setAuth(a);
		a.getBooks().add(b);
		
		em.getTransaction().begin();		
		em.merge(a);
		em.getTransaction().commit();
	}
	
	public List<book> getBooks(){
		Query query = em.createQuery("select b from book b");
		@SuppressWarnings("unchecked")
		List<book> b = query.getResultList();
		return b;
	}
	
	public List<book> getSearchBooks(String name){
		Query query = em.createQuery("select b from book b where b.name like :name ");
		query.setParameter("name", "%"+name+"%");
		@SuppressWarnings("unchecked")
		List<book> b = query.getResultList();
		return b;
	}
	
	public book getBookById(int id){
		em.getEntityManagerFactory().getCache().evictAll();
		return em.find(book.class, id);
	}
	
	public book getBookByISBN(int isbn){
		Query query = em.createQuery("select b from book b where b.isbn = :id ");
		query.setParameter("id", isbn);
		List<book> b = query.getResultList();
		
		return b.get(0);		
	}
	
	public book getBookByName(String name){
		Query query = em.createQuery("select b from book b where b.name = :id ");
		query.setParameter("id", name);
		book b = (book) query.getSingleResult();
		return b;		
	}
	
	public void removeBookById(int id){
		book b = em.find(book.class, id);
		em.getTransaction().begin();
		em.remove(b);
		em.getTransaction().commit();
	}
	
	public void removeBookByName(String name){
		Query query = em.createQuery("select b from book b where b.name = :id ");
		query.setParameter("id", name);
		book b = (book) query.getSingleResult();
		em.getTransaction().begin();
		em.remove(b);
		em.getTransaction().commit();		
	}
	
	public void removeBookByISBN(int isbn){
		Query query = em.createQuery("select b from book b where b.isbn = :id ");
		query.setParameter("id", isbn);
		book b = (book) query.getSingleResult();
		em.getTransaction().begin();
		em.remove(b);
		em.getTransaction().commit();	
	}
	
	public void updateBook(int id, book b){
		em.getEntityManagerFactory().getCache().evictAll();
		em.getTransaction().begin();
		b.setId(id);
		em.merge(b);
		em.getTransaction().commit();
		
		//em.close();
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
   
		bookDAO bd = new bookDAO();
		
		book b = new book(0,45,2015,"ccd","It is one of a kind of book", "Penguin", 234,34, 45, 5, "Vishnu the power");
		
		authorDAO a = new authorDAO();
		 bd.createBook(a.getAuthorByName("Vishnu"), b);
		
		
		
	}*/
}

