package spring.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import spring.entity.User;
import spring.entity.UserCheatDTO;


@Repository
public class UserRepositoryImpl  {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAtest");
	EntityManager em = emf.createEntityManager();


	public int insertUser(User dto) {
		int result = 0;
		User user = null;
		try {
			try {
				user = em.createQuery("SELECT s FROM User s WHERE s.email = :email", User.class)
						.setParameter("email", dto.getEmail()).getSingleResult();
			} catch (NoResultException e) {
				System.out.println("search with email : " + e.getMessage());
			}
			if (user == null) {
				em.getTransaction().begin();
				em.persist(dto);
				em.getTransaction().commit();
				result = 1;
				
			}

		} catch (Exception e) {
			System.out.println("insert user : " + e.getMessage());
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		return result;
	}

	public User selectUser(User dto) {
		User user = null;
		try {
			TypedQuery<User> query = em.createQuery(
					"SELECT s FROM User s WHERE s.email = :email AND s.password = :password and s.status = 1", User.class);
			query.setParameter("email", dto.getEmail());
			query.setParameter("password", dto.getPassword());
			user = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("select user : " + e.getMessage());
		} 
		return user;
	}

	public List<UserCheatDTO> getAllUser(){
		List<UserCheatDTO> list = null;
	    try {
	    	list = em.createQuery("SELECT u, COUNT(c) FROM User u LEFT JOIN u.cheatSheets c GROUP BY u").getResultList();
	    } catch (Exception e) {
	        System.out.println("get all users with cheatsheet count: " + e.getMessage());
	    }
	    return list;
	}
	
	public List<User> getAllUserList(){
		List<User> list = null;
		try {
			list =em.createQuery("select u from User u",User.class).getResultList();
		} catch (Exception e) {
			System.out.println("Get all user : " + e.getMessage());
		}
		return list;
	}
	
	public User selectUser(int id) {
		User user = null;
		try {
			
			user = em.find(User.class, id);
			
		} catch (Exception e) {
			System.out.println("select user : " + e.getMessage());
		} 
		return user;
	}
	
	public int deleteUser(int id ) {
		int result = 0;
		try {
			em.getTransaction().begin();
			User user = em.find(User.class, id);
			user.setStatus(0);
			em.merge(user);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("delete user :  "+ e.getMessage());
		}
		return result;
	}
	public int activeUser(int id ) {
		int result = 0;
		try {
			em.getTransaction().begin();
			User user = em.find(User.class, id);
			user.setStatus(1);
			em.merge(user);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("Active user :  "+ e.getMessage());
		}
		return result;
	}
}
