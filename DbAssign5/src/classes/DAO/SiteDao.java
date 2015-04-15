package classes.DAO;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import classes.users.*;

@Path("/api")
public class SiteDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("assign5");
	
	EntityManager em = factory.createEntityManager();
	
	@GET
	@Path("/site/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId){
		Site a = null;
		try{
			Query query = em.createQuery("select s from Site s where s.id = :vid");
			query.setParameter("vid", siteId);
			a = (Site) query.getSingleResult();
			System.out.println(a.getId());
			System.out.println(a.getName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}

	@GET
	@Path("/site")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> findAllSites(){
		Query query = em.createQuery("select s from Site s");
		return (List<Site>) query.getResultList();
		
	}
	
	@PUT
	@Path("/site/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> updateSite(@PathParam("id") int siteId, Site a){
	
	em.getTransaction().begin();
	a.setId(siteId);
	em.merge(a);
	em.getTransaction().commit();
	
	Query query = em.createQuery("select s from Site s");
	return (List<Site>) query.getResultList();
	
	
	}
	
	
	@DELETE
	@Path("/site")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> removeSite(@QueryParam("id") int siteId){
		Query query = em.createQuery("select s from Site s where s.id = :vid");
		query.setParameter("vid",siteId);
		Site a = (Site) query.getSingleResult();
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
		Query queryy = em.createQuery("select s from Site s");
		return (List<Site>) queryy.getResultList();
	}
	
	@POST
	@Path("/site")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Site> createSite(Site a)
	{
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		
		Query q = em.createQuery("select s from Site s");
		return (List<Site>) q.getResultList();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SiteDao d = new SiteDao();
		Site a = d.findSite(1);
	}

}
