package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    public User login(String username, String password) {
        return userDao.login(username, password);
    }

  
    public void create(User user) {
        userDao.create(user);
    }

   
    public void update(User user) {
        userDao.update(user);
    }

   
    public void delete(int id) {
        userDao.delete(id);
    }

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
