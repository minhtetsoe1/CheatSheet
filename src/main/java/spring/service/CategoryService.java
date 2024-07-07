package spring.service;

import java.util.List;

import spring.entity.Category;

public interface CategoryService {

	public int createCategory(Category dto);
	public List<Category> selectAllCategory();
	public List<Category> findCategoriesById(List<Integer> category);
	public int activeCategory(int id);
	public int deleteCategory(int id);
	public Category selectCategory(int id);
	public int updateCategory(Category dto);
}
