package classes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import classes.users.User;


public class UserDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookHub");
	EntityManager em = factory.createEntityManager();
	

	public User createUser(User u) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		return u;
	}

	
	public User readUserByUname(String Uname){
		return em.find(User.class, Uname);
	}
	
	public List<User> readAllUsers(){
		Query query = em.createQuery("select r from user r");
		return (List<User>) query.getResultList();
	}
	
	public User updateRegUsers(User r){
		em.getTransaction().begin();
		em.merge(r);
		em.getTransaction().commit();
		
		return r;
	}
	
	public void deleteUser(String uname){
		User r = em.find(User.class, uname);
		em.getTransaction().begin();
		em.remove(r);
		em.getTransaction().commit();
		
	}
	
	public boolean isValidUser(String uname, String pwd){
		Query query = em.createQuery("select r from regusers r where r.username = :uname and r.password = :pwd");
		query.setParameter("uname", uname);
		query.setParameter("pwd", pwd);
		User r = new User();
		try{
			r = (User) query.getSingleResult();
		}
		catch (NoResultException nre){
			return false;
		}
		
		return true;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*UserDAO ud = new UserDAO();
		User u = new User("jestt");
		u = ud.createUser(u);
		System.out.println(u.getUsername());*/

	}


}
