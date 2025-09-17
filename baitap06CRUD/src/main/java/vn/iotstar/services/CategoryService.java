package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Category;

public interface CategoryService {
	void create(Category category);
    void update(Category category);
    void delete(int cateid) throws Exception;
    Category findById(int cateid);
    List<Category> findByCategoryname(String catename);
    List<Category> findAll();
    List<Category> findByUser(int userId);
}
