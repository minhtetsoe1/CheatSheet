package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.entity.Category;
import spring.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	public CategoryServiceImpl(CategoryRepository repo) {
		super();
		this.repo = repo;
	}

	private final CategoryRepository repo;
	@Override
	public int createCategory(Category dto) {
		return repo.createCategory(dto);
	}
	@Override
	public List<Category> selectAllCategory(){
		return repo.selectAllCategory();
	}
	
	@Override
	public List<Category> findCategoriesById(List<Integer> categoryId){
		return repo.findCategoriesById(categoryId);
	}
	
	@Override
	public int activeCategory(int id) {
		return repo.activeCategory(id);
	}
	@Override
	public int deleteCategory(int id) {
		return repo.deleteCategory(id);
	}
	@Override
	public int updateCategory(Category dto) {
		return repo.updateCategory(dto);
	}
	@Override
	public Category selectCategory(int id) {
		return repo.selectCategory(id);
	}
}
