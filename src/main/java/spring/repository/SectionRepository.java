package spring.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import spring.entity.CheatSheet;
import spring.entity.Section;

@Repository
public class SectionRepository {


	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAtest");
	EntityManager em = emf.createEntityManager();
	
	public int addSection(Section dto,int cheatSheetiId) {
		int result  =0;
		try {
			em.getTransaction().begin();
			CheatSheet cheatSheet = em.find(CheatSheet.class, cheatSheetiId);
			Section sec = new Section();
			sec.setTitle(dto.getTitle());
			sec.setContent(dto.getContent());
			sec.setCheatSheet(cheatSheet);
			em.persist(sec);
			em.getTransaction().commit();
			result=1;
		} catch (Exception e) {
			System.out.println("add section : " + e.getMessage());
			if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
		}
		return result;
	}
	
	public int updateSection(Section dto) {
		int result  =0;
		try {
			em.getTransaction().begin();
			Section sec = em.find(Section.class, dto.getId());
			sec.setTitle(dto.getTitle());
			sec.setContent(dto.getContent());
			em.merge(sec);
			em.getTransaction().commit();
			result= 1;
		} catch (Exception e) {
			System.out.println("update section : " + e.getMessage());
			if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
		}
		return result;
	}
	public List<Section> getSectionsByCheatSheetId(int cheatSheetId) {
	    List<Section> sections = new ArrayList<Section>();
	    try {
	        em.getTransaction().begin();
	        TypedQuery<Section> query = em.createQuery("SELECT s FROM Section s WHERE s.status=1 and  s.cheatSheet.id = :cheatSheetId", Section.class);
	        query.setParameter("cheatSheetId", cheatSheetId);
	        sections = query.getResultList();
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        System.out.println("get sections : " + e.getMessage());
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	    }
	    return sections;
	}
	
	public Section getSectionById(int id) {
		Section section = null;
		try {
			section = em.find(Section.class, id);
		} catch (Exception e) {
			System.out.println("get section : " + e.getMessage());		
			}
		return section;
	}
}
