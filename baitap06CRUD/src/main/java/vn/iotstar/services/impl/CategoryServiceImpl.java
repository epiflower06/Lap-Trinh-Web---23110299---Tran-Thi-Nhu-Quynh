package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.services.CategoryService;


public class CategoryServiceImpl implements CategoryService {
	private CategoryDao cateDao = new CategoryDaoImpl();

    @Override
    public void create(Category category) {
        cateDao.create(category);
    }

    @Override
    public void update(Category category) {
        cateDao.update(category);
    }

    @Override
    public void delete(int cateid) throws Exception {
        cateDao.delete(cateid);
    }

    @Override
    public Category findById(int cateid) {
        return cateDao.findById(cateid);
    }

    @Override
    public List<Category> findByCategoryname(String catename) {
        return cateDao.findByCategoryname(catename);
    }

    @Override
    public List<Category> findAll() {
        return cateDao.findAll();
    }

    @Override
    public List<Category> findByUser(int userId) {
        return cateDao.findByUser(userId);
    }
}
