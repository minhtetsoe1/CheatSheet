package spring.repository;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import spring.bean.CheatSheetSection;
import spring.entity.Category;
import spring.entity.CheatSheet;
import spring.entity.Comment;
import spring.entity.User;

@Repository
public class CheatSheetRepositoryImpl {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAtest");
	EntityManager em = emf.createEntityManager();

	public int addCheatSheet(CheatSheet dto, int userId) {
		int result = 0;
		try {
			em.getTransaction().begin();
			User user = new User();
			user.setId(userId);

			List<Category> categories = dto.getCategory();
			if (categories != null) {
				for (int i = 0; i < categories.size(); i++) {
					Category category = categories.get(i);
					if (category != null) {
						category = em.find(Category.class, category.getId());
						if (category == null) {
							em.persist(category);
						}
						categories.set(i, category);
					} else {
						System.out.println("Category at index " + i + " is null");
					}
				}
			}

			CheatSheet cs = new CheatSheet();
			cs.setTitle(dto.getTitle());
			cs.setContent(dto.getContent());
			cs.setCreatedAt(dto.getCreatedAt());
			cs.setImage(dto.getImage());
			cs.setCategory(categories);
			cs.setUser(user);
			em.persist(cs);
			em.getTransaction().commit();
			result = cs.getId();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("adding cheat sheet : " + e.getMessage());
		}
		return result;
	}

	public List<CheatSheet> getAllCheatSheet() {
		List<CheatSheet> cheatSheets = null;
		try {
			cheatSheets = em.createQuery("SELECT c FROM CheatSheet c ", CheatSheet.class).getResultList();
		} catch (Exception e) {
			System.out.println("Getting all cheat sheet : " + e.getMessage());
		}
		return cheatSheets;
	}

	public List<CheatSheet> getCheatSheetUser() {
		List<CheatSheet> cheatSheets = null;
		try {
			cheatSheets = em
					.createQuery("SELECT c FROM CheatSheet c where c.status = 1 order by c.id desc", CheatSheet.class)
					.setMaxResults(6).getResultList();
		} catch (Exception e) {
			System.out.println("Getting all cheat sheet : " + e.getMessage());
		}
		return cheatSheets;
	}

	public int deleteCheetSheet(int id) {
		int result = 0;
		try {
			em.getTransaction().begin();
			CheatSheet c = em.find(CheatSheet.class, id);
			c.setStatus(0);
			em.merge(c);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("Deleting CheatSheet : " + e.getMessage());
		}
		return result;
	}

	public int activeCheetSheet(int id) {
		int result = 0;
		try {
			em.getTransaction().begin();
			CheatSheet c = em.find(CheatSheet.class, id);
			c.setStatus(1);
			em.merge(c);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("Active CheatSheet : " + e.getMessage());
		}
		return result;
	}

	public CheatSheet getCheatSheetById(int id) {
		CheatSheet cheat = null;
		try {
			cheat = em.find(CheatSheet.class, id);
		} catch (Exception e) {
			System.out.println("Getting cheat sheet by id : " + e.getMessage());
		}
		return cheat;
	}

	public List<CheatSheet> getCheatListById(int userId) {
		List<CheatSheet> cheat = null;
		try {
			cheat = em.createQuery("SELECT c FROM CheatSheet c WHERE c.status = 1 AND c.user.id = :userId",
					CheatSheet.class).setParameter("userId", userId).getResultList();
		} catch (Exception e) {
			System.out.println("Getting cheat  sheet list by id : " + e.getMessage());
		}
		return cheat;
	}

	public List<CheatSheet> getCheatByCategory(int categoryId) {
		List<CheatSheet> list = new ArrayList<CheatSheet>();
		try {
			list = em.createQuery("SELECT c FROM CheatSheet c JOIN c.category cat WHERE cat.id = :categoryId",
					CheatSheet.class).setParameter("categoryId", categoryId).getResultList();
		} catch (Exception e) {
			System.out.println("Select all by category : " + e.getMessage());
		}
		return list;
	}

	public int updateCheatSheetById(CheatSheet dto) {
		int result = 0;
		try {
			em.getTransaction().begin();

			List<Category> updatedCategories = new ArrayList<>();
			if (dto.getCategory() != null) {
				for (Category category : dto.getCategory()) {
					Category existingCategory = em.find(Category.class, category.getId());
					if (existingCategory != null) {
						updatedCategories.add(existingCategory);
					} else {
						System.out.println("Category with ID " + category.getId() + " not found in the database.");
					}
				}
			}
			CheatSheet c = em.find(CheatSheet.class, dto.getId());
			c.setTitle(dto.getTitle());
			c.setCategory(updatedCategories);
			c.setContent(dto.getContent());
			em.merge(c);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("Update cheat sheet by id : " + e.getMessage());
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		return result;
	}

	public int addComment(Comment dto) {
		int result = 0;
		try {
			em.getTransaction().begin();
			em.persist(dto);
			em.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			System.out.println("Adding comment : " + e.getMessage());
		}
		return result;
	}
}
