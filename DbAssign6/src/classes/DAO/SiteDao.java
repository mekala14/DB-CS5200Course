package classes.DAO;



import java.io.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



import javax.ws.rs.QueryParam;

import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import classes.users.*;


public class SiteDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("assign5");
	
	EntityManager em = factory.createEntityManager();
	
	
	public Site findSite(int siteId){
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


	@SuppressWarnings("unchecked")
	public List<Site> findAllSites(){
		Query query = em.createQuery("select s from Site s");
		return (List<Site>) query.getResultList();
		
	}
	
	
	public void exportSitesToXmlFile(SiteList sitelist, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sitelist, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public void convertXmlFileToOutputFile(
			String sitelistXmlFileName,
			String outputFileName,
			String xsltFileName)
	{
		File inputXmlFile = new File(sitelistXmlFileName);
		File outputXmlFile = new File(outputFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Site> updateSite(int siteId, Site a){
	
	em.getTransaction().begin();
	a.setId(siteId);
	em.merge(a);
	em.getTransaction().commit();
	
	Query query = em.createQuery("select s from Site s");
	return (List<Site>) query.getResultList();
	
	
	}
	
	
	
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

		//SiteDao d = new SiteDao();
		//Site a = d.findSite(1);
	}

}
