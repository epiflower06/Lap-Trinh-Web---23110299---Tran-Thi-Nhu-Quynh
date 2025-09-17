package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDaoImpl implements CategoryDao {
	@Override
	public void create(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(category);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(category);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int cateid) throws Exception {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Category category = em.find(Category.class, cateid);
			if (category != null) {
				em.remove(category);
			} else {
				throw new Exception("Không tìm thấy Category id= "+ cateid);
			}
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public Category findById(int cateid) {
		EntityManager em = JPAConfig.getEntityManager();
		Category category = em.find(Category.class,cateid);
		try {
			return em.find(Category.class, cateid);
		} finally {
			em.close();
		}

	}
	@Override
    public List<Category> findByCategoryname(String catename) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.categoryname LIKE :catename";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("catename", "%" + catename + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createNamedQuery("Category.findAll", Category.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findByUser(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.user.userId = :uid";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("uid", userId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}

