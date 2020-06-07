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

public class UserController {
	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;

	public HstsUser getSubsAndCourses(HstsUser user) {
		HstsUser foundUser = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<HstsUser> criteriaQuery = builder.createQuery(HstsUser.class);
			Root<HstsUser> rootEntry = criteriaQuery.from(HstsUser.class);
			criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("userId"), user.getUserId()));
			TypedQuery<HstsUser> query = session.createQuery(criteriaQuery);
			foundUser = query.getResultList().get(0);

//			Criteria crit = session.createCriteria(HstsUser.class);
//			crit.add(Restrictions.eq("userId", user.getUserId()));
//			foundUser = crit.list();

		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return foundUser;

	}

	public HstsUser identification(HstsUser user) {
		HstsUser foundUser = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<HstsUser> criteriaQuery = builder.createQuery(HstsUser.class);
			Root<HstsUser> rootEntry = criteriaQuery.from(HstsUser.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("userId"), user.getUserId()), 
					builder.equal(rootEntry.get("userPassword"), user.getUserPassword()));
			TypedQuery<HstsUser> query = session.createQuery(criteriaQuery);
			foundUser = query.getResultList().get(0);

			//System.out.println(foundUser.getUserId());

		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
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

}
