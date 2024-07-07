package spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import spring.entity.Category;

@Repository
public class CategoryRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAtest");
	EntityManager em = emf.createEntityManager();

	public int createCategory(Category dto) {
		int result = 0;
		try {
			em.getTransaction().begin();
			em.persist(dto);
			em.getTransaction().commit();
			result = 1;

		} catch (Exception e) {
			System.out.println("create category : " + e.getMessage());
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		return result;
	}

	public List<Category> selectAllCategory() {
		List<Category> categorylst = null;
		try {
			categorylst = em.createQuery("select s from Category s",Category.class).getResultList();
		} catch (Exception e) {
			System.out.println("Select all category : " + e.getMessage());
			em.close();
		}
		return categorylst;
	}
	
	public  List<Category> findCategoriesById(List<Integer> categoryId){
		List <Category> lst = null;
		try {
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.id IN :categoryId", Category.class);
			query.setParameter("categoryId", categoryId);
			lst = query.getResultList();		
			} catch (Exception e) {
			System.out.println("Find category : " + e.getMessage());
		}
		return lst;
	}
	
	public int deleteCategory(int id ) {
		int result = 0;
		try {
			em.getTransaction().begin();
			Category c = em.find(Category.class, id);
			c.setStatus(0);
			em.merge(c);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("Deleting category : " + e.getMessage());
		}
		return result;
	}
	
	public int activeCategory(int id) {
		int result = 0;
		try {
			em.getTransaction().begin();
			Category c = em.find(Category.class, id);
			c.setStatus(1);
			em.merge(c);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("Active category : " + e.getMessage());
		}
		return result;
	}
	public Category selectCategory(int id) {
		Category c = null;
		try {
			 c = em.find(Category.class, id);
		} catch (Exception e) {
			System.out.println("select category : " + e.getMessage());
		}
		return c;
	}
	
	public int updateCategory(Category dto) {
		int result = 0;
		try {
			em.getTransaction().begin();
			Category c = em.find(Category.class, dto.getId());
			c.setStatus(1);
			c.setType(dto.getType());
			em.merge(c);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("updating category : " + e.getMessage());
		}
		return result;
	}
}
