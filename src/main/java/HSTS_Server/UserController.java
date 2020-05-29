package HSTS_Server;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.Question;

public class UserController 
{
	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;
	
	public HstsUser getSubsAndCourses(HstsUser user)
	{
		HstsUser foundUser = new HstsUser();
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			List<HstsUser> users = getAll(HstsUser.class);

			for (HstsUser findUser : users) 
			{ // Change to a better type of search!!!!!!!
				if (findUser.getUserId().equals(user.getUserId()))
				{
					foundUser = findUser;
				}
			}
			
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		}
		finally {
			session.close();
		}
		return foundUser;
		
	}
	
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package
		configuration.addAnnotatedClass(HstsUser.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
		}
	
	public static <T> List<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}
	
}
